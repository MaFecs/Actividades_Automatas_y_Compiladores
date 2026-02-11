package com.mycompany.punto5numeroentero;

import java.util.Scanner;

public class Punto5NumeroEntero {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Escribe una secuencia de caracteres:");
        String texto = entrada.nextLine(); //nextLine()Lee el texto
        String[] numPalabras = texto.trim().split("\\s+"); //Divide la oración en palabras mediante los espacios en blanco

        for (int x = 0; x < numPalabras.length; x++) {
            String palabraAct = numPalabras[x];
            if (palabraAct.matches("[0-9]+")) {
                System.out.println("La cadena de simbolos '" + palabraAct
                        + "' son números enteros.");
            } else {
                System.out.println("La cadena de simbolos '" + palabraAct
                        + "' No son números enteros.");
            }
        }
        System.out.println("La cadena de simbolos '" + texto + "' tiene "
                + numPalabras.length + " palabras.");
    }
}
