package net.milanqiu.enceladus.datatype.basictype.specialized;

import net.milanqiu.enceladus.datatype.basictype.BtMoney;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class BtMoneyQuadrillion extends BtMoney {

    public BtMoneyQuadrillion(int scale) {
        super(15+scale, scale);
    }

    public BtMoneyQuadrillion() {
        this(2);
    }
}
