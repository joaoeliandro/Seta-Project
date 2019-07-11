package Controller;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Usuario;
import model.modelSQLite.SQLClientesDAO;
import model.modelSQLite.SQLUsuariosDAO;

/**
 *
 * @author Eliandro
 */
public class Main extends Application {

    private static Stage stage;

    private static Scene mainContabil;
    private static Scene login;
    private static Scene clientes;
    private static Scene cadastro;
    private static Scene cadastroUsuario;
    private static Scene config;
    private static Scene relatorios;
    private static Scene recuperacao;

    private static ArrayList<OnChangeScreen> listners = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;

        Main.stage.initStyle(StageStyle.TRANSPARENT);

//        aqui para iniciar o login
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        login = new Scene(fxmlLogin);

//        aqui para a screen inicial
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/view/MainContabil.fxml"));
        mainContabil = new Scene(fxmlMain);

//        aqui para a screen clientes
        Parent fxmlClientes = FXMLLoader.load(getClass().getResource("/view/Clientes.fxml"));
        clientes = new Scene(fxmlClientes);

//        aqui para a screen cadastro
        Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("/view/CadastroCliente.fxml"));
        cadastro = new Scene(fxmlCadastro);

//        aqui para a screen cadastro Usuario
        Parent fxmlCadastroUsuario = FXMLLoader.load(getClass().getResource("/view/CadastroUsuario.fxml"));
        cadastroUsuario = new Scene(fxmlCadastroUsuario);

//        aqui para a screen configuracao
        Parent fxmlConfig = FXMLLoader.load(getClass().getResource("/view/Configuracoes.fxml"));
        config = new Scene(fxmlConfig);

//        aqui para a screen relatorio
        Parent fxmlRelatorios = FXMLLoader.load(getClass().getResource("/view/Relatorios.fxml"));
        relatorios = new Scene(fxmlRelatorios);

//        aqui para a screen recuperacao de senha
        Parent fxmlRecuperacao = FXMLLoader.load(getClass().getResource("/view/EsqueceuSenha.fxml"));
        recuperacao = new Scene(fxmlRecuperacao);

        Main.stage.setScene(login);
        Main.stage.getIcons().add(new Image("/image/iconApp48.png"));

        //execução do bd para usuarios
        SQLUsuariosDAO sqlUsuarios = new SQLUsuariosDAO();
        //execução do bd para clientes
        SQLClientesDAO sqlClientes = new SQLClientesDAO();

        System.out.println("Banco: " + sqlUsuarios.all());
        System.out.println("\nBanco: " + sqlClientes.all());
        Main.stage.show();
    }

    public static void changeScreen(String screen, Object userData) {
        switch (screen) {
            case "login":
                stage.setScene(login);
                notifyAllListenrs("login", userData);
                break;
            case "main":
                stage.setScene(mainContabil);
                notifyAllListenrs("main", userData);
                break;
            case "clientes":
                stage.setScene(clientes);
                notifyAllListenrs("clientes", userData);
                break;
            case "cadastro":
                stage.setScene(cadastro);
                notifyAllListenrs("cadastro", userData);
                break;
            case "cadastroUsuario":
                stage.setScene(cadastroUsuario);
                notifyAllListenrs("cadastroUsuario", userData);
                break;
            case "config":
                stage.setScene(config);
                notifyAllListenrs("config", userData);
                break;
            case "relatorios":
                stage.setScene(relatorios);
                notifyAllListenrs("relatorios", userData);
                break;
            case "recuperacao":
                stage.setScene(recuperacao);
                notifyAllListenrs("recuperacao", userData);
                break;
            case "Proximo Painel":
                notifyAllListenrs("Proximo Painel", userData);
                break;
        }
    }

    public static void changeScreen(String screen) {
        changeScreen(screen, null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static interface OnChangeScreen {

        void onScreenChange(String newScreen, Object userData);
    }

    public static void addOnChangeScreen(OnChangeScreen newListners) {
        listners.add(newListners);
    }

    private static void notifyAllListenrs(String newScreen, Object userData) {
        listners.forEach((l) -> {
            l.onScreenChange(newScreen, userData);
        });
    }
}
