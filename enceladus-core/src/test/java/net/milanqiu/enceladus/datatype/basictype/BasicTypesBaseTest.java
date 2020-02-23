package net.milanqiu.enceladus.datatype.basictype;

import net.milanqiu.enceladus.datatype.basictype.specialized.BtMoneyBillion;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtPercentMillion;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString20;
import org.junit.Assert;
import org.junit.Test;

/**
 * Creation Date: 2020-02-22
 * @author Milan Qiu
 */
public class BasicTypesBaseTest {

    @Test
    public void test_equals() throws Exception {
        // BtDomainId descendants
        Assert.assertEquals(new BtInt32Id(), new BtInt32Id());
        Assert.assertEquals(new BtAutoIncInt32Id(), new BtAutoIncInt32Id());
        Assert.assertEquals(new BtStringId(20), new BtStringId(20));
        Assert.assertNotEquals(new BtStringId(20), new BtStringId(30));
        Assert.assertEquals(new BtUuidId(), new BtUuidId());

        // BtDomainNumber descendants
        Assert.assertEquals(new BtBit(), new BtBit());
        Assert.assertEquals(new BtInt32(), new BtInt32());
        Assert.assertEquals(new BtFloat32(), new BtFloat32());

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

        // BtDomainDateTime descendants
        Assert.assertEquals(new BtDateTime(), new BtDateTime());

        // BtDomainChar descendants
        Assert.assertEquals(new BtString(20), new BtString(20));
        Assert.assertNotEquals(new BtString(20), new BtString(30));
        Assert.assertEquals(new BtString(20), new BtString20());
        Assert.assertEquals(new BtText64K(), new BtText64K());

        // BtDomainByte descendants
        Assert.assertEquals(new BtBinary(20), new BtBinary(20));
        Assert.assertNotEquals(new BtBinary(20), new BtBinary(30));
        Assert.assertEquals(new BtBlob64K(), new BtBlob64K());
        Assert.assertEquals(new BtUuid(), new BtUuid());

        // misc
        Assert.assertNotEquals(new BtInt32Id(), new BtInt32());
        Assert.assertNotEquals(new BtDecimal(10, 2), new BtPercent(10, 2));
    }

    @Test
    public void test_toString() throws Exception {
        // BtDomainId descendants
        Assert.assertEquals("BtInt32Id", new BtInt32Id().toString());
        Assert.assertEquals("BtAutoIncInt32Id", new BtAutoIncInt32Id().toString());
        Assert.assertEquals("BtStringId(20)", new BtStringId(20).toString());
        Assert.assertEquals("BtUuidId", new BtUuidId().toString());

        // BtDomainNumber descendants
        Assert.assertEquals("BtBit", new BtBit().toString());
        Assert.assertEquals("BtInt32", new BtInt32().toString());
        Assert.assertEquals("BtFloat32", new BtFloat32().toString());
        Assert.assertEquals("BtDecimal(10,2)", new BtDecimal(10, 2).toString());
        Assert.assertEquals("BtPercent(10,2)", new BtPercent(10, 2).toString());
        Assert.assertEquals("BtPercentMillion", new BtPercentMillion().toString());
        Assert.assertEquals("BtMoney(10,2)", new BtMoney(10, 2).toString());
        Assert.assertEquals("BtMoneyBillion", new BtMoneyBillion().toString());

        // BtDomainDateTime descendants
        Assert.assertEquals("BtDateTime", new BtDateTime().toString());

        // BtDomainChar descendants
        Assert.assertEquals("BtString(20)", new BtString(20).toString());
        Assert.assertEquals("BtString20", new BtString20().toString());
        Assert.assertEquals("BtText64K", new BtText64K().toString());

        // BtDomainByte descendants
        Assert.assertEquals("BtBinary(20)", new BtBinary(20).toString());
        Assert.assertEquals("BtBlob64K", new BtBlob64K().toString());
        Assert.assertEquals("BtUuid", new BtUuid().toString());
    }
}
