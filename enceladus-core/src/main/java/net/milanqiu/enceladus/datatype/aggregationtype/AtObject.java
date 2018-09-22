package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class AtObject extends AggregationType {

    private LinkedHashMap<String, DataType> properties;

    public Map<String, DataType> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public AtObject(LinkedHashMap<String, DataType> properties) {
        this.properties = new LinkedHashMap<>(properties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtObject atObject = (AtObject) o;

        return !(properties != null ? !properties.equals(atObject.properties) : atObject.properties != null);
    }

    @Override
    public int hashCode() {
        return properties != null ? properties.hashCode() : 0;
    }
}
