/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import bean.abreSessao;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LIANCARLORolim
 */
public class Distribuidor {
    
    public static void main(String[] args) throws ClassNotFoundException {
        String mens = "";
        boolean continua = true;
        
        while(continua){
            ServerSocket serverSocket = null;
            Socket listenSocket = null;
            try {
                
                serverSocket = new ServerSocket(9000);
                listenSocket = serverSocket.accept();
                //DataInputStream dis = new DataInputStream(listenSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(listenSocket.getOutputStream());

                ObjectInputStream ois = new ObjectInputStream( listenSocket.getInputStream() );
                
                //RECEBO OS DADOS DA ABERTURA DA SESSAO
                abreSessao abre = (abreSessao) ois.readObject();
                
                
                 /////////////////////////
                // COMUNICO COM O COMISSARIO PARA PEGAR OS DADOS DA SESSAO
                
                Socket socketCliente = null;
        
                String porta = "2380";
        
                try {
                    
                    socketCliente = new Socket("localhost", Integer.parseInt(porta));
                    DataInputStream dis = new DataInputStream(socketCliente.getInputStream());
                    //DataOutputStream dos = new DataOutputStream(socketCliente.getOutputStream());

                    // comunico ao distribuidor a abertura da URNA!!
                    try (ObjectOutputStream output = new ObjectOutputStream(socketCliente.getOutputStream())) {
                        output.writeObject(abre);
                        output.flush();
                        output.reset();
                    }



                } catch (IOException ex) {

                    Logger.getLogger(Distribuidor.class.getName()).log(Level.SEVERE, null, ex);

                } finally{

                    try {

                        if(socketCliente != null)
                            socketCliente.close();

                    } catch (IOException ex) {

                        Logger.getLogger(Distribuidor.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
                
                
                
                /*Eleitor eleitor = (Eleitor) ois.readObject();
                
                Distribuidor dist = new Distribuidor();
                
                mens = dist.validaDadosVotacao(eleitor);
                
                if("Sucesso".equals(mens)){
                    
                }*/

            } catch (IOException ex) {
                Logger.getLogger(Distribuidor.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(listenSocket != null){
                    try {
                        listenSocket.close();
                        serverSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Distribuidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }

    }
    
}
