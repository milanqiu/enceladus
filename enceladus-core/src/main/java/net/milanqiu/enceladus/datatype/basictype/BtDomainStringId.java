package net.milanqiu.enceladus.datatype.basictype;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public abstract class BtDomainStringId extends BtDomainId {

    private int maxLength;

    public int getMaxLength() {
        return maxLength;
    }
    protected void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BtDomainStringId that = (BtDomainStringId) o;

        return maxLength == that.maxLength;
    }

    @Override
    public int hashCode() {
        return maxLength;
    }
}
