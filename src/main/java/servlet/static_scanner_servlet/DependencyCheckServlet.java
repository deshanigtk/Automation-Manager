package servlet.static_scanner_servlet;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "DependencyCheckServlet",
        urlPatterns = {"/dependencyCheck"}
)

public class DependencyCheckServlet extends HttpServlet {

    private String url="http://localhost:8081/staticScanner/runScan/dependencyCheck";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpClient httpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet(url);

        httpClient.execute(httpGet);
    }
}
