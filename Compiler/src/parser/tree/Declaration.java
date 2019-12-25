package parser.tree;

public abstract class Declaration {
	private final String name;
	
	public Declaration(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
