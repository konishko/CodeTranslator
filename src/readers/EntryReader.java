package readers;

import baseReader.BaseReader;
import token.Token;

public class EntryReader extends BaseReader {
    public EntryReader()
    {
        super();
        this.setStates(2);
        this.setState(0);
        this.setType("er");
    }

    protected Token correctType(String string){
        return new Token("er", string);
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        int tokenLength = 0;
        String initialCheck = "public static void main(String[] args)";
        while(this.getState() != this.getStates()){
            if(potentialToken.startsWith(initialCheck)) {
                potentialToken.substring(initialCheck.length());
                tokenLength += initialCheck.length();
                this.setState(1);
                continue;
            }

            if(this.getState() == 1 & potentialToken.startsWith("{")) {
                potentialToken = potentialToken.substring(1);
                this.readChild(potentialToken);
                this.setState(2);
            }

            else if(string.length() != 0)
                return null;

            tokenLength++;
            potentialToken = potentialToken.substring(1);
        }
        this.setState(0);
        String result = string.substring(0, tokenLength);
        return this.correctType(result);
    }
}
