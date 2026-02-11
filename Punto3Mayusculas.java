
package com.mycompany.punto3mayusculas;

import java.util.Scanner;

public class Punto3Mayusculas {

    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        boolean mayuscula=true;
        
        System.out.println("Escribe una secuencia de caracteres:");
        String texto = entrada.nextLine(); //nextLine()Lee el texto
        
        for(int x=0;x<texto.length();x++){ //.length cuenta el número de caracteres
           //.charAt selecciona un caracter en específico
          if (texto.charAt(x)>='A' && texto.charAt(x)<='Z'){
              mayuscula=true;
        }  else{
              mayuscula=false;
              break;
              }
        }
        if(mayuscula){
            System.out.println(texto+" Es una palabra en mayúsculas.");
        } else{
            System.out.println(texto+" No es una palabra en mayúsculas.");
        }
    }
}
