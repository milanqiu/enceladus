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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtMap atMap = (AtMap) o;

        if (keyType != null ? !keyType.equals(atMap.keyType) : atMap.keyType != null) return false;
        if (valueType != null ? !valueType.equals(atMap.valueType) : atMap.valueType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = keyType != null ? keyType.hashCode() : 0;
        result = 31 * result + (valueType != null ? valueType.hashCode() : 0);
        return result;
    }
}
