package servlet;

import classes.Constants;
import classes.Mailer;

import java.io.*;
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

        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String scanType = request.getParameter("scanType");

        String fileName = null;

        switch (scanType) {
            case Constants.FIND_SECURITY_BUGS:
                fileName = Constants.FIND_SEC_BUGS_REPORTS + Constants.ZIP_FILE_EXTENSION;
                break;
            case Constants.DEPENDENCY_CHECK:
                fileName = Constants.DEPENDENCY_CHECK_REPORTS + Constants.ZIP_FILE_EXTENSION;
                break;
            case Constants.ZAP:
                fileName = Constants.ZAP_REPORT;
                break;
        }


        if (fileName != null) {
            File file = (File) getServletContext().getAttribute(ServletContext.TEMPDIR);

            File[] files = file.listFiles();
            for (File f : files) {
                if (f.getName().equals(fileName)) {
                    fileName = f.getAbsolutePath();
                    Mailer.send(to, subject, fileName, message, user, pass);
                    break;
                }
            }

        }


    }
}