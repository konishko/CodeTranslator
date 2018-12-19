package baseLanguage;

import baseMiniTranslator.BaseMiniTranslator;

import java.util.ArrayList;

abstract public class BaseLanguage {
    private String name;
    private ArrayList<BaseMiniTranslator> miniTranslators = new ArrayList<BaseMiniTranslator>();

    public void register(BaseMiniTranslator miniTranslator){
        miniTranslators.add(miniTranslator);
    }
}
