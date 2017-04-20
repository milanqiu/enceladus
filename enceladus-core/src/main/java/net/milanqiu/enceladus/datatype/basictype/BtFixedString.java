package net.milanqiu.enceladus.datatype.basictype;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class BtFixedString extends BtDomainString {

    public BtFixedString(int maxLength) {
        setMaxLength(maxLength);
    }
}
