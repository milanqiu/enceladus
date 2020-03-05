package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import net.milanqiu.enceladus.datatype.DataType;
import net.milanqiu.enceladus.datatype.basictype.BtInt32;

/**
 * <p>
 * Creation Date: 2017-06-12
 * @author Milan Qiu
 */
public class Attribute {

    private Entity owner;
    private String name = "";
    private String description = "";

    private DataType type = new BtInt32();
    private boolean nullable = true;

    private Reference reference = null;

    public Entity getOwner() {
        return owner;
    }
    private void setOwner(Entity owner) {
        this.owner = Preconditions.checkNotNull(owner);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        if (withName(name))
            return;

        Preconditions.checkArgument(!owner.hasAttribute(name), "attribute name %s already exists", name);
        Preconditions.checkArgument(!name.equalsIgnoreCase(owner.getIdAttributeName()),    "attribute name %s is reserved", owner.getIdAttributeName());
        Preconditions.checkArgument(!name.equalsIgnoreCase(owner.getNameAttributeName()),  "attribute name %s is reserved", owner.getNameAttributeName());
        Preconditions.checkArgument(!name.equalsIgnoreCase(Entity.PARENT_ATTRIBUTE_NAME),  "attribute name %s is reserved", Entity.PARENT_ATTRIBUTE_NAME);
        Preconditions.checkArgument(!name.equalsIgnoreCase(Entity.LEVEL_ATTRIBUTE_NAME),   "attribute name %s is reserved", Entity.LEVEL_ATTRIBUTE_NAME);
        Preconditions.checkArgument(!name.equalsIgnoreCase(Entity.ORDINAL_ATTRIBUTE_NAME), "attribute name %s is reserved", Entity.ORDINAL_ATTRIBUTE_NAME);

        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = Preconditions.checkNotNull(description);
    }

    public DataType getType() {
        return type;
    }
    public void setType(DataType type) {
        this.type = Preconditions.checkNotNull(type);
    }
    public boolean isNullable() {
        return nullable;
    }
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Reference getReference() {
        return reference;
    }
    public void setIdTypeReference(Entity refEntity) {
        if (reference == null) {
            reference = Reference.createIdType(this, refEntity);
        } else {
            reference.updateToIdType(refEntity);
        }
    }
    public void setIdTypeReference(String refEntityName) {
        if (reference == null) {
            reference = Reference.createIdType(this, refEntityName);
        } else {
            reference.updateToIdType(refEntityName);
        }
    }
    public void setOtherTypeReference(Entity refEntity, Attribute refAttribute) {
        if (reference == null) {
            reference = Reference.createOtherType(this, refEntity, refAttribute);
        } else {
            reference.updateToOtherType(refEntity, refAttribute);
        }
    }
    public void setOtherTypeReference(String refEntityName, String refAttributeName) {
        if (reference == null) {
            reference = Reference.createOtherType(this, refEntityName, refAttributeName);
        } else {
            reference.updateToOtherType(refEntityName, refAttributeName);
        }
    }

    Attribute(Entity owner, String name) {
        setOwner(owner);
        setName(name);
    }

    public boolean withName(String name) {
        return getName().equalsIgnoreCase(name);
    }

    public Model getModel() {
        return owner.getOwner();
    }

    public boolean belongsTo(Model model) {
        return owner.getOwner().equals(model);
    }

    public boolean belongsTo(Entity entity) {
        return owner.equals(entity);
    }

    public boolean inSameModel(Entity entity) {
        return owner.getOwner().equals(entity.getOwner());
    }

    public boolean inSameModel(Attribute attribute) {
        return owner.getOwner().equals(attribute.getModel());
    }

    public boolean inSameEntity(Attribute attribute) {
        return owner.equals(attribute.owner);
    }

    public void deleteReference() {
        reference = null;
    }
}
