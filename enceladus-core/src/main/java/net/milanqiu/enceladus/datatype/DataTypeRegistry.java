package net.milanqiu.enceladus.datatype;

import com.google.common.base.Preconditions;
import net.milanqiu.enceladus.datatype.aggregationtype.AggregationType;
import net.milanqiu.enceladus.datatype.aggregationtype.AtBundle;
import net.milanqiu.enceladus.datatype.aggregationtype.AtObject;
import net.milanqiu.enceladus.datatype.basictype.*;
import net.milanqiu.enceladus.datatype.basictype.specialized.*;
import net.milanqiu.enceladus.datatype.collectiontype.CollectionType;
import net.milanqiu.enceladus.datatype.collectiontype.CtArray;
import net.milanqiu.enceladus.datatype.collectiontype.CtList;
import net.milanqiu.enceladus.datatype.collectiontype.CtMap;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Creation Date: 2018-10-19
 * @author Milan Qiu
 */
public class DataTypeRegistry {

    private static DataTypeRegistry singleton;

    public static DataTypeRegistry getSingleton() {
        if (singleton == null) {
            singleton = new DataTypeRegistry();
        }
        return singleton;
    }

    private final Map<String, Class<? extends DataType>> DATA_TYPES = new HashMap<>();

    private void registerDataType(Class<? extends DataType> dataTypeClazz) {
        DATA_TYPES.put(dataTypeClazz.getSimpleName(), dataTypeClazz);
    }

    private void registerDataTypes() {
        registerDataType(DataType.class);

        registerDataType(BasicType.class);
        registerDataType(AggregationType.class);
        registerDataType(CollectionType.class);

        registerDataType(BtDomainId.class);
        registerDataType(BtDomainIntId.class);
        registerDataType(BtInt32Id.class);
        registerDataType(BtInt64Id.class);
        registerDataType(BtDomainAutoIncId.class);
        registerDataType(BtAutoIncInt32Id.class);
        registerDataType(BtAutoIncInt64Id.class);
        registerDataType(BtDomainStringId.class);
        registerDataType(BtStringId.class);
        registerDataType(BtFixedStringId.class);
        registerDataType(BtDomainStandardId.class);
        registerDataType(BtUuidId.class);

        registerDataType(BtDomainNumber.class);
        registerDataType(BtDomainInt.class);
        registerDataType(BtBit.class);
        registerDataType(BtInt8.class);
        registerDataType(BtInt16.class);
        registerDataType(BtInt32.class);
        registerDataType(BtInt64.class);
        registerDataType(BtUInt8.class);
        registerDataType(BtUInt16.class);
        registerDataType(BtUInt32.class);
        registerDataType(BtDomainFloat.class);
        registerDataType(BtFloat32.class);
        registerDataType(BtFloat64.class);
        registerDataType(BtDomainDecimal.class);
        registerDataType(BtDecimal.class);
        registerDataType(BtPercent.class);
        registerDataType(BtPercentMillion.class);
        registerDataType(BtPercentTrillion.class);
        registerDataType(BtMoney.class);
        registerDataType(BtMoneyBillion.class);
        registerDataType(BtMoneyQuadrillion.class);

        registerDataType(BtDomainDateTime.class);
        registerDataType(BtDomainCalendar.class);
        registerDataType(BtDateTime.class);
        registerDataType(BtDate.class);
        registerDataType(BtTime.class);

        registerDataType(BtDomainChar.class);
        registerDataType(BtDomainString.class);
        registerDataType(BtString.class);
        registerDataType(BtString20.class);
        registerDataType(BtString50.class);
        registerDataType(BtString200.class);
        registerDataType(BtFixedString.class);
        registerDataType(BtDomainText.class);
        registerDataType(BtText64K.class);
        registerDataType(BtText4G.class);

        registerDataType(BtDomainByte.class);
        registerDataType(BtDomainBinary.class);
        registerDataType(BtBinary.class);
        registerDataType(BtFixedBinary.class);
        registerDataType(BtDomainBlob.class);
        registerDataType(BtBlob64K.class);
        registerDataType(BtBlob4G.class);
        registerDataType(BtDomainStandard.class);
        registerDataType(BtUuid.class);

        registerDataType(AtBundle.class);
        registerDataType(AtObject.class);

        registerDataType(CtArray.class);
        registerDataType(CtList.class);
        registerDataType(CtMap.class);
    }

    private DataTypeRegistry() {
        registerDataTypes();
    }

    public Class<? extends DataType> getDataType(String name) {
        Class<? extends DataType> result = DATA_TYPES.get(name);
        Preconditions.checkArgument(result != null, "data type name not found: %s", name);
        return result;
    }
}
