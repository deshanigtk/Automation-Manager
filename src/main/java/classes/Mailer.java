package classes;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

public class Mailer {
    public static void send(String to, String sub, String fileName,
                            String msg, final String user, final String pass) {
        //create an instance of Properties Class
        Properties props = new Properties();

     /* Specifies the IP address of your default mail server
            for e.g if you are using gmail server as an email sever
           you will pass smtp.gmail.com as value of mail.smtp host.
           As shown here in the code.
           Change accordingly, if your email id is not a gmail id
        */
        props.put("mail.smtp.host", "smtp.gmail.com");//smtp.gmail.com
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");//587
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

     /* Pass Properties object(props) and Authenticator object
           for authentication to Session instance
        */

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

 	/* Create an instance of MimeMessage,
           it accept MIME types and headers
 	   */

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);

            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = fileName;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);


       /* Transport class is used to deliver the message to the recipients */

            Transport.send(message);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
