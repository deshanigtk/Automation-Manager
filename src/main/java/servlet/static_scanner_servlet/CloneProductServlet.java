package servlet.static_scanner_servlet;

import classes.DockerHandler;
import classes.MainController;
import com.google.common.collect.ImmutableSet;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "CloneProductServlet",
        urlPatterns = {"/cloneProduct"}
)
public class CloneProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (MainController.getIsProductCloned()) {
            req.setAttribute("message", "Product is already cloned");
        } else {

            String responseString = MainController.cloneProduct(req.getParameter("gitUrl"), req.getParameter("branch"));

            //REMOVE THIS

            MainController.setIsProductCloned(true);
            req.getRequestDispatcher("/staticScanners.jsp").forward(req, resp);

            if ("true".equals(responseString)) {
                MainController.setIsProductCloned(true);
                req.getRequestDispatcher("/staticScanners.jsp").forward(req, resp);

            }
        }
    }
}