package AppFXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*CLASSE CONTROLADORA DO MEU ARQUIVO LOGIN.FXML*/
public class Controlador implements Tela{

    String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    Pattern pattern = Pattern.compile(regx);
    public Map<String, String> listaAcessos;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    @FXML
    private Button loginBotao;
    private GravadorDeDados gravadorLogins;
    /*CRIÇÃO DE UM ARQUIVO TXT PARA ESCREVER OS DADOS SALVOS*/
    public Controlador (){
    gravadorLogins = new GravadorDeDados("ListaLogins.txt");
    listaAcessos = new HashMap<>();

}

    /*METODO CRIADO PARA SALVAR DADOS NO ARQUIVO TXT*/
    public void salvarDados() throws IOException {
        Map<String,String> listaNova = new HashMap<>();
        for (Map.Entry<String, String> logins : this.listaAcessos.entrySet()) {
            listaNova.put(logins.getKey().toString(),logins.getValue().toString());
        }
        listaAcessos = listaNova;
        this.gravadorLogins.GravarLoginsEmArquivos(listaAcessos);
    }

    /*GETS AND SETS*/
    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public PasswordField getSenha() {
        return senha;
    }

    public void setSenha(PasswordField senha) {
        this.senha = senha;
    }


    /*METODO CRIADO PARA RECUPERAR OS DADOS QUE FORAM SALVOS NO ARQUIVO TXT*/
    public  void  recuperarDados() throws IOException{
        Map<String,String> listaNova = this.gravadorLogins.recuperarDados();
        for (Map.Entry<String,String> logins: listaNova.entrySet()){

            String [] dados = logins.toString().split("#");
            this.listaAcessos.put(dados[0],dados[1]);

        }
   }
    /*METODO CRIADO PARA SALVAR OS DADOS OBTIDOS NOS LABEL DE EMAIL E SENHA, NA LISTA DE LOGINS*/
    public void salvar(){

        Matcher matcher = pattern.matcher(email.getText());

        if(email.getText().isEmpty() || senha.getText().isEmpty()){
            Notifications.create().title("Login ( System.V )").text("Email ou Senha não Inseridos").position(Pos.BOTTOM_RIGHT).showWarning();
        } else if (!matcher.matches() || !email.getText().contains("gmail") || !email.getText().substring(email.getText().length() - 4, email.getLength()).equals(".com"))
        {
            Notifications.create().title("Login ( System.V )").text("Insira um Email Valido").position(Pos.BOTTOM_RIGHT).showWarning();

        } else if (senha.getText().length() < 8) {
            Notifications.create().title("Login ( System.V )").text("Insira uma senha com mais de 7 digitos").position(Pos.BOTTOM_RIGHT).showWarning();
        }else if(senha.getText().length() > 15){
            Notifications.create().title("Login ( System.V )").text("Insira uma senha com no maximo 15 digitos").position(Pos.BOTTOM_RIGHT).showWarning();
        }
        else if (listaAcessos.containsValue(email.getText().toString())) {
            Notifications.create().title("Login ( System.V )").text("Email já existente no Banco de Dados").position(Pos.BOTTOM_RIGHT).showError();
        } else if(!listaAcessos.containsKey(email.getText())) {
            listaAcessos.put(email.getText(), senha.getText());
            Notifications.create().title("Login ( System.V )").text("Dados Salvos no Banco de Dados.").position(Pos.BOTTOM_RIGHT).showInformation();
            try {
                salvarDados();
                System.out.println("Dados Salvos com sucesso");
            } catch (IOException d){
                System.out.println("Erro ao salvar arquivos" + d.getMessage());
            }
        }
     }
    /*METODO CRIADO PARA DAR ACESSO DA PLATAFROMA AO USUARIO, CASO OS DADOS DELES FOR ENCONTRADO NA LISTA*/
    public void entrar() throws IOException {



        if(email.getText().isEmpty() || senha.getText().isEmpty()){
            Notifications.create().title("Login ( System.V )").text("Email ou Senha não Inseridos").position(Pos.BOTTOM_RIGHT).showWarning();

        }
        else if (!email.getText().contains("@")  || !email.getText().contains(email.getText().toLowerCase()) || !email.getText().contains("gmail") ) {
            Notifications.create().title("Login ( System.V )").text("Insira um Email Valido").position(Pos.BOTTOM_RIGHT).showWarning();
        } else if (senha.getText().length() < 8) {
            Notifications.create().title("Login ( System.V )").text("Insira uma senha com mais de 7 digitos").position(Pos.BOTTOM_RIGHT).showWarning();
        }
        else if(senha.getText().length() > 15){
            Notifications.create().title("Login ( System.V )").text("Insira uma senha com no maximo 15 digitos").position(Pos.BOTTOM_RIGHT).showWarning();
        }
        else if (listaAcessos.containsValue(senha.getText()) && listaAcessos.containsKey(email.getText())){

            Notifications.create().title("Login ( System.V )").text("Login Efetuado com sucesso").position(Pos.BOTTOM_RIGHT).showInformation();

            Notifications.create().title("Login ( System.V )").text("Pressione o Botão de entrar novamente").position(Pos.BOTTOM_RIGHT).showInformation();
            loginBotao.setOnAction(e -> {
                try {
                    mudarTela(e);
                } catch (IOException io){
                    io.getMessage();
                }

            });
        }
        else if (listaAcessos.containsValue(senha.getText()) && !listaAcessos.containsKey(email.getText())){
            Notifications.create().title("Login ( System.V )").text("Senha invalida, tente novamente").position(Pos.BOTTOM_RIGHT).showInformation();
        }
        else   {
            Notifications.create().title("Login ( System.V )").text("Dados não Salvos no Banco de Dados").position(Pos.BOTTOM_RIGHT).showWarning();
        }

    }
    /*MUDANÇA DE JANELA, ENTRE A JANELA DE LOGIN E A DE HOME.*/
    public void mudarTela(ActionEvent event) throws IOException{
        Parent telaPag1 = FXMLLoader.load(getClass().getResource("/AppFXML/home.fxml"));
        Scene cenaPag01 = new Scene(telaPag1,450,500);
        Stage window =  (Stage)((Node)event.getSource()).getScene().getWindow();
        String arquivoCSS = getClass().getResource("/AppFXML/login.css").toExternalForm();
        cenaPag01.getStylesheets().add(arquivoCSS);
        window.setTitle("Home");
        window.setScene(cenaPag01);
        window.show();

    }

}
