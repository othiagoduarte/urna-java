/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;

import java.util.ArrayList;

/**
 *
 * @author LIANCARLORolim
 */
public class Comissario {
    
    public ArrayList<Eleitor> nomes = new ArrayList();
    public ArrayList<Boletim> boletim = new ArrayList();
    
    public static void main(String[] args) {
        
        Comissario com = new Comissario();
        
        // prepara eleição
        com.addEleitores();

    }
    
    public void addEleitores(){

            System.out.println("------------------------");
            System.out.println("Preparando eleição 2018");
            System.out.println("------------------------");

            Eleitor e = new Eleitor();
            e.setCpf("02119747067");
            e.setNome("Liancarlo Leffa Rolim");
            e.setTituloEleitor("045678");
            e.setZonaEleitoral("085");

            this.nomes.add(e);

            System.out.println("------------------------");
            System.out.println("Eleição preparada");
            System.out.println("------------------------");
    }
    
    public void computaBoletim(Eleitor ele){
        
        Boletim bo = new Boletim();
        
        
        
    }
    
    public void imprimeBoletim(){
        
    }
    
}
