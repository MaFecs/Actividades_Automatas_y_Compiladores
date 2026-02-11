
package com.mycompany.punto4identificadores;

import java.util.Scanner;

public class Punto4Identificadores {

    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        boolean identificador=true;
                
        System.out.println("Escribe una secuencia de caracteres:");
        String texto = entrada.nextLine(); //nextLine()Lee el texto
        
        if(texto.charAt(0)>='0' && texto.charAt(0)<='9'||(texto.equals("_"))){  //.equals evalua a que es igual toda la cadena de caracteres
            identificador=false;
        } else{
        for(int x=0;x<texto.length();x++){ //.length cuenta el número de caracteres
           //.charAt selecciona un caracter en específico
          if ((texto.charAt(x)>='A' && texto.charAt(x)<='Z')|| 
                  (texto.charAt(x)>='a' && texto.charAt(x)<='z')||
                  (texto.charAt(x)=='_')||
                  (texto.charAt(x)>='0' && texto.charAt(x)<='9')){
              identificador=true;
        }  else{
              identificador=false;
              break;
              }
        }}
        if(identificador){
            System.out.println(texto+" Es un identificador.");
        } else{
            System.out.println(texto+" No es un identificador.");
        }
    }
}
