package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class AtArray extends AggregationType {

    private DataType elementType;

    public DataType getElementType() {
        return elementType;
    }

    public AtArray(DataType elementType) {
        this.elementType = elementType;
    }
}
