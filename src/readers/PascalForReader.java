package readers;

import baseReader.BaseReader;
import lexer.Lexer;
import token.Token;

public class PascalForReader extends BaseReader {
    public PascalForReader()
    {
        super();
        this.setStates(5);
        this.setState(0);
        this.setType("pascal_for_reader");
    }

    protected Token correctType(String string){
        return new Token("pascal_for_reader", string);
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

            else if(this.getState() == 1 || this.getState() == 2 || this.getState() == 3){
                if(potentialToken.startsWith(":=")){
                    potentialToken = potentialToken.substring(2);
                    tokenLength += 2;
                    this.setState(2);
                    continue;
                }

                else if(potentialToken.startsWith("to")){
                    potentialToken = potentialToken.substring(2);
                    tokenLength += 2;
                    this.setState(3);
                    continue;
                }

                else if(potentialToken.startsWith("do")){
                    potentialToken = potentialToken.substring(2);
                    tokenLength += 2;
                    this.setState(4);
                    continue;
                }

                if(Character.isLetterOrDigit(potentialToken.charAt(0)) || Character.isWhitespace(potentialToken.charAt(0)))
                    continue;

                else return null;
            }

            else if(this.getState() == 4 & potentialToken.startsWith("begin")) {
                int countOfClosingBrackets = 1;
                int childLength = 1;
                String copyOfToken = potentialToken.substring(1);

                while(countOfClosingBrackets != 0){
                    if(copyOfToken.startsWith("begin")) {
                        childLength += 5;
                        copyOfToken = copyOfToken.substring(5);
                        countOfClosingBrackets++;
                        continue;
                    }

                    else if(potentialToken.startsWith("end;")) {
                        childLength += 4;
                        copyOfToken = copyOfToken.substring(4);
                        countOfClosingBrackets--;
                        continue;
                    }

                    childLength++;
                    copyOfToken = copyOfToken.substring(1);
                }
                String childString = potentialToken.substring(5, childLength - 4);
                potentialToken = potentialToken.substring(childLength);

                Lexer childLexer = new Lexer();
                childLexer.register(new JavaEntryReader());
                childLexer.register(new JavaForReader());
                childLexer.register(new JavaAssignmentReader());
                childLexer.register(new JavaNewVarReader());
                childLexer.register(new JavaPrintReader());

                childTokens = childLexer.tokenize(childString);

                this.setState(5);
            }

            else return null;

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

