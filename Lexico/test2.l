%option noyywrap
%{
	#include<stdio.h>
	#define TOKEN_NUM_DECIMAL 1
%}

%%
[0-9]+[.][0-9]* { return TOKEN_NUM_DECIMAL; }
%%
int main()
{
int token;
while(1) {
	token = yylex();
	if ( token == TOKEN_NUM_DECIMAL) {
		printf("TOKEN_NUM_DECIMAL = %s\n",yytext);
	}
}
	return 0;
}