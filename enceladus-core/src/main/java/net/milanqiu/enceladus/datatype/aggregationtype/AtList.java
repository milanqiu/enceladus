package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class AtList extends AggregationType {

    private DataType elementType;

    public DataType getElementType() {
        return elementType;
    }

    public AtList(DataType elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtList atList = (AtList) o;

        return !(elementType != null ? !elementType.equals(atList.elementType) : atList.elementType != null);
    }

    @Override
    public int hashCode() {
        return elementType != null ? elementType.hashCode() : 0;
    }
}
