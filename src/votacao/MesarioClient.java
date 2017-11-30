/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LIANCARLORolim
 */
public class MesarioClient {
    
    public static void main(String[] args) {
        
        boolean continua = true;
        int controle = 0;
        
        while(continua){
        Socket socketCliente = null;
        
        String porta = "9000";
        
        try {
            
            socketCliente = new Socket("localhost", Integer.parseInt(porta));
            System.out.println("Conectado ao distribuidor!");
            DataInputStream dis = new DataInputStream(socketCliente.getInputStream());
            //DataOutputStream dos = new DataOutputStream(socketCliente.getOutputStream());
            
            
            String nome = JOptionPane.showInputDialog("Nome: ");
            String cpf = JOptionPane.showInputDialog("Cpf: ");
            String tituloEleitor = JOptionPane.showInputDialog("Titulo Eleitor: ");
            String zonaEleitoral = JOptionPane.showInputDialog("Zona Eleitoral: ");
            
            
            Eleitor ele = new Eleitor();
            ele.setNome(nome);
            ele.setCpf(cpf);
            ele.setTituloEleitor(tituloEleitor);
            ele.setZonaEleitoral(zonaEleitoral);
            
            try (ObjectOutputStream output = new ObjectOutputStream(socketCliente.getOutputStream())) {
                output.writeObject(ele);
                output.flush();
                output.reset();
            }
            
            //String preparaMensagem = nome + ";" + cpf + ";" + tituloEleitor + ";" + zonaEleitoral;
            
            //dos.writeUTF(preparaMensagem);

            String encerra = JOptionPane.showInputDialog("Encerrar Mesario ? SIM/NAO");
            if("SIM".equals(encerra)){
                continua = false;
            }
            
        } catch (IOException ex) {
            
            Logger.getLogger(MesarioClient.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally{
            
            try {
                
                if(socketCliente != null)
                    socketCliente.close();
                
            } catch (IOException ex) {
                
                Logger.getLogger(MesarioClient.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }

    }
    
}
