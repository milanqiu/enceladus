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
}
