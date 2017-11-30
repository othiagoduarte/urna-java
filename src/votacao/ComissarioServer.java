/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import bean.abreSessao;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LIANCARLORolim
 */
public class ComissarioServer {
    
    public ArrayList<Eleitor> nomes = new ArrayList();
    public ArrayList<Eleitor> eleitoresDaSessao = new ArrayList();
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        boolean continua = true;
        
        while(continua){
            
            ServerSocket serverSocket = null;
            Socket listenSocket = null;
            
            try {
                
                serverSocket = new ServerSocket(2380);
                listenSocket = serverSocket.accept();
                //DataInputStream dis = new DataInputStream(listenSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(listenSocket.getOutputStream());

                ObjectInputStream ois = new ObjectInputStream( listenSocket.getInputStream() );
                
                //RECEBO OS DADOS DA ABERTURA DA SESSAO
                abreSessao abre = (abreSessao) ois.readObject();
                
                
                ComissarioServer com = new ComissarioServer();
                
                com.addEleitores();
                
                
                


            } catch (IOException ex) {
                Logger.getLogger(ComissarioServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(listenSocket != null){
                    try {
                        listenSocket.close();
                        serverSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ComissarioServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }

    }
    
    public void addEleitores(){

            Eleitor e = new Eleitor();
            e.setCpf("02119747067");
            e.setNome("Liancarlo Leffa Rolim");
            e.setTituloEleitor("045678");
            e.setZonaEleitoral("085");
            e.setCidade("Porto Alegre");
            e.setEstado("RS");

            this.nomes.add(e);
            
            Eleitor el = new Eleitor();
            el.setCpf("0859413789");
            el.setNome("João da Silva");
            el.setTituloEleitor("04444444");
            el.setZonaEleitoral("085");
            el.setCidade("Porto Alegre");
            el.setEstado("RS");

            this.nomes.add(el);
            
            Eleitor ele = new Eleitor();
            ele.setCpf("0498764569");
            ele.setNome("Fabiana da Silva");
            ele.setTituloEleitor("076589172");
            ele.setZonaEleitoral("041");
            ele.setCidade("Alegrete");
            ele.setEstado("RS");

            this.nomes.add(ele);
            
    }
    
    public void buscarListaEleitoresSessao(Eleitor ele){
        
         
         for(Eleitor eleitores: this.nomes){
         
             if(eleitores.getZonaEleitoral().equals(ele.getZonaEleitoral()) &&
                     eleitores.getCidade().equals(ele.getCidade()) &&
                     eleitores.getEstado().equals(ele.getEstado())
             ){
                 
                 Eleitor elet = new Eleitor();
                 
                 elet.setCidade(eleitores.getCidade());
                 elet.setCpf(eleitores.getCpf());
                 elet.setEstado(eleitores.getEstado());
                 elet.setNome(eleitores.getNome());
                 elet.setTituloEleitor(elet.getTituloEleitor());
                 
                 
             }
         
         }
        
    }
    
}
