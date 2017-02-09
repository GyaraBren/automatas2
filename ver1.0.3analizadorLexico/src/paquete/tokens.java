/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author roberto
 */
public class tokens {
   private ArrayList lexemaPalabraReservada = new ArrayList();
            private ArrayList TokenPalabraReservada = new ArrayList();
    
    public void token() throws IOException{
          String cadena;
          
        int contador=0;
        try{
         BufferedReader n = new BufferedReader(new FileReader("palabrasreservadas.txt"));
         
            while ((cadena = n.readLine()) != null) {
                String[] auxiliar=cadena.split(" ");
                
                
                getLexemaPalabraReservada().add(auxiliar[0]);
                getTokenPalabraReservada().add(auxiliar[1]);
                contador++;
                  
            }
            for (int i = 0; i <getLexemaPalabraReservada().size() ; i++) {
                System.out.println(getLexemaPalabraReservada().get(i));
            }
        }catch(FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        }
    }

    /**
     * @return the lexemaPalabraReservada
     */
    public ArrayList getLexemaPalabraReservada() {
        return lexemaPalabraReservada;
    }

    /**
     * @param lexemaPalabraReservada the lexemaPalabraReservada to set
     */
    public void setLexemaPalabraReservada(ArrayList lexemaPalabraReservada) {
        this.lexemaPalabraReservada = lexemaPalabraReservada;
    }

    /**
     * @return the TokenPalabraReservada
     */
    public ArrayList getTokenPalabraReservada() {
        return TokenPalabraReservada;
    }

    /**
     * @param TokenPalabraReservada the TokenPalabraReservada to set
     */
    public void setTokenPalabraReservada(ArrayList TokenPalabraReservada) {
        this.TokenPalabraReservada = TokenPalabraReservada;
    }
}
