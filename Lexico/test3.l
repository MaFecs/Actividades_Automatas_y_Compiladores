%option noyywrap
%{
	#include<stdio.h>
	#define TOKEN_ID 1
%}

%%
([a-zA-Z]+[0-9_]*) { return TOKEN_ID; }
%%
int main()
{
int token;
while(1) {
	token = yylex();
	if ( token == TOKEN_ID) {
		printf("TOKEN_ID = %s\n",yytext);
	}
}
	return 0;
}