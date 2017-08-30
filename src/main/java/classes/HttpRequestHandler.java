package classes;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestHandler {

    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

    public static void sendGetRequest(String request) throws IOException {
        HttpGet httpGetRequest = new HttpGet(request);
        httpClient.execute(httpGetRequest);
    }

    public static HttpResponse sendPostrequest(String request, ArrayList<NameValuePair> parameters) throws IOException {
        HttpPost httpPostRequest = new HttpPost(request);

        for (NameValuePair parameter : parameters) {
            urlParameters.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
        }

        httpPostRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
        return httpClient.execute(httpPostRequest);
    }
}
