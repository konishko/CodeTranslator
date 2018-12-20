package baseLanguage;

import baseMiniTranslator.BaseMiniTranslator;

import java.util.ArrayList;

abstract public class BaseLanguage {
    private String name;
    private ArrayList<BaseMiniTranslator> miniTranslators = new ArrayList<BaseMiniTranslator>();

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void register(BaseMiniTranslator miniTranslator){
        if(miniTranslator.getType().startsWith(this.name))
            miniTranslators.add(miniTranslator);
    }
}
