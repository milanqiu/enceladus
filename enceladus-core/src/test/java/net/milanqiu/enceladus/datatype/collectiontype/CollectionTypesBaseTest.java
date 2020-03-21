package net.milanqiu.enceladus.datatype.collectiontype;

import net.milanqiu.enceladus.datatype.DataType;
import net.milanqiu.enceladus.datatype.aggregationtype.AtBundle;
import net.milanqiu.enceladus.datatype.basictype.*;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString20;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

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
        Assert.assertEquals(new CtArray(new CtArray(new BtString(20))), new CtArray(new CtArray(new BtString20())));
        Assert.assertNotEquals(new CtArray(new CtArray(new BtString(20))), new CtArray(new CtArray(new BtString(30))));

        // CtList
        Assert.assertEquals(new CtList(new BtString(20)), new CtList(new BtString20()));
        Assert.assertNotEquals(new CtList(new BtString(20)), new CtList(new BtString(30)));
        Assert.assertEquals(new CtList(new CtList(new BtString(20))), new CtList(new CtList(new BtString20())));
        Assert.assertNotEquals(new CtList(new CtList(new BtString(20))), new CtList(new CtList(new BtString(30))));

        // CtMap
        Assert.assertEquals(new CtMap(new BtString(20), new BtInt32()),
                            new CtMap(new BtString20(), new BtInt32()));
        Assert.assertNotEquals(new CtMap(new BtString(20), new BtInt32()),
                               new CtMap(new BtString(30), new BtInt32()));
        Assert.assertNotEquals(new CtMap(new BtString(20), new BtInt32()),
                               new CtMap(new BtString20(), new BtInt32Id()));
        Assert.assertEquals(new CtMap(new CtMap(new BtString(20), new BtInt32()), new BtInt32()),
                            new CtMap(new CtMap(new BtString20(), new BtInt32()), new BtInt32()));
        Assert.assertNotEquals(new CtMap(new CtMap(new BtString(20), new BtInt32()), new BtInt32()),
                               new CtMap(new CtMap(new BtString(30), new BtInt32()), new BtInt32()));
        Assert.assertNotEquals(new CtMap(new CtMap(new BtString(20), new BtInt32()), new BtInt32()),
                               new CtMap(new CtMap(new BtString20(), new BtInt32Id()), new BtInt32()));

        // misc
        Assert.assertNotEquals(new CtArray(new BtInt32()), new CtList(new BtInt32()));
        Assert.assertNotEquals(new CtList(new BtInt32()), new CtArray(new BtInt32()));
    }

    @Test
    public void test_toString() throws Exception {
        // CtArray
        Assert.assertEquals("CtArray(BtString(20))", new CtArray(new BtString(20)).toString());
        Assert.assertEquals("CtArray(CtArray(BtString(20)))", new CtArray(new CtArray(new BtString(20))).toString());

        // CtList
        Assert.assertEquals("CtList(BtString(20))", new CtList(new BtString(20)).toString());
        Assert.assertEquals("CtList(CtList(BtString(20)))", new CtList(new CtList(new BtString(20))).toString());

        // CtMap
        Assert.assertEquals("CtMap(BtString(20),BtInt32)", new CtMap(new BtString(20), new BtInt32()).toString());
        Assert.assertEquals("CtMap(CtMap(BtString(20),BtInt32),BtInt32)", new CtMap(new CtMap(new BtString(20), new BtInt32()), new BtInt32()).toString());

        // misc
        LinkedHashMap<String, DataType> properties = new LinkedHashMap<>();
        properties.put("1", new BtInt32());
        properties.put("2", new CtArray(new BtString(20)));
        Assert.assertEquals("CtMap(CtMap(BtString(20),CtList(CtMap(BtDateTime,BtPercent(10,2)))),CtMap(BtInt32,AtBundle(\"1\":BtInt32,\"2\":CtArray(BtString(20)))))",
                new CtMap(new CtMap(new BtString(20), new CtList(new CtMap(new BtDateTime(), new BtPercent(10,2)))),
                          new CtMap(new BtInt32(), new AtBundle(properties)))
                        .toString());
    }
}
