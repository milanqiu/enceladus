package net.milanqiu.enceladus.datatype;

import com.google.common.base.Preconditions;
import net.milanqiu.enceladus.antlr.generated.DataTypeLexer;
import net.milanqiu.enceladus.antlr.generated.DataTypeParser;
import net.milanqiu.mimas.instrumentation.DebugUtils;
import net.milanqiu.mimas.instrumentation.exception.CompiletimeException;
import net.milanqiu.mimas.runtime.ReflectionUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <pre><tt>
 * DataType
 *     BasicType
 *         BtDomainId
 *             BtDomainIntId
 *                 BtInt32Id
 *                 BtInt64Id
 *             BtDomainAutoIncId
 *                 BtAutoIncInt32Id
 *                 BtAutoIncInt64Id
 *             BtDomainStringId
 *                 BtStringId
 *                 BtFixedStringId
 *             BtDomainStandardId
 *                 BtUuidId
 *         BtDomainNumber
 *             BtDomainInt
 *                 BtBit
 *                 BtInt8
 *                 BtInt16
 *                 BtInt32
 *                 BtInt64
 *                 BtUInt8
 *                 BtUInt16
 *                 BtUInt32
 *             BtDomainFloat
 *                 BtFloat32
 *                 BtFloat64
 *             BtDomainDecimal
 *                 BtDecimal
 *                 BtPercent
 *                     BtPercentMillion
 *                     BtPercentTrillion
 *                 BtMoney
 *                     BtMoneyBillion
 *                     BtMoneyQuadrillion
 *         BtDomainDateTime
 *             BtDomainCalendar
 *                 BtDateTime
 *                 BtDate
 *                 BtTime
 *         BtDomainChar
 *             BtDomainString
 *                 BtString
 *                     BtString20
 *                     BtString50
 *                     BtString200
 *                 BtFixedString
 *             BtDomainText
 *                 BtText64K
 *                 BtText4G
 *         BtDomainByte
 *             BtDomainBinary
 *                 BtBinary
 *                 BtFixedBinary
 *             BtDomainBlob
 *                 BtBlob64K
 *                 BtBlob4G
 *             BtDomainStandard
 *                 BtUuid
 *     AggregationType
 *         AtBundle
 *         AtObject
 *     CollectionType
 *         CtArray
 *         CtList
 *         CtMap
 * </tt></pre>
 * <p>
 * Creation Date: 2017-04-17
 * @author Milan Qiu
 */
public abstract class DataType {

    protected Class<?> getUnspecializedClass() {
        if (this instanceof Specialized)
            return getClass().getSuperclass();
        else
            return getClass();
    }

    protected boolean equalsCustom(Object o) {
        throw new CompiletimeException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof DataType)) return false;
        if (getUnspecializedClass() != ((DataType)o).getUnspecializedClass()) return false;
        if (ReflectionUtils.hasPublicDefaultConstructor(getUnspecializedClass())) {
            return true;
        } else {
            return equalsCustom(o);
        }
    }

    protected String toStringCustom() {
        throw new CompiletimeException();
    }

    @Override
    public String toString() {
        if (ReflectionUtils.hasPublicDefaultConstructor(getClass())) {
            return getClass().getSimpleName();
        } else {
            return toStringCustom();
        }
    }

    private static DataType fromContext(DataTypeParser.DataTypeContext context) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String name = context.IDENTIFIER().getText();
        Class<? extends DataType> clazz = DataTypeRegistry.getSingleton().getDataTypeClazz(name);
        if (context.paramList() == null) {
            // no param
            return clazz.getConstructor().newInstance();
        } else {
            // has params
            List<Object> params = new ArrayList<>();
            for (DataTypeParser.ParamContext paramContext : context.paramList().param()) {
                switch (paramContext.getAltNumber()) {
                    case 1:
                        // NONNEGATIVE_INTEGER
                        params.add(Integer.parseInt(paramContext.NONNEGATIVE_INTEGER().getText()));
                        break;
                    case 2:
                        // dataType
                        params.add(fromContext(paramContext.dataType()));
                        break;
                    case 3:
                        // '{' propertyList '}'
                        LinkedHashMap<String, DataType> properties = new LinkedHashMap<>();
                        for (DataTypeParser.PropertyContext propertyContext : paramContext.propertyList().property()) {
                            properties.put(propertyContext.IDENTIFIER().getText(), fromContext(propertyContext.dataType()));
                        }
                        params.add(properties);
                        break;
                    default:
                        DebugUtils.neverGoesHere("illegal alt number of param");
                        break;
                }
            }
            Constructor<?> constructor = ReflectionUtils.findConstructorByParamCount(clazz, params.size());
            Preconditions.checkNotNull(constructor, "wrong parameter count");
            return (DataType) constructor.newInstance(params.toArray());
        }
    }

    public static DataType fromString(String s) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ANTLRInputStream ais = new ANTLRInputStream(s);
        DataTypeLexer lexer = new DataTypeLexer(ais);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        DataTypeParser parser = new DataTypeParser(cts);
        DataTypeParser.DataTypeContext context = parser.dataType();
        return fromContext(context);
    }
}
