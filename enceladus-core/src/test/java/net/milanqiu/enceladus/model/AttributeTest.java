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
    public void test_setReference_deleteReference() throws Exception {
        Attribute attribute = model.getEntity("e1").getAttribute("a1");
        Entity refEntity2 = model.getEntity("e2");
        Attribute refAttribute2 = model.getEntity("e2").getAttribute("a1");
        Entity refEntity3 = model.newEntity("e3");
        Attribute refAttribute3 = model.getEntity("e3").newAttribute("a1");
        Entity refEntityWild = new Model().newEntity("e1");

        Assert.assertNull(attribute.getReference());

        // void setReference(Entity refEntity)
        attribute.setReference(refEntity2);
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity2, attribute.getReference().getRefEntity());
        Assert.assertEquals(null, attribute.getReference().getRefAttribute());

        attribute.setReference(refEntity3);
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity3, attribute.getReference().getRefEntity());
        Assert.assertEquals(null, attribute.getReference().getRefAttribute());

        AssertExt.assertExceptionThrown(() -> { attribute.setReference(refEntityWild); },
                IllegalArgumentException.class, "reference entity should be in same model");
        AssertExt.assertExceptionThrown(() -> { attribute.setReference(attribute.getOwner()); },
                IllegalArgumentException.class, "reference entity equals to owner entity");

        // void deleteReference()
        attribute.deleteReference();
        Assert.assertNull(attribute.getReference());

        // void setReference(String refEntityName)
        attribute.setReference("e2");
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity2, attribute.getReference().getRefEntity());
        Assert.assertEquals(null, attribute.getReference().getRefAttribute());

        attribute.setReference("e3");
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity3, attribute.getReference().getRefEntity());
        Assert.assertEquals(null, attribute.getReference().getRefAttribute());

        AssertExt.assertExceptionThrown(() -> { attribute.setReference("e0"); },
                IllegalArgumentException.class, "can't find reference entity name: e0");
        AssertExt.assertExceptionThrown(() -> { attribute.setReference("e1"); },
                IllegalArgumentException.class, "reference entity equals to owner entity");

        // void deleteReference()
        attribute.deleteReference();
        Assert.assertNull(attribute.getReference());

        // void setReference(Entity refEntity, Attribute refAttribute)
        attribute.setReference(refEntity2, refAttribute2);
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity2, attribute.getReference().getRefEntity());
        Assert.assertEquals(refAttribute2, attribute.getReference().getRefAttribute());

        attribute.setReference(refEntity3, refAttribute3);
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity3, attribute.getReference().getRefEntity());
        Assert.assertEquals(refAttribute3, attribute.getReference().getRefAttribute());

        AssertExt.assertExceptionThrown(() -> { attribute.setReference(refEntityWild, refAttribute3); },
                IllegalArgumentException.class, "reference entity should be in same model");
        AssertExt.assertExceptionThrown(() -> { attribute.setReference(attribute.getOwner(), refAttribute3); },
                IllegalArgumentException.class, "reference entity equals to owner entity");
        AssertExt.assertExceptionThrown(() -> { attribute.setReference(refEntity2, refAttribute3); },
                IllegalArgumentException.class, "reference attribute should belongs to reference entity");

        // void deleteReference()
        attribute.deleteReference();
        Assert.assertNull(attribute.getReference());

        // void setReference(String refEntityName, String refAttributeName)
        attribute.setReference("e2", "a1");
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity2, attribute.getReference().getRefEntity());
        Assert.assertEquals(refAttribute2, attribute.getReference().getRefAttribute());

        attribute.setReference("e3", "a1");
        Assert.assertEquals(attribute, attribute.getReference().getOwner());
        Assert.assertEquals(refEntity3, attribute.getReference().getRefEntity());
        Assert.assertEquals(refAttribute3, attribute.getReference().getRefAttribute());

        AssertExt.assertExceptionThrown(() -> { attribute.setReference("e0", "a1"); },
                IllegalArgumentException.class, "can't find reference entity name: e0");
        AssertExt.assertExceptionThrown(() -> { attribute.setReference("e1", "a1"); },
                IllegalArgumentException.class, "reference entity equals to owner entity");
        AssertExt.assertExceptionThrown(() -> { attribute.setReference("e2", "a0"); },
                IllegalArgumentException.class, "can't find reference attribute name: a0");
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
