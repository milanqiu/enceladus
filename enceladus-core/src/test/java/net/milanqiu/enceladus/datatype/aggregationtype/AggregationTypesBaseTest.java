package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;
import net.milanqiu.enceladus.datatype.basictype.BtInt32;
import net.milanqiu.enceladus.datatype.basictype.BtString;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString20;
import net.milanqiu.enceladus.datatype.collectiontype.CtMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * Creation Date: 2020-02-22
 * @author Milan Qiu
 */
public class AggregationTypesBaseTest {

    private LinkedHashMap<String, DataType> propertiesA;
    private LinkedHashMap<String, DataType> propertiesB;
    private LinkedHashMap<String, DataType> propertiesC;
    private LinkedHashMap<String, DataType> propertiesD;
    private LinkedHashMap<String, DataType> propertiesBA;
    private LinkedHashMap<String, DataType> propertiesBB;
    private LinkedHashMap<String, DataType> propertiesBC;
    private LinkedHashMap<String, DataType> propertiesBD;
    private LinkedHashMap<String, DataType> propertiesOA;
    private LinkedHashMap<String, DataType> propertiesOB;
    private LinkedHashMap<String, DataType> propertiesOC;
    private LinkedHashMap<String, DataType> propertiesOD;
    private LinkedHashMap<String, DataType> propertiesComplicated;

    @Before
    public void setUp() throws Exception {
        propertiesA = new LinkedHashMap<>();
        propertiesA.put("p1", new BtString(20));
        propertiesA.put("p2", new BtInt32());

        propertiesB = new LinkedHashMap<>();
        propertiesB.put("p1", new BtString20());
        propertiesB.put("p2", new BtInt32());

        propertiesC = new LinkedHashMap<>();
        propertiesC.put("p1", new BtString(30));
        propertiesC.put("p2", new BtInt32());

        propertiesD = new LinkedHashMap<>();
        propertiesD.put("p1", new BtString(20));
        propertiesD.put("p2", new BtInt32());
        propertiesD.put("p3", new BtInt32());

        propertiesBA = new LinkedHashMap<>();
        propertiesBA.put("nested", new AtBundle(propertiesA));

        propertiesBB = new LinkedHashMap<>();
        propertiesBB.put("nested", new AtBundle(propertiesB));

        propertiesBC = new LinkedHashMap<>();
        propertiesBC.put("nested", new AtBundle(propertiesC));

        propertiesBD = new LinkedHashMap<>();
        propertiesBD.put("nested", new AtBundle(propertiesD));

        propertiesOA = new LinkedHashMap<>();
        propertiesOA.put("nested", new AtObject(propertiesA));

        propertiesOB = new LinkedHashMap<>();
        propertiesOB.put("nested", new AtObject(propertiesB));

        propertiesOC = new LinkedHashMap<>();
        propertiesOC.put("nested", new AtObject(propertiesC));

        propertiesOD = new LinkedHashMap<>();
        propertiesOD.put("nested", new AtObject(propertiesD));

        propertiesComplicated = new LinkedHashMap<>();
        propertiesComplicated.put("nested1", new AtObject(propertiesBA));
        propertiesComplicated.put("nested2", new AtBundle(propertiesOB));
        propertiesComplicated.put("nested3", new CtMap(new AtObject(propertiesBA), new AtBundle(propertiesOB)));
    }

    @Test
    public void test_equals() throws Exception {
        // AtBundle
        Assert.assertEquals(new AtBundle(propertiesA), new AtBundle(propertiesB));
        Assert.assertNotEquals(new AtBundle(propertiesA), new AtBundle(propertiesC));
        Assert.assertNotEquals(new AtBundle(propertiesA), new AtBundle(propertiesD));

        Assert.assertEquals(new AtBundle(propertiesBA), new AtBundle(propertiesBB));
        Assert.assertNotEquals(new AtBundle(propertiesBA), new AtBundle(propertiesBC));
        Assert.assertNotEquals(new AtBundle(propertiesBA), new AtBundle(propertiesBD));
        Assert.assertNotEquals(new AtBundle(propertiesBA), new AtBundle(propertiesOA));

        // AtObject
        Assert.assertEquals(new AtObject(propertiesA), new AtObject(propertiesB));
        Assert.assertNotEquals(new AtObject(propertiesA), new AtObject(propertiesC));
        Assert.assertNotEquals(new AtObject(propertiesA), new AtObject(propertiesD));

        Assert.assertEquals(new AtObject(propertiesOA), new AtObject(propertiesOB));
        Assert.assertNotEquals(new AtObject(propertiesOA), new AtObject(propertiesOC));
        Assert.assertNotEquals(new AtObject(propertiesOA), new AtObject(propertiesOD));
        Assert.assertNotEquals(new AtObject(propertiesOA), new AtObject(propertiesBA));

        // misc
        Assert.assertNotEquals(new AtBundle(propertiesA), new AtObject(propertiesA));
        Assert.assertNotEquals(new AtBundle(propertiesBA), new AtObject(propertiesBA));
        Assert.assertNotEquals(new AtBundle(propertiesOA), new AtObject(propertiesOA));
        Assert.assertNotEquals(new AtObject(propertiesA), new AtBundle(propertiesA));
        Assert.assertNotEquals(new AtObject(propertiesBA), new AtBundle(propertiesBA));
        Assert.assertNotEquals(new AtObject(propertiesOA), new AtBundle(propertiesOA));
    }

    @Test
    public void test_toString() throws Exception {
        // AtBundle
        Assert.assertEquals("AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})", new AtBundle(propertiesA).toString());
        Assert.assertEquals("AtBundle({\"nested\":AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})})", new AtBundle(propertiesBA).toString());

        // AtObject
        Assert.assertEquals("AtObject({\"p1\":BtString(20),\"p2\":BtInt32})", new AtObject(propertiesA).toString());
        Assert.assertEquals("AtObject({\"nested\":AtObject({\"p1\":BtString(20),\"p2\":BtInt32})})", new AtObject(propertiesOA).toString());

        // misc
        Assert.assertEquals("AtObject({\"nested1\":AtObject({\"nested\":AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})}),\"nested2\":AtBundle({\"nested\":AtObject({\"p1\":BtString20,\"p2\":BtInt32})}),\"nested3\":CtMap(AtObject({\"nested\":AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})}),AtBundle({\"nested\":AtObject({\"p1\":BtString20,\"p2\":BtInt32})}))})",
                new AtObject(propertiesComplicated).toString());
    }

    @Test
    public void test_fromString() throws Exception {
        // AtBundle
        Assert.assertEquals(new AtBundle(propertiesA), DataType.fromString("AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})"));
        Assert.assertEquals(new AtBundle(propertiesBA), DataType.fromString("AtBundle({\"nested\":AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})})"));

        // AtObject
        Assert.assertEquals(new AtObject(propertiesA), DataType.fromString("AtObject({\"p1\":BtString(20),\"p2\":BtInt32})"));
        Assert.assertEquals(new AtObject(propertiesOA), DataType.fromString("AtObject({\"nested\":AtObject({\"p1\":BtString(20),\"p2\":BtInt32})})"));

        // misc
        Assert.assertEquals(new AtObject(propertiesComplicated),
                DataType.fromString("AtObject({\"nested1\":AtObject({\"nested\":AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})}),\"nested2\":AtBundle({\"nested\":AtObject({\"p1\":BtString20,\"p2\":BtInt32})}),\"nested3\":CtMap(AtObject({\"nested\":AtBundle({\"p1\":BtString(20),\"p2\":BtInt32})}),AtBundle({\"nested\":AtObject({\"p1\":BtString20,\"p2\":BtInt32})}))})"));

        // skip spaces and tabs
        Assert.assertEquals(new AtBundle(propertiesA), DataType.fromString("AtBundle\t({\"p1\":BtString(20),   \"p2\":BtInt32})"));
    }
}
