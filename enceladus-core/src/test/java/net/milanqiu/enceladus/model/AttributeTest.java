package net.milanqiu.enceladus.model;

import net.milanqiu.mimas.junit.AssertExt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Creation Date: 2018-09-13
 * @author Milan Qiu
 */
public class AttributeTest {

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model();
        model.newEntity("e1");
        model.getEntity("e1").newAttribute("a1");
        model.getEntity("e1").newAttribute("a2");
        model.newEntity("e2");
        model.getEntity("e2").newAttribute("a1");
        model.getEntity("e2").newAttribute("a3");
    }

    @Test
    public void test_setName() throws Exception {
        Attribute attribute = model.getEntity("e1").getAttribute("a2");
        attribute.setName("a4");
        Assert.assertEquals("a4", attribute.getName());
        attribute.setName("a3");
        Assert.assertEquals("a3", attribute.getName());
        attribute.setName("a3");
        Assert.assertEquals("a3", attribute.getName());

        AssertExt.assertExceptionThrown(() -> attribute.setName("A1"),
                IllegalArgumentException.class, "attribute name A1 already exists");

        AssertExt.assertExceptionThrown(() -> attribute.setName("e1id"),
                IllegalArgumentException.class, "attribute name e1Id is reserved");
        AssertExt.assertExceptionThrown(() -> attribute.setName("e1name"),
                IllegalArgumentException.class, "attribute name e1Name is reserved");
        AssertExt.assertExceptionThrown(() -> attribute.setName("parentid"),
                IllegalArgumentException.class, "attribute name ParentId is reserved");
        AssertExt.assertExceptionThrown(() -> attribute.setName("treelevel"),
                IllegalArgumentException.class, "attribute name TreeLevel is reserved");
        AssertExt.assertExceptionThrown(() -> attribute.setName("ordinal"),
                IllegalArgumentException.class, "attribute name Ordinal is reserved");
    }

    @Test
    public void test_inSameModel() throws Exception {
        // boolean inSameModel(Entity entity)
        Assert.assertTrue(model.getEntity("e1").getAttribute("a1").inSameModel(model.getEntity("e2")));
        Assert.assertFalse(model.getEntity("e1").getAttribute("a1").inSameModel(new Model().newEntity("e1")));

        // boolean inSameModel(Attribute attribute)
        Assert.assertTrue(model.getEntity("e1").getAttribute("a1").inSameModel(model.getEntity("e2").getAttribute("a3")));
        Assert.assertFalse(model.getEntity("e1").getAttribute("a1").inSameModel(new Model().newEntity("e1").newAttribute("a1")));
    }

    @Test
    public void test_inSameEntity() throws Exception {
        Assert.assertTrue(model.getEntity("e1").getAttribute("a1").inSameEntity(model.getEntity("e1").getAttribute("a2")));
        Assert.assertFalse(model.getEntity("e1").getAttribute("a1").inSameEntity(model.getEntity("e2").getAttribute("a1")));
    }
}
