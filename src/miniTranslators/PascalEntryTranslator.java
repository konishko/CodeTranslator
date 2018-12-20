package miniTranslators;

import baseMiniTranslator.BaseMiniTranslator;
import java.util.HashMap;
import java.util.Map;
import token.Token;

public class PascalEntryTranslator extends BaseMiniTranslator {
    public PascalEntryTranslator(){
        super();
        this.setType("pascal_entry");
    }

    public Token toPseudo(Token token){
        String value = (String)token.getValue();

        Map<String, String> pseudoTokenValue = new HashMap<String, String>();

        pseudoTokenValue.put("entry", value);

        Token pseudoToken = new Token("pseudo_entry", token.getText(), pseudoTokenValue);
        return pseudoToken;
    }

    public Token fromPseudo(Token token){
        Map<String, String> value = (Map<String, String>)token.getValue();

        String tokenValue = "begin end.";

        Token javaToken = new Token(this.getType(), tokenValue);
        return javaToken;
    }
}
