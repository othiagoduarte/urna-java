/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urnaeletronica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LIANCARLORolim
 */
public class ContadorServerSocket {
    
    public static void main(String[] args) {
        
        while(true){
            ServerSocket serverSocket = null;
            Socket listenSocket = null;
            try {
                serverSocket = new ServerSocket(6791);
                System.out.println("Aguardando conexao...");
                listenSocket = serverSocket.accept();
                System.out.println("Cliente conectado!!");
                DataInputStream dis = new DataInputStream(
                        listenSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(
                        listenSocket.getOutputStream());
                
                
                String msg = dis.readUTF();

                Socket socketCliente = null;
            
            try {
            
            socketCliente = new Socket("localhost", 9090);
            System.out.println("Conectado ao Contador!");
            DataInputStream req = new DataInputStream(
                    socketCliente.getInputStream());
            DataOutputStream res = new DataOutputStream(
                    socketCliente.getOutputStream());
            
            res.writeUTF(msg);
            
            
        } catch (IOException ex) {
            
            Logger.getLogger(ContadorServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally{
            
            try {
                
                if(socketCliente != null)
                    socketCliente.close();
                
            } catch (IOException ex) {
                
                Logger.getLogger(ContadorServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
              
                
                dos.writeUTF("ok");
                
                /*String calc = dis.readUTF();
                System.out.println("Recebido "+calc);
                retorno = Integer.parseInt(calc) + 100;
                dos.writeUTF(Integer.toString(retorno));            
                System.out.println("Enviado "+ retorno);*/

            } catch (IOException ex) {
                Logger.getLogger(ContadorServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(listenSocket != null){
                    try {
                        listenSocket.close();
                        serverSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ContadorServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        
        }
    }
    
}
