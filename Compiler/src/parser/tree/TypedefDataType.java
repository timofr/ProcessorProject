package parser.tree;

import java.util.List;

import lexer.TokenType;

public class TypedefDataType extends DataType {

	private final TypedefDeclaration typedefDeclaration;

	public TypedefDataType(TypedefDeclaration typedefDeclaration, DataType dataType) {
		super(dataType.getQualifiers(), dataType.getModifiers());
		this.typedefDeclaration = typedefDeclaration;
	}

	public String getName() {
		return typedefDeclaration.getName();
	}

}
