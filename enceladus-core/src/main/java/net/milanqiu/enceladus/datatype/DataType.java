package net.milanqiu.enceladus.datatype;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return true;
    }
}
