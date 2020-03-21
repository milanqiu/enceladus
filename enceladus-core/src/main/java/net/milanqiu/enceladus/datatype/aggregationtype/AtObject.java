package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;

import java.util.*;

/**
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public class AtObject extends AggregationType {

    protected LinkedHashMap<String, DataType> properties;

    public Map<String, DataType> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public AtObject(LinkedHashMap<String, DataType> properties) {
        this.properties = new LinkedHashMap<>(properties);
    }

    @Override
    protected boolean equalsCustom(Object o) {
        AtObject that = (AtObject) o;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return properties != null ? properties.hashCode() : 0;
    }

    @Override
    protected String toStringCustom() {
        Iterator<Map.Entry<String, DataType>> itr = properties.entrySet().iterator();
        if (itr.hasNext()) {
            StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
            while (itr.hasNext()) {
                Map.Entry<String, DataType> element = itr.next();
                sb.append("\"")
                        .append(element.getKey())
                        .append("\":")
                        .append(element.getValue());
                if (itr.hasNext())
                    sb.append(",");
            }
            sb.append(")");
            return sb.toString();
        } else {
            return getClass().getSimpleName() + "()";
        }
    }
}
