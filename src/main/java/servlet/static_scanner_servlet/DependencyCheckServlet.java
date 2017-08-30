package servlet.static_scanner_servlet;

import classes.Constants;
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
        name = "DependencyCheckServlet",
        urlPatterns = {"/dependencyCheck"}
)

public class DependencyCheckServlet extends HttpServlet {

    private String url = "http://localhost:8081/staticScanner/dependencyCheck";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        httpClient.execute(httpGet);

        String filePathToCopy = MainController.getProductPath() + Constants.DEPENDENCY_CHECK_REPORTS + Constants.ZIP_FILE_EXTENSION;
        String destinationFile = Constants.DEPENDENCY_CHECK_REPORTS + Constants.ZIP_FILE_EXTENSION;
        File destinationFolder = (File) getServletContext().getAttribute(ServletContext.TEMPDIR);

        try {
            DockerHandler.copyFilesFromContainer(MainController.getStaticScannerContainerId(), filePathToCopy, destinationFile, destinationFolder);
        } catch (DockerCertificateException | DockerException | InterruptedException e) {
            e.printStackTrace();
        }

        req.setAttribute(Constants.SCAN_TYPE, Constants.DEPENDENCY_CHECK);
        req.getRequestDispatcher("/sendEmail.jsp").forward(req, resp);
    }
}