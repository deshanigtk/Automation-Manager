package classes;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MainController {

    private static String staticScannerStatus = "not started";
    private static String dynamicScannerStatus = "not started";
    private static String staticScannerContainerId;
    private static boolean isProductCloned = false;

    public static void setStaticScannerStatus(String status) {
        staticScannerStatus = status;
    }

    public static void setDynamicScannerStatus(String status) {
        dynamicScannerStatus = status;
    }

    public static void setStaticScannerContainerId(String containerId) {
        staticScannerContainerId = containerId;
    }

    public static void setIsProductCloned(boolean status) {
        isProductCloned = status;
    }

    public static String cloneProduct(String gitUrl, String branch) throws IOException {


        String[] urlArray = new String[]{Constant.CLONE_URL, "?", "gitUrl=", gitUrl, "&", "branch=", branch};
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

    public static String getStaticScannerStatus() {
        return staticScannerStatus;
    }

    public static String getDynamicScannerStatus() {
        return dynamicScannerStatus;
    }

    public static String getStaticScannerContainerId() {
        return staticScannerContainerId;
    }

    public static boolean getIsProductCloned() {
        return isProductCloned;
    }
}
