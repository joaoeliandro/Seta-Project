package Controller;

import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Eliandro
 */
public class EsqueceuSenhaController implements Initializable {

    private static String host = "smtp.gmail.com";
    private static String user = "javamaileliandro@gmail.com";
    private static String password = "javamail98";

    Alert alert;

    @FXML
    private TextField txtUsuario,
            txtPalavraSegur, txtEmailRecupe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void btn_Enviar(ActionEvent enviar) {
        new Thread() {
            @Override
            public void run() {
                if (!txtUsuario.getText().isEmpty() && !txtPalavraSegur.getText().isEmpty() && !txtEmailRecupe.getText().isEmpty()) {
                    Usuario usuario = Usuario.findUsuario(txtUsuario.getText());
                    if (usuario.getUsuario().equals(txtUsuario.getText())) {
                        if (usuario.getRespostaSegur().equals(txtPalavraSegur.getText())) {
                            System.out.println("\n\n\nUsu: " + usuario.getUsuario());
                            enviarEmail(usuario.getUsuario(), usuario.getEmail(),
                                    usuario.getRespostaSegur(), usuario.getSenha());

                            clearAll();

                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Recuperação");
                            alert.setHeaderText("Recuperação de senha");
                            alert.setContentText("Senha enviada para seu email de recuperação,"
                                    + " verfique sua caixa de entrada");
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Erro na Resposta de segurança");
                            alert.setContentText("Resposta não correspondente para a pergunta: "
                                    + usuario.getPerguntaSegur());
                        }
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Erro no Usuário");
                        alert.setContentText("Usuário não encontrado");
                    }
                } else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Campos vazios");
                    alert.setContentText("Preencha todos os campos");
                }
            }
        }.start();
    }

    @FXML
    protected void btn_Voltar(ActionEvent voltar) {
        clearAll();
        Main.changeScreen("login");
    }

    private void clearAll() {
        txtUsuario.clear();
        txtPalavraSegur.clear();
        txtEmailRecupe.clear();
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

    private void enviarEmail(String usuario, String email, String resp, String senha) {
        try {
            Message message = new MimeMessage(sessionMail());

            message.setFrom(new InternetAddress(user)); // Remetente
            Address[] address = InternetAddress.parse(email); // Destinatário oficial
            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject(usuario);
            message.setSentDate(new Date());
            message.setText("Recuperação de senha do usuário: " + usuario
                    + "\n\n" + "Email de recuperação: " + email
                    + "\n\n" + "Resposta de Segurança: " + resp
                    + "\n\n" + "Senha de atual: " + senha);
            message.saveChanges();

            Transport.send(message);

        } catch (AddressException e) {
            System.out.println(e);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
