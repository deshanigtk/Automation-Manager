package servlet;

// File Name SendEmail.java

import classes.DockerHandler;
import classes.Mailer;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;

import java.io.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(
        name = "EmailServlet",
        urlPatterns = {"/sendEmail"}
)

public class EmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String scanType = request.getParameter("scanType");

        System.out.println(scanType);
        File file = (File) getServletContext().getAttribute(ServletContext.TEMPDIR);
        String fileName = null;

        File[] files = file.listFiles();

        HashMap<String, String> scanReportMap = new HashMap<>();
        scanReportMap.put("Dependency Check", "Dependency-Check-Reports.zip");
        scanReportMap.put("FindSecBugs", "FindSecBugs-Reports.zip");
        scanReportMap.put("Zap", "Zap-report");

        if (scanReportMap.containsKey(scanType)) {
            for (File x : files) {
                if (x.getName().equals(scanReportMap.get(scanType))) {
                    fileName = x.getAbsolutePath();
                }
            }
        }
        if (fileName != null) {
            Mailer.send(to, subject, fileName, message, user, pass);
            out.println("Mail send successfully");

        }
    }
}