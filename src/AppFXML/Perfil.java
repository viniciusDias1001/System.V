package AppFXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Perfil {
    @FXML
    private Button voltarHome;



    private String nome;
    private String email;
    private String cpf;
    private String areaTrabalho;
    private String observacoes;


    public Perfil(String nome, String email, String cpf, String areaTrabalho, String observacoes){

        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.areaTrabalho = areaTrabalho;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailReal) {
        this.email = emailReal;
    }

    public String getCpf() {
        return cpf;
    }

    public String getAreTrabalho() {
        return areaTrabalho;
    }



    public void setAreTrabalho(String areTrabalhoReal) {
        this.areaTrabalho = areTrabalhoReal;
    }

    public void setCpf(String cpfReal) {
        this.cpf = cpfReal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoesReal) {
        this.observacoes = observacoesReal;
    }

    public  String toString(Perfil perfil){
        String saida = "Perfil do Cliente      " + perfil.getNome() + "\n" +
                       "------------------------------------------------" + "\n" +
                       "Nome: " + perfil.getNome() + "\n" +
                       "Email: " + perfil.getEmail() + "\n" +
                       "CPF: " + imprimeCPF(perfil.getCpf()) + "\n" +
                       "Área de Trabalho: " + perfil.getAreTrabalho() + "\n" +
                       "Observações: " + perfil.getObservacoes() + "\n" +
                        "------------------------------------------------";

        System.out.println(saida);
        return saida;
    }

    public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

}
  
