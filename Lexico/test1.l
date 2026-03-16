%option noyywrap
%{
	#include<stdio.h>
	#define TOKEN_NUM_ENTERO 1
%}

%%
[0-9]+ { return TOKEN_NUM_ENTERO; }
%%
int main()
{
int token;
while(1) {
	token = yylex();
	if ( token == TOKEN_NUM_ENTERO) {
		printf("TOKEN_NUM_ENTERO = %s\n",yytext);
	}
}
	return 0;
}