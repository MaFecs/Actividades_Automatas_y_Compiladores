## Análisis Léxico en Java — Patrones, Lexemas y Tokens
### 02 · Caso 1 — Básico: Keywords e Identifiers
### 💬 Preguntas de Discusión
1. ¿Qué pasa si eliminas los `\b` del patrón KEYWORD? R= No se identifican las palabras reservadas completas.
2. ¿Por qué el patrón KEYWORD debe evaluarse antes que IDENTIFIER? R= Por que es mejor identificar algo que ya está previamente establecido y no varían. 
3. ¿Podrías construir una sola ER que reconozca ambos tokens a la vez? R= Probablemente sí haciendo uso del operador '|'.

### 03 · Caso 2 — Básico+: Operadores y Separadores
### 💬 Preguntas de Discusión
1. ¿Cómo manejarías el operador ternario `?:` en tu analizador? como dos operadores individuales :) .
2. El operador `>>>` (shift sin signo) debe reconocerse antes que `>>`. ¿Por qué? R= Para que tome todos los simbolos (3) en vez de los primeros 2 al encontrar una coincidencia. 
3. ¿Qué diferencia hay entre un separador y un operador desde la perspectiva de la gramática? R= Que el operador es un símbolo que ejecuta una acción o cálculo sobre datos (operandos) para producir un nuevo valor, mientras que el separador es un símbolo  solo organiza y agrupa las piezas del lenguaje.

### 04 · Caso 3 — Intermedio: Literales Numéricos y de Cadena
### 💬 Preguntas de Discusión 
1. ¿Por qué `08` NO es un octal válido en Java? ¿Cómo ajustarías la ER? R= Porque el 8 no es parte del sistema octal ya que este solo debe de llegar hasta el número 7. Pero si se quire inclir solo se debe alargar el rango de números en la ER de "[0-8]".
2. ¿Cómo extenderías el patrón STRING para soportar Text Blocks de Java 15+ (`"""..."""`)? R= la expresión regular debe evolucionar de un patrón de una sola línea a uno que admita múltiples líneas y saltos de carro. Mientras que un String normal busca un par de comillas simples " y prohíbe saltos de línea literales, el patrón para bloques de texto debe comenzar y terminar obligatoriamente con la secuencia de triple comilla """.
3. ¿Tiene sentido que el analizador léxico verifique si un número está en rango (e.g., `int > 2^31`)? R= No tiene sentido que el analizador léxico verifique si un número está en rango, ya que su única función es la identificación de patrones.

### 05 · Caso 4 — Intermedio: Comentarios y Errores Léxicos
### 💬 Preguntas de Discusión
1. ¿Cómo manejaría tu lexer un comentario de bloque no cerrado al final del archivo? R= No será reconocido por el patrón BLOCKCOMMENT, ya que la expresión regular /\*[\s\S]*?\*/ es estricta y exige encontrar el cierre para que el Matcher confirme una coincidencia.
2. ¿Deberían los JavaDoc comments producir un token diferente a los block comments? R= Sí porque desde la perspectiva de las herramientas de desarrollo y el análisis estático, los JavaDoc contienen metadatos estructurados (como @param o @return) que deben ser procesados por generadores de documentación. Al separarlos en TokenType.JAVADOC y TokenType.BLOCKCOMMENT, se permite que un proceso posterior pueda extraer información valiosa de los primeros mientras descarta los segundos por completo.
3. ¿Cuál es la diferencia entre recuperación de errores en el **lexer** vs en el **parser**? R= Que el lexer recupera alfabeto, mientras que el parser recupera gramática intentando sincronizarse, usualmente saltando hasta el siguiente separador como un punto y coma.

### Decisiones de diseño 
1. Estrategia de Tokenización: Patrón Maestro (Master Regex)
El uso de Pattern.compile con grupos como (?<IDENTIFIER>...) permite centralizar toda la lógica del alfabeto en un solo lugar.

2. Jerarquía de Reconocimiento (Prioridad de Patrones)
El orden de los grupos en el MASTER PATTERN no es aleatorio; sigue una jerarquía de "el más específico primero":

3. Representación de Datos: Uso de record
Se implementó el token utilizando la característica record de Java 16+. Al ser datos inmutables, un record es ideal para representar tokens que no cambiarán una vez identificados. Reduce el código repetitivo (boilerplate) al generar automáticamente constructores, getters y métodos toString.

4. Robustez y Recuperación: Panic Mode Recovery
El diseño no se detiene ante el primer error encontrado. Si el Matcher no encuentra una coincidencia en la posición actual (m.start() > pos), el lexer captura el carácter ilegal, genera un LexicalError y fuerza el avance del puntero una posición.

5. Clasificación Semántica de Comentarios
Este diseño distingue entre LINECOMMENT, BLOCKCOMMENT y JAVADOC. Conserva los JAVADOC como tokens independientes.
### Resultados de pruebas
<img width="1037" height="998" alt="Captura de pantalla 2026-03-18 203903" src="https://github.com/user-attachments/assets/d98fb7a9-dea0-419c-b013-305cde2b2c2e" />
<img width="756" height="948" alt="Captura de pantalla 2026-03-18 203936" src="https://github.com/user-attachments/assets/097ec20e-0de5-4711-b188-a5467d242afd" />
<img width="586" height="809" alt="Captura de pantalla 2026-03-18 203955" src="https://github.com/user-attachments/assets/4986a08d-5b43-4d61-a471-99f7f373b249" />

