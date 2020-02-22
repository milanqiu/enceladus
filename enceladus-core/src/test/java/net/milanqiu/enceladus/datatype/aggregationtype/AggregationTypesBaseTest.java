package net.milanqiu.enceladus.datatype.aggregationtype;

import net.milanqiu.enceladus.datatype.DataType;
import net.milanqiu.enceladus.datatype.basictype.BtInt32;
import net.milanqiu.enceladus.datatype.basictype.BtString;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString20;
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

    @Before
    public void setUp() throws Exception {
        propertiesA = new LinkedHashMap<>();
        propertiesA.put("1", new BtString(20));
        propertiesA.put("2", new BtInt32());

        propertiesB = new LinkedHashMap<>();
        propertiesB.put("1", new BtString20());
        propertiesB.put("2", new BtInt32());

        propertiesC = new LinkedHashMap<>();
        propertiesC.put("1", new BtString(30));
        propertiesC.put("2", new BtInt32());

        propertiesD = new LinkedHashMap<>();
        propertiesD.put("1", new BtString(20));
        propertiesD.put("2", new BtInt32());
        propertiesD.put("3", new BtInt32());

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
    }
}
