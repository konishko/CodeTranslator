package baseReader;

import token.Token;

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

    public String getType(){ return this.type; }

    protected abstract Token correctType(String string);

    public abstract Token tryReadToken(String input);
}
