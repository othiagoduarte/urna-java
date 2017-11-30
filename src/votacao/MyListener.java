/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


import javax.swing.JTextField;
/**
 *
 * @author LIANCARLORolim
 */
class MyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {  
   
    }  
  
    @Override
    public void keyPressed(KeyEvent e) {  
         
    }  
   
    @Override
    public void keyReleased(KeyEvent e) {  
        Object source = e.getSource(); // pega o objeto que disparou a acao  
        if (source instanceof JTextField) {  
            JTextField txt = (JTextField) source;  
            try {  
                //testa se existem apenas numeros  
                Integer.parseInt(txt.getText());   
            } 
            catch (NumberFormatException ex) {  
                //Pega a String que esta no textfield  
                String text = txt.getText();  
                //retira o utimo caracter da string, que por sua vez nao e um numero  
                String newText = text.substring(0,text.length()-1);  
                txt.setText( newText );  
            } 
        }
        
        // trata aqui se o número digitado até então 
        // coincide com os números dos possíveis candidatos
    }  
    
}
