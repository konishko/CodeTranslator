package lexer;

import token.Token;
import baseReader.BaseReader;
import java.util.ArrayList;

public class Lexer
{
    private ArrayList<BaseReader> readers = new ArrayList<BaseReader>();

    public void register(BaseReader reader){
        readers.add(reader);
    }

    public Token[] tokenize(String s)
    {
        ArrayList<Token> result = new ArrayList<Token>();
        while(s.length() != 0) {
            Token potentialToken = null;
            for (int i = 0; i < readers.size(); i++) {
                potentialToken = readers.get(i).tryReadToken(s);
                if (potentialToken != null){
                    break;
                }
            }
            if (potentialToken != null) {
                result.add(potentialToken);
                s = s.substring(potentialToken.getText().length());
            }
        }
        return result.toArray(new Token[result.size()]);
    }
}
