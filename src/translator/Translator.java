package translator;

import baseLanguage.BaseLanguage;
import token.Token;
import java.util.HashMap;
import java.util.Map;

public class Translator {
    private Map<String, BaseLanguage> languages = new HashMap<String, BaseLanguage>();

    public void register(BaseLanguage lang){
        languages.put(lang.getName(), lang);
    }

    public Token[] translate(String initialLang, String targetLang, String source){

    }
}
