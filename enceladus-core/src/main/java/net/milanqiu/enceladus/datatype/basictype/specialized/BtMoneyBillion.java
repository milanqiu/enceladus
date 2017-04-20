package net.milanqiu.enceladus.datatype.basictype.specialized;

import net.milanqiu.enceladus.datatype.basictype.BtMoney;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class BtMoneyBillion extends BtMoney {

    public BtMoneyBillion(int scale) {
        super(9+scale, scale);
    }

    public BtMoneyBillion() {
        this(2);
    }
}
