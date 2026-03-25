#include <iostream>

using namespace std;

/* j. Bloque de comentarios
   Este bloque debe ser reconocido como un solo token 
   de tipo BLOCKCOMMENT.*/
   
// i. Comentario de una línea: Esto es un LINECOMMENT
int main() {
// b. Identificadores (Variables)
    int mi_variable = 10;
    float _temp2 = 0.5;
    long contador_123 = 100L;
// c. Números enteros (Integer Literals)
    int entero = 12345;
    int con_separador = 1'000'000; // C++14
// d. Números en diferentes sistemas y flotantes
    int hexa = 0x1A6F;         // Hexadecimal
    int octal = 0755;          // Octal
    int binario = 0b101010;    // Binario
    float pi = 3.1415f;        // Flotante estándar
    double cientifico = 2.1e-10; // Notación científica
    float punto = 0.5;      
// e. Cadenas de caracteres (Strings)
    const String mensaje = "Hola Mundo \"con escapes\" \n";
// f. Caracteres (Chars)
    char letra = 'A';
    char escape = '\n';
// g. Operadores (Aritméticos, lógicos, relacionales, asignación)
    int calc = (5 + 3) * 2 / 1;
    bool comp = (10 == 10) && (5 != 2) || !(true);
    mi_variable += 5;
    mi_variable <<= 1;
    int* ptr = &mi_variable;
// h. Separadores
    {
        int arreglo[] = {1, 2, 3};
        cout << arreglo[0] << ";" <<endl;
    }
    return 0;
}
