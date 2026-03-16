%option noyywrap
%{
	#include<stdio.h>
	#define TOKEN_CURP 1
%}

%%
[A-Z][AEIOU][A-Z]{2}[0-9]{2}([0][1-9]|[1][0-2])([0][1-9]|[12][0-9]|[3][01])[HM][A-Z]{2}[B-DF-HJ-NP-TV-Z]{3}([0-9]{2}|[A-Z][0-9]) { return TOKEN_CURP; }
%%
int main()
{
int token;
while(1) {
	token = yylex();
	if ( token == TOKEN_CURP) {
		printf("TOKEN_CURP = %s\n",yytext);
	}
}
	return 0;
}