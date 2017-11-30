/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import bean.abreSessao;
import java.io.DataInputStream;
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
public class aberturaSessaoEleitoral {
    
    
    public static void main(String[] args) {
        
        boolean continua = true;
        int controle = 0;
        
        Socket socketCliente = null;
        
        String porta = "9000";
        
        try {
            
            System.out.println("SESSAO SENDO INICIADA!");
            
            socketCliente = new Socket("localhost", Integer.parseInt(porta));
            DataInputStream dis = new DataInputStream(socketCliente.getInputStream());
            //DataOutputStream dos = new DataOutputStream(socketCliente.getOutputStream());
            
            
            
            String zonaEleitoral = JOptionPane.showInputDialog("Zona Eleitoral: ");
            String cidade = JOptionPane.showInputDialog("Cidade: ");
            String estado = JOptionPane.showInputDialog("Estado: ");
            
            abreSessao abre = new abreSessao();
            
            abre.setCidade(cidade);
            abre.setEstado(estado);
            abre.setZonaEleitoral(zonaEleitoral);
            
            // comunico ao distribuidor a abertura da URNA!!
            try (ObjectOutputStream output = new ObjectOutputStream(socketCliente.getOutputStream())) {
                output.writeObject(abre);
                output.flush();
                output.reset();
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
    
    
