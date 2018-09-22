package net.milanqiu.enceladus.datatype.basictype;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public abstract class BtDomainDecimal extends BtDomainNumber {

    private int precision;
    private int scale;

    public int getPrecision() {
        return precision;
    }
    protected void setPrecision(int precision) {
        this.precision = precision;
    }
    public int getScale() {
        return scale;
    }
    protected void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BtDomainDecimal that = (BtDomainDecimal) o;

        return precision == that.precision && scale == that.scale;
    }

    @Override
    public int hashCode() {
        int result = precision;
        result = 31 * result + scale;
        return result;
    }
}
