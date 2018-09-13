package net.milanqiu.enceladus.model;

import net.milanqiu.mimas.instrumentation.DebugUtils;
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
        model.getEntities().get(0).newAttribute("a1");
        model.getEntities().get(0).newAttribute("a2");
        model.newEntity("e2");
        model.getEntities().get(1).newAttribute("a1");
        model.getEntities().get(1).newAttribute("a3");
    }

    @Test
    public void test_setName() throws Exception {
        Attribute attribute = model.getEntities().get(0).getAttributes().get(1);
        attribute.setName("a4");
        Assert.assertEquals("a4", attribute.getName());
        attribute.setName("a3");
        Assert.assertEquals("a3", attribute.getName());
        attribute.setName("a3");
        Assert.assertEquals("a3", attribute.getName());

        try {
            attribute.setName("a1");
            DebugUtils.neverGoesHere();
        } catch (Exception e) {
            AssertExt.assertClassification(IllegalArgumentException.class, e);
            Assert.assertEquals("attribute name a1 already exists", e.getMessage());
        }
    }
}
