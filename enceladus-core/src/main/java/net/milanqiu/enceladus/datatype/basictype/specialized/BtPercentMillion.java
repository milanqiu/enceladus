package net.milanqiu.enceladus.datatype.basictype.specialized;

import net.milanqiu.enceladus.datatype.Specialized;
import net.milanqiu.enceladus.datatype.basictype.BtPercent;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class BtPercentMillion extends BtPercent implements Specialized {

    public BtPercentMillion(int scale) {
        super(6+scale, scale);
    }

    public BtPercentMillion() {
        this(4);
    }
}
