package Controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Clientes;

/**
 * FXML Controller class
 *
 * @author Eliandro
 */
public class ClientesController implements Initializable {

    Image image;

    Clientes cliente = new Clientes();

    @FXML
    private Label lblPesquisar, lblExitPop;

    @FXML
    private JFXTextField txtPesquisar;

    @FXML
    private TableView<Clientes> tblClientes;

    @FXML
    private TableColumn<Clientes, String> tblColunClientes, tblColunVendedor;

    @FXML
    private TableColumn<Clientes, Float> tblColunValTotal, tblColunValRest;

    @FXML
    private TableColumn<Clientes, Integer> tblColunID, tblColunParcelas;

    @FXML
    private TextField txtApelido, txtTelefone,
            txtRua, txtBairro, txtNumCasa,
            txtCidade, txtUF, txtDia, txtMes;

    private List<Clientes> listClientes;
    private ObservableList<Clientes> observableListClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        image = new Image(getClass().getResourceAsStream("/image/32x32pesquisar.png"));
        lblPesquisar.setGraphic(new ImageView(image));

        Main.addOnChangeScreen(new Main.OnChangeScreen() {
            @Override
            public void onScreenChange(String newScreen, Object userData) {
                if (newScreen.equals("clientes")) {
                    updateTableClientes();
                }
            }
        });

    }

    @FXML
    protected void btn_Voltar(ActionEvent voltar) {
        txtPesquisar.clear();
        Main.changeScreen("main");
    }
    
    @FXML
    protected void btn_Pesquisar(ActionEvent pesquisar) {
        if (txtPesquisar.getText().equals(tblColunClientes.getCellValueFactory().toString())) {
            System.out.println(tblColunClientes.getCellValueFactory().toString());
        }
    }
    
    @FXML
    protected void btn_Info(ActionEvent info) {
        if (!tblClientes.getSelectionModel().getSelectedCells().isEmpty()) {
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/view/popupInfoClientes.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.TRANSPARENT);
                txtApelido.setText("apelido");
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    protected void delete(KeyEvent delete) {

        if (!tblClientes.getSelectionModel().isEmpty()) {
            if (delete.getCode() == KeyCode.DELETE) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exclusão de Cliente");
                alert.setHeaderText("Deseja realmente excluir este cliente?");
                cliente = tblClientes.getSelectionModel().getSelectedItem();
                alert.setContentText(cliente.toStringCliente());

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    cliente.delete();
                }
            }
        }
    }

    @FXML
    protected void btnEditar(ActionEvent editar) {
        if (!tblClientes.getSelectionModel().isEmpty()) {
            cliente = cliente.find(tblClientes.getSelectionModel().getSelectedItem().getId());
            Main.changeScreen("cadastro", cliente);
        }
    }

    private void updateTableClientes() {
        System.out.println("UPDATE");

        tblClientes.getItems().clear();

        tblColunID.setCellValueFactory(new PropertyValueFactory<>("Nº"));
        tblColunClientes.setCellValueFactory(new PropertyValueFactory<>("Clientes"));
        tblColunParcelas.setCellValueFactory(new PropertyValueFactory<>("Parcelas"));
        tblColunValTotal.setCellValueFactory(new PropertyValueFactory<>("Valor Total"));
        tblColunValRest.setCellValueFactory(new PropertyValueFactory<>("Valor Restante"));
        tblColunVendedor.setCellValueFactory(new PropertyValueFactory<>("Vendedor"));

        listClientes = cliente.table();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tblClientes.setItems(observableListClientes);
    }
}
