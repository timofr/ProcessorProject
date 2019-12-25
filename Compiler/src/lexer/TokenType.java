package lexer;

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
	
	STATIC("static"),
	
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
	
	
	public static final TokenType[] QUALIFIERS = {STATIC};
	public static final TokenType[] MODIFIERS = {};
	
	public static final TokenType[] BUILDINDATATYPE = {INT};
	
	public static final TokenType[] CONTAINERDATATYPE = {STRUCT, UNION};
	
	
	TokenType(String spelling) {
		this.spelling = spelling;
	}
	
	public String getSpelling() {
		return this.spelling;
	}
}
