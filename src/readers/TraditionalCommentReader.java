package readers;

import baseReader.BaseReader;
import token.Token;

public class TraditionalCommentReader extends BaseReader{
    public TraditionalCommentReader(){
        super();
        this.requiredPassedStates = 3;
        this.setType("tc");
    }
    protected boolean isTokenStarted(String input){
        return input.startsWith("/*");
    }

    protected boolean isTokenOnFirstBreakingPoint(String input){
        char symbol = input.charAt(0);
        this.passedStates++;
        return symbol == '*';
    }

    protected boolean isTokenActive(String input){
        return true;
    }

    protected boolean isTokenOnSecondBreakingPoint(String input){
        this.passedStates++;
        return isTokenOnFirstBreakingPoint(input);
    }

    protected boolean isTokenFinishing(String input){
        return true;
    }

    protected  boolean isTokenEnded(String input) {
        char symbol = input.charAt(0);
        this.passedStates++;
        return symbol == '/';
    }

    protected Token correctType(String string){
        return new Token("tc", string);
    }
}
