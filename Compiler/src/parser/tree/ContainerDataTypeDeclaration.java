package parser.tree;

import lexer.TokenType;

public class ContainerDataTypeDeclaration extends Declaration {
	
	TokenType type;
	
	public ContainerDataTypeDeclaration(TokenType type, String name) {
		super(name);
		this.type = type;
	}
	
}
