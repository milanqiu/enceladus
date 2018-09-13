package net.milanqiu.enceladus.model;

import net.milanqiu.enceladus.datatype.basictype.BtInt32;
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
public class EntityTest {

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
        Entity entity = model.getEntities().get(0);
        entity.setName("e3");
        Assert.assertEquals("e3", entity.getName());
        entity.setName("e3");
        Assert.assertEquals("e3", entity.getName());

        try {
            entity.setName("e2");
            DebugUtils.neverGoesHere();
        } catch (Exception e) {
            AssertExt.assertClassification(IllegalArgumentException.class, e);
            Assert.assertEquals("entity name e2 already exists", e.getMessage());
        }
    }

    @Test
    public void test_indexOfAttribute() throws Exception {
        Entity entity = model.getEntities().get(0);
        Assert.assertEquals(0, entity.indexOfAttribute("a1"));
        Assert.assertEquals(1, entity.indexOfAttribute("a2"));
        Assert.assertEquals(-1, entity.indexOfAttribute("a3"));
    }

    @Test
    public void test_findAttribute() throws Exception {
        Entity entity = model.getEntities().get(0);
        Assert.assertEquals(entity.getAttributes().get(0), entity.findAttribute("a1"));
        Assert.assertEquals(entity.getAttributes().get(1), entity.findAttribute("a2"));
        Assert.assertEquals(null, entity.findAttribute("a3"));
    }

    @Test
    public void test_newAttribute() throws Exception {
        Entity entity = model.getEntities().get(0);
        Assert.assertEquals(2, entity.getAttributes().size());

        Attribute attribute = entity.newAttribute("a3");
        Assert.assertEquals(3, entity.getAttributes().size());
        Assert.assertEquals(attribute, entity.getAttributes().get(2));
        Assert.assertEquals(entity, entity.getAttributes().get(2).getOwner());
        Assert.assertEquals("a3", entity.getAttributes().get(2).getName());
        Assert.assertEquals(new BtInt32(), entity.getAttributes().get(2).getType());
        Assert.assertEquals(true, entity.getAttributes().get(2).isNullable());
        Assert.assertEquals("", entity.getAttributes().get(2).getDescription());

        try {
            entity.newAttribute("a1");
            DebugUtils.neverGoesHere();
        } catch (Exception e) {
            AssertExt.assertClassification(IllegalArgumentException.class, e);
            Assert.assertEquals("attribute name a1 already exists", e.getMessage());
        }
    }
}
