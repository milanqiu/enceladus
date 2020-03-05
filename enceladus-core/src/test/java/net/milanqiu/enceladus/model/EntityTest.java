package net.milanqiu.enceladus.model;

import net.milanqiu.mimas.junit.AssertExt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Creation Date: 2018-09-13
 * @author Milan Qiu
 */
public class EntityTest {

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
    public void test_setName() throws Exception {
        Entity entity = model.findEntity("e1");
        Assert.assertEquals("e1", entity.getName());
        Assert.assertEquals("e1Id", entity.getIdAttributeName());
        Assert.assertEquals("e1Name", entity.getNameAttributeName());

        entity.setName("e3");
        Assert.assertEquals("e3", entity.getName());
        Assert.assertEquals("e3Id", entity.getIdAttributeName());
        Assert.assertEquals("e3Name", entity.getNameAttributeName());

        entity.setName("E3");
        Assert.assertEquals("e3", entity.getName());
        Assert.assertEquals("e3Id", entity.getIdAttributeName());
        Assert.assertEquals("e3Name", entity.getNameAttributeName());

        AssertExt.assertExceptionThrown(() -> entity.setName("E2"),
                IllegalArgumentException.class, "entity name E2 already exists");

        entity.findAttribute("a1").setName("e4Id");
        AssertExt.assertExceptionThrown(() -> entity.setName("e4"),
                IllegalArgumentException.class, "reserved attribute name e4Id already exists");

        entity.findAttribute("e4Id").setName("E4name");
        AssertExt.assertExceptionThrown(() -> entity.setName("e4"),
                IllegalArgumentException.class, "reserved attribute name e4Name already exists");
    }

    @Test
    public void test_setSequenceTypeToByGroup() throws Exception {
        // void setSequenceTypeToByGroup(Attribute sequenceGroupAttribute)
        {
            Entity entity = model.findEntity("e1");
            Assert.assertEquals(Entity.SEQUENCE_TYPE_SINGLE, entity.getSequenceType());
            Assert.assertEquals(null, entity.getSequenceGroupAttribute());

            entity.setSequenceTypeToByGroup(entity.findAttribute("a1"));
            Assert.assertEquals(Entity.SEQUENCE_TYPE_BY_GROUP, entity.getSequenceType());
            Assert.assertEquals(entity.findAttribute("a1"), entity.getSequenceGroupAttribute());

            AssertExt.assertExceptionThrown(() -> entity.setSequenceTypeToByGroup(model.findEntity("e2").findAttribute("a1")),
                    IllegalArgumentException.class, "sequence group attribute should belong to this entity");
        }

        // void setSequenceTypeToByGroup(String sequenceGroupAttributeName)
        {
            Entity entity = model.findEntity("e2");
            Assert.assertEquals(Entity.SEQUENCE_TYPE_SINGLE, entity.getSequenceType());
            Assert.assertEquals(null, entity.getSequenceGroupAttribute());

            entity.setSequenceTypeToByGroup("A1");
            Assert.assertEquals(Entity.SEQUENCE_TYPE_BY_GROUP, entity.getSequenceType());
            Assert.assertEquals(entity.findAttribute("a1"), entity.getSequenceGroupAttribute());

            AssertExt.assertExceptionThrown(() -> entity.setSequenceTypeToByGroup("a4"),
                    IllegalArgumentException.class, "can't find sequence group attribute name: a4");
        }
    }

    @Test
    public void test_withName() throws Exception {
        Entity entity = model.findEntity("e1");
        Assert.assertTrue(entity.withName("e1"));
        Assert.assertTrue(entity.withName("E1"));
        Assert.assertFalse(entity.withName("e2"));
    }

    @Test
    public void test_findAttribute() throws Exception {
        Entity entity = model.findEntity("e1");
        Assert.assertEquals(entity.getAttributes().get(0), entity.findAttribute("a1"));
        Assert.assertEquals(entity.getAttributes().get(1), entity.findAttribute("a2"));
        Assert.assertEquals(entity.getAttributes().get(1), entity.findAttribute("A2"));
        Assert.assertEquals(null, entity.findAttribute("a3"));
    }

    @Test
    public void test_hasAttribute() throws Exception {
        Entity entity = model.findEntity("e1");
        Assert.assertTrue(entity.hasAttribute("a1"));
        Assert.assertTrue(entity.hasAttribute("a2"));
        Assert.assertTrue(entity.hasAttribute("A2"));
        Assert.assertFalse(entity.hasAttribute("a3"));
    }

    @Test
    public void test_belongsTo() throws Exception {
        Entity entity = model.findEntity("e1");
        Assert.assertTrue(entity.belongsTo(model));
        Assert.assertFalse(entity.belongsTo(new Model()));
    }

    @Test
    public void test_inSameModel() throws Exception {
        Entity entity = model.findEntity("e1");

        // boolean inSameModel(Entity entity)
        Assert.assertTrue(entity.inSameModel(model.findEntity("e2")));
        Assert.assertFalse(entity.inSameModel(new Model().newEntity("e1")));

        // boolean inSameModel(Attribute attribute)
        Assert.assertTrue(entity.inSameModel(model.findEntity("e2").findAttribute("a1")));
        Assert.assertFalse(entity.inSameModel(new Model().newEntity("e1").newAttribute("a1")));
    }

    @Test
    public void test_newAttribute() throws Exception {
        Entity entity = model.findEntity("e1");
        Assert.assertEquals(2, entity.getAttributes().size());

        Attribute attribute = entity.newAttribute("a3");
        Assert.assertEquals(3,         entity.getAttributes().size());
        Assert.assertEquals(attribute, entity.getAttributes().get(2));
        Assert.assertEquals(entity,    entity.getAttributes().get(2).getOwner());
        Assert.assertEquals("a3",      entity.getAttributes().get(2).getName());
        Assert.assertEquals("",        entity.getAttributes().get(2).getDescription());

        AssertExt.assertExceptionThrown(() -> entity.newAttribute("A1"),
                IllegalArgumentException.class, "attribute name A1 already exists");
        AssertExt.assertExceptionThrown(() -> entity.newAttribute("e1Id"),
                IllegalArgumentException.class, "attribute name e1Id is reserved");
        AssertExt.assertExceptionThrown(() -> entity.newAttribute("E1name"),
                IllegalArgumentException.class, "attribute name e1Name is reserved");
        AssertExt.assertExceptionThrown(() -> entity.newAttribute("ordinal"),
                IllegalArgumentException.class, "attribute name Ordinal is reserved");
    }
}
