package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Clientes;

/**
 * FXML Controller class
 *
 * @author Eliandro
 */
public class CadastroClienteController implements Initializable {

    private Clientes clienteAtual;

    Alert alert;

    @FXML
    private TextField txtCliente, txtApelido,
            txtRua, txtTelefone, txtBairro,
            txtNumCasa, txtUF, txtValTotal,
            txtParce, txtEntrada, txtVendedor, txtCidade;

    @FXML
    private ComboBox<String> cbxMes;

    ObservableList<String> listMes = FXCollections.observableArrayList("Janeiro", "Fevereiro", "Marco",
            "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");

    @FXML
    private ComboBox<Integer> cbxDia;

    ObservableList<Integer> listDia = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxMes.setItems(listMes);
        cbxDia.setItems(listDia);

        Main.addOnChangeScreen(new Main.OnChangeScreen() {
            @Override
            public void onScreenChange(String newScreen, Object userData) {

                if (userData != null) {
                    try {
                        clienteAtual = (Clientes) userData;

                        txtCliente.setText(clienteAtual.getNomeCompleto());
                        txtApelido.setText(clienteAtual.getApelido());
                        txtTelefone.setText(clienteAtual.getTelefone());
                        txtRua.setText(clienteAtual.getRua());
                        txtBairro.setText(clienteAtual.getBairro());
                        txtNumCasa.setText(clienteAtual.getNumCasa());
                        txtCidade.setText(clienteAtual.getCidade());
                        txtUF.setText(clienteAtual.getUF());
                        txtValTotal.setText(String.valueOf(clienteAtual.getValorTotal()));
                        txtParce.setText(String.valueOf(clienteAtual.getParcelas()));
                        txtEntrada.setText(String.valueOf(clienteAtual.getEntrada()));
                        txtVendedor.setText(clienteAtual.getVendedor());

                        cbxDia.setValue(Integer.parseInt(clienteAtual.getDia()));
                        cbxMes.setValue(clienteAtual.getMes());

                    } catch (ClassCastException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    clienteAtual = null;
                    clearAll();
                }
            }
        });
    }

    @FXML

    protected void btnVoltar(ActionEvent voltar) {
        clearAll();
        Main.changeScreen("main");
    }

    @FXML
    protected void btnSalvar(ActionEvent salvar) {

        try {

            if (txtCliente.getText().isEmpty()) {
                throw new RuntimeException("Campo Cliente vazio");
            }
            if (txtApelido.getText().isEmpty()) {
                throw new RuntimeException("Campo Apelido vazio");
            }
            if (txtTelefone.getText().isEmpty()) {
                throw new RuntimeException("Campo Telefone vazio");
            }
            if (txtRua.getText().isEmpty()) {
                throw new RuntimeException("Campo Rua vazio");
            }
            if (txtBairro.getText().isEmpty()) {
                throw new RuntimeException("Campo Bairro vazio");
            }
            if (txtNumCasa.getText().isEmpty()) {
                throw new RuntimeException("Campo Numero da Casa vazio");
            }
            if (txtCidade.getText().isEmpty()) {
                throw new RuntimeException("Campo Cidade vazio");
            }
            if (txtUF.getText().isEmpty()) {
                throw new RuntimeException("Campo UF vazio");
            }
            if (txtValTotal.getText().isEmpty()) {
                throw new RuntimeException("Campo Valor Total vazio");
            }
            if (txtParce.getText().isEmpty()) {
                throw new RuntimeException("Campo Parcelas vazio");
            }
            if (txtEntrada.getText().isEmpty()) {
                throw new RuntimeException("Campo Entrada vazio");
            }
            if (txtVendedor.getText().isEmpty()) {
                throw new RuntimeException("Campo Vendedor vazio");
            }

            if (clienteAtual != null) {
                clienteAtual.setNomeCompleto(txtCliente.getText());
                clienteAtual.setApelido(txtApelido.getText());
                clienteAtual.setTelefone(txtTelefone.getText());
                clienteAtual.setRua(txtRua.getText());
                clienteAtual.setBairro(txtBairro.getText());
                clienteAtual.setNumCasa(txtNumCasa.getText());
                clienteAtual.setCidade(txtCidade.getText());
                clienteAtual.setUF(txtUF.getText());
                clienteAtual.setDia(cbxDia.getValue().toString());
                clienteAtual.setMes(cbxMes.getValue());
                clienteAtual.setValorTotal(Float.parseFloat(txtValTotal.getText()));
                clienteAtual.setParcelas(Integer.parseInt(txtParce.getText()));
                clienteAtual.setEntrada(Float.parseFloat(txtEntrada.getText()));
                clienteAtual.setVendedor(txtVendedor.getText());
                clienteAtual.setValorRestante(Float.parseFloat(txtValTotal.getText())
                        - Float.parseFloat(txtEntrada.getText()));

                clienteAtual.save();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edição");
                alert.setHeaderText("Edição realizada");
                alert.showAndWait();
            } else {

                Clientes clientes = new Clientes(txtCliente.getText(),
                        txtApelido.getText(), txtTelefone.getText(), txtRua.getText(),
                        txtBairro.getText(), txtNumCasa.getText(), txtCidade.getText(),
                        txtUF.getText(), cbxDia.getValue().toString(), cbxMes.getValue(),
                        Float.parseFloat(txtValTotal.getText()), Integer.parseInt(txtParce.getText()),
                        Float.parseFloat(txtEntrada.getText()), txtVendedor.getText());

                clientes.save();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText("Cadastro realizado");
                alert.showAndWait();
            }

        } catch (NumberFormatException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao salvar Cliente");
            alert.setContentText("Nos campos \"Valor Total, Parcelas e Entrada\" são valores numéricos");
            alert.showAndWait();
        } catch (RuntimeException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao salvar Cliente");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } finally {
            clearAll();
        }
    }

    private void clearAll() {
        txtCliente.clear();
        txtApelido.clear();
        txtRua.clear();
        txtTelefone.clear();
        txtBairro.clear();
        txtNumCasa.clear();
        txtCidade.clear();
        txtUF.clear();
        txtValTotal.clear();
        txtParce.clear();
        txtEntrada.clear();
        txtVendedor.clear();

        cbxMes.setValue(null);
        cbxDia.setValue(null);
    }
}
