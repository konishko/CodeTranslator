package baseMiniTranslator;

import token.Token;

abstract public class BaseMiniTranslator {
    protected String type;
    private String langName;

    public void setType(String type){
        this.type = type;
    }

    public void setLangName(String name){
        this.langName = name;
    }

    public String getType(){
        return this.type;
    }

    public String getLangName(){
        return this.langName;
    }

    abstract public Token toPseudo(Token token);
    abstract public Token fromPseudo(Token token);
}
