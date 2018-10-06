package net.milanqiu.enceladus.datatype.collectiontype;

import net.milanqiu.enceladus.datatype.DataType;

import java.util.Objects;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class CtArray extends CollectionType {

    protected DataType elementType;

    public DataType getElementType() {
        return elementType;
    }

    public CtArray(DataType elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean equals(Object o) {
        switch (precheckEqual(o)) {
            case TRUE:
                return true;
            case FALSE:
                return false;
            default:
                CtArray that = (CtArray) o;
                return Objects.equals(elementType, that.elementType);
        }
    }

    @Override
    public int hashCode() {
        return elementType != null ? elementType.hashCode() : 0;
    }
}
