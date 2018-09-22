package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Creation Date: 2018-09-22
 * @author Milan Qiu
 */
public class AtBundle extends AggregationType {

    protected LinkedHashMap<String, DataType> properties;

    public Map<String, DataType> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public AtBundle(LinkedHashMap<String, DataType> properties) {
        this.properties = new LinkedHashMap<>(properties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtBundle atBundle = (AtBundle) o;

        return !(properties != null ? !properties.equals(atBundle.properties) : atBundle.properties != null);
    }

    @Override
    public int hashCode() {
        return properties != null ? properties.hashCode() : 0;
    }
}
