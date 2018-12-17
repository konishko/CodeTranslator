package readers;

import baseReader.BaseReader;
import token.Token;

public class childReader extends BaseReader
{
    public childReader(){
        super();
        this.setType("child");
    }

    public Token correctType(String string) { return new Token("child")
}
