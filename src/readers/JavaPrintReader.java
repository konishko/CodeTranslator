package readers;

import baseReader.BaseReader;
import token.Token;

public class JavaPrintReader extends BaseReader {
    public JavaPrintReader()
    {
        super();
        this.setStates(3);
        this.setState(0);
        this.setType("java_print");
    }

    protected Token correctType(String string){
        return new Token(this.getType(), string);
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        Token[] childTokens = new Token[0];
        int tokenLength = 0;
        String value = "";
        String initialCheck = "System.out.println(";
        while(this.getState() != this.getStates()){
            if(potentialToken.startsWith(initialCheck)) {
                potentialToken = potentialToken.substring(initialCheck.length());
                tokenLength += initialCheck.length();
                this.setCollectingValue(true);
                this.setState(1);
                continue;
            }

            else if(this.getState() == 1){
                if(potentialToken.charAt(0) != ')')
                    this.setState(1);
                else
                    this.setState(2);
            }

            else if(this.getState() == 2){
                if(potentialToken.charAt(0) != ';')
                    return null;
                else {
                    this.setCollectingValue(false);
                    this.setState(3);
                }
            }

            else
                return null;

            if(this.getCollectingValue())
                value += potentialToken.charAt(0);

            tokenLength++;
            potentialToken = potentialToken.substring(1);
        }
        this.setState(0);
        this.setCollectingValue(false);
        String result = string.substring(0, tokenLength);
        Token token = this.correctType(result);
        token.setChilds(childTokens);
        return token;
    }
}