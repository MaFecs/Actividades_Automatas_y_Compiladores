## Analizar códigos de C++ con LEX
### Decisiones de diseño 
Para la implementación del scanner mediante la herramienta Flex, se establecieron los siguientes criterios de diseño orientados a la robustez y compatibilidad con el estándar de C++:
1. Codificación de Tokens: Se implementó un sistema de macros (#define) para la gestión de categorías léxicas. Esta arquitectura permite una separación clara entre el motor de reconocimiento (Flex) y la lógica de reporte en el lenguaje anfitrión (C).
2. Prioridad de Emparejamiento: El diseño de las expresiones regulares sigue la regla de Maximal Munch. Los operadores compuestos (ej. ==, <<=) se definieron antes que los operadores simples para garantizar que el autómata reconozca el lexema más largo posible.
3. Soporte de Literales Modernos: Se integró el reconocimiento de separadores de dígitos (comilla simple ') en literales numéricas, permitiendo la lectura de formatos conformes a C++14 y posteriores.
4. Persistencia de Datos: El sistema se diseñó para realizar una redirección de flujo desde un archivo fuente .cpp hacia un archivo de salida de texto plano (MyScanner.txt), automatizando el proceso de auditoría de tokens.
### Alcances (Capacidades del Sistema)
El analizador léxico presenta las siguientes capacidades operativas:
1. Análisis Multibase: Reconocimiento integral de sistemas numéricos decimales, hexadecimales (0x), binarios (0b) y octales (0), incluyendo sufijos de precisión (f, L, u).
2. Identificación de Directivas: Clasificación de palabras reservadas esenciales y directivas de preprocesador comunes como #include dentro de la categoría de KEYWORD.
3. Discriminación de Comentarios: Capacidad de ignorar el contenido de comentarios de bloque (/* ... */) y de línea (//), evitando la generación de tokens erróneos a partir de código comentado.
4. Robustez en Identificadores: Soporte completo para nombres de variables y funciones que inicien con guion bajo (_) o letras, seguidos de caracteres alfanuméricos.
### Áreas de mejora
- Identificación y tratamiento de errores léxicos.
- Implementación de conteo de líneas.

### Resultados de pruebas
<img width="985" height="868" alt="imagen" src="https://github.com/user-attachments/assets/01d46207-1148-4fb1-bf8d-ad3c4e8fc2e6" />
<img width="796" height="806" alt="imagen" src="https://github.com/user-attachments/assets/86af7a63-4f92-452a-8233-9d43196d3cfe" />
<img width="911" height="891" alt="imagen" src="https://github.com/user-attachments/assets/9ff3e36b-5871-40c1-9fee-423eb79a3ee9" />
<img width="728" height="773" alt="imagen" src="https://github.com/user-attachments/assets/7e922ac5-f9da-4535-901f-11f20dc21525" />



