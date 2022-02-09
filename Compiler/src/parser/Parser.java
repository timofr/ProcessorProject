package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import lexer.Token;
import lexer.TokenType;
import main.Main;
import parser.tree.DataType;
import parser.tree.Declaration;
import parser.tree.ParseTree;
import parser.tree.Statement;
import parser.tree.TypedefDataType;
import parser.tree.Declaration.ContainerDeclaration;
import parser.tree.Declaration.TypedefDeclaration;

public class Parser {

	private final List<Supplier<Declaration>> globalDeclarationParser = Arrays.asList(this::parseTypedefDeclaration, this::parseGlobalStorageSpecifierWithSpecifiedDeclaration);
	private final List<Supplier<Declaration>> localDeclarationParser = Arrays.asList(this::parseTypedefDeclaration);
	private final List<Supplier<DataType>> dataTypeParser = Arrays.asList();
	private final List<Token> tokenList;
	private final List<Declaration> declarations = new ArrayList<>();
	private final List<TypedefDeclaration> typedefDeclarations = new ArrayList<>();
	private final List<ContainerDeclaration> containerDeclaration = new ArrayList<>();


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
		Optional<Declaration> declaration = globalDeclarationParser.stream().map(p -> p.get()).filter(d -> d != null).findAny();
		return declaration.isPresent() ? declaration.get() : null;
	}

	private TypedefDeclaration parseTypedefDeclaration()  {
		if (!match(TokenType.TYPEDEF)) {
			return null;
		}

		consume();

		DataType dt = parseDataType();
		if (dt == null) {
			error("Expected data type after typedef.");
		}

		consume();
		if (!match(TokenType.IDENTIFIER)) {
			error("Expected identifier after data type for a complete typedef.");
		}

		String name = peekContent();
		if (typedefDeclarations.stream().noneMatch(t -> t.getName().equals(name))) {
			error("Typedef for " + name + " already declared.");
		}
		consume();
		TypedefDeclaration typedefDeclaration = new TypedefDeclaration(name);
		typedefDeclaration.add(new TypedefDataType(typedefDeclaration, dt));
		return typedefDeclaration;
	}

	private Declaration parseGlobalStorageSpecifierWithSpecifiedDeclaration() {
		return parseStorageSpecifierWithSpecifiedDeclaration(false);
	}

	private Declaration parseLocalStorageSpecifierWithSpecifiedDeclaration() {
		return parseStorageSpecifierWithSpecifiedDeclaration(true);
	}

	private Declaration parseStorageSpecifierWithSpecifiedDeclaration(boolean local) {
		Declaration.STORAGE_SPECIFIER storageSpecifier = null;
		if (match(TokenType.STORAGE_SPECIFIERS)) {
			TokenType tokenType = peekType();
			if (TokenType.isStorageSpecifier(tokenType)) {
				storageSpecifier = storageSpecifierFromTokenType(tokenType);
			} else {

			}
			consume();
		}
		TokenType typeQualifier;
		if (match(TokenType.TYPE_QUALIFIERS)) {
			typeQualifier = peekType();
			consume();
		}
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

		Optional<TypedefDeclaration> typdefDataType = dataTypes.stream()
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
	private Statement parseStatement() {
		// TODO implement this
		return null;
	}
	private List<TokenType> parseKeywordsFromSet(TokenType[] keywordSet, ) {
		List<TokenType> keywords = new ArrayList<TokenType>();
		while (Arrays.stream(keywordSet).anyMatch(t -> t == peek().getType())) {
			keywords.add(peek().getType());
			consume();
		}
		return keywords;
	}

	






























	private Declaration.STORAGE_SPECIFIER storageSpecifierFromTokenType(TokenType type) {
		switch (type) {
			case AUTO:
				return AUTO;
			case REGISTER:
				return REGISTER;
			case EXTERN:
				return EXTERN;
			case: STATIC:
				return STATIC;
			default:
				throw IllegalArgumentException("Method expects only AUTO, REGISTER, EXTERN or STATIC as TokenType. All other type cannot be converted");
		}
	} 






	
	private Token consume() {
		if (!isAtEnd()) {
			position++;
		}
		return peek();
	}

	private Token consumeNpoop() {
		Token t;
		consume();
		return t;
	}

	private Token checkAndConsume(String errorMsg, TokenType... types) {
		check(types, errorMsg);
		return consume();
	}
	private boolean isAtEnd() {
		return match(TokenType.EOF);
	}

	private Token peek() {
		return tokenList.get(position);
	}

	private TokenType peekType() {
		return peek().getType();
	}

	private String peekContent() {
		return peek().getContent();
	}

	private Token previous() {
		return tokenList.get(position - 1);
	}

	private int getCurrentLine() {
		return peek().getLine();
	}

	private boolean match(TokenType type) {
		return peek().getType() == type;
	} 

	private boolean match(TokenType... types) {
		return Arrays.stream(types).anyMatch(t -> t == peek().getType());
	}

	private void check(TokenType... types) throws ParseException {
		if (!match(types)) {
			throw error(types.toString());
		}
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