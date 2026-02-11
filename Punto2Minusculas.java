import java.util.Scanner;

public class Punto2Minusculas {

    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        boolean minuscula=true;
        
        System.out.println("Escribe una secuencia de caracteres:");
        String texto = entrada.nextLine(); //nextLine()Lee el texto
        
        for(int x=0;x<texto.length();x++){ //.length cuenta el número de caracteres
           //.charAt selecciona un caracter en específico
          if (texto.charAt(x)>='a' && texto.charAt(x)<='z'){
              minuscula=true;
        }  else {
              minuscula=false;
              break;
              }
        }
        if(minuscula){
            System.out.println(texto+" Es una palabra en minúsculas.");
        } else {
            System.out.println(texto+" No es una palabra en minúsculas.");
        }
    }
}
