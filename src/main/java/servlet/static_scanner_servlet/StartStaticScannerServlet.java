package servlet.static_scanner_servlet;

import classes.Constant;
import classes.DockerHandler;
import classes.MainController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "StartStaticScannerServlet",
        urlPatterns = {"/staticScanner"}
)

public class StartStaticScannerServlet extends HttpServlet {
    private String containerId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            containerId = DockerHandler.dockerRun(
                    Constant.STATIC_SCANNER_IMAGE_NAME,
                    Constant.STATIC_SCANNER_IP_ADDRESS,
                    Constant.STATIC_SCANNER_CONTAINER_PORT,
                    Constant.STATIC_SCANNER_HOST_PORT,
                    new String[]{});



        } catch (Exception e) {
            e.printStackTrace();
        }

        if (containerId != null) {
            MainController.setStaticScannerContainerId(containerId);
            MainController.setStaticScannerStatus("running");

//            message="Static Scanner is Started";
//            req.setAttribute("message", message);

            req.getRequestDispatcher("/staticScannerProductUpload.jsp").forward(req, resp);
        }
    }
}