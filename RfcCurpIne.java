package com.mycompany.rfccurpine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;

public class RfcCurpIne {

    public static void main(String[] args) {
        FileReader archivo;
        BufferedReader lector;
        FileWriter archivoNuevo;
        try {
            archivo = new FileReader("C:\\Users\\CorSan\\OneDrive\\Desktop\\RFC_CURP_INE\\Claves.txt");
            archivoNuevo = new FileWriter("C:\\Users\\CorSan\\OneDrive\\Desktop\\RFC_CURP_INE\\ClavesValidadas.txt");

            if (archivo.ready()) {
                lector = new BufferedReader(archivo);
                String cadena;
                //Saltar las 2 líneas de encabezado
                archivoNuevo.write(lector.readLine());
                archivoNuevo.write("\n" + lector.readLine());
                for (int x = 0; x < 3; x++) {
                    cadena = lector.readLine();
                    String expresionReg = "^[A-Z][AEIOU][A-Z]{2}[0-9]{2}(?:[0][1-9]|[1][0-2])(?:[0][1-9]|[12][0-9]|"
                            + "[3][01])[A-Z0-9]{3}$";
                    Pattern patron = Pattern.compile(expresionReg);
                    Matcher rfc = patron.matcher(cadena);
                    if (rfc.find()) {
                        System.out.println(cadena + " es valido.");
                        archivoNuevo.write("\n" + cadena + " es valido.");
                    } else {
                        System.out.println(cadena + " no es valido.");
                        archivoNuevo.write("\n" + cadena + " no es valido.");
                    }
                }
                //Saltar las 2 líneas de encabezado
                archivoNuevo.write("\n" + lector.readLine());
                archivoNuevo.write("\n" + lector.readLine());

                for (int x = 0; x < 5; x++) {
                    cadena = lector.readLine();
                    String expresionReg = "^[A-Z][AEIOU][A-Z]{2}[0-9]{2}(?:[0][1-9]|[1][0-2])(?:[0][1-9]|[12][0-9]|"
                            + "[3][01])[HM][A-Z]{2}[B-DF-HJ-NP-TV-Z]{3}(?:[0-9]{2}|[A-Z][0-9])$";
                    Pattern patron = Pattern.compile(expresionReg);
                    Matcher curp = patron.matcher(cadena);
                    if (curp.find()) {
                        System.out.println(cadena + " es valido.");
                        archivoNuevo.write("\n" + cadena + " es valido.");
                    } else {
                        System.out.println(cadena + " no es valido.");
                        archivoNuevo.write("\n" + cadena + " no es valido.");
                    }
                }

                //Saltar las 2 líneas de encabezado
                archivoNuevo.write("\n" + lector.readLine());
                archivoNuevo.write("\n" + lector.readLine());

                for (int x = 0; x < 4; x++) {
                    cadena = lector.readLine();
                    String expresionReg = "^[B-DF-HJ-NP-TV-Z]{6}[0-9]{2}(?:[0][1-9]|[1][0-2])(?:[0][1-9]|[12][0-9]|"
                            + "[3][01])(?:[0][1-9]|[12][0-9]|[3][0-2])[HM][0-9]{3}$";
                    Pattern patron = Pattern.compile(expresionReg);
                    Matcher ine = patron.matcher(cadena);
                    if (ine.find()) {
                        System.out.println(cadena + " es valido.");
                        archivoNuevo.write("\n" + cadena + " es valido.");
                    } else {
                        System.out.println(cadena + " no es valido.");
                        archivoNuevo.write("\n" + cadena + " no es valido.");
                    }
                }
                lector.close();
                archivoNuevo.close();
            } else {
                System.out.println("El archivo no está listo para ser leído...");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
