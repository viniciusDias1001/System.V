package AppFXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorCriacaoDePf implements Tela {
    @FXML
    private Button voltarHome;



public  void voltarHome(){
    voltarHome.setOnAction(e ->{
        try {
            mudarTela(e);

        } catch (IOException io){
            io.getMessage();
        }

    });
}



    public void mudarTela(ActionEvent event) throws IOException {
        Parent telaPag1 = FXMLLoader.load(getClass().getResource("/AppFXML/home.fxml"));
        Scene cenaPag01 = new Scene(telaPag1,450,500);
        Stage window =  (Stage)((Node)event.getSource()).getScene().getWindow();
        String arquivoCSS = getClass().getResource("/AppFXML/login.css").toExternalForm();
        cenaPag01.getStylesheets().add(arquivoCSS);
        window.setTitle("Criação de Perfil");
        window.setScene(cenaPag01);
        window.show();

    }
}
