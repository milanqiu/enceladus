package net.milanqiu.enceladus.datatype.collectiontype;

import net.milanqiu.enceladus.datatype.basictype.BtInt32;
import net.milanqiu.enceladus.datatype.basictype.BtInt32Id;
import net.milanqiu.enceladus.datatype.basictype.BtString;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString20;
import org.junit.Assert;
import org.junit.Test;

/**
 * Creation Date: 2020-02-22
 * @author Milan Qiu
 */
public class CollectionTypesBaseTest {

    @Test
    public void test_equals() throws Exception {
        // CtArray
        Assert.assertEquals(new CtArray(new BtString(20)), new CtArray(new BtString20()));
        Assert.assertNotEquals(new CtArray(new BtString(20)), new CtArray(new BtString(30)));

        // CtList
        Assert.assertEquals(new CtList(new BtString(20)), new CtList(new BtString20()));
        Assert.assertNotEquals(new CtList(new BtString(20)), new CtList(new BtString(30)));

        // CtMap
        Assert.assertEquals(new CtMap(new BtString(20), new BtInt32()), new CtMap(new BtString20(), new BtInt32()));
        Assert.assertNotEquals(new CtMap(new BtString(20), new BtInt32()), new CtMap(new BtString(30), new BtInt32()));
        Assert.assertNotEquals(new CtMap(new BtString(20), new BtInt32()), new CtMap(new BtString20(), new BtInt32Id()));

        // misc
        Assert.assertNotEquals(new CtArray(new BtInt32()), new CtList(new BtInt32()));
    }
}
