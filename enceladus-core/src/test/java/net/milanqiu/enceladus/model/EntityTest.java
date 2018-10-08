package net.milanqiu.enceladus.model;

import net.milanqiu.enceladus.datatype.basictype.BtInt32;
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
        model.getEntity("e1").newAttribute("a1");
        model.getEntity("e1").newAttribute("a2");
        model.newEntity("e2");
        model.getEntity("e2").newAttribute("a1");
        model.getEntity("e2").newAttribute("a3");
    }

    @Test
    public void test_setName() throws Exception {
        Entity entity = model.getEntity("e1");
        entity.setName("e3");
        Assert.assertEquals("e3", entity.getName());
        entity.setName("e3");
        Assert.assertEquals("e3", entity.getName());

        AssertExt.assertExceptionThrown(() -> entity.setName("E2"),
                IllegalArgumentException.class, "entity name E2 already exists");

        entity.getAttribute("a1").setName("e4id");
        AssertExt.assertExceptionThrown(() -> entity.setName("e4"),
                IllegalArgumentException.class, "reserved attribute name e4Id already exists");
        entity.getAttribute("e4id").setName("e4name");
        AssertExt.assertExceptionThrown(() -> entity.setName("e4"),
                IllegalArgumentException.class, "reserved attribute name e4Name already exists");
    }

    @Test
    public void test_getAttribute() throws Exception {
        Entity entity = model.getEntity("e1");
        Assert.assertEquals(entity.getAttributes().get(0), entity.getAttribute("a1"));
        Assert.assertEquals(entity.getAttributes().get(1), entity.getAttribute("a2"));
        Assert.assertEquals(entity.getAttributes().get(1), entity.getAttribute("A2"));
        Assert.assertEquals(null, entity.getAttribute("a3"));
    }

    @Test
    public void test_setSequenceTypeByGroup() throws Exception {
        Entity entity = model.getEntity("e1");
        Assert.assertNull(entity.getSequenceGroupAttribute());

        // void setSequenceTypeByGroup(Attribute sequenceGroupAttribute)
        entity.setSequenceTypeByGroup(entity.getAttribute("a1"));
        Assert.assertEquals(entity.getAttribute("a1"), entity.getSequenceGroupAttribute());

        AssertExt.assertExceptionThrown(() -> entity.setSequenceTypeByGroup(model.getEntity("e2").getAttribute("a1")),
                IllegalArgumentException.class, "sequence group attribute should belong to this entity");

        // void setSequenceTypeByGroup(String sequenceGroupAttributeName)
        entity.setSequenceTypeByGroup("A2");
        Assert.assertEquals(entity.getAttribute("a2"), entity.getSequenceGroupAttribute());

        AssertExt.assertExceptionThrown(() -> entity.setSequenceTypeByGroup("a3"),
                IllegalArgumentException.class, "can't find sequence group attribute name: a3");
    }

    @Test
    public void test_newAttribute() throws Exception {
        Entity entity = model.getEntity("e1");
        Assert.assertEquals(2, entity.getAttributes().size());

        Attribute attribute = entity.newAttribute("a3");
        Assert.assertEquals(3, entity.getAttributes().size());
        Assert.assertEquals(attribute, entity.getAttributes().get(2));
        Assert.assertEquals(entity, entity.getAttributes().get(2).getOwner());
        Assert.assertEquals("a3", entity.getAttributes().get(2).getName());
        Assert.assertEquals(new BtInt32(), entity.getAttributes().get(2).getType());
        Assert.assertEquals(true, entity.getAttributes().get(2).isNullable());
        Assert.assertEquals("", entity.getAttributes().get(2).getDescription());

        AssertExt.assertExceptionThrown(() -> { entity.newAttribute("A1"); },
                IllegalArgumentException.class, "attribute name A1 already exists");

        AssertExt.assertExceptionThrown(() -> { entity.newAttribute("e1id"); },
                IllegalArgumentException.class, "attribute name e1Id is reserved");
        AssertExt.assertExceptionThrown(() -> { entity.newAttribute("e1name"); },
                IllegalArgumentException.class, "attribute name e1Name is reserved");
    }
}
