package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class AtMap extends AggregationType {

    private DataType keyType;
    private DataType valueType;

    public DataType getKeyType() {
        return keyType;
    }
    public DataType getValueType() {
        return valueType;
    }

    public AtMap(DataType keyType, DataType valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }
}
