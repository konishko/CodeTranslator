package miniTranslators;

import baseMiniTranslator.BaseMiniTranslator;
import java.util.HashMap;
import java.util.Map;
import token.Token;

public class PascalNewVarTranslator extends BaseMiniTranslator {
    public PascalNewVarTranslator(){
        super();
        this.type = "java_assignment";
    }

    public Token toPseudo(Token token){
        String value = (String)token.getValue();

        String[] parsedValue = value.split(" ");
        Map<String, String> pseudoTokenValue = new HashMap<String, String>();

        pseudoTokenValue.put("var type", parsedValue[0]);
        pseudoTokenValue.put("var name", parsedValue[1]);
        pseudoTokenValue.put("var value", parsedValue[3]);

        Token pseudoToken = new Token("pseudo_assignment", token.getText(), pseudoTokenValue);
        return pseudoToken;
    }

    public Token fromPseudo(Token token){
        Map<String, String> value = (Map<String, String>)token.getValue();

        String tokenValue = String.format("% % = %", value.get("var type"), value.get("var name"), value.get("var value"));
        String tokenText = String.format("%;", tokenValue);

        Token javaToken = new Token("java_assignment", tokenText, tokenValue);
        return javaToken;
    }
}
