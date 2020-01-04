package lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import main.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Lexer {
	public static final char EOF = (char) -1;
	private static final char[] GRAPHICALCHARACTERS = 
		{'!', '%', '&', '(', ')', '*', '+', '-', '.', '/', ';', '<', '=', '>', '[', ']', '^', '{', '|', '}', '~'};
	
	private final Map<Predicate<Character>, TokenSupplier> handlers = new HashMap<Predicate<Character>, TokenSupplier>();
	
	private static final CharMatcher LETTERMATCHER = CharMatcher.createCharMatcher().rangeCharMatcher('a', 'z').rangeCharMatcher('A', 'Z');
	private static final CharMatcher GRAPHICALCHARACTERMATCHER = CharMatcher.createCharMatcher().multipleCharMatcher(Lexer.GRAPHICALCHARACTERS);
	private static final CharMatcher NUMBERCHARACTERMATCHER = CharMatcher.createCharMatcher().rangeCharMatcher('0', '9');
	
	private String content;
	private int pos = -1;
	private char currentChar;
	private int line = 1;
	
	public Lexer(String content) {
		this.content = content;
		initialize();
	}
	
	
	private void initialize() {
		handlers.put(c -> CharMatcher.createCharMatcher().multipleCharMatcher(' ', '\t', '\n').match(c),
				this::handleWhiteSpace);
		handlers.put(LETTERMATCHER::match, this::handleLetters);
		handlers.put(GRAPHICALCHARACTERMATCHER::match, this::handleGraphicalCharacters);
		handlers.put(NUMBERCHARACTERMATCHER::match, this::handleNumbers);
	}
	
	public List<Token> getTokenList() {
		consume();
		List<Token> tokenList = new ArrayList<Token>();
		while (currentChar != Lexer.EOF) {
			Optional<TokenSupplier> handler = handlers.entrySet().stream()
					.filter(entry -> entry.getKey().test(currentChar))
					.map(Entry<Predicate<Character>, TokenSupplier>::getValue)
					.findAny();

			if (!handler.isPresent()) {
				Main.error(line, "Unkown character \"" + currentChar);
			}

			Token token = handler.get().getToken();
			if (token != null) {
				tokenList.add(token);
			}
		}
		return tokenList;
	}
	
	private char consume() {
		if(pos + 1 < content.length()) {
			return currentChar = content.charAt(++pos);
		}
		return currentChar = Lexer.EOF;
	}
	
	private Token handleWhiteSpace() {
		if (currentChar == '\n') {
			++line;
		}
		consume();
		return null;
	}
	
	private Token handleLetters() {
		StringBuilder builder = new StringBuilder(Character.toString(currentChar));
		while (LETTERMATCHER.match(consume()) || NUMBERCHARACTERMATCHER.match(currentChar)) {
			builder.append(currentChar);
		}
		
		String content = builder.toString();
		Optional<TokenType> type = Arrays.stream(TokenType.KEYWORDS).filter(op -> op.getSpelling().equals(content)).findAny();
		if (type.isPresent()) {
			return new Token(type.get(), content, line);
		}
		return new Token(TokenType.IDENTIFIER, content, line);
	}
	
	private Token handleGraphicalCharacters() {
		String currentString = Character.toString(currentChar);
		Optional<TokenType> separatorType = Arrays.stream(TokenType.SEPARATORS).filter(sep -> sep.getSpelling().equals(currentString)).findAny();
		if (separatorType.isPresent()) {
			consume();
			return new Token(separatorType.get(), currentString, line);
		}
		
		String[] content = {currentString};
		while (GRAPHICALCHARACTERMATCHER.match(consume())) {
			if (Arrays.stream(TokenType.OPERATORS).noneMatch(op -> op.getSpelling().startsWith(content[0] + currentChar))) {
				break;
			}
			
			content[0] += currentChar;
		}
		
		Optional<TokenType> operatorType = Arrays.stream(TokenType.OPERATORS).filter(op -> op.getSpelling().equals(content[0])).findAny();
		if (!operatorType.isPresent()) {
			Main.error(line, "Unknown graphical character operator \"" + content[0]);
		}
		return new Token(operatorType.get(), content[0], line);
	}
	
	private Token handleNumbers() {
		StringBuilder builder = new StringBuilder(Character.toString(currentChar));
		while (NUMBERCHARACTERMATCHER.match(consume())) {
			builder.append(currentChar);
		}
		return new Token(TokenType.LITERAL, builder.toString(), line);
	}
	
	@FunctionalInterface
	private interface TokenSupplier {
		public Token getToken();
	}

	static class LexerException extends Exception {
		private static final long serialVersionUID = 2085479406987624432L;
	}
}