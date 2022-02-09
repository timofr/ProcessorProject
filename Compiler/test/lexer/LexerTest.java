package lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Main;


class LexerTest extends AbstractTest {

	@Test
	void identifierLineTest() throws Lexer.LexerException {
		String input = "foo\nbar";
		Lexer lexer = new Lexer(input);
		List<Token> list = Arrays.asList(
				new Token(TokenType.IDENTIFIER, "foo", 1),
				new Token(TokenType.IDENTIFIER, "bar", 2));
		assertEquals(list, lexer.getTokenList());
		assertFalse(Main.hadError());
	}
	
	@Test
	void keywordTest() throws Lexer.LexerException {
		String input = "int struct union if else while for break continue sizeof short long signed unsigned";
		Lexer lexer = new Lexer(input);
		List<Token> list = Arrays.asList(
				new Token(TokenType.INT, "int", 1),
				new Token(TokenType.STRUCT, "struct", 1),
				new Token(TokenType.UNION, "union", 1),
				new Token(TokenType.IF, "if", 1),
				new Token(TokenType.ELSE, "else", 1),
				new Token(TokenType.WHILE, "while", 1),
				new Token(TokenType.FOR, "for", 1),
				new Token(TokenType.BREAK, "break", 1),
				new Token(TokenType.CONTINUE, "continue", 1),
				new Token(TokenType.SIZEOF, "sizeof", 1),
				new Token(TokenType.SHORT, "short", 1),
				new Token(TokenType.LONG, "long", 1),
				new Token(TokenType.SIGNED, "signed", 1),
				new Token(TokenType.UNSIGNED, "unsigned", 1),
				new Token(TokenType.GOTO, "goto", 1));
		assertEquals(list, lexer.getTokenList());
		assertFalse(Main.hadError());
	}
	
	@Test
	void literalTest() throws Lexer.LexerException {
		String input = "123 45";
		Lexer lexer = new Lexer(input);
		List<Token> list = Arrays.asList(
				new Token(TokenType.LITERAL, "123", 1),
				new Token(TokenType.LITERAL, "45", 1));
		assertEquals(list, lexer.getTokenList());
		assertFalse(Main.hadError());
	}
	
	@Test
	void symbolicCharacter() throws Lexer.LexerException {
		String input = "( ) {}[];+ - * / % ~ & | ^ ! . -> = += -= "
				+ "*= /= %= &= |= ^= <<= >>=<< >> && || == "
				+ "!= ++ -- < <= > >=";
		Lexer lexer = new Lexer(input);
		List<Token> list = Arrays.asList(
				new Token(TokenType.LPAREN, "(", 1),
				new Token(TokenType.RPAREN, ")", 1),
				new Token(TokenType.LBRACE, "{", 1),
				new Token(TokenType.RBRACE, "}", 1),
				new Token(TokenType.LSQUARE, "[", 1),
				new Token(TokenType.RSQUARE, "]", 1),
				new Token(TokenType.SEMI, ";", 1),
				new Token(TokenType.PLUS, "+", 1),
				new Token(TokenType.MINUS, "-", 1),
				new Token(TokenType.ASTERISK, "*", 1),
				new Token(TokenType.SLASH, "/", 1),
				new Token(TokenType.PERCENT, "%", 1),
				new Token(TokenType.TILDE, "~", 1),
				new Token(TokenType.AMPERSAND, "&", 1),
				new Token(TokenType.PIPE, "|", 1),
				new Token(TokenType.CARET, "^", 1),
				new Token(TokenType.EXCLAIM, "!", 1),
				new Token(TokenType.PERIOD, ".", 1),
				new Token(TokenType.MINUSGREATER, "->", 1),
				new Token(TokenType.EQUAL, "=", 1),
				new Token(TokenType.PLUSEQUAL, "+=", 1),
				new Token(TokenType.MINUSEQUAL, "-=", 1),
				new Token(TokenType.ASTERISKEQUAL, "*=", 1),
				new Token(TokenType.SLASHEQUAL, "/=", 1),
				new Token(TokenType.PERCENTEQUAL, "%=", 1),
				new Token(TokenType.AMPERSANDEQUAL, "&=", 1),
				new Token(TokenType.PIPEEQUAL, "|=", 1),
				new Token(TokenType.CARETEQUAL, "^=", 1),
				new Token(TokenType.LESSLESSEQUAL, "<<=", 1),
				new Token(TokenType.GREATERGREATEREQUAL, ">>=", 1),
				new Token(TokenType.LESSLESS, "<<", 1),
				new Token(TokenType.GREATERGREATER, ">>", 1),
				new Token(TokenType.AMPERSANDAMPERSAND, "&&", 1),
				new Token(TokenType.PIPEPIPE, "||", 1),
				new Token(TokenType.EQUALEQUAL, "==", 1),
				new Token(TokenType.EXCLAIMEQUAL, "!=", 1),
				new Token(TokenType.PLUSPLUS, "++", 1),
				new Token(TokenType.MINUSMINUS, "--", 1),
				new Token(TokenType.LESS, "<", 1),
				new Token(TokenType.LESSEQUAL, "<=", 1),
				new Token(TokenType.GREATER, ">", 1),
				new Token(TokenType.GREATEREQUAL, ">=", 1));
		assertEquals(list, lexer.getTokenList());
		assertFalse(Main.hadError());
	}

	@Test
	void errorTest() {
		String input = "test\\123";
		Lexer lexer = new Lexer(input);
		

		List<Token> list = Arrays.asList(
				new Token(TokenType.IDENTIFIER, "test", 1),
				new Token(TokenType.LITERAL, "123", 1));
		assertEquals(list, lexer.getTokenList()); 
		assertTrue(Main.hadError());
	}

}