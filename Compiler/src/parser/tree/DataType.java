package parser.tree;

import java.util.ArrayList;
import java.util.List;

import lexer.TokenType;

public abstract class DataType {

	public enum TYPE_QUALIFIER {
		NONE, CONST, VOLATILE;
	}


	private final String name;
	private final TYPE_QUALIFIER typeQualifier;

	public DataType(String name, TYPE_QUALIFIER typeQualifier) {
		this.name = name;
		this.typeQualifier = typeQualifier;
	}

	public String getName() {
		return name;
	}

	public static class BuildinDataType {

		public enum TYPE_SIGN_MODIFIER {
			SIGNED, UNSIGNED;
		}

		public enum TYPE_SIZE_MODIFIER {
			SHORT, LONG;
		}

		public BuildinDataType(String name, TYPE_QUALIFIER typeQualifier, TYPE_SIGN_MODIFIER typeSignModifier, List<TYPE_SIZE_MODIFIER> typeSizeModifiers) {
			super(name, typeQualifier);
			this.typeSignModifier = typeSignModifier;
			this.typeSizeModifiers = typeSizeModifiers;
		}
	}

	public static class ContainerDataType extends DataType {

		private final List<VariableDeclaration> variables;

		public ContainerDataType(String name, List<TYPE_QUALIFIER> qualifiers) {
			super(name, qualifiers);
			variables = null;
		}

		public ContainerDataType(String name, List<TYPE_QUALIFIER> qualifiers, List<VariableDeclaration> variables) {
			super(name, qualifiers);
			this.variables = variables;
		}


		public boolean isDefined() {
			return variables != null;
		}
	}
}
