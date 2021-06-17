import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class Main {
    public static void main(String[] varargs) {

        Random random = new Random();

        Observable<Integer> source = Observable.range(1, 100);


        Observer<Boolean> observer = new Observer<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                System.out.println(aBoolean ? "Device working properly." : "Detecting system failure, taking measures.");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("success");
            }
        };



        source.map(number -> random.nextBoolean()).subscribe(observer);

        /*
        Otra forma de establecer el subscribe
        
        source.map(number -> random.nextBoolean()).subscribe(status -> {
            System.out.println(status ? "Device working properly." : "Detecting system failure, taking measures.");
        });
        
         */









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
