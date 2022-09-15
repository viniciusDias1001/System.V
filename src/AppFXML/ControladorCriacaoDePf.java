package AppFXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorCriacaoDePf  implements Tela  {

    private String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private Pattern pattern = Pattern.compile(regx);

    public Map<String, Perfil> listaKeynome = new HashMap<String, Perfil>();
    public Map<String, Perfil> listaKeycpf = new HashMap<String, Perfil>();
    @FXML
    private Button voltarHome;
    @FXML
    private TextField nome;
    @FXML
    private TextField email;

    @FXML
    private TextField cpf;

    @FXML
    private TextField areaTrabalho;

    @FXML
    private TextField observacoes;

    GravadorDeDados gravadorDePerfil = new GravadorDeDados("Perfils.txt");




    public void addPerfilListakeyNome(String nome, Perfil perfil) {
        listaKeynome.put(nome, perfil);
    }

    public void addPerfilListakeyCPF(String cpf, Perfil perfil) {
        listaKeynome.put(cpf, perfil);
    }

    public void salvarDados() throws IOException {
        Map<String,String> listaNova = new HashMap<>();
        for (Map.Entry<String, Perfil> logins : this.listaKeynome.entrySet()) {
            listaNova.put(logins.getKey().toString(),logins.getValue().toString());
        }

        this.gravadorDePerfil.GravarPerfilEmArquivos(listaKeynome);
    }




    public void salvarPerfil(){
        Matcher matcher = pattern.matcher(email.getText());
        if (nome.getText().isEmpty() || email.getText().isEmpty() || cpf.getText().isEmpty() || areaTrabalho.getText().isEmpty() || observacoes.getText().isEmpty()){
            Notifications.create().title("Login ( System.V )").text("Preencha todos os dados").position(Pos.BOTTOM_RIGHT).showWarning();
        } else if (cpf.getText().equals("00000000000") ||cpf.getText().equals("11111111111") || cpf.getText().equals("22222222222") || cpf.getText().equals("33333333333") ||cpf.getText().equals("44444444444") || cpf.getText().equals("55555555555") || cpf.getText().equals("66666666666") || cpf.getText().equals("77777777777") || cpf.getText().equals("88888888888") || cpf.getText().equals("99999999999") || cpf.getText().length() != 11 || cpf.getText().substring(0,10).matches("[A-Z]*")) {

            Notifications.create().title("Login ( System.V )").text("Insira um CPF Válido").position(Pos.BOTTOM_RIGHT).showWarning();

        } else if (!matcher.matches() || !email.getText().contains("gmail") || !email.getText().substring(email.getText().length() - 4, email.getLength()).equals(".com")) {
            Notifications.create().title("Login ( System.V )").text("Insira um Email Valido").position(Pos.BOTTOM_RIGHT).showWarning();

        } else {
            Perfil perfil = new Perfil(nome.getText(),email.getText(),cpf.getText(),areaTrabalho.getText(),observacoes.getText());
            addPerfilListakeyNome(nome.getText(),perfil);
            addPerfilListakeyCPF(cpf.getText(),perfil);
            Notifications.create().title("Login ( System.V )").text("Perfil salvo no Banco de Dados").position(Pos.BOTTOM_RIGHT).showInformation();
             perfil.toString(perfil);
        }






    }



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
