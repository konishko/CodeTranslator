package readers;

import baseReader.BaseReader;
import lexer.Lexer;
import token.Token;

public class JavaEntryReader extends BaseReader {
    public JavaEntryReader()
    {
        super();
        this.setStates(2);
        this.setState(0);
        this.setType("java_entry_");
    }

    protected Token correctType(String string){
        return new Token(this.getType(), string);
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        Token[] childTokens = new Token[0];
        int tokenLength = 0;
        String value = "";
        String initialCheck = "public static void main(String[] args)";
        while(this.getState() != this.getStates()){
            if(potentialToken.startsWith(initialCheck)) {
                value += initialCheck;
                potentialToken = potentialToken.substring(initialCheck.length());
                tokenLength += initialCheck.length();
                this.setState(1);
                continue;
            }

            else if(this.getState() == 1 & potentialToken.startsWith("{")) {
                int countOfClosingBrackets = 1;
                int childLength = 1;
                String copyOfToken = new String(potentialToken.substring(1));
                while(countOfClosingBrackets != 0){
                    if(copyOfToken.charAt(0) == '{')
                        countOfClosingBrackets++;

                    else if(potentialToken.charAt(0) == '}')
                        countOfClosingBrackets--;

                    childLength++;
                    copyOfToken = copyOfToken.substring(1);
                }


                String childString = potentialToken.substring(1, childLength - 1);
                potentialToken = potentialToken.substring(childLength);

                Lexer childLexer = new Lexer();
                childLexer.register(new JavaEntryReader());
                childLexer.register(new JavaForReader());
                childLexer.register(new JavaAssignmentReader());
                childLexer.register(new JavaNewVarReader());
                childLexer.register(new JavaPrintReader());

                childTokens = childLexer.tokenize(childString);

                value += " }";
                this.setState(2);
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
