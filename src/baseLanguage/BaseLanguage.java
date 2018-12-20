package baseLanguage;

import baseMiniTranslator.BaseMiniTranslator;

import java.util.Map;
import java.util.HashMap;

abstract public class BaseLanguage {
    private String name;
    private Map<String, BaseMiniTranslator> miniTranslators = new HashMap<String, BaseMiniTranslator>();

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void register(BaseMiniTranslator miniTranslator){
        if(miniTranslator.getType().startsWith(this.name))
            miniTranslators.put(miniTranslator.getType(), miniTranslator);
    }
}
