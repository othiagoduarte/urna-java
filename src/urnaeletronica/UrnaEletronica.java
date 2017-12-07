/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urnaeletronica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author LIANCARLORolim
 */
public class UrnaEletronica {

    private JTextField voto;
    private JFrame framePrincipal;
    private ArrayList<Candidato> candidatos;
    
    public static void main(String[] args) {
        new UrnaEletronica().escolheCandidato();        
    }
    
    public UrnaEletronica(){
        this.candidatos = getCandidatos();
    }
    
    public void escolheCandidato(){
        this.voto = textVotoFactor();
        this.framePrincipal = framePrincipalFactor();
        this.framePrincipal.add(this.voto);
        this.framePrincipal.add(labelfactor("URNA ELETRÔNICA DO BRASIL", 120, 30));
        this.framePrincipal.add(labelfactor("Selecione o número do candidato", 120, 50));
        this.framePrincipal.add(labelfactor("13 - Dilma Roussef - PT", 120, 80));
        this.framePrincipal.add(labelfactor("45 - Aecio Neves   - PSDB", 120, 110));
        this.framePrincipal.add(labelfactor("28 - Levy Fidelix  - PRTB", 120, 140));
        this.framePrincipal.add(labelfactor("43 - Marina Silva  - PPS", 120, 170));
        this.framePrincipal.add(botaoEnterFactor());
        this.framePrincipal.add(botaoVotoBrancoFactor());
        this.framePrincipal.setVisible(true);
    }
    
//    public void confirmarCandidato(String nome, String partido, String caminhoImg){
        public void confirmarCandidato(Candidato candidato){        
    
        JFrame f = new JFrame();
        f.setLayout(null);
           
            // Objeto Font para ajustar o tipo, estilo e tamanho da fonte
//        Font t = new Font("Arial", Font.BOLD, 20); 
           
            // insere as frases do candidato
        JLabel Nome = new JLabel("Candidato: " + candidato.nome);
            // utilizando o objeto Fnto para ajustar o texto
        Nome.setFont(fontDefalut());
            // ajustar posição do elemento: (coluna, linha, largura, altura)
        Nome.setBounds(150, 120, 600, 20);
           
        JLabel Partido = new JLabel("Partido: " + candidato.partido);
        Partido.setFont(fontDefalut());
        Partido.setBounds(120, 150, 600, 20);
           
        // insere a foto do candidato
        ImageIcon img_candidato = new ImageIcon(candidato.caminhoImg);
        JLabel Foto = new JLabel(img_candidato);
        Foto.setBounds(150, 200, 250, 250);

            // Objeto do botão Corrige
        JButton corrige = new JButton("Corrige");
        corrige.setBounds(140, 500, 120, 25);
        
        corrige.addActionListener((ActionEvent e) -> {
            
            f.dispose();
            UrnaEletronica vot = new UrnaEletronica();
            vot.escolheCandidato();
            
        });
        
        // Ibjeto do botão Confirma
        JButton confirma = new JButton("Confirma");
        confirma.setBounds(360, 500, 120, 25);           
        
        // executaca ação do botao... contabiliza o voto nesse caso!!!
        confirma.addActionListener((ActionEvent e) -> {
            
            Socket socketCliente = null;
            
            try {
            
            socketCliente = new Socket("localhost", 6791);
            System.out.println("Conectado ao Contador!");
            DataInputStream dis = new DataInputStream(
                    socketCliente.getInputStream());
            DataOutputStream dos = new DataOutputStream(
                    socketCliente.getOutputStream());
            
            dos.writeUTF(candidato.partido);
            
            
        } catch (IOException ex) {
            
            Logger.getLogger(UrnaEletronica.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally{
            
            try {
                
                if(socketCliente != null)
                    socketCliente.close();
                
            } catch (IOException ex) {
                
                Logger.getLogger(UrnaEletronica.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
            
        JOptionPane.showMessageDialog(f, "Voto computado com sucesso");
        
        f.dispose();
        UrnaEletronica vot = new UrnaEletronica();
        vot.escolheCandidato();
        
        });
            
            // adiciona as Labels
        f.add(Nome);
        f.add(Partido);
           
            // adiciona a foto
        f.add(Foto);
           
        f.add(corrige);
        f.add(confirma);
           
            // tamanho da tela
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
    
    public Font fontDefalut(){
        return new Font("Arial", Font.BOLD, 20); 
    }
    
    public JLabel labelfactor(String nome, int x, int y){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, 600, 20);
        label.setFont(fontDefalut());
        return label;
    }
    
    public JButton botaoEnterFactor(){
        JButton enter = new JButton("Enter");
        enter.setBounds(120, 300, 120, 25);
        enter.addActionListener((ActionEvent e) -> {
            UrnaEletronica urnaEletronica = new UrnaEletronica();
                Candidato candidatosEncontrado = buscarCandidatoPorLegenda(getVoto());
                if(candidatosEncontrado != null){
                    System.out.println(candidatosEncontrado.getNome() + "Escolhido");
                    urnaEletronica.confirmarCandidato(candidatosEncontrado);
                }else{
                    System.out.println("Não deu nada");
                    JOptionPane.showMessageDialog(this.framePrincipal, "Candidato não encontrado");
                }

        });
        return enter;
    }
    
    public String getVoto(){
        return this.voto.getText().trim();
    }
    
    public JFrame framePrincipalFactor(){
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        return frame;
    }
    
    public JTextField textVotoFactor(){
        JTextField caixa = new JTextField("");
        caixa.setBounds(120, 220, 560, 25);
        return caixa;
    }
    
    public JButton botaoVotoBrancoFactor(){
              
        JButton branco = new JButton("Branco");
        branco.setBounds(340, 300, 120, 25);
        
        branco.addActionListener((ActionEvent e) -> {
            
            Socket socketCliente = null;
            
            try {
            
            socketCliente = new Socket("localhost", 6791);
            System.out.println("Conectado ao Contador!");
            DataInputStream dis = new DataInputStream(
                    socketCliente.getInputStream());
            DataOutputStream dos = new DataOutputStream(
                    socketCliente.getOutputStream());
            
            dos.writeUTF("branco");
            
            
        } catch (IOException ex) {
            
            Logger.getLogger(UrnaEletronica.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally{
            
            try {
                
                if(socketCliente != null)
                    socketCliente.close();
                
            } catch (IOException ex) {
                
                Logger.getLogger(UrnaEletronica.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
            
        JOptionPane.showMessageDialog(framePrincipal, "Voto computado com sucesso");
        dispose();
        UrnaEletronica vot = new UrnaEletronica();
        vot.escolheCandidato();
            
        });
        return branco;       
    }
    
    public void dispose(){
        this.framePrincipal.dispose();
    }
    
    public ArrayList<Candidato> getCandidatos(){
        ArrayList<Candidato> candidatos = new ArrayList<Candidato>();
        
        candidatos.add(new Candidato("13", "Dilma Roussef", "PT - Partido dos Trabalhadores", "C:/Candidatos/dilma.jpg"));
        candidatos.add(new Candidato("45", "Aecio Neves", "PSDB - Partido da Social democracia Brasileira", "C:/Candidatos/aecio.jpg"));
        candidatos.add(new Candidato("28", "Levy Fidelix", "PRTB - Partido Renovador Trabalhista Brasileiro", "C:/Candidatos/levy.jpg"));
        candidatos.add(new Candidato("13", "Dilma Roussef", "PT - Partido dos Trabalhadores", "C:/Candidatos/dilma.jpg"));
        candidatos.add(new Candidato("43", "Marina Silva", "PPS - Partido Popular Socialista", "C:/Candidatos/marina.jpg"));
        
        return candidatos;
    }
    
    public Candidato buscarCandidatoPorLegenda(String legendaEscolhida){
        try {
            Candidato candidato = null;
            for (Candidato c : this.candidatos) {
                if(c.getLegenda().equalsIgnoreCase(legendaEscolhida)){
                    candidato = c;
                }                
            }            
            return candidato;
        } catch (Exception ex) {
             Logger.getLogger(UrnaEletronica.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }          
    }
    
    class Candidato {
        private String legenda;
        private String nome;
        private String partido;
        private String caminhoImg;

        public Candidato(String legenda, String nome, String partido, String caminhoImg) {
            this.legenda = legenda;
            this.nome = nome;
            this.partido = partido;
            this.caminhoImg = caminhoImg;
        }
        
        public String getImagem() {
            return caminhoImg;
        }

        public String getLegenda() {
            return legenda;
        }

        public String getNome() {
            return nome;
        }

        public String getNomePartigo() {
            return partido;
        }
        
    }
    
    
}
