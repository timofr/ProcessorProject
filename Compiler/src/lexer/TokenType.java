package lexer;

import java.util.Arrays;

public enum TokenType {
	EOF(Character.toString(Lexer.EOF)),
	
	IDENTIFIER(null),
	LITERAL(null),
	
	//KEYWORDS
	INT("int"),
	STRUCT("struct"),
	UNION("union"),
	IF("if"),
	ELSE("else"),
	WHILE("while"),
	FOR("for"),
	BREAK("break"),
	CONTINUE("continue"),
	SIZEOF("sizeof"),
	GOTO("goto"),

	SHORT("short"),
	LONG("long"),
	SIGNED("signed"),
	UNSIGNED("unsigned"),

	EXTERN("extern"),
	STATIC("static"),
	REGISTER("register"),
	AUTO("auto"),

	CONST("const"),
	VOLATILE("volatile"),

	TYPEDEF("typedef"),
	
	//SEPARATOR
	
	RPAREN(")"),
	LPAREN("("),
	RBRACE("}"),
	LBRACE("{"),
	LSQUARE("["),
	RSQUARE("]"),
	SEMI(";"),
	
	//OPERATORS
	
	PLUS("+"),
	MINUS("-"),
	ASTERISK("*"),
	SLASH("/"),
	PERCENT("%"),
	
	TILDE("~"),
	AMPERSAND("&"),
	PIPE("|"),
	CARET("^"),
	EXCLAIM("!"),
	
	PERIOD("."),
	MINUSGREATER("->"),
	
	EQUAL("="),
	PLUSEQUAL("+="),
	MINUSEQUAL("-="),
	ASTERISKEQUAL("*="),
	SLASHEQUAL("/="),
	PERCENTEQUAL("%="),
	AMPERSANDEQUAL("&="),
	PIPEEQUAL("|="),
	CARETEQUAL("^="),
	LESSLESSEQUAL("<<="),
	GREATERGREATEREQUAL(">>="),
	
	LESSLESS("<<"),
	GREATERGREATER(">>"),
	AMPERSANDAMPERSAND("&&"),
	PIPEPIPE("||"),
	EQUALEQUAL("=="),
	EXCLAIMEQUAL("!="),
	PLUSPLUS("++"),
	MINUSMINUS("--"),
	LESS("<"),
	LESSEQUAL("<="),
	GREATER(">"),
	GREATEREQUAL(">=");
	
	private final String spelling;
	
	public static final TokenType[] KEYWORDS = {INT, STRUCT, UNION, IF, ELSE, WHILE, FOR, BREAK, CONTINUE, SIZEOF, STATIC, TYPEDEF};
	public static final TokenType[] OPERATORS = {SIZEOF, PLUS, MINUS, ASTERISK, SLASH, PERCENT, TILDE, AMPERSAND, PIPE, CARET, EXCLAIM, PERIOD, MINUSGREATER,
			EQUAL, PLUSEQUAL, MINUSEQUAL, ASTERISKEQUAL, SLASHEQUAL, PERCENTEQUAL, AMPERSANDEQUAL, PIPEEQUAL, CARETEQUAL, LESSLESSEQUAL, GREATERGREATEREQUAL,
			LESSLESS, GREATERGREATER, AMPERSANDAMPERSAND, PIPEPIPE, EQUALEQUAL, EXCLAIMEQUAL, PLUSPLUS, MINUSMINUS, LESS, LESSEQUAL, GREATER, GREATEREQUAL,
			RPAREN, LPAREN, LSQUARE, RSQUARE};
	public static final TokenType[] SEPARATORS = {LBRACE, RBRACE, SEMI};
	
	public static final TokenType[] STORAGE_SPECIFIERS = {STATIC, EXTERN, REGISTER, AUTO};
	public static final TokenType[] TYPE_QUALIFIERS = {CONST, VOLATILE};
	public static final TokenType[] TYPE_MODIFIERS = {SHORT, LONG, SIGNED, UNSIGNED};
	
	public static final TokenType[] BUILDINDATATYPE = {INT};
	
	public static final TokenType[] CONTAINERDATATYPE = {STRUCT, UNION};
	
	public static boolean isInGroup(TokenType type, TokenType[] group) {
		return Arrays.stream(group).anyMatch(t -> t == type);
	}
	
	public static boolean isKeyword(TokenType type) {
		return isInGroup(type, KEYWORDS);
	}
	
	public static boolean isOperator(TokenType type) {
		return isInGroup(type, OPERATORS);
	}

	public static boolean isSeparator(TokenType type) {
		return isInGroup(type,	SEPARATORS);
	}

	public static boolean isStorageSpecifier(TokenType type) {
		return isInGroup(type, STORAGE_SPECIFIERS);
	}
	
	public static boolean isTypeQualifier(TokenType type) {
		return isInGroup(type, TYPE_QUALIFIERS);
	}

	public static boolean isTypeModifier(TokenType type) {
		return isInGroup(type, TYPE_MODIFIERS);
	}

	public static boolean isBuildinDatatype(TokenType type) {
		return isInGroup(type, BUILDINDATATYPE);
	}

	public static boolean isContainerDatatype(TokenType type) {
		return isInGroup(type, CONTAINERDATATYPE);
	}
	
	TokenType(String spelling) {
		this.spelling = spelling;
	}
	
	public String getSpelling() {
		return this.spelling;
	}
}
