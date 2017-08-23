package servlet;

import classes.Constant;
import classes.DockerHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "DynamicScannerServlet",
        urlPatterns = {"/dynamicScanner"}
)

public class DynamicScannerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
//            DockerHandler.dockerRun(
//                    Constant.ZAP_DOCKER_IMAGE_NAME,
//                    Constant.ZAP_IP_ADDRESS,
//                    Constant.ZAP_CONTAINER_PORT,
//                    Constant.ZAP_HOST_PORT,
//                    Constant.ZAP_CONTAINER_STARTUP_COMMAND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}