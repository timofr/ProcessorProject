package parser.tree;

import java.util.List;

import lexer.TokenType;

public class PointerDataType extends DataType {

	private DataType pointerType;

	public PointerDataType(DataType pointerType, List<TokenType> qualifiers) {
		super(qualifiers);
		this.pointerType = pointerType;
	}

}
