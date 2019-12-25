package parser.tree;

import java.util.List;

public class ParseTree {
	private final List<Declaration> declarations;
	
	public ParseTree(List<Declaration> declarations) {
		this.declarations = declarations;
	}
}
