package Classes;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Eliandro
 */
public class EnviarEmail implements Runnable {

    private static String host = "smtp.gmail.com";
    private static String user = "javamaileliandro@gmail.com";
    private static String dev = "eliandrogermano@gmail.com";
    private static String parce = "romarioaraujodm@outlook.com.br";
    private static String password = "javamail98";
    private String assunto, mensagem, pessoaEnviando;

    public EnviarEmail() {
    }

    public EnviarEmail(String assunto, String mensagem, String pessoaEnviando) {
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.pessoaEnviando = pessoaEnviando;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        enviarEmail();
    }

    private Session sessionMail() {
        Properties prop = new Properties();

        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        return session;
    }

    public void enviarEmail() {
        try {
            Message message = new MimeMessage(sessionMail());

            message.setFrom(new InternetAddress(user)); // Remetente
            Address[] address = InternetAddress.parse(user); // Destinatário oficial
            Address[] address2 = InternetAddress.parse(dev); // Destinatário desenvolvedor
            Address[] address3 = InternetAddress.parse(parce); // Destinatário parceria
            message.setRecipients(Message.RecipientType.TO, address);
            message.setRecipients(Message.RecipientType.CC, address2);
            message.setRecipients(Message.RecipientType.BCC, address3);

            message.setSubject(assunto);
            message.setSentDate(new Date());
            message.setText(pessoaEnviando + "\n\n"
                    + "----------------------------------------\n"
                    + mensagem
                    + "\n----------------------------------------"); // Mensagem
            message.saveChanges();

            Transport.send(message, message.getAllRecipients());
        } catch (AddressException e) {
            System.out.println(e);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

    public void enviarEmail(String usuario, String mensagem) {
        try {
            Message message = new MimeMessage(sessionMail());

            message.setFrom(new InternetAddress(user)); // Remetente
            Address[] address = InternetAddress.parse(user); // Destinatário oficial
            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject(usuario);
            message.setSentDate(new Date());
            message.setText("Mensagem vinda do usuário: " + mensagem);
            message.saveChanges();

            Transport.send(message);

        } catch (AddressException e) {
            System.out.println(e);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

}
