package analizador_lexico;

import java.util.regex.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class KeywordsIdentifiers {

    // Tipos de token
    enum TokenType {
        JAVADOC, BLOCKCOMMENT, LINECOMMENT, STRINGLIT, CHARLIT, HEXADECIMALLIT, BINARYLIT, OCTALLIT, FLOATLIT, INTEGERLIT, KEYWORD, IDENTIFIER, WHITESPACE, OPERATOR, SEPARATOR
    }

    // Record para representar un Token (Java 16+)
    record Token(TokenType type, String lexeme, int line) {

        public String toString() {
            return String.format("[L%-3d] %-14s -> \"%s\"", line, type, lexeme);
        }
    }

    // Patrón de keywords (todas las palabras reservadas de Java)
    static final String KW_PATTERN
            = "\\b(abstract|assert|boolean|break|byte|case|catch|char|"
            + "class|const|continue|default|do|double|else|enum|extends|"
            + "final|finally|float|for|if|implements|import|instanceof|"
            + "int|interface|long|new|package|private|protected|public|"
            + "return|short|static|super|switch|synchronized|this|throw|"
            + "throws|try|void|volatile|while|true|false|null|String)\\b";

    // Patrón MASTER con grupos nombrados
    static final Pattern MASTER = Pattern.compile(
            // 1. Comentarios primero (no producen tokens de salida)
            "(?<JAVADOC>/\\*\\*[\\s\\S]*?\\*/)|"
            + "(?<BLOCKCOMMENT>/\\*[\\s\\S]*?\\*/)|"
            + "(?<LINECOMMENT>//[^\\n]*)|"
            + // 2. Literales de cadena y char (contienen cualquier carácter)
            "(?<STRINGLIT>\"([^\"\\\\]|\\\\.)*\")|"
            + "(?<CHARLIT>'([^'\\\\]|\\\\.)')|"
            + // 3. Literales numéricos (FLOAT > HEX/BIN/OCT > INT)
            "(?<FLOATLIT>[0-9][0-9_]*\\.[0-9][0-9_]*([eE][+-]?[0-9]+)?[fFdD]?)|"
            + "(?<HEXADECIMALLIT>0[xX][0-9a-fA-F][0-9a-fA-F_]*[lL]?)|"
            + "(?<BINARYLIT>0[bB][01][01_]*[lL]?)|"
            + "(?<OCTALLIT>0[0-7]+[lL]?)|"
            + "(?<INTEGERLIT>0|[1-9][0-9_]*[lL]?)|"
            + // 4. Keywords antes que dentificadores
            "(?<KEYWORD>" + KW_PATTERN + ")|"
            + "(?<IDENTIFIER>[a-zA-Z_$][a-zA-Z0-9_$]*)|"
            + // 5. Operadores compuestos antes que simples
            "(?<OPERATOR>==|!=|<=|>=|&&|\\|\\||<<|>>>|>>|\\+\\+|--|\\+=|-=|\\*=|/=|[+\\-*/%<>=!&|^~?:])|"
            + // 6. Separadores
            "(?<SEPARATOR>[(){}\\[\\];,.])|"
            + // 7. Whitespace (descartar)
            "(?<WHITESPACE>[ \\t\\r\\n]+)",
            Pattern.MULTILINE
    );

    // Método de Tokenización
    public static List<Token> tokenize(String source) {
        List<Token> tokens = new ArrayList<>();
        List<LexicalError> errors = new ArrayList<>();
        Matcher m = MASTER.matcher(source);
        int line = 1, pos = 0;

        while (m.find()) {
// En el tokenizador — estrategia PANIC RECOVERY:
// Si queda texto sin consumir, avanzar 1 char, registrar el error y continuar.
            if (m.start() > pos) {
                String illegal = source.substring(pos, m.start());
                int col = pos - source.lastIndexOf('\n', pos);
                System.err.printf("Error léxico [L%d, C%d]: '%s'%n", line, col, illegal);
                errors.add(new LexicalError(illegal.charAt(0), line, col));
            }
            if (m.group("WHITESPACE") != null) {
                // Contar saltos de línea en el whitespace
                line += m.group("WHITESPACE").chars().filter(c -> c == '\n').count();
            } else if (m.group("JAVADOC") != null) {
                tokens.add(new Token(TokenType.JAVADOC, m.group(), line));
            } else if (m.group("BLOCKCOMMENT") != null) {
                tokens.add(new Token(TokenType.BLOCKCOMMENT, m.group(), line));
            } else if (m.group("LINECOMMENT") != null) {
                tokens.add(new Token(TokenType.LINECOMMENT, m.group(), line));
            } else if (m.group("STRINGLIT") != null) {
                tokens.add(new Token(TokenType.STRINGLIT, m.group(), line));
            } else if (m.group("CHARLIT") != null) {
                tokens.add(new Token(TokenType.CHARLIT, m.group(), line));
            } else if (m.group("HEXADECIMALLIT") != null) {
                tokens.add(new Token(TokenType.HEXADECIMALLIT, m.group(), line));
            } else if (m.group("BINARYLIT") != null) {
                tokens.add(new Token(TokenType.BINARYLIT, m.group(), line));
            } else if (m.group("OCTALLIT") != null) {
                tokens.add(new Token(TokenType.OCTALLIT, m.group(), line));
            } else if (m.group("FLOATLIT") != null) {
                tokens.add(new Token(TokenType.FLOATLIT, m.group(), line));
            } else if (m.group("INTEGERLIT") != null) {
                tokens.add(new Token(TokenType.INTEGERLIT, m.group(), line));
            } else if (m.group("KEYWORD") != null) {
                tokens.add(new Token(TokenType.KEYWORD, m.group(), line));
            } else if (m.group("IDENTIFIER") != null) {
                tokens.add(new Token(TokenType.IDENTIFIER, m.group(), line));
            } else if (m.group("OPERATOR") != null) {
                tokens.add(new Token(TokenType.OPERATOR, m.group(), line));
            } else if (m.group("SEPARATOR") != null) {
                tokens.add(new Token(TokenType.SEPARATOR, m.group(), line));
            }
            pos = m.end();
        }
        return tokens;
    }

    public static void main(String[] args) {
        FileReader archivo;
        BufferedReader lector;
        StringBuilder constructorTexto = new StringBuilder();

        try {
            archivo = new FileReader("C:\\Users\\Ferny-KFC\\Desktop\\UAEH\\6TO. SEMESTRE\\Autómatas y Compiladores\\Archivos Lex\\codigoEntrada.txt");

            if (archivo.ready()) {
                lector = new BufferedReader(archivo);
                String linea;
                // Leemos línea por línea hasta que no haya más
                while ((linea = lector.readLine()) != null) {
                    constructorTexto.append(linea).append("\n");
                }
                String source = constructorTexto.toString();
                tokenize(source).forEach(System.out::println);
                lector.close();
            } else {
                System.out.println("El archivo no está listo para ser leído...");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

//Excepción para errores léxicos — contiene línea y columna exactas
class LexicalError extends RuntimeException {

    private final int line, column;
    private final char illegal;

    public LexicalError(char c, int line, int col) {
        super(String.format(
                "Error léxico [línea %d, col %d]: carácter ilegal '%c' (U+%04X)",
                line, col, c, (int) c
        ));
        this.line = line;
        this.column = col;
        this.illegal = c;
    }
}
