package servlet.static_scanner_servlet;

import classes.DockerHandler;
import classes.MainController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "StopStaticScannerServlet",
        urlPatterns = {"/stopStaticScanner"}
)

public class StopStaticScannerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            if ("running".equals(MainController.getStaticScannerStatus())) {
                DockerHandler.killContainer(MainController.getStaticScannerContainerId());
                MainController.setStaticScannerStatus("stopped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
