package net.milanqiu.enceladus.model;

import net.milanqiu.mimas.junit.AssertExt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Creation Date: 2020-03-05
 * @author Milan Qiu
 */
public class ReferenceTest {

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
    public void test_createIdType() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Entity refEntity = model.findEntity("e2");

        // Reference createIdType(Attribute owner, Entity refEntity)
        {
            Reference reference = Reference.createIdType(attribute, refEntity);
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_ID, reference.getType());
            Assert.assertEquals(null,      reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> Reference.createIdType(attribute, new Model().newEntity("e1")),
                    IllegalArgumentException.class, "reference entity should be in same model");
            AssertExt.assertExceptionThrown(() -> Reference.createIdType(attribute, attribute.getOwner()),
                    IllegalArgumentException.class, "reference entity should not be owner");
        }

        // Reference createIdType(Attribute owner, String refEntityName)
        {
            Reference reference = Reference.createIdType(attribute, "E2");
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_ID, reference.getType());
            Assert.assertEquals(null,      reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> Reference.createIdType(attribute, "e3"),
                    IllegalArgumentException.class, "can't find reference entity name: e3");
            AssertExt.assertExceptionThrown(() -> Reference.createIdType(attribute, "e1"),
                    IllegalArgumentException.class, "reference entity should not be owner");
        }
    }

    @Test
    public void test_createOtherType() throws Exception {
        Attribute attribute = model.findEntity("e1").findAttribute("a2");
        Entity refEntity = model.findEntity("e2");

        // Reference createOtherType(Attribute owner, Entity refEntity, Attribute refAttribute)
        {
            Reference reference = Reference.createOtherType(attribute, refEntity, refEntity.findAttribute("a3"));
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_OTHER, reference.getType());
            Assert.assertEquals(refEntity.findAttribute("a3"),  reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> Reference.createOtherType(attribute, new Model().newEntity("e1"), refEntity.findAttribute("a3")),
                    IllegalArgumentException.class, "reference entity should be in same model");
            AssertExt.assertExceptionThrown(() -> Reference.createOtherType(attribute, attribute.getOwner(), attribute.getOwner().findAttribute("a1")),
                    IllegalArgumentException.class, "reference entity should not be owner");

            AssertExt.assertExceptionThrown(() -> Reference.createOtherType(attribute, refEntity, attribute.getOwner().findAttribute("a1")),
                    IllegalArgumentException.class, "reference attribute should belongs to reference entity");
        }

        // Reference createOtherType(Attribute owner, String refEntityName, String refAttributeName)
        {
            Reference reference = Reference.createOtherType(attribute, "E2", "A3");
            Assert.assertEquals(attribute, reference.getOwner());
            Assert.assertEquals("",        reference.getDescription());
            Assert.assertEquals(refEntity, reference.getRefEntity());
            Assert.assertEquals(Reference.REFERENCE_TYPE_OTHER, reference.getType());
            Assert.assertEquals(refEntity.findAttribute("a3"),  reference.getRefAttribute());

            AssertExt.assertExceptionThrown(() -> Reference.createOtherType(attribute, "e3", "a1"),
                    IllegalArgumentException.class, "can't find reference entity name: e3");
            AssertExt.assertExceptionThrown(() -> Reference.createOtherType(attribute, "e1", "a1"),
                    IllegalArgumentException.class, "reference entity should not be owner");

            AssertExt.assertExceptionThrown(() -> Reference.createOtherType(attribute, "e2", "a4"),
                    IllegalArgumentException.class, "can't find reference attribute name: a4");
        }
    }
}
