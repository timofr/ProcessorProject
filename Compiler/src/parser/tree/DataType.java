package parser.tree;

import java.util.List;

import lexer.TokenType;

public abstract class DataType {
	
	private final List<TokenType> qualifiers;
	
	public DataType(List<TokenType> qualifiers) {
		this.qualifiers = qualifiers;
	}
	
	
}
