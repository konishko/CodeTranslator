package readers;

import baseReader.BaseReader;
import token.Token;

public class PascalPrintReader extends BaseReader {
    public PascalPrintReader()
    {
        super();
        this.setStates(2);
        this.setState(0);
        this.setType("pascal_print_reader");
    }

    protected Token correctType(String string){
        return new Token("pascal_print_reader", string);
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        Token[] childTokens = new Token[0];
        int tokenLength = 0;
        String initialCheck = "print(";
        while(this.getState() != this.getStates()){
            if(potentialToken.startsWith(initialCheck)) {
                potentialToken = potentialToken.substring(initialCheck.length());
                tokenLength += initialCheck.length();
                this.setState(1);
                continue;
            }

            if(this.getState() == 1){
                if(!potentialToken.startsWith(");"))
                    this.setState(1);
                else {
                    potentialToken = potentialToken.substring(2);
                    tokenLength += 2;
                    this.setState(2);
                    continue;
                }
            }

            else
                return null;

            tokenLength++;
            potentialToken = potentialToken.substring(1);
        }
        this.setState(0);
        String result = string.substring(0, tokenLength);
        Token token = this.correctType(result);
        token.setChilds(childTokens);
        return token;
    }
}