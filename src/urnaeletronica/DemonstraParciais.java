/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urnaeletronica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author LIANCARLORolim
 */
public class DemonstraParciais extends JFrame {
    
    public static int dilma = 0;
    public static int aecio = 0;
    public static int levy = 0;
    public static int marina = 0;
    public static int brancos = 0;
    JFrame f = new JFrame();
    static JLabel ptParcial = new JLabel(Integer.toString(dilma));
    static JLabel ppsParcial = new JLabel(Integer.toString(marina));
    static JLabel prtbParcial = new JLabel(Integer.toString(levy));
    static JLabel psdbParcial = new JLabel(Integer.toString(aecio));
    static JLabel votosBranco = new JLabel(Integer.toString(brancos));
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        DemonstraParciais par = new DemonstraParciais();
        
        par.apuraParciais();
        
            while(true){
                
                ServerSocket serverSocket = null;
                Socket listenSocket = null;
                try {
                    serverSocket = new ServerSocket(9090);
                    System.out.println("Aguardando conexao...");
                    listenSocket = serverSocket.accept();
                    System.out.println("Cliente conectado!!");
                    DataInputStream dis = new DataInputStream(
                            listenSocket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(
                            listenSocket.getOutputStream());


                    String msg = dis.readUTF();
                    System.out.println(msg);
                    if("PT - Partido dos Trabalhadores".equals(msg)){
                        DemonstraParciais.dilma++;
                        DemonstraParciais.ptParcial.setText(Integer.toString(dilma));
                    }else if("PSDB - Partido da Social democracia Brasileira".equals(msg)){
                        DemonstraParciais.aecio++;
                        DemonstraParciais.psdbParcial.setText(Integer.toString(aecio));
                    }else if("PRTB - Partido Renovador Trabalhista Brasileiro".equals(msg)){
                        DemonstraParciais.levy++;
                        DemonstraParciais.prtbParcial.setText(Integer.toString(aecio));
                    }else if("PPS - Partido Popular Socialista".equals(msg)){
                        DemonstraParciais.marina++;
                        DemonstraParciais.ppsParcial.setText(Integer.toString(aecio));
                    }else if("branco".equals(msg)){
                        DemonstraParciais.brancos++;
                        DemonstraParciais.votosBranco.setText(Integer.toString(brancos));
                    }

                    dos.writeUTF("ok");


                } catch (IOException ex) {
                    Logger.getLogger(DemonstraParciais.class.getName()).log(Level.SEVERE, null, ex);
                } finally{
                    if(listenSocket != null){
                        try {
                            listenSocket.close();
                            serverSocket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(DemonstraParciais.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
               
            
            }
        
    }
    
    public void apuraParciais(){
        
        //JFrame f = new JFrame();
        f.setLayout(null);
           
        // Objeto Font para ajustar o tipo, estilo e tamanho da fonte
        Font t = new Font("Arial", Font.BOLD, 20); 
        JLabel descUrna = new JLabel("PARCIAIS");
        JLabel Descricao = new JLabel("Selecione o número do candidato");
        
        descUrna.setFont(t);
        descUrna.setBounds(120, 10, 400, 20);
        
        // ajustar posição do elemento: (coluna, linha, largura, altura)
        JLabel pt = new JLabel("Dilma Rousself: ");
        
        pt.setFont(t);
        pt.setBounds(120, 40, 200, 20);
        
        //JLabel ptParcial = new JLabel(Integer.toString(dilma));
        
        ptParcial.setFont(t);
        ptParcial.setBounds(400, 40, 100, 20);
        
        JLabel psdb = new JLabel("Aecio Neves: ");
        // ajustar posição do elemento: (coluna, linha, largura, altura)
        psdb.setFont(t);
        psdb.setBounds(120, 80, 200, 20);
        
        // ajustar posição do elemento: (coluna, linha, largura, altura)
        psdbParcial.setFont(t);
        psdbParcial.setBounds(400, 80, 100, 20);
        
        
        JLabel prtb = new JLabel("Levy Fidelix: ");
        
        prtb.setFont(t);
        prtb.setBounds(120, 120, 200, 20);
        
        
        prtbParcial.setFont(t);
        prtbParcial.setBounds(400, 120, 100, 20);
        
        JLabel pps = new JLabel("Marina Silva: ");
        
        pps.setFont(t);
        pps.setBounds(120, 160, 200, 20);
        
        ppsParcial.setFont(t);
        ppsParcial.setBounds(400, 160, 100, 20);
        
        JLabel brancu = new JLabel("Votos Brancos: ");
        
        brancu.setFont(t);
        brancu.setBounds(120, 200, 200, 20);
        
        votosBranco.setFont(t);
        votosBranco.setBounds(400, 200, 100, 20);
        
        JButton enter = new JButton("Encerrar");
        enter.setBounds(120, 300, 120, 25);
        
        enter.addActionListener((ActionEvent e) -> {
            //addParcial();
            System.exit(0);
        });
        
        f.add(Descricao);
        f.add(descUrna);
        f.add(pt);
        f.add(ptParcial);
        f.add(psdb);
        f.add(psdbParcial);
        f.add(prtb);
        f.add(prtbParcial);
        f.add(pps);
        f.add(ppsParcial);
        f.add(brancu);
        f.add(votosBranco);
        // adiciona os botões
        f.add(enter);
           
        // tamanho da tela
        f.setSize(800, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true); 
        
    }
    
    
    public void addParcial(String cand){
        
        
        if(null != cand)switch (cand) {
            case "13":
                dilma++;
                ptParcial.setText(Integer.toString(dilma));
                break;
            case "45":
                aecio++;
                break;
            case "28":
                levy++;
                break;
            case "43":
                marina++;
                break;
        }
        
    }
    
    
}
