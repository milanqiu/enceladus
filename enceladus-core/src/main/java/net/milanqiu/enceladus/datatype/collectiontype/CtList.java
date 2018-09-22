package net.milanqiu.enceladus.datatype.collectiontype;

import net.milanqiu.enceladus.datatype.DataType;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class CtList extends CollectionType {

    private DataType elementType;

    public DataType getElementType() {
        return elementType;
    }

    public CtList(DataType elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CtList ctList = (CtList) o;

        return !(elementType != null ? !elementType.equals(ctList.elementType) : ctList.elementType != null);
    }

    @Override
    public int hashCode() {
        return elementType != null ? elementType.hashCode() : 0;
    }
}
