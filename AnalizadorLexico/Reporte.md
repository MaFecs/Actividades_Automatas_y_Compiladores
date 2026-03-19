## Análisis Léxico en Java — Patrones, Lexemas y Tokens
### 02 · Caso 1 — Básico: Keywords e Identifiers
### 💬 Preguntas de Discusión
1. ¿Qué pasa si eliminas los `\b` del patrón KEYWORD?
2. ¿Por qué el patrón KEYWORD debe evaluarse antes que IDENTIFIER?
3. ¿Podrías construir una sola ER que reconozca ambos tokens a la vez?

### 03 · Caso 2 — Básico+: Operadores y Separadores
### 💬 Preguntas de Discusión
1. ¿Cómo manejarías el operador ternario `?:` en tu analizador?
2. El operador `>>>` (shift sin signo) debe reconocerse antes que `>>`. ¿Por qué?
3. ¿Qué diferencia hay entre un separador y un operador desde la perspectiva de la gramática?

### 04 · Caso 3 — Intermedio: Literales Numéricos y de Cadena
### 💬 Preguntas de Discusión
1. ¿Por qué `08` NO es un octal válido en Java? ¿Cómo ajustarías la ER?
2. ¿Cómo extenderías el patrón STRING para soportar Text Blocks de Java 15+ (`"""..."""`)?
3. ¿Tiene sentido que el analizador léxico verifique si un número está en rango (e.g., `int > 2^31`)?

### 05 · Caso 4 — Intermedio: Comentarios y Errores Léxicos
### 💬 Preguntas de Discusión
1. ¿Cómo manejaría tu lexer un comentario de bloque no cerrado al final del archivo?
2. ¿Deberían los JavaDoc comments producir un token diferente a los block comments?
3. ¿Cuál es la diferencia entre recuperación de errores en el **lexer** vs en el **parser**?

### Decisiones de diseño 

### Resultados de pruebas
