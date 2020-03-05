package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import net.milanqiu.enceladus.datatype.basictype.BtDomainChar;
import net.milanqiu.enceladus.datatype.basictype.BtDomainId;
import net.milanqiu.enceladus.datatype.basictype.BtInt32Id;
import net.milanqiu.enceladus.datatype.basictype.specialized.BtString50;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Creation Date: 2017-06-12
 * @author Milan Qiu
 */
public class Entity {

    private Model owner;
    private String name = "";
    private String description = "";

    private List<Attribute> attributes = new LinkedList<>();

    public static final String ID_ATTRIBUTE_NAME_SUFFIX = "Id";
    private String idAttributeName = ID_ATTRIBUTE_NAME_SUFFIX;
    private BtDomainId idAttributeType = new BtInt32Id();

    private boolean hasNameAttribute = false;
    public static final String NAME_ATTRIBUTE_NAME_SUFFIX = "Name";
    private String nameAttributeName = NAME_ATTRIBUTE_NAME_SUFFIX;
    private BtDomainChar nameAttributeType = new BtString50();

    private boolean isTree = false;
    public static final String PARENT_ATTRIBUTE_NAME = "ParentId";
    public static final String LEVEL_ATTRIBUTE_NAME = "TreeLevel";

    private boolean isSequence = false;
    public static final String ORDINAL_ATTRIBUTE_NAME = "Ordinal";
    public static final int SEQUENCE_TYPE_SINGLE = 0;
    public static final int SEQUENCE_TYPE_BY_GROUP = 1;
    public static final int SEQUENCE_TYPE_IN_TREE = 2;
    private int sequenceType = SEQUENCE_TYPE_SINGLE;
    private Attribute sequenceGroupAttribute;

    public Model getOwner() {
        return owner;
    }
    private void setOwner(Model owner) {
        this.owner = Preconditions.checkNotNull(owner);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        if (withName(name))
            return;

        String idAttributeName = name.concat(ID_ATTRIBUTE_NAME_SUFFIX);
        String nameAttributeName = name.concat(NAME_ATTRIBUTE_NAME_SUFFIX);

        Preconditions.checkArgument(!owner.hasEntity(name), "entity name %s already exists", name);
        Preconditions.checkArgument(!hasAttribute(idAttributeName), "reserved attribute name %s already exists", idAttributeName);
        Preconditions.checkArgument(!hasAttribute(nameAttributeName), "reserved attribute name %s already exists", nameAttributeName);

        this.name = name;
        this.idAttributeName = idAttributeName;
        this.nameAttributeName = nameAttributeName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public String getIdAttributeName() {
        return idAttributeName;
    }
    public BtDomainId getIdAttributeType() {
        return idAttributeType;
    }
    public void setIdAttributeType(BtDomainId idAttributeType) {
        this.idAttributeType = Preconditions.checkNotNull(idAttributeType);
    }

    public boolean isHasNameAttribute() {
        return hasNameAttribute;
    }
    public void setHasNameAttribute(boolean hasNameAttribute) {
        this.hasNameAttribute = hasNameAttribute;
    }
    public String getNameAttributeName() {
        return nameAttributeName;
    }
    public BtDomainChar getNameAttributeType() {
        return nameAttributeType;
    }
    public void setNameAttributeType(BtDomainChar nameAttributeType) {
        this.nameAttributeType = Preconditions.checkNotNull(nameAttributeType);
    }

    public boolean isTree() {
        return isTree;
    }
    public void setIsTree(boolean isTree) {
        this.isTree = isTree;
    }

    public boolean isSequence() {
        return isSequence;
    }
    public void setIsSequence(boolean isSequence) {
        this.isSequence = isSequence;
    }
    public int getSequenceType() {
        return sequenceType;
    }
    public void setSequenceTypeToSingle() {
        this.sequenceType = SEQUENCE_TYPE_SINGLE;
    }
    public void setSequenceTypeToByGroup(Attribute sequenceGroupAttribute) {
        Preconditions.checkNotNull(sequenceGroupAttribute);
        Preconditions.checkArgument(sequenceGroupAttribute.belongsTo(this), "sequence group attribute should belong to this entity");
        this.sequenceType = SEQUENCE_TYPE_BY_GROUP;
        this.sequenceGroupAttribute = sequenceGroupAttribute;
    }
    public void setSequenceTypeToByGroup(String sequenceGroupAttributeName) {
        Preconditions.checkNotNull(sequenceGroupAttributeName);
        Attribute sequenceGroupAttribute = findAttribute(sequenceGroupAttributeName);
        Preconditions.checkArgument(sequenceGroupAttribute != null, "can't find sequence group attribute name: %s", sequenceGroupAttributeName);
        this.sequenceType = SEQUENCE_TYPE_BY_GROUP;
        this.sequenceGroupAttribute = sequenceGroupAttribute;
    }
    public void setSequenceTypeToInTree() {
        this.sequenceType = SEQUENCE_TYPE_IN_TREE;
    }
    public Attribute getSequenceGroupAttribute() {
        return sequenceGroupAttribute;
    }

    Entity(Model owner, String name) {
        setOwner(owner);
        setName(name);
    }

    public boolean withName(String name) {
        return getName().equalsIgnoreCase(name);
    }

    public Attribute findAttribute(String attributeName) {
        return Iterables.find(attributes, attribute -> attribute.withName(attributeName), null);
    }

    public boolean hasAttribute(String attributeName) {
        return Iterables.any(attributes, attribute -> attribute.withName(attributeName));
    }

    public boolean belongsTo(Model model) {
        return owner.equals(model);
    }

    public boolean inSameModel(Entity entity) {
        return owner.equals(entity.owner);
    }

    public boolean inSameModel(Attribute attribute) {
        return owner.equals(attribute.getModel());
    }

    public Attribute newAttribute(String attributeName) {
        Attribute result = new Attribute(this, attributeName);
        attributes.add(result);
        return result;
    }
}
