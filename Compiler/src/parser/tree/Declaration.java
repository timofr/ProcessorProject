package parser.tree;

public abstract class Declaration {


	public enum STORAGE_SPECIFIER {
		EXTERN, STATIC, AUTO, REGISTER;
	}

	public static class ContainerDeclaration extends Declaration {

	}


	public static class TypedefDeclaration extends Declaration {

		public TypedefDeclaration(String name) {
			super(name);
		}

}
	public static class FunctionDeclaration extends Declaration {

	}

	public static class VariableDeclaration extends Declaration {

	}



	private final String name;

	
	
	public Declaration(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
