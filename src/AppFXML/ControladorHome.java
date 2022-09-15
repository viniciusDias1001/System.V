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

public class ControladorHome implements Tela {
    @FXML
    private Button criaPf;
    @FXML
    private Button voltarLogin;



    public void criarPf() throws IOException{
        criaPf.setOnAction(e ->{
            try {
                mudarTelaparaCriarPF(e);
            } catch (IOException io){
                io.getMessage();
            }
        });
    }

    public void voltarLogin(){
        voltarLogin.setOnAction(e ->{
            try {
                mudarTela(e);
            }catch (IOException io){
                io.getMessage();
            }
        });
    }





    public void mudarTelaparaCriarPF(ActionEvent event) throws IOException {
        Parent telaPag1 = FXMLLoader.load(getClass().getResource("/AppFXML/criacaoDePerfil.fxml"));
        Scene cenaPag01 = new Scene(telaPag1,450,500);
        Stage window =  (Stage)((Node)event.getSource()).getScene().getWindow();
        String arquivoCSS = getClass().getResource("/AppFXML/login.css").toExternalForm();
        cenaPag01.getStylesheets().add(arquivoCSS);
        window.setTitle("Criação de Perfil");
        window.setScene(cenaPag01);
        window.show();

    }
    public void mudarTela(ActionEvent event) throws IOException {
        Parent telaPag1 = FXMLLoader.load(getClass().getResource("/AppFXML/login.fxml"));
        Scene cenaPag01 = new Scene(telaPag1,450,500);
        Stage window =  (Stage)((Node)event.getSource()).getScene().getWindow();
        String arquivoCSS = getClass().getResource("/AppFXML/login.css").toExternalForm();
        cenaPag01.getStylesheets().add(arquivoCSS);
        window.setTitle("Criação de Perfil");
        window.setScene(cenaPag01);
        window.show();

    }
}
