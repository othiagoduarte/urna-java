/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author LIANCARLORolim
 */
public class Votacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Votacao v = new Votacao();
        v.escolheCandidato();

    }
    
    public void escolheCandidato(){
        
        ArrayList<String> candidatos = new ArrayList();
        
        
        JFrame f = new JFrame();
        f.setLayout(null);
           
        // Objeto Font para ajustar o tipo, estilo e tamanho da fonte
        Font t = new Font("Arial", Font.BOLD, 20); 
        JLabel descUrna = new JLabel("URNA ELETRÔNICA DO BRASIL");
        JLabel Descricao = new JLabel("Selecione o número do candidato");
        
        descUrna.setFont(t);
        descUrna.setBounds(120, 20, 400, 20);
        
        Descricao.setFont(t);
        Descricao.setBounds(120, 50, 400, 20);
        JTextField caixa = new JTextField("");
        caixa.setBounds(120, 220, 560, 25);
        //caixa.addKeyListener(new MyListener()); 
       
        
        JButton enter = new JButton("Enter");
        enter.setBounds(120, 300, 120, 25);
        
        // Objeto do botão Branco.
        JButton branco = new JButton("Branco");
        branco.setBounds(340, 300, 120, 25);
        
        enter.addActionListener((ActionEvent e) -> {
            
            Votacao vot = new Votacao();
            
            if("13".equals(caixa.getText())){
                
                f.dispose();
                vot.run("Dilma Roussef", "PT - Partido dos Trabalhadores", "C:/Candidatos/dilma.jpg");
                
            }
            if("45".equals(caixa.getText())){
                
                f.dispose();
                vot.run("Aecio Neves", "PSDB - Partido da Social democracia Brasileira", "C:/Candidatos/aecio.jpg");
                
            }
            if("28".equals(caixa.getText())){
                
                f.dispose();
                vot.run("Levy Fidelix", "PRTB - Partido Renovador Trabalhista Brasileiro", "C:/Candidatos/levy.jpg");
                
            }
            if("43".equals(caixa.getText())){
                
                f.dispose();
                vot.run("Marina Silva", "PPS - Partido Popular Socialista", "C:/Candidatos/marina.jpg");
                
            }
            
        });
        
        f.add(Descricao);
        f.add(descUrna);
        // adiciona o JTextField
        f.add(caixa);
           
        // adiciona os botões
        f.add(enter);
        f.add(branco);
           
        // tamanho da tela
        f.setSize(800, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true); 
        
    }
    
    public void run(String nome, String partido, String caminhoImg){
    
        JFrame f = new JFrame();
        f.setLayout(null);
           
            // Objeto Font para ajustar o tipo, estilo e tamanho da fonte
        Font t = new Font("Arial", Font.BOLD, 20); 
           
            // insere as frases do candidato
        JLabel Nome = new JLabel("Candidato: " + nome);
            // utilizando o objeto Fnto para ajustar o texto
        Nome.setFont(t);
            // ajustar posição do elemento: (coluna, linha, largura, altura)
        Nome.setBounds(150, 120, 600, 20);
           
        JLabel Partido = new JLabel("Partido: " + partido);
        Partido.setFont(t);
        Partido.setBounds(120, 150, 600, 20);
           
        // insere a foto do candidato
        ImageIcon img_candidato = new ImageIcon(caminhoImg);
        JLabel Foto = new JLabel(img_candidato);
        Foto.setBounds(150, 200, 250, 250);

            // Objeto do botão Corrige
        JButton corrige = new JButton("Corrige");
        corrige.setBounds(140, 500, 120, 25);
        
        corrige.addActionListener((ActionEvent e) -> {
            
            f.dispose();
            Votacao vot = new Votacao();
            vot.escolheCandidato();
            
        });
        
        // Ibjeto do botão Confirma
        JButton confirma = new JButton("Confirma");
        confirma.setBounds(360, 500, 120, 25);           
        
        // executaca ação do botao... contabiliza o voto nesse caso!!!
        confirma.addActionListener((ActionEvent e) -> {
            System.out.println("imprimi");
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
    
}
