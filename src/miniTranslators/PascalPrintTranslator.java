package miniTranslators;

import baseMiniTranslator.BaseMiniTranslator;
import java.util.HashMap;
import java.util.Map;
import token.Token;

public class PascalPrintTranslator extends BaseMiniTranslator {
    public PascalPrintTranslator(){
        super();
        this.setType("print");
        this.setLangName("pascal");
    }

    public Token toPseudo(Token token){
        String value = (String)token.getValue();

        String[] parsedValue = value.split(" ");
        Map<String, String> pseudoTokenValue = new HashMap<String, String>();

        pseudoTokenValue.put("print text", parsedValue[0]);

        Token pseudoToken = new Token("pseudo_print", token.getText(), pseudoTokenValue);
        return pseudoToken;
    }

    public Token fromPseudo(Token token){
        Map<String, String> value = (Map<String, String>)token.getValue();

        String tokenValue = String.format("print(%)", value.get("print text"));
        String tokenText = String.format("%;", tokenValue);

        Token javaToken = new Token(this.getType(), tokenText, tokenValue);
        return javaToken;
    }
}
