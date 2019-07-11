package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author Eliandro
 */
public class RelatoriosController implements Initializable {

    @FXML
    private ProgressBar progressBar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    protected void btnVoltar() {
        Main.changeScreen("main");
    }
    
}
