package lexer;

import java.util.ArrayList;
import java.util.List;

public class CharMatcher {
	private List<InternalAbstractCharMatcher> matchers = new ArrayList<InternalAbstractCharMatcher>();

	private CharMatcher() {}
	
	public static CharMatcher createCharMatcher() {
		return new CharMatcher();
	}
	
	public boolean match(char c) {
		return this.matchers.stream().anyMatch(m -> m.match(c));
	}
	
	public CharMatcher singleCharMatcher(char c) {
		this.matchers.add(new InternalSingleCharMatcher(c));
		return this;
	}
	
	public CharMatcher rangeCharMatcher(char start, char end) {
		this.matchers.add(new InternalRangeCharMatcher(start, end));
		return this;
	}
	
	public CharMatcher multipleCharMatcher(char... cs) {
		this.matchers.add(new InternalMultipleCharMatcher(cs));
		return this;
	}
}

abstract class InternalAbstractCharMatcher {
	public abstract boolean match(char c);
}

class InternalSingleCharMatcher extends InternalAbstractCharMatcher {
	private final char c;
	
	public InternalSingleCharMatcher(char c) {
		this.c = c;
	}
	
	@Override
	public boolean match(char c) {
		return this.c == c;
	}
}

class InternalRangeCharMatcher extends InternalAbstractCharMatcher {
	private final char start;
	private final char end;
	
	public InternalRangeCharMatcher(char start, char end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public boolean match(char c) {
		return this.start <= c && c <= this.end;
	}
}

class InternalMultipleCharMatcher extends InternalAbstractCharMatcher {
	private final char[] cs;
	
	public InternalMultipleCharMatcher(char[] cs) {
		this.cs = cs;
	}
	
	@Override
	public boolean match(char c) {
		for (char i : this.cs) {
			if (i == c) {
				return true;
			}
		}
		return false;
	}
}


