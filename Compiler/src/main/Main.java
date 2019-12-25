package main;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;

public class Main {

	private static boolean hadError = false;

	public static void main(String[] args)  {
		String input = "int struct union if else while for break continue sizof";
		Lexer lexer = new Lexer(input);
		lexer.getTokenList();
	}

	public static void error(int line, String message) {
		report(line, "", message);
	}

	private static void report(int line, String where, String message) {
		System.err.println("[line " + line + "] Error" + where + ": " + message);
		hadError = true;
	}

	public static void error(Token token, String message) {
		if (token.getType() == TokenType.EOF) {
			report(token.getLine(), " at end", message);
		} else {
			report(token.getLine(), " at '" + token.getContent() + "'", message);
		}
	}
}
