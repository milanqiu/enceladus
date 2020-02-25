package net.milanqiu.enceladus.datatype.basictype;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public abstract class BtDomainString extends BtDomainChar {

    private int maxLength;

    public int getMaxLength() {
        return maxLength;
    }
    protected void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    protected boolean equalsCustom(Object o) {
        BtDomainString that = (BtDomainString) o;
        return maxLength == that.maxLength;
    }

    @Override
    public int hashCode() {
        return maxLength;
    }

    @Override
    protected String toStringCustom() {
        return getClass().getSimpleName() + "(" + maxLength + ")";
    }
}
