package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Eliandro
 */
public class CadastroUsuarioController implements Initializable {

    private Usuario novoUsuario;
    private String dirImage = "/image/usu.png";
    private Paint colorBlack = Paint.valueOf("black"), colorRed = Paint.valueOf("red");

    @FXML
    private TextField txtNomeCompleto, txtEmail, txtPalavraSeg;

    @FXML
    private ComboBox<String> cbxMes;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtSenha, txtConfirmaSenha;

    @FXML
    private ToggleGroup perguntaSegur;

    @FXML
    private RadioButton radioNomeMae, radioApelidoInfan, radioNumCasa;

    @FXML
    private Label lblErroCadastro;

    @FXML
    private ImageView imgUsuario;

    ObservableList<String> listMes = FXCollections.observableArrayList("Janeiro", "Fevereiro", "Marco",
            "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");

    @FXML
    private ComboBox<Integer> cbxDia, cbxAno;

    ObservableList<Integer> listDia = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);

    ObservableList<Integer> listAno = FXCollections.observableArrayList(
            2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991,
            1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981,
            1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971,
            1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961,
            1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxDia.setItems(listDia);
        cbxMes.setItems(listMes);
        cbxAno.setItems(listAno);

        imgUsuario.setImage(new Image(dirImage));

    }

    @FXML
    protected void btnVoltar() {
        clearAll();
        Main.changeScreen("login");
    }

    @FXML
    protected void cadastrar() {
        switch (testeCadastro()) {
            case 0:
                cadastrarUsuario();
                Main.changeScreen("login");
                lblErroCadastro.setText("");
                clearAll();
                break;
            case 1:
                lblErroCadastro.setText("*Usuário tem que ser no minimo 8 caracteres");
                txtSenha.unFocusColorProperty().set(colorBlack);
                txtUsuario.clear();
                break;
            case 2:
                lblErroCadastro.setText("*Senha tem que ser no minimo 8 digitos");
                txtUsuario.unFocusColorProperty().set(colorBlack);
                txtSenha.clear();
                break;
            case 3:
                lblErroCadastro.setText("*Senhas incompatíveis");
                txtConfirmaSenha.clear();
                break;
        }
    }

    @FXML
    protected void carregarImagem() {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivo de Imagem", "*.png", "*.jpg"));
        File selectedFile = file.showOpenDialog(null);

        if (selectedFile != null) {
            dirImage = selectedFile.toURI().toString();
            System.out.println("diretorio: " + dirImage);
            imgUsuario.setImage(new Image(dirImage));
        } else {

        }
    }

    private String pegarPergSelect() {

        if (radioNomeMae.isSelected()) {
            return radioNomeMae.getText();
        } else if (radioApelidoInfan.isSelected()) {
            return radioApelidoInfan.getText();
        } else {
            return radioNumCasa.getText();
        }
    }

    private int testeCadastro() {
        if (!txtUsuario.getText().equals("") && txtUsuario.getText().length() >= 8) {
            if (txtSenha.getText().length() >= 8) {
                if (txtSenha.getText().equals(txtConfirmaSenha.getText())) {
                    txtUsuario.unFocusColorProperty().set(colorBlack);
                    txtSenha.unFocusColorProperty().set(colorBlack);
                    txtConfirmaSenha.unFocusColorProperty().set(colorBlack);
                    return 0;
                } else {
                    txtConfirmaSenha.unFocusColorProperty().set(colorRed);
                    return 3;
                }
            } else {
                txtSenha.unFocusColorProperty().set(colorRed);
                return 2;
            }
        } else {
            txtUsuario.unFocusColorProperty().set(colorRed);
            return 1;
        }
    }

    private void clearAll() {
        txtNomeCompleto.clear();
        txtEmail.clear();
        txtPalavraSeg.clear();

        txtUsuario.clear();
        txtSenha.clear();
        txtConfirmaSenha.clear();

        cbxDia.setValue(null);
        cbxMes.setValue(null);
        cbxAno.setValue(null);

    }

    private void cadastrarUsuario() {

        try {
            if (txtNomeCompleto.getText().isEmpty()) {
                throw new RuntimeException("Campo Nome Completo vazio");
            }
            if (txtEmail.getText().isEmpty()) {
                throw new RuntimeException("Campo Email vazio");
            }
            if (txtPalavraSeg.getText().isEmpty()) {
                throw new RuntimeException("Campo Resposta de Segurança vazio");
            }

            novoUsuario = new Usuario(txtNomeCompleto.getText(), txtEmail.getText(),
                    cbxDia.getValue().toString(), cbxMes.getValue(), cbxAno.getValue().toString(),
                    pegarPergSelect(), txtPalavraSeg.getText(),
                    txtUsuario.getText(), txtSenha.getText());

            novoUsuario.save();

        } catch (RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao salvar Cliente");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
