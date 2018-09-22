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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtArray atArray = (AtArray) o;

        return !(elementType != null ? !elementType.equals(atArray.elementType) : atArray.elementType != null);
    }

    @Override
    public int hashCode() {
        return elementType != null ? elementType.hashCode() : 0;
    }
}
