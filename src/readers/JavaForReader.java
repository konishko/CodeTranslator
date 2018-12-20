package readers;

import baseReader.BaseReader;
import lexer.Lexer;
import token.Token;

public class JavaForReader extends BaseReader {
    public JavaForReader()
    {
        super();
        this.setStates(4);
        this.setState(0);
        this.setType("java_for");
    }

    protected Token correctType(String string){
        return new Token(this.getType(), string);
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        Token[] childTokens = new Token[0];
        int tokenLength = 0;
        String initialCheck = "for";
        while(this.getState() != this.getStates()){
            if(potentialToken.startsWith(initialCheck)) {
                potentialToken = potentialToken.substring(initialCheck.length());
                tokenLength += initialCheck.length();
                this.setState(1);
                continue;
            }

            if(this.getState() == 1 & potentialToken.startsWith("(")) {
                this.setState(2);
            }

            if(this.getState() == 2 & potentialToken.startsWith(")")){
                this.setState(3);
            }

            if(this.getState() == 3 & potentialToken.startsWith("{")) {
                int countOfClosingBrackets = 1;
                int childLength = 1;
                String copyOfToken = potentialToken.substring(1);

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

                this.setState(4);
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

