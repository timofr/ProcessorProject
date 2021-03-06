package lexer;

public class Token {
	
	private final TokenType type;
	private final String content;
	private final int line;
	
	
	public Token(TokenType type, String content, int line) {
		this.type = type;
		this.content = content;
		this.line = line;
	}
	
	public TokenType getType() {
		return this.type;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public int getLine() {
		return this.line;
	}

	@Override
	public String toString() {
		return "Token [type=" + this.type + ", content=" + this.content + ", line=" + this.line + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + line;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Token)) {
			return false;
		}
		Token other = (Token) obj;
		if (this.content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!this.content.equals(other.content)) {
			return false;
		}
		if (this.line != other.line) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}


	
	
}
