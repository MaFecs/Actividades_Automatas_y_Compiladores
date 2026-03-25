%option noyywrap
%{
	#include<stdio.h>
	#define BLOCKCOMMENT 1
	#define LINECOMMENT 2
	#define STRINGLIT 3
	#define CHARLIT 4
	#define HEXADECIMALLIT 5
	#define BINARYLIT 6
	#define OCTALLIT 7
	#define FLOATLIT 8
	#define INTEGERLIT 9
	#define KEYWORD 10
	#define IDENTIFIER 11
	#define OPERATOR 12
	#define SEPARATOR 13
	FILE *yyin, *yyout;
%}

%%
"/*"([^*]|\*+[^*/])*\*+"/" { return BLOCKCOMMENT; }
"//"[^\n]*	{ return LINECOMMENT; }
\"([^\"\\]|\\.)*\" {return STRINGLIT; }
'([^'\\]|\\.)*'    {return CHARLIT; }
"0"[xX][0-9a-fA-F][0-9a-fA-F']*[lLuU]?		       { return HEXADECIMALLIT; }
"0"[bB][01][01']*[lLuU]?			       { return BINARYLIT; }
"0"[0-7][0-7']*[lLuU]?			               { return OCTALLIT; }
[0-9][0-9']*"."[0-9][0-9']*([eE][+-]?[0-9]+)?[fFdDlL]? {return FLOATLIT; }
0|[1-9][0-9']*[lLuU]?				       {return INTEGERLIT; }
auto|else|LONG|switch|String|break|enum|register|typedef|case|extern|return|union|char|float|SHORT|unsigned|const|for|signed|void|continue|goto|sizeof|volatile|default|if|static|while|do|int|struct|_Packed|double|main|using|namespace|#include { return KEYWORD; }
[a-zA-Z_][a-zA-Z0-9_]* { return IDENTIFIER; }
"=="|"!="|"<="|">="|"&&"|"||"|"<<"|">>"|"++"|"--"|"+="|"-="|"*="|"/="|"|=" { return OPERATOR; }
"+"|"-"|"*"|"/"|"%"|"<"|">"|"="|"!"|"&"|"|"|"^"|"~"|"?"|":"                { return OPERATOR; }
"("|")"|"{"|"}"|"["|"]"|";"|","|"." { return SEPARATOR; }
%%
int main()
{
	yyin = fopen("C:/Users/Ferny-KFC/Desktop/UAEH/6TO. SEMESTRE/Autómatas y Compiladores/Archivos Lex/codigo.cpp","r");
	if(yyin == NULL){
	printf("No se pudo abrir el archivo codigo.cpp\n");
	return 1;
	}
	yyout = fopen("C:/Users/Ferny-KFC/Desktop/UAEH/6TO. SEMESTRE/Autómatas y Compiladores/Archivos Lex/MyScanner.txt","w");

int token;
while((token = yylex()) != 0) {
	if ( token == BLOCKCOMMENT) {
		fprintf(yyout, "BLOCKCOMMENT -> %s\n", yytext);
	}
	if ( token == LINECOMMENT) {
		fprintf(yyout, "LINECOMMENT -> %s\n", yytext);
	}
	if ( token == STRINGLIT) {
		fprintf(yyout, "STRINGLIT -> %s\n", yytext);
	}
	if ( token == CHARLIT) {
		fprintf(yyout, "CHARLIT -> %s\n", yytext);
	}
	if ( token == HEXADECIMALLIT) {
		fprintf(yyout, "HEXADECIMALLIT -> %s\n", yytext);
	}
	if ( token == BINARYLIT) {
		fprintf(yyout, "BINARYLIT -> %s\n", yytext);
	}
	if ( token == OCTALLIT) {
		fprintf(yyout, "OCTALLIT -> %s\n", yytext);
	}
	if ( token == FLOATLIT) {
		fprintf(yyout, "FLOATLIT -> %s\n", yytext);
	}
	if ( token == INTEGERLIT) {
		fprintf(yyout, "INTEGERLIT -> %s\n", yytext);
	}
	if ( token == KEYWORD) {
		fprintf(yyout, "KEYWORD -> %s\n", yytext);
	}
	if ( token == IDENTIFIER) {
		fprintf(yyout, "IDENTIFIER -> %s\n", yytext);
	}
	if ( token == OPERATOR) {
		fprintf(yyout, "OPERATOR -> %s\n", yytext);
	}
	if ( token == SEPARATOR) {
		fprintf(yyout, "SEPARATOR -> %s\n", yytext);
	}
}
    fclose(yyin);
    fclose(yyout);

	return 0;
}