package baseMiniTranslator;

import token.Token;

abstract public class BaseMiniTranslator {
    protected String type;

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    abstract public Token toPseudo(Token token);
    abstract public Token fromPseudo(Token token);
}
