package net.milanqiu.enceladus.datatype.basictype;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class BtFixedStringId extends BtDomainStringId {

    public BtFixedStringId(int maxLength) {
        setMaxLength(maxLength);
    }
}
