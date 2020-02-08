package net.milanqiu.enceladus.datatype;

import net.milanqiu.enceladus.datatype.aggregationtype.AtBundle;
import net.milanqiu.enceladus.datatype.aggregationtype.AtObject;
import net.milanqiu.enceladus.datatype.basictype.*;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtMoneyBillion;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtPercentMillion;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString20;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString50;
import net.milanqiu.enceladus.datatype.collectiontype.CtArray;
import net.milanqiu.enceladus.datatype.collectiontype.CtList;
import net.milanqiu.enceladus.datatype.collectiontype.CtMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * <p>
 * Creation Date: 2018-10-04
 * @author Milan Qiu
 */
public class BaseTest {

    @Test
    public void test_equals() throws Exception {
        Assert.assertEquals(new BtInt32Id(), new BtInt32Id());
        Assert.assertEquals(new BtAutoIncInt32Id(), new BtAutoIncInt32Id());
        Assert.assertEquals(new BtUuidId(), new BtUuidId());
        Assert.assertEquals(new BtBit(), new BtBit());
        Assert.assertEquals(new BtFloat32(), new BtFloat32());
        Assert.assertEquals(new BtDateTime(), new BtDateTime());
        Assert.assertEquals(new BtText64K(), new BtText64K());
        Assert.assertEquals(new BtBlob64K(), new BtBlob64K());
        Assert.assertEquals(new BtUuid(), new BtUuid());

        Assert.assertEquals(new BtStringId(20), new BtStringId(20));
        Assert.assertNotEquals(new BtStringId(20), new BtStringId(30));

        Assert.assertEquals(new BtDecimal(10, 2), new BtDecimal(10, 2));
        Assert.assertNotEquals(new BtDecimal(10, 2), new BtDecimal(10, 4));
        Assert.assertNotEquals(new BtDecimal(10, 2), new BtDecimal(12, 2));

        Assert.assertEquals(new BtPercent(10, 2), new BtPercent(10, 2));
        Assert.assertNotEquals(new BtPercent(10, 2), new BtPercent(10, 4));
        Assert.assertNotEquals(new BtPercent(10, 2), new BtPercent(12, 2));
        Assert.assertEquals(new BtPercent(10, 4), new BtPercentMillion());

        Assert.assertEquals(new BtMoney(10, 2), new BtMoney(10, 2));
        Assert.assertNotEquals(new BtMoney(10, 2), new BtMoney(10, 4));
        Assert.assertNotEquals(new BtMoney(10, 2), new BtMoney(12, 2));
        Assert.assertEquals(new BtMoney(11, 2), new BtMoneyBillion());

        Assert.assertEquals(new BtString(20), new BtString(20));
        Assert.assertNotEquals(new BtString(20), new BtString(30));
        Assert.assertEquals(new BtString(20), new BtString20());

        Assert.assertEquals(new BtBinary(20), new BtBinary(20));
        Assert.assertNotEquals(new BtBinary(20), new BtBinary(30));

        LinkedHashMap<String, DataType> propertiesA = new LinkedHashMap<>();
        propertiesA.put("1", new BtString(20));
        propertiesA.put("2", new BtInt32());
        LinkedHashMap<String, DataType> propertiesB = new LinkedHashMap<>();
        propertiesB.put("1", new BtString20());
        propertiesB.put("2", new BtInt32());
        Assert.assertEquals(new AtBundle(propertiesA), new AtBundle(propertiesB));
        Assert.assertEquals(new AtObject(propertiesA), new AtObject(propertiesB));
        propertiesB.put("1", new BtString50());
        Assert.assertNotEquals(new AtBundle(propertiesA), new AtBundle(propertiesB));
        Assert.assertNotEquals(new AtObject(propertiesA), new AtObject(propertiesB));
        propertiesB.put("3", new BtString20());
        Assert.assertNotEquals(new AtBundle(propertiesA), new AtBundle(propertiesB));
        Assert.assertNotEquals(new AtObject(propertiesA), new AtObject(propertiesB));

        Assert.assertEquals(new CtArray(new BtString(20)), new CtArray(new BtString20()));
        Assert.assertEquals(new CtList(new BtString(20)), new CtList(new BtString20()));
        Assert.assertEquals(new CtMap(new BtString(20), new BtInt32()), new CtMap(new BtString20(), new BtInt32()));

        Assert.assertNotEquals(new BtInt32Id(), new BtInt32());
        Assert.assertNotEquals(new BtDecimal(10, 2), new BtPercent(10, 2));
        Assert.assertNotEquals(new AtBundle(propertiesA), new AtObject(propertiesA));
        Assert.assertNotEquals(new CtArray(new BtInt32()), new CtList(new BtInt32()));
    }
}
