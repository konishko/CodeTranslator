package token;

import java.util.ArrayList;

public class Token {
	private final Object value;
	private final String text;
	private final String type;
	protected Token[] childs;

	public Token(String type, String text, Object value) {
		super();
		this.type = type;
		this.value = value;
		this.text = text;
	}

	public Token(String type, String text) {
		this(type, text, text);
	}

	public Object getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

	public Token[] getChilds(){
	    return childs;
    }

	public String getText() {
		return text;
	}

	public void setChilds(Token[] childs){
	    this.childs = childs;
    }

	@Override
	public String toString() {
		return type + "[" + text + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Token other = (Token) obj;
		return type.equals(other.type) && value.equals(other.value)
				&& text.equals(other.text);
	}
}