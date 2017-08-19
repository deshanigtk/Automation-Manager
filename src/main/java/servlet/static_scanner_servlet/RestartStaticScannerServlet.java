package servlet.static_scanner_servlet;

import classes.DockerHandler;
import classes.MainController;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "RestartStaticScannerServlet",
        urlPatterns = {"/restartStaticScanner"}
)

public class RestartStaticScannerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if("stopped".equals(MainController.getStaticScannerStatus())){
            try {
                DockerHandler.restartContainer(MainController.getStaticScannerContainerId());
            } catch (DockerCertificateException | InterruptedException | DockerException e) {
                e.printStackTrace();
            }
        }

    }
}
