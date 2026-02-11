
package com.mycompany.punto9simbolos;

import java.util.Scanner;

public class Punto9Simbolos {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Escribe una secuencia de caracteres:");
        String texto = entrada.nextLine(); //nextLine()Lee el texto
        String[] numPalabras = texto.trim().split("\\s+"); //Divide la oración en palabras mediante los espacios en blanco

        for (int x = 0; x < numPalabras.length; x++) {
            String palabraAct = numPalabras[x];
            if (palabraAct.matches("[^a-zA-Z0-9_]+")) {
                System.out.println("La cadena de caracteres '" + palabraAct
                        + "' son simbolos.");
            } else {
                System.out.println("La cadena de caracteres '" + palabraAct
                        + "' No son simbolos.");
            }
        }
        System.out.println("La cadena de simbolos '" + texto + "' tiene "
                + numPalabras.length + " palabras.");
    }
}
