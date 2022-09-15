package AppFXML;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
/* CLASSE PRINCIPAL, DA CRIAÇÃO DA JANELA*/
public class AppFXML  extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controlador c = new Controlador();

        for (Map.Entry<String,String> logins: c.listaAcessos.entrySet()){
            System.out.println("Lista de login:");
            System.out.println("E-mail: " + logins.getKey() + " Senha:" + logins.getValue());

        }
        String arquivoCSS = getClass().getResource("/AppFXML/login.css").toExternalForm();
        URL arquivoFXML = getClass().getResource("/AppFXML/login.fxml");
        GridPane raiz = FXMLLoader.load(arquivoFXML);
        stage.setResizable(false);
        stage.setTitle("Tela de Login");
        Scene cena = new Scene(raiz,450,500);
        cena.getStylesheets().add(arquivoCSS);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/AppFXML/imagens/logo-berserk.jfif")));
        stage.setScene(cena);

        stage.show();

    }

/* EXECUÇÃO DESSA JANELA*/
    public static void main (String[]args) throws IOException {
        launch();

            }
        }
