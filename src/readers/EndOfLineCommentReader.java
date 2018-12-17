package readers;

import baseReader.BaseReader;
import token.Token;

public class EndOfLineCommentReader extends BaseReader{
    public EndOfLineCommentReader(){
        super();
        this.requiredPassedStates = 2;
        this.setType("eofc");
    }
    protected boolean isTokenStarted(String input){
        return input.startsWith("//");
    }

    protected boolean isTokenOnFirstBreakingPoint(String input){
        char symbol = input.charAt(0);
        this.passedStates++;
        return symbol == '/';
    }

    protected boolean isTokenActive(String input){
        return true;
    }

    protected boolean isTokenOnSecondBreakingPoint(String input){
        return true;
    }

    protected boolean isTokenFinishing(String input){
        return true;
    }

    protected  boolean isTokenEnded(String input){
        char symbol = input.charAt(0);
        this.passedStates++;
        return symbol == '\n';
    }

    protected Token correctType(String string){
        return new Token("eolc", string);
    }
}

