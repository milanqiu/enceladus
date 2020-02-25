package net.milanqiu.enceladus.datatype.collectiontype;

import net.milanqiu.enceladus.datatype.DataType;

import java.util.Objects;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class CtMap extends CollectionType {

    protected DataType keyType;
    protected DataType valueType;

    public DataType getKeyType() {
        return keyType;
    }
    public DataType getValueType() {
        return valueType;
    }

    public CtMap(DataType keyType, DataType valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    protected boolean equalsCustom(Object o) {
        CtMap that = (CtMap) o;
        return Objects.equals(keyType, that.keyType) && Objects.equals(valueType, that.valueType);
    }

    @Override
    public int hashCode() {
        int result = keyType != null ? keyType.hashCode() : 0;
        result = 31 * result + (valueType != null ? valueType.hashCode() : 0);
        return result;
    }

    @Override
    protected String toStringCustom() {
        return getClass().getSimpleName() + "(" + keyType + "," + valueType + ")";
    }
}
