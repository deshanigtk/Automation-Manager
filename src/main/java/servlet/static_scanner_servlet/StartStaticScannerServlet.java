package servlet.static_scanner_servlet;

import classes.Constant;
import classes.MainController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "StartStaticScannerServlet",
        urlPatterns = {"/startStaticScanner"}
)

public class StartStaticScannerServlet extends HttpServlet {
    private boolean isContainerStarted;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            isContainerStarted = MainController.dockerRun(
                    Constant.STATIC_SCANNER,
                    Constant.STATIC_SCANNER_IMAGE_NAME,
                    Constant.STATIC_SCANNER_IP_ADDRESS,
                    Constant.STATIC_SCANNER_CONTAINER_PORT,
                    Constant.STATIC_SCANNER_HOST_PORT,
                    new String[]{});

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isContainerStarted) {

            req.getRequestDispatcher("staticScannerProductUpload.jsp").forward(req, resp);
        } else {
            if (!MainController.getStaticScannerPullStatus()) {
                req.setAttribute("message", "Can't pull Docker image");
            } else if (MainController.getStaticScannerContainerId() == null) {
                req.setAttribute("message", "Can't create container");
            } else if (!Constant.RUNNING_STATE.equals(MainController.getStaticScannerStatus())) {
                req.setAttribute("message", "Can't start container");
            }
            req.getRequestDispatcher("/startStaticScanner").forward(req, resp);
        }
    }
}