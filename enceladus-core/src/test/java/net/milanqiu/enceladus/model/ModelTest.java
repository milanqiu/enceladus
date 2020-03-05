package net.milanqiu.enceladus.model;

import net.milanqiu.mimas.junit.AssertExt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Creation Date: 2018-09-13
 * @author Milan Qiu
 */
public class ModelTest {

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model();
        model.newEntity("e1");
        model.findEntity("e1").newAttribute("a1");
        model.findEntity("e1").newAttribute("a2");
        model.newEntity("e2");
        model.findEntity("e2").newAttribute("a1");
        model.findEntity("e2").newAttribute("a3");
    }

    @Test
    public void test_findEntity() throws Exception {
        Assert.assertEquals(model.getEntities().get(0), model.findEntity("e1"));
        Assert.assertEquals(model.getEntities().get(1), model.findEntity("e2"));
        Assert.assertEquals(model.getEntities().get(1), model.findEntity("E2"));
        Assert.assertEquals(null, model.findEntity("e3"));
    }

    @Test
    public void test_hasEntity() throws Exception {
        Assert.assertTrue(model.hasEntity("e1"));
        Assert.assertTrue(model.hasEntity("e2"));
        Assert.assertTrue(model.hasEntity("E2"));
        Assert.assertFalse(model.hasEntity("e3"));
    }

    @Test
    public void test_newEntity() throws Exception {
        Assert.assertEquals(2, model.getEntities().size());

        Entity entity = model.newEntity("e3");
        Assert.assertEquals(3,      model.getEntities().size());
        Assert.assertEquals(entity, model.getEntities().get(2));
        Assert.assertEquals(model,  model.getEntities().get(2).getOwner());
        Assert.assertEquals("e3",   model.getEntities().get(2).getName());
        Assert.assertEquals("",     model.getEntities().get(2).getDescription());
        Assert.assertEquals(0,      model.getEntities().get(2).getAttributes().size());

        AssertExt.assertExceptionThrown(() -> model.newEntity("E1"),
                IllegalArgumentException.class, "entity name E1 already exists");
    }
}
