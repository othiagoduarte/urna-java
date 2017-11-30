/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LIANCARLORolim
 */
public class Administrador {
    
    public static void main(String[] args) throws ClassNotFoundException {
        String mens = "";
        boolean continua = true;
        
        while(continua){
            ServerSocket serverSocket = null;
            Socket listenSocket = null;
            try {
                
                serverSocket = new ServerSocket(8600);
                System.out.println("Modulo distribuidor estartado e aguardando conexao...");
                listenSocket = serverSocket.accept();
                System.out.println("Cliente conectado ao distribuidor!!");
                //DataInputStream dis = new DataInputStream(listenSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(listenSocket.getOutputStream());

                ObjectInputStream ois = new ObjectInputStream( listenSocket.getInputStream() );
                Eleitor eleitor = (Eleitor) ois.readObject();
                
                Administrador adm = new Administrador();
                
                mens = adm.validaDadosVotacao(eleitor);
                
                if("Sucesso".equals(mens)){
                    
                }
                
                

            } catch (IOException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(listenSocket != null){
                    try {
                        listenSocket.close();
                        serverSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }
    }
    
    public String validaDadosVotacao(Eleitor eleitor){
        
        String msg = "Sucesso";
        ComissarioServer com = new ComissarioServer();
        com.addEleitores();
        
        for(Eleitor e: com.nomes){
            if(e.getCpf().equals(eleitor.getCpf())){
                if(!e.getTituloEleitor().equals(eleitor.getTituloEleitor())){
                    msg = "Titulo de eleitor não confere";
                }else if( !e.getNome().equals(eleitor.getNome()) ){
                    msg = "Nome eleitor não confere";
                }else if( !e.zonaEleitoral.equals(eleitor.zonaEleitoral) ){
                    msg = "Zona eleitoral não confere";
                }
            }else{
                msg = "Cpf não confere";
            }
        }
        
        return msg;
    }
    
}
