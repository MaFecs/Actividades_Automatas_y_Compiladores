%option noyywrap
%{
	#include<stdio.h>
	#define TOKEN_RFC 1
%}

%%
[A-Z][AEIOU][A-Z]{2}[0-9]{2}([0][1-9]|[1][0-2])([0][1-9]|[12][0-9]|[3][01])[A-Z0-9]{3} { return TOKEN_RFC; }
%%
int main()
{
int token;
while(1) {
	token = yylex();
	if ( token == TOKEN_RFC) {
		printf("TOKEN_RFC = %s\n",yytext);
	}
}
	return 0;
}