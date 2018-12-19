package baseMiniTranslator;

import token.Token;

abstract public class BaseMiniTranslator {
    protected String type;

    abstract public Token toPseudo(Token token);
    abstract public Token fromPseudo(Token token);
}
