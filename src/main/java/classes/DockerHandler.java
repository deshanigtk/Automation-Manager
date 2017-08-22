package classes;

import com.google.common.collect.ImmutableSet;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DockerHandler {

    private static DockerClient dockerClient = null;

    private static DockerClient getDockerClient() throws DockerCertificateException {
        if (dockerClient == null) {
            dockerClient = DefaultDockerClient.fromEnv().build();
        }
        return dockerClient;

    }

    public static boolean pullImage(String imageName) throws Exception {
        getDockerClient().pull(imageName);
        return getDockerClient().searchImages(imageName) != null;
    }

    public static String createContainer(String imageName, String ipAddress, String containerPort, String hostPort, String[] cmd) throws Exception {
        String[] ports = {containerPort, hostPort};
        HashMap<String, List<PortBinding>> portBindings = new HashMap<>();

        for (String port : ports) {
            List<PortBinding> hostPorts = new ArrayList<>();
            hostPorts.add(PortBinding.of(ipAddress, port));
            portBindings.put(port, hostPorts);
        }

        HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();
        ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(hostConfig)
                .image(imageName).exposedPorts(ports)
                .cmd(cmd)
                .build();

        ContainerCreation creation = getDockerClient().createContainer(containerConfig);
        return creation.id();

    }

    public static boolean startContainer(String containerId) throws Exception {
        getDockerClient().startContainer(containerId);
        return "running".equals(inspectContainer(containerId).state().status());
    }

    public static ContainerInfo inspectContainer(String containerId) throws Exception {
        return getDockerClient().inspectContainer(containerId);
    }

    public static void killContainer(String containerId) throws Exception {
        getDockerClient().killContainer(containerId);
    }

    public static void removeContainer(String containerId) throws Exception {
        getDockerClient().removeContainer(containerId);
    }

    public static void restartContainer(String containerId) throws DockerCertificateException, DockerException, InterruptedException {
        getDockerClient().restartContainer(containerId);
    }

    public static String getContainerLogs(String container_id) throws Exception {
        final String logs;
        try (LogStream stream = getDockerClient().logs(container_id, DockerClient.LogsParam.stdout(), DockerClient.LogsParam.stderr())) {
            logs = stream.readFully();
            return logs;
        }
    }

    public static List<Container> getRunningContainersList() throws Exception {
        return getDockerClient().listContainers();
    }

    public static List<Container> getAllContainerslist() throws Exception {
        return getDockerClient().listContainers(DockerClient.ListContainersParam.allContainers());
    }

    public static void closeDockerClient() throws Exception {
        getDockerClient().close();
    }

    public static void copyFilesFromContainer(String containerId,String filePathToCopy, String destinationFile, File destinationFolder) throws IOException, DockerCertificateException, DockerException, InterruptedException {

        InputStream inputStream = getDockerClient().archiveContainer(containerId, filePathToCopy);

        FileOutputStream outputStream =
                new FileOutputStream(new File(destinationFolder, destinationFile));

        int read;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }

    }

    public static void copyFilesToContainer(InputStream inputStream, String containerId, String path) throws DockerCertificateException, InterruptedException, DockerException, IOException {
        getDockerClient().copyToContainer(inputStream,containerId,path);
    }

}

