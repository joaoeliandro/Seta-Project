package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Eliandro
 */
public class MainContabil {

    @FXML
    private ImageView imgUsuario;

    public void initialize(URL url, ResourceBundle rb) {

        imgUsuario.setImage(new Image(""));

    }

    @FXML
    protected void btn_Cadastro(ActionEvent cadastro) {
        Main.changeScreen("cadastro");
    }

    @FXML
    protected void btn_Logout(ActionEvent logout) {
        Main.changeScreen("login");
    }

    @FXML
    protected void btn_Clientes(ActionEvent clientes) {
        Main.changeScreen("clientes");
    }

    @FXML
    protected void btn_Config(ActionEvent config) {
        Main.changeScreen("config");
    }

    @FXML
    protected void btn_Relatorios(ActionEvent relatorios) {
        Main.changeScreen("relatorios");
    }

}
