package Controller;

import Classes.EnviarEmail;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Eliandro
 */
public class ConfiguracoesController implements Initializable {

    @FXML
    private TextField txtAssunto, txtPessoaEnviando;

    @FXML
    private TextArea txtMensagem;

    @FXML
    private ToggleGroup tipoRelatorio;

    @FXML
    private JFXPasswordField txtNovaSenha;

    @FXML
    private JFXTextField txtSenhaAtual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void btn_Voltar(ActionEvent voltar) {
        clearAll();
        Main.changeScreen("main");
    }

    @FXML
    protected void btn_AlterarSenha(ActionEvent voltar) {
        txtNovaSenha.clear();
        txtSenhaAtual.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Troca de senha");
        alert.setHeaderText("Senha alterada");
        alert.setContentText("Sua senha foi alterada com sucesso");
        alert.show();
    }

    @FXML
    protected void btn_Enviar(ActionEvent enviar){
        EnviarEmail enviarMail = new EnviarEmail(txtAssunto.getText(), txtMensagem.getText(),
                txtPessoaEnviando.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FeedBack");
        alert.setHeaderText("Seu feedback foi enviado");
        alert.setContentText("Seu email será respondido até 48h após o envio");
        alert.show();
        clearAll();
    }

    private void clearAll() {
        txtAssunto.clear();
        txtMensagem.clear();
        txtPessoaEnviando.clear();
    }

}
