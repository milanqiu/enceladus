package net.milanqiu.enceladus.datatype;

import net.milanqiu.enceladus.datatype.basictype.BtInt32;
import net.milanqiu.mimas.junit.AssertExt;
import org.junit.Assert;
import org.junit.Test;

/**
 * Creation Date: 2018-10-20
 * @author Milan Qiu
 */
public class DataTypeRegistryTest {

    @Test
    public void test_getDataTypeClazz() throws Exception {
        Assert.assertEquals(DataType.class, DataTypeRegistry.getSingleton().getDataTypeClazz("DataType"));
        Assert.assertEquals(BtInt32.class, DataTypeRegistry.getSingleton().getDataTypeClazz("BtInt32"));

        AssertExt.assertExceptionThrown(() -> DataTypeRegistry.getSingleton().getDataTypeClazz("Int32"),
                IllegalArgumentException.class, "data type name not found: Int32");
    }
}
