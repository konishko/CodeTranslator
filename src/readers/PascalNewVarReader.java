package readers;

import baseReader.BaseReader;
import token.Token;

public class PascalNewVarReader extends BaseReader {
    public PascalNewVarReader(){
        super();
        this.setStates(4);
        this.setState(0);
        this.setType("pascal_new_var");
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        Token[] childTokens = new Token[0];
        int tokenLength = 0;
        String value = "";
        while(this.getState() != this.getStates()){
            if(this.getState() == 0 & Character.isLetterOrDigit(potentialToken.charAt(0))) {
                this.setCollectingValue(true);
                this.setState(1);
            }

            else if(this.getState() == 1) {
                if(Character.isLetterOrDigit(potentialToken.charAt(0)))
                    this.setState(1);

                else if(Character.isWhitespace(potentialToken.charAt(0))) {
                    this.setState(2);
                }
            }

            else if(this.getState() == 2 & Character.isLetterOrDigit(potentialToken.charAt(0))){
                this.setState(3);
            }

            else if(this.getState() == 3) {
                if(Character.isLetterOrDigit(potentialToken.charAt(0)))
                    this.setState(3);

                else if(potentialToken.startsWith(" := ")) {
                    potentialToken = potentialToken.substring(3);
                    tokenLength += 3;
                    this.setState(3);
                    value += " := ";
                    continue;
                }
                else if(potentialToken.charAt(0) == ';') {
                    this.setCollectingValue(false);
                    this.setState(4);
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
        Token token = this.correctType(result, value);
        token.setChilds(childTokens);
        return token;
    }
}
