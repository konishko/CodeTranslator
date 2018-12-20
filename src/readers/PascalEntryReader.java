package readers;

import baseReader.BaseReader;
import lexer.Lexer;
import token.Token;

public class PascalEntryReader extends BaseReader {
    public PascalEntryReader()
    {
        super();
        this.setStates(2);
        this.setState(0);
        this.setType("pascal_entry");
    }

    protected Token correctType(String string){
        return new Token(this.getType(), string);
    }

    public Token tryReadToken(String string){
        String potentialToken = string;
        Token[] childTokens = new Token[0];
        int tokenLength = 0;
        String initialCheck = "begin";
        while(this.getState() != this.getStates()) {
            if (potentialToken.startsWith(initialCheck)) {
                potentialToken = potentialToken.substring(initialCheck.length());
                tokenLength += initialCheck.length();
                this.setState(1);
                continue;
            } else if (this.getState() == 1) {
                if (potentialToken.startsWith("end.")) {
                    potentialToken = potentialToken.substring(4);
                    tokenLength += 4;
                    this.setState(2);

                    String childString = potentialToken.substring(5, tokenLength - 4);
                    potentialToken = potentialToken.substring(tokenLength);

                    Lexer childLexer = new Lexer();
                    childLexer.register(new JavaForReader());
                    childLexer.register(new JavaAssignmentReader());
                    childLexer.register(new JavaNewVarReader());
                    childLexer.register(new JavaPrintReader());

                    childTokens = childLexer.tokenize(childString);

                    continue;
                } else
                    this.setState(1);
            } else return null;

            tokenLength++;
            potentialToken = potentialToken.substring(1);
        }

        String result = string.substring(0, tokenLength);
        Token token = this.correctType(result);
        token.setChilds(childTokens);
        return token;
    }
}
