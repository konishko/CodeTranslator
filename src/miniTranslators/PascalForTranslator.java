package miniTranslators;

import baseMiniTranslator.BaseMiniTranslator;
import java.util.HashMap;
import java.util.Map;
import token.Token;

public class PascalForTranslator extends BaseMiniTranslator {
    public PascalForTranslator(){
        super();
        this.setType("pascal_for");
    }

    public Token toPseudo(Token token){
        String value = (String)token.getValue();

        String[] parsedValue = value.split(" |:=|to|do");
        Map<String, String> pseudoTokenValue = new HashMap<String, String>();

        pseudoTokenValue.put("iterable type", parsedValue[0]);
        pseudoTokenValue.put("iterable", parsedValue[1]);
        pseudoTokenValue.put("iterable init value", parsedValue[3]);
        pseudoTokenValue.put("iterable limit", parsedValue[5]);

        Token pseudoToken = new Token("pseudo_for", token.getText(), pseudoTokenValue);
        return pseudoToken;
    }

    public Token fromPseudo(Token token){
        Map<String, String> value = (Map<String, String>)token.getValue();

        String tokenValue = String.format("1$ 2$ := 3$ to $4 do",
                value.get("iterable type"),
                value.get("iterable"),
                value.get("iterable init value"),
                value.get("iterable limit"));
        String tokenText = String.format("for %;", tokenValue);

        Token javaToken = new Token(this.getType(), tokenText, tokenValue);
        return javaToken;
    }
}
