package net.milanqiu.enceladus.datatype.collectiontype;

import net.milanqiu.enceladus.datatype.DataType;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class CtArray extends CollectionType {

    private DataType elementType;

    public DataType getElementType() {
        return elementType;
    }

    public CtArray(DataType elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CtArray ctArray = (CtArray) o;

        return !(elementType != null ? !elementType.equals(ctArray.elementType) : ctArray.elementType != null);
    }

    @Override
    public int hashCode() {
        return elementType != null ? elementType.hashCode() : 0;
    }
}
