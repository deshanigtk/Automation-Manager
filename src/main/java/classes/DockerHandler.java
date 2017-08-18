package classes;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;

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

    public static String dockerRun(String imageName, String ipAddress, String containerPort, String hostPort, String[] cmd) throws Exception {
        Boolean isImageAvailable = pullImage(imageName);
        String containerId = null;

        if (isImageAvailable) {
            containerId = createContainer(imageName, ipAddress, containerPort, hostPort, cmd);
            if (containerId != null) {
                startContainer(containerId);
            }

        }
        return containerId;
    }

    private static boolean pullImage(String imageName) throws Exception {
        getDockerClient().pull(imageName);
        return getDockerClient().searchImages(imageName) != null;
    }

    private static String createContainer(String imageName, String ipAddress, String containerPort, String hostPort, String[] cmd) throws Exception {
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

    private static boolean startContainer(String containerId) throws Exception {
        getDockerClient().startContainer(containerId);
        return "running".equals(inspectContainer(containerId).state().status());
    }

    private static ContainerInfo inspectContainer(String containerId) throws Exception {
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

}

