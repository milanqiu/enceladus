package net.milanqiu.enceladus.antlr;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * A subclass of {@link ParserRuleContext} that stores which alternative for a rule was matched.
 * <p>
 * Creation Date: 2020-03-22
 * @author Milan Qiu
 */
public class AltNumberStoredParserRuleContext extends ParserRuleContext {

    public AltNumberStoredParserRuleContext(ParserRuleContext parent, int invokingStateNumber) {
        super(parent, invokingStateNumber);
    }

    protected int altNumber = 0;

    @Override
    public int getAltNumber() {
        return altNumber;
    }
    @Override
    public void setAltNumber(int altNumber) {
        this.altNumber = altNumber;
    }
}
