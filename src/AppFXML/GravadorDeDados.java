package AppFXML;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class GravadorDeDados {

    private String nomeArquivo;

     public GravadorDeDados(String nomeArquivo){
         this.nomeArquivo = nomeArquivo;
     }


    public void GravarLoginsEmArquivos(Map<String,String> listaGravar) throws IOException{

        BufferedWriter gravador = null;
        try {
            gravador = new BufferedWriter(new FileWriter(this.nomeArquivo,true));

            for(Map.Entry<String,String> s: listaGravar.entrySet()){
                gravador.write( s.toString().replaceAll("=","#") +  "#" + "\n");
            }

        }
        finally {
            if(gravador != null) {
                gravador.close();
            }
        }



    }


    public Map<String,String> recuperarDados() throws IOException{
        Map<String,String> listaDadosRecuperados = new HashMap<>();
        BufferedReader leitor = null;

        try {
            leitor = new BufferedReader(new FileReader(this.nomeArquivo));
            String linhaLida = null;
            do {
                linhaLida = leitor.readLine();
                if(linhaLida != null) {
                    listaDadosRecuperados.put(linhaLida,linhaLida);
                }
            }while(linhaLida != null);
        }
        finally {
            if(leitor != null) {
                leitor.close();
            }
        }
        return listaDadosRecuperados;
    }



}
