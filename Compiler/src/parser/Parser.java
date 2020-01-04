package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lexer.Token;
import lexer.TokenType;
import main.Main;
import parser.tree.BuildInDataType;
import parser.tree.ContainerDataType;
import parser.tree.DataType;
import parser.tree.Declaration;
import parser.tree.ParseTree;
import parser.tree.Statement;
import parser.tree.TypedefDataType;
import parser.tree.TypedefDeclaration;

public class Parser {
	

	private final List<Token> tokenList;
	private final List<Declaration> declarations = new ArrayList<Declaration>();
	private final List<TypedefDeclaration> typedefDeclarations = new ArrayList<TypedefDeclaration>();

	private int position = 0;

	public Parser(List<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public ParseTree parse() {

		while (peek().getType() != TokenType.EOF) {

		}

		return new ParseTree(declarations);
	}

	private Declaration parseGlobalDeclaration() {
		Declaration declaration;
		DataType dataType = parseDataType();
		if (dataType != null) {

		}

		if ((declaration = parseTypedefDeclaration()) != null) {
			return declaration;
		}

		return null;
	}

	private TypedefDeclaration parseTypedefDeclaration()  {
		if (peek().getType() != TokenType.TYPEDEF) {
			return null;
		}
		
		consume("Expected data type after typedef.");
		
		DataType dataType = parseDataType();
		if (dataType == null) {
			// TODO throw new ParserException("Expected data type after typedef.", getCurrentLine());
		}
		
		consume("Expected identifier after data type for a complete typedef.");
		if (peek().getType() != TokenType.IDENTIFIER) {
			return null;
		}
		
		String name = peek().getContent();
		if (typedefDeclarations.stream().anyMatch(t -> t.getName().equals(name))) {
			//TODO throw new ParserException("", getCurrentLine());
		}
		consume("Expected semicolon to complete typedef declaration.");
		







		return null;
	}

	private DataType parseDataType() {
		List<TokenType> qualifiers = parseQualifier();

		DataType dataType;
		if ((dataType = parseBuildInDataType(qualifiers)) != null) {
			return dataType;
		}

		if ((dataType = parseContainerDataType(qualifiers)) != null) {
			return dataType;
		}

		if ((dataType = parseContainerDataType(qualifiers)) != null) {
			return dataType;
		}

		return null;
	}
	
	private TypedefDataType parseTypedefDataType(List<TokenType> qualifiers) {
		if (peek().getType() != TokenType.IDENTIFIER) {
			return null;
		}

		Optional<TypedefDeclaration> typdefDataType = typedefDeclarations.stream()
				.filter(t -> t.getName().equals(peek().getContent())).findAny();

		if (typdefDataType.isPresent()) {
			consume("Expected identifier for a declaration after data type.");
			return new TypedefDataType(typdefDataType.get(), qualifiers);
		}

		return null;
	}

	private ContainerDataType parseContainerDataType(List<TokenType> qualifiers) {
		Optional<TokenType> containerDataType = Arrays.stream(TokenType.CONTAINERDATATYPE)
				.filter(t -> t == peek().getType()).findAny();

		if (containerDataType.isPresent()) {
			consume("Expected identifier for complete container data type.");
			if (peek().getType() != TokenType.IDENTIFIER) {
				error("Expected identifier for complete container data type.");
			}

			String containerName = peek().getContent();

			consume("Expected identifier for a declaration after data type.");
			return new ContainerDataType(containerName, containerDataType.get(), qualifiers);
		}

		return null;
	}

	private BuildInDataType parseBuildInDataType(List<TokenType> qualifiers) {
		List<TokenType> modifiers = parseModifier();

		Optional<TokenType> buildInDataType = Arrays.stream(TokenType.BUILDINDATATYPE)
				.filter(t -> t == peek().getType()).findAny();
		if (buildInDataType.isPresent()) {
			consume("Expected identifier for a declaration after type.");
			return new BuildInDataType(buildInDataType.get(), modifiers, qualifiers);
		}

		return null;
	}

	private List<TokenType> parseQualifier() {
		return parseKeywordsFromSet(TokenType.QUALIFIERS, "qualifier");
	}

	private List<TokenType> parseModifier() {
		return parseKeywordsFromSet(TokenType.MODIFIERS, "modifier");
	}

	private List<TokenType> parseKeywordsFromSet(TokenType[] keywordSet, String type) {
		List<TokenType> keywords = new ArrayList<TokenType>();
		while (Arrays.stream(keywordSet).anyMatch(t -> t == peek().getType())) {
			keywords.add(peek().getType());
			consume("Expected a data type after a " + type + ".");
		}
		return keywords;
	}

	private Statement parseStatement() {
		// TODO implement this
		return null;
	}

	private Token consume(String msg) {
		if (!isAtEnd()) {
			position++;
		}
		return peek();
	}

	private boolean isAtEnd() {
		return peek().getType() == TokenType.EOF;
	}

	private Token peek() {
		return tokenList.get(position);
	}

	private Token previous() {
		return tokenList.get(position - 1);
	}

	private int getCurrentLine() {
		return peek().getLine();
	}

	private boolean check(TokenType type) {
		if (isAtEnd()) return false;         
		return peek().getType() == type;          
	} 

	private ParseException error(Token token, String message) {
	    Main.error(token, message);
	    return new ParseException();
	}

	private ParseException error(String message) {
		Main.error(peek(), message);
	    return new ParseException();
	}
	
	private static class ParseException extends Exception {}

	private static class ExpressionException extends ParseException {}
}