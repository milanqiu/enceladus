package net.milanqiu.enceladus.datatype.basictype;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class BtMoney extends BtDomainDecimal {

    public BtMoney(int precision, int scale) {
        setPrecision(precision);
        setScale(scale);
    }
}
