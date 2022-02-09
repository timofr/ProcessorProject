package main;

import lexer.Token;
import lexer.TokenType;

public class Main {

	private static boolean errorOccured = false;
	public static void main(String[] args)  {
		
	}


	public static void error(int line, String message) {
		report(line, "", message);
	}

	private static void report(int line, String where, String message) {
		System.err.println("[line " + line + "] Error" + where + ": " + message);
		errorOccured = true;
	}

	public static void error(Token token, String message) {
		if (token.getType() == TokenType.EOF) {
			report(token.getLine(), " at end", message);
		} else {
			report(token.getLine(), " at '" + token.getContent() + "'", message);
		}
	}

	public static boolean hadError() {
		return errorOccured;
	}
}
