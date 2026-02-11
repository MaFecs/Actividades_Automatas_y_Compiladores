package com.mycompany.punto1numeroentero;
import java.util.Scanner;

public class Punto1NumeroEntero {

    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        
        System.out.println("Escribe una secuencia de caracteres:");
        String texto = entrada.nextLine(); //nextLine()Lee el texto

        for(int x=0;x<texto.length();x++){ //.length cuenta el número de caracteres
            //.charAt selecciona un caracter en específico
          if (texto.charAt(x)>='0' && texto.charAt(x)<='9'){
            System.out.println(texto.charAt(x)+" Es un número entero.");
        }  else{
              System.out.println(texto.charAt(x)+" No es un número entero.");
              
          }
        }  
    }
}
