#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <string>

using namespace std;

int main() {
//Entrada
    int N, S, D, q0, T, C;
    
    // 1. Leer la primera línea "Tupla"
    if (!(cin>>N>>S>>D>>q0>>T>>C)) {
    	return 0;
	}

    // 2. Leer el alfabeto 
    char simbolo;
    for (int x = 0; x < S; x++) {
        cin >> simbolo;
    }

    // 3. Leer los estados de aceptación
    unordered_set<int> es_aceptado; //"unordered_set<int>" Declara un conjunto de números enteros llamado "es_aceptado"
    for (int x = 0; x < T; x++) {
        int estado_final;
        cin >> estado_final;
        es_aceptado.insert(estado_final); // ".insert" Agrega un estado al conjunto
    }

    // 4. Letura de transiciones (Creación del manual de reglas de forma dinámica)
    vector<unordered_map<char, int>> manual_de_reglas(N + 1); // "N+1" Reserva un espacio extra en la memoria para que 
    for (int x = 0; x < D; x++) {								//el índice del vector coincida con el nombre (número) ingresado por el usuario
        int origen, destino;
        char transicion;
        cin >> origen >> transicion >> destino;
        manual_de_reglas[origen][transicion] = destino; // Guarda la función de transición 
    } 												//Al ser declarada como "vector<unordered_map<char, int>>"se va creando un tipo de mapa con cada repetición
	
    // 5. Ingresar las C palabras
    vector<string> lista_palabras(C);
    for (int x = 0; x < C; x++) {
        cin >> lista_palabras[x]; // Guarda cada palabra en el vector
    }
	
	cout<<"El ejemplo verifica "<<C<<" palabras con los siguientes resultados:"<<endl; //Parte de la "Salida"
	
//Proceso para aceptar o rechazar la palabra	
		for (int x=0;x<C;x++){
			string palabra = lista_palabras[x]; //Ubica la palabra en la lista
        int estado_actual = q0; 
        bool atascado = false;

        // Recorrer la palabra letra por letra
        for (int y = 0; y < palabra.length(); y++) {
            char letra = palabra[y];
            if (manual_de_reglas[estado_actual].count(letra) > 0) { // Comprueba si la letra existe como opción en el estado en el que se encuentra
                estado_actual = manual_de_reglas[estado_actual][letra]; //Empieza el recorrido siguiendo la transiciones ingresadas anteriormente
            } else {
                // Si no hay camino para esa letra se rechaza la cadena
                atascado = true;
                break;
            }
        }
// Salida
        	if (!atascado && es_aceptado.count(estado_actual) > 0) { // Comprueba si el estado actual exite como opción en el conjunto de estados aceptados
            cout << palabra<<" ACEPTADA\n";
        } else {
            cout << palabra<<" RECHAZADA\n";
        }
		}
    return 0;
}
