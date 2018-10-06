package net.milanqiu.enceladus.datatype.basictype.specialized;

import net.milanqiu.enceladus.datatype.Specialized;
import net.milanqiu.enceladus.datatype.basictype.BtPercent;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class BtPercentTrillion extends BtPercent implements Specialized {

    public BtPercentTrillion(int scale) {
        super(12+scale, scale);
    }

    public BtPercentTrillion() {
        this(4);
    }
}
