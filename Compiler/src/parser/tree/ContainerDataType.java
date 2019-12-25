package parser.tree;

import java.util.List;

import lexer.TokenType;

public class ContainerDataType extends DataType {

	private final String name;
	private final TokenType type;
	
	public ContainerDataType(String name, TokenType type, List<TokenType> qualifiers) {
		super(qualifiers);
		this.type = type;
		this.name = name;
	}
	
	
}
