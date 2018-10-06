package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

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
        switch (precheckEqual(o)) {
            case TRUE:
                return true;
            case FALSE:
                return false;
            default:
                AtBundle that = (AtBundle) o;
                return Objects.equals(properties, that.properties);
        }
    }

    @Override
    public int hashCode() {
        return properties != null ? properties.hashCode() : 0;
    }
}
