package parser.tree;

import java.util.List;

import lexer.TokenType;

public class TypedefDataType extends DataType {

	private final TypedefDeclaration typedefDeclaration;

	public TypedefDataType(TypedefDeclaration typedefDeclaration, List<TokenType> qualifiers) {
		super(qualifiers);
		this.typedefDeclaration = typedefDeclaration;
	}

}
