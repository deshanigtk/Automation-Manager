package servlet.static_scanner_servlet;

import classes.MainController;
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
        name = "ConfigureServlet",
        urlPatterns = {"/configure"}
)
public class ConfigureServlet extends HttpServlet {

    private String url = "http://localhost:8081/staticScanner/productPath?productPath=";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        String productPath = req.getParameter("productPath");
        System.out.println(productPath);

        MainController.setProductPath(productPath);
        HttpGet httpGet = new HttpGet(url + productPath);

        System.out.println(httpClient.execute(httpGet));
        req.getRequestDispatcher("/staticScannerProductUpload.jsp").forward(req, resp);

    }
}
