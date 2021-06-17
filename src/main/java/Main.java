import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {
    public static void main(String[] varargs) {

    }

    public static String sendMail(String to) {
        String from = "gera.zacha@gmail.com";
        final String username = "gera.zacha@gmail.com";
        final String password = "Ma1l$2021";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Reporting");

            message.setContent(
                    "<h1>Welcome user!</h1>",
                    "text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return "";
    }
}
