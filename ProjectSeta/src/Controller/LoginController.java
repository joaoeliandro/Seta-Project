package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Eliandro
 */
public class LoginController {

    private String usuSenha = "admin";
    private Paint colorBlack = Paint.valueOf("black"), colorRed = Paint.valueOf("red");

    @FXML
    private Label labelErroLogin;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreen(new Main.OnChangeScreen() {
            @Override
            public void onScreenChange(String newScreen, Object userData) {

            }
        });
    }

    @FXML
    protected void btn_Entrar(ActionEvent entrar) {
        entrar();
    }

    @FXML
    protected void key_Entrar(KeyEvent entrar) {
        if (entrar.getCode() == KeyCode.ENTER) {
            entrar();
        }
    }

    @FXML
    protected void lbl_Cadastrar(MouseEvent cadastrar) {
        txtUsuario.clear();
        txtSenha.clear();
        Main.changeScreen("cadastroUsuario");
    }
    @FXML
    protected void lbl_Recuperacao(MouseEvent cadastrar) {
        txtUsuario.clear();
        txtSenha.clear();
        Main.changeScreen("recuperacao");
    }

    public int testeLogin() {
        
        Usuario usuario = new Usuario();
        
        if (txtUsuario.getText().equals(usuSenha) && txtSenha.getText().equals(usuSenha)) {
            txtUsuario.unFocusColorProperty().set(colorBlack);
            txtSenha.unFocusColorProperty().set(colorBlack);
            return 0;
        }
        if (txtUsuario.getText().equals("")) {
            txtUsuario.unFocusColorProperty().set(colorRed);
            return 1;
        }
        if (txtSenha.getText().equals("")) {
            txtSenha.unFocusColorProperty().set(colorRed);
            return 2;
        }

        txtUsuario.unFocusColorProperty().set(colorBlack);
        txtSenha.unFocusColorProperty().set(colorBlack);
        return 3;
    }

    private void entrar() {
        switch (testeLogin()) {
            case 0:
                Main.changeScreen("main");
                labelErroLogin.setText("");
                txtUsuario.clear();
                txtSenha.clear();
                break;
            case 1:
                labelErroLogin.setText("*Campo de usuário vazio");
                txtSenha.unFocusColorProperty().set(colorBlack);
                txtUsuario.clear();
                break;
            case 2:
                labelErroLogin.setText("*Campo de senha vazio");
                txtUsuario.unFocusColorProperty().set(colorBlack);
                txtSenha.clear();
                break;
            default:
                labelErroLogin.setText("*Erro ao fazer o login");
                txtUsuario.clear();
                txtSenha.clear();
                break;
        }
    }
}
