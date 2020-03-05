package net.milanqiu.enceladus.model;

import net.milanqiu.mimas.junit.AssertExt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Creation Date: 2018-09-13
 * @author Milan Qiu
 */
public class AttributeTest {

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
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Assert.assertEquals("a2", attribute.getName());

        attribute.setName("a3");
        Assert.assertEquals("a3", attribute.getName());
        attribute.setName("A3");
        Assert.assertEquals("a3", attribute.getName());

        AssertExt.assertExceptionThrown(() -> attribute.setName("A1"),
                IllegalArgumentException.class, "attribute name A1 already exists");
        AssertExt.assertExceptionThrown(() -> attribute.setName("e1Id"),
                IllegalArgumentException.class, "attribute name e1Id is reserved");
        AssertExt.assertExceptionThrown(() -> attribute.setName("E1name"),
                IllegalArgumentException.class, "attribute name e1Name is reserved");
        AssertExt.assertExceptionThrown(() -> attribute.setName("ordinal"),
                IllegalArgumentException.class, "attribute name Ordinal is reserved");
    }

    @Test
    public void test_setIdTypeReference() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Entity refEntity = model.findEntity("e2");
        Assert.assertNull(attribute.getReference());

        // void setIdTypeReference(Entity refEntity)
        {
            attribute.setIdTypeReference(refEntity);
            Reference reference = attribute.getReference();
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_ID, reference.getType());
            Assert.assertEquals(null,      reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> attribute.setIdTypeReference(new Model().newEntity("e1")),
                    IllegalArgumentException.class, "reference entity should be in same model");
            AssertExt.assertExceptionThrown(() -> attribute.setIdTypeReference(attribute.getOwner()),
                    IllegalArgumentException.class, "reference entity should not be owner");
        }

        // void setIdTypeReference(String refEntityName)
        {
            attribute.setIdTypeReference("E2");
            Reference reference = attribute.getReference();
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_ID, reference.getType());
            Assert.assertEquals(null,      reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> attribute.setIdTypeReference("e3"),
                    IllegalArgumentException.class, "can't find reference entity name: e3");
            AssertExt.assertExceptionThrown(() -> attribute.setIdTypeReference("e1"),
                    IllegalArgumentException.class, "reference entity should not be owner");
        }
    }

    @Test
    public void test_setOtherTypeReference() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Entity refEntity = model.findEntity("e2");
        Assert.assertNull(attribute.getReference());

        // void setOtherTypeReference(Entity refEntity, Attribute refAttribute)
        {
            attribute.setOtherTypeReference(refEntity, refEntity.findAttribute("a3"));
            Reference reference = attribute.getReference();
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_OTHER, reference.getType());
            Assert.assertEquals(refEntity.findAttribute("a3"),  reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> attribute.setOtherTypeReference(new Model().newEntity("e1"), refEntity.findAttribute("a3")),
                    IllegalArgumentException.class, "reference entity should be in same model");
            AssertExt.assertExceptionThrown(() -> attribute.setOtherTypeReference(attribute.getOwner(), attribute.getOwner().findAttribute("a1")),
                    IllegalArgumentException.class, "reference entity should not be owner");

            AssertExt.assertExceptionThrown(() -> attribute.setOtherTypeReference(refEntity, attribute.getOwner().findAttribute("a1")),
                    IllegalArgumentException.class, "reference attribute should belongs to reference entity");
        }

        // void setOtherTypeReference(String refEntityName, String refAttributeName)
        {
            attribute.setOtherTypeReference("E2", "A3");
            Reference reference = attribute.getReference();
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_OTHER, reference.getType());
            Assert.assertEquals(refEntity.findAttribute("a3"),  reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> attribute.setOtherTypeReference("e3", "a1"),
                    IllegalArgumentException.class, "can't find reference entity name: e3");
            AssertExt.assertExceptionThrown(() -> attribute.setOtherTypeReference("e1", "a1"),
                    IllegalArgumentException.class, "reference entity should not be owner");

            AssertExt.assertExceptionThrown(() -> attribute.setOtherTypeReference("e2", "a4"),
                    IllegalArgumentException.class, "can't find reference attribute name: a4");
        }
    }

    @Test
    public void test_withName() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Assert.assertTrue(attribute.withName("a2"));
        Assert.assertTrue(attribute.withName("A2"));
        Assert.assertFalse(attribute.withName("a3"));
    }

    @Test
    public void test_getModel() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Assert.assertEquals(model, attribute.getModel());
    }

    @Test
    public void test_belongsTo() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");

        // boolean belongsTo(Model model)
        Assert.assertTrue(attribute.belongsTo(model));
        Assert.assertFalse(attribute.belongsTo(new Model()));

        // boolean belongsTo(Entity entity)
        Assert.assertTrue(attribute.belongsTo(model.findEntity("e1")));
        Assert.assertFalse(attribute.belongsTo(model.findEntity("e2")));
    }

    @Test
    public void test_inSameModel() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");

        // boolean inSameModel(Entity entity)
        Assert.assertTrue(attribute.inSameModel(model.findEntity("e2")));
        Assert.assertFalse(attribute.inSameModel(new Model().newEntity("e1")));

        // boolean inSameModel(Attribute attribute)
        Assert.assertTrue(attribute.inSameModel(model.findEntity("e2").findAttribute("a3")));
        Assert.assertFalse(attribute.inSameModel(new Model().newEntity("e1").newAttribute("a1")));
    }

    @Test
    public void test_inSameEntity() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Assert.assertTrue(attribute.inSameEntity(model.findEntity("e1").findAttribute("a1")));
        Assert.assertFalse(attribute.inSameEntity(model.findEntity("e2").findAttribute("a1")));
    }
}
