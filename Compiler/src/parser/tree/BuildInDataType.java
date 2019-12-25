package parser.tree;

import java.util.List;

import lexer.TokenType;

public class BuildInDataType extends DataType {

	private final TokenType type;
	
	private final List<TokenType> modifiers;

	public BuildInDataType(TokenType type, List<TokenType> modifiers, List<TokenType> qualifiers) {
		super(qualifiers);
		this.type = type;
		this.modifiers = modifiers;
	}
	
	
	
}
