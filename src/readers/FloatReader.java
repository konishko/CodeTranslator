package readers;

import baseReader.BaseReader;
import token.Token;

public class FloatReader extends BaseReader{
    public FloatReader(){
        super();
        this.requiredPassedStates = 2;
        this.setType("f");
    }
    protected boolean isTokenStarted(String input){
        char symbol = input.charAt(0);
        return Character.isDigit(symbol) || symbol == '.';
    }

    protected boolean isTokenOnFirstBreakingPoint(String input){
        char symbol = input.charAt(0);
        passedStates++;
        return symbol == '.';
    }

    protected boolean isTokenActive(String input){
        return isTokenStarted(input);
    }

    protected boolean isTokenOnSecondBreakingPoint(String input){
        char symbol = input.charAt(0);
        passedStates++;
        return Character.toLowerCase(symbol) == 'e';
    }

    protected boolean isTokenFinishing(String input){
        char symbol = input.charAt(0);
        return Character.isDigit(symbol) || symbol == '+' || symbol == '-';
    }

    protected  boolean isTokenEnded(String input){
        char symbol = input.charAt(0);
        symbol = Character.toLowerCase(symbol);
        passedStates++;
        return symbol == 'f' && symbol == 'd';
    }

    protected Token correctType(String string){
        Float value = new Float(string);
        return new Token("f", string);
    }
}
