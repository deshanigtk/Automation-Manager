package servlet;

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
//                    Constants.ZAP_DOCKER_IMAGE_NAME,
//                    Constants.ZAP_IP_ADDRESS,
//                    Constants.ZAP_CONTAINER_PORT,
//                    Constants.ZAP_HOST_PORT,
//                    Constants.ZAP_CONTAINER_STARTUP_COMMAND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}