package servlet.static_scanner_servlet;

import classes.DockerHandler;
import classes.MainController;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet(
        name = "FindSecBugsServlet",
        urlPatterns = {"/findSecBugs"}
)

public class FindSecBugsServlet extends HttpServlet {

    private String url = "http://localhost:8081/staticScanner/runScan/findSecBugs";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        httpClient.execute(httpGet);

        String filePathToCopy="/opt/Product/FindSec.zip";
        String destinationFile="Dependency-Check-Reports.zip";
        File destinationFolder=(File) getServletContext().getAttribute(ServletContext.TEMPDIR);

        try {
            DockerHandler.copyFilesFromContainer(MainController.getStaticScannerContainerId(),filePathToCopy,destinationFile,destinationFolder);
        } catch (DockerCertificateException | DockerException | InterruptedException e) {
            e.printStackTrace();
        }

        req.setAttribute("scanType","Dependency Check");
        req.getRequestDispatcher("/sendEmail.jsp").forward(req, resp);
    }
}