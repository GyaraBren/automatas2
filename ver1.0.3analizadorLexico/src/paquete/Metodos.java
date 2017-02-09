/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author roberto
 */
public class Metodos {
     ArrayList codigo = new ArrayList();
     ArrayList renglon = new ArrayList();
    
    public ArrayList separadorCodigo(File archivo) throws IOException{
        int c = 0;
        String linea = "";
        String palabra = "";
        char[] caracterPalabra;
        try{
            BufferedReader n = new BufferedReader(new FileReader(archivo)); 
            while ((linea = n.readLine()) != null) {
                c++;
                caracterPalabra = linea.toCharArray();
                for (int i = 0; i <  caracterPalabra.length; i++) {
                    if (caracterPalabra[i] > 47 && caracterPalabra[i] < 58
                            || caracterPalabra[i]>64 && caracterPalabra[i] <91
                            || caracterPalabra[i] > 96 && caracterPalabra[i] < 123
                            ||caracterPalabra[i] == '"'
                            ||caracterPalabra[i] == 39
                            ||caracterPalabra[i] == '.'
                             ) {
                        
                        palabra += caracterPalabra[i];
                        
                    }else if(caracterPalabra[i] == ';' //separadores especiales
                           ||caracterPalabra[i] == '('
                           ||caracterPalabra[i] == ')'
                           ||caracterPalabra[i] == '{'
                           ||caracterPalabra[i] == '}'
                           ||caracterPalabra[i] == '['
                           ||caracterPalabra[i] == ']'
                           ||caracterPalabra[i] == ':'
                           ||caracterPalabra[i] == ','
                           ||caracterPalabra[i] == '@'
                           ||caracterPalabra[i] == '/'
                           ||caracterPalabra[i] == '#'
                           
                    
                            ){
                         if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                       renglon.add(c);
                    }  
                          codigo.add(caracterPalabra[i]);
                          renglon.add(c);
                    }else if(caracterPalabra[i]== '>'  //condicion op relacionales
                            ||caracterPalabra[i]== '<'
                            ||caracterPalabra[i]== '='
                            ||caracterPalabra[i]== '!'){ 
                        
                        if(i+1 < caracterPalabra.length ){
                            if (caracterPalabra[i +1]== '=') {
                                
                                
                                if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                     renglon.add(c);

                    }
                          codigo.add(caracterPalabra[i] + caracterPalabra[i+1]);
                            renglon.add(c);    
                            }else{
                                if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                    renglon.add(c);

                    }
                       codigo.add(caracterPalabra[i]); 
                       renglon.add(c);
                            }
                            
                        }else{
                                 if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                    renglon.add(c);

                    }
                       codigo.add(caracterPalabra[i]);    
                       renglon.add(c);
                        }
                        
                    
                    
                    }else if(caracterPalabra[i]== '+'
                            ||caracterPalabra[i]== '-'){
                        if (i+1 < caracterPalabra.length) {
                            if (caracterPalabra[i] == caracterPalabra[i+1] 
                                    || caracterPalabra[i+1] == '=') {
                                if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                     renglon.add(c);
                    }
                          codigo.add(caracterPalabra[i] + caracterPalabra[i+1]);
                             renglon.add(c); 
                            }else{
                                if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                    renglon.add(c);
                    }
                          codigo.add(caracterPalabra[i] );
                            renglon.add(c);  
                            }
                        }else{
                            if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                        renglon.add(c);
                    }
                          codigo.add(caracterPalabra[i]);
                           renglon.add(c);   
                        }
                    
                    
                    
                    }else{
                           if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                     renglon.add(c);
                    } 
                    }
                   
                }
                   if(!"".equals(palabra)){
                    codigo.add(palabra);
                     palabra = "";
                     renglon.add(c);
                    } 
                
            }
            
            
        }catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo de tokens");
        }   catch (IOException ex) {
                
            
            }
     return codigo;
    }
    
    public ArrayList compararTablaDeTokens(ArrayList lexema, ArrayList token){
        ArrayList codigoTokenfinal = new ArrayList();
        boolean b = false;
        for (int i = 0; i < codigo.size(); i++) {
            for (int j = 0; j < lexema.size(); j++) {
                if (codigo.get(i).toString().compareTo(lexema.get(j).toString())==0) {
                    codigoTokenfinal.add(token.get(j));
                    b = true;
                    j = lexema.size();
                }else{
                   b = false;
                }
            }
            
            if(!b){
                
              codigoTokenfinal.add( automata(convertidor(codigo.get(i).toString())));
               
            }
        }
     return codigoTokenfinal;   
    }
    
     
    
    public int[] convertidor(String palabra){
        char[] caracterPalabra = palabra.toCharArray();
        int[] palabraNumeros = new int[caracterPalabra.length];
        for (int i = 0; i < caracterPalabra.length; i++) {
            if (caracterPalabra[i]> 47 && caracterPalabra[i] < 58) {
              palabraNumeros[i] = 1;
            }else if (caracterPalabra[i] == '"') {
                palabraNumeros[i]= 2;
            }else if (caracterPalabra[i] == 39) {
                palabraNumeros[i]= 3;
            }else if (caracterPalabra[i] > 64 && caracterPalabra[i] < 91
                   || caracterPalabra[i] > 96 && caracterPalabra[i] < 123) {
                palabraNumeros[i]= 4;
            }else if (caracterPalabra[i] == '.') {
                palabraNumeros[i]= 5;
            }else if (caracterPalabra[i] == '[') {
                palabraNumeros[i]= 6;
            }else if (caracterPalabra[i] == ']') {
                palabraNumeros[i]= 7;
            }
            
        }
        return palabraNumeros;
    }
    public int automata(int[] palabraNumeros){
        int Sestado = 0;
        int[][] Automata =   {{0, 1, 4, 7,10,-1,12,-1, 0,-3},
                             {1, 1,-1,-1,-1, 2,-1,-1, 1, 499},
                             {2, 3,-1,-1,-1,-1,-1,-1, 0,-5},
                             {3, 3,-1,-1,-1,-1,-1,-1, 1,500},
                             {4,-1,-1,-1, 5,-1,-1,-1, 0,-6},
                             {5,-1, 6,-1, 5,-1,-1,-1, 0,-7},
                             {6,-1,-1,-1,-1,-1,-1,-1, 1,501},
                             {7,-1,-1,-1, 8,-1,-1,-1, 0,-8},
                             {8,-1,-1,-1, 9,-1,-1,-1, 0,-9},
                             {9,-1,-1,-1,-1,-1,-1,-1, 1,-10},
                            {10,11,-1,-1,10,-1,-1,-1, 0,-11},
                            {11,11,-1,-1,-1,-1,-1,-1, 1,503},
                            {12,13,-1,-1,-1,-1,-1,-1, 0,-12},
                            {13,14,-1,-1,-1,-1,-1,-1, 0,-13},
                            {14,-1,-1,-1,-1,-1,-1,15, 0,-14},
                            {15,-1,-1,-1,-1,-1,16,-1, 1,504},
                            {16,17,-1,-1,-1,-1,-1,-1, 0,-15},
                            {17,18,-1,-1,-1,-1,-1,-1, 0,-16},
                            {18,-1,-1,-1,-1,-1,-1,19, 0,-17},
                            {19,-1,-1,-1,-1,-1,-1,-1, 1,505}}; 
        int temp; 
        boolean b =false;
        for (int i = 0; i < palabraNumeros.length; i++) {
            if (Automata[Sestado][palabraNumeros[i]]> 0) {
                Sestado = Automata[Sestado][palabraNumeros[i]];
            }else{
                temp=i;
                b = true;
                i = palabraNumeros.length;
                
            }
   
        }
        if (b) {
            if (Automata[Sestado][8] == 1) {
                return -1;
            }else{
                return Automata[Sestado][9];
            }
        }else{
            return Automata[Sestado][9];
        }
    }
}
