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
    protected boolean equalsCustom(Object o) {
        CtArray that = (CtArray) o;
        return Objects.equals(elementType, that.elementType);
    }

    @Override
    public int hashCode() {
        return elementType != null ? elementType.hashCode() : 0;
    }

    @Override
    protected String toStringCustom() {
        return getClass().getSimpleName() + "(" + elementType + ")";
    }
}
