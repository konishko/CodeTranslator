package baseReader;

import token.Token;
import java.util.ArrayList;

abstract public class BaseReader
{
    private int state;
    private int states;
    private String type;

    public void setType(String type){
        this.type = type;
    }

    public void setStates(int statesCount){
        this.states = statesCount;
    }

    public void setState(int state){
        if(state <= this.states)
            this.state = state;
    }

    public int getState(){
        return this.state;
    }

    public int getStates(){
        return this.states;
    }


    protected abstract Token correctType(String string);

    public abstract Token tryReadToken(String input);

//    public Token tryReadToken(String input){
//        if (this.detected(input)) {
//            this.state = states[1];
//            int tokenLength = 0;
//            String potentialToken = input + "";
//            while (!ended(potentialToken)) {
//                if(isTokenStarted(potentialToken))
//                    this.state = states[1];
//
//                if(isTokenOnFirstBreakingPoint(potentialToken))
//                    this.state = states[2];
//
//                else if(isTokenActive(potentialToken) && this.state == states[2])
//                    this.state = states[3];
//
//                else if(isTokenOnSecondBreakingPoint(potentialToken))
//                    this.state = states[4];
//
//                else if (isTokenFinishing(potentialToken) && this.state == states[4])
//                    this.state = states[5];
//
//                else if (isTokenEnded(potentialToken))
//                    this.state = states[6];
//
//                else if (passedStates == requiredPassedStates)
//                    break;
//
//                else
//                    return null;
//
//                tokenLength++;
//                potentialToken = potentialToken.substring(1);
//            }
//            this.state = states[0];
//            passedStates = 0;
//            String result = input.substring(0, tokenLength);
//            return this.correctType(result);
//        }
//        return null;
//    }

}
