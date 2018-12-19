package translator;

import baseLanguage.BaseLanguage;
import token.Token;
import baseReader.BaseReader;
import java.util.ArrayList;

public class Translator {
    private ArrayList<BaseLanguage> languages = new ArrayList<BaseLanguage>();

    public void register(BaseLanguage lang){
        languages.add(lang);
    }

    public Token[] translate(String initialLang, String targetLang, String source){

    }
}
