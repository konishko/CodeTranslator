package baseReader;

import token.Token;

abstract public class BaseReader
{
    private int state;
    private int states;
    private String type;
    private boolean collectingValue = false;

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

    public void setCollectingValue(boolean collectingValue){
        this.collectingValue = collectingValue;
    }

    public int getState(){
        return this.state;
    }

    public int getStates(){
        return this.states;
    }

    public String getType(){ return this.type; }

    public boolean getCollectingValue(){
        return this.collectingValue;
    }

    protected Token correctType(String text, String value){
        return new Token(this.getType(), text, value);
    }

    abstract public Token tryReadToken(String input);
}
