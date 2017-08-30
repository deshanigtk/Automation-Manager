package classes;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MainController {

    private static String staticScannerStatus = Constants.NOT_STARTED_STATE;
    private static String staticScannerContainerId;

    private static boolean staticScannerPullState = false;

    private static boolean isProductCloned = false;


    private static String dynamicScannerStatus = Constants.NOT_STARTED_STATE;
    private static String dynamicScannerContainerId;

    private static boolean dynamicScannerPullState = false;

    private static String productPath;

    public static void setStaticScannerStatus(String status) {
        staticScannerStatus = status;
    }

    public static void setDynamicScannerStatus(String status) {
        dynamicScannerStatus = status;
    }

    public static void setIsProductCloned(boolean status) {
        isProductCloned = status;
    }

    public static String cloneProduct(String gitUrl, String branch) throws IOException {

        String[] urlArray = new String[]{Constants.CLONE_URL, "?", "gitUrl", "=", gitUrl, "&", "branch", "=", branch, "&", "replaceExisting=false"};
        StringBuilder sb = new StringBuilder();
        for (String s : urlArray) {
            sb.append(s);
        }

        String url = sb.toString();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);

        HttpEntity entity = httpResponse.getEntity();
        return EntityUtils.toString(entity, "UTF-8");

    }

    public static boolean dockerRun(String scannerType, String imageName, String ipAddress, String containerPort, String hostPort, String[] cmd) throws Exception {

        if (Constants.STATIC_SCANNER.equals(scannerType)) {
            staticScannerPullState = DockerHandler.pullImage(imageName);

            if (staticScannerPullState) {
                staticScannerContainerId = DockerHandler.createContainer(imageName, ipAddress, containerPort, hostPort, cmd);

                if (staticScannerContainerId != null) {

                    if (DockerHandler.startContainer(staticScannerContainerId)) {
                        staticScannerStatus = Constants.RUNNING_STATE;
                        return true;
                    }

                }
            }
        }
        if (Constants.DYNAMIC_SCANNER.equals(scannerType)) {
            dynamicScannerPullState = DockerHandler.pullImage(imageName);

            if (dynamicScannerPullState) {
                dynamicScannerContainerId = DockerHandler.createContainer(imageName, ipAddress, containerPort, hostPort, cmd);

                if (dynamicScannerContainerId != null) {

                    if (DockerHandler.startContainer(dynamicScannerContainerId)) {
                        dynamicScannerStatus = Constants.RUNNING_STATE;
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public static void setProductPath(String path) {
        productPath = path;
    }

    public static String getProductPath(){
        return productPath;
    }

    public static boolean getStaticScannerPullStatus() {
        return staticScannerPullState;
    }

    public static String getStaticScannerStatus() {
        return staticScannerStatus;
    }

    public static boolean getDynamicScannerPullStatus() {
        return staticScannerPullState;
    }

    public static String getDynamicScannerStatus() {
        return dynamicScannerStatus;
    }

    public static String getStaticScannerContainerId() {
        return staticScannerContainerId;
    }

    public static String getDynamicScannerContainerId() {
        return staticScannerContainerId;
    }

    public static boolean getIsProductCloned() {
        return isProductCloned;
    }
}
