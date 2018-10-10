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
    private DataType type = new BtInt32();
    private boolean nullable = true;
    private String description = "";

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
        if (this.name.equalsIgnoreCase(name))
            return;
        checkAttributeName(name);
        this.name = name;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = Preconditions.checkNotNull(description);
    }

    Attribute(Entity owner, String name) {
        setOwner(owner);
        setName(name);
    }

    void checkAttributeName(String attributeName) {
        Preconditions.checkArgument(owner.getAttribute(attributeName) == null, "attribute name %s already exists", attributeName);

        Preconditions.checkArgument(!attributeName.equalsIgnoreCase(owner.getIdAttributeName()),    "attribute name %s is reserved", owner.getIdAttributeName());
        Preconditions.checkArgument(!attributeName.equalsIgnoreCase(owner.getNameAttributeName()),  "attribute name %s is reserved", owner.getNameAttributeName());
        Preconditions.checkArgument(!attributeName.equalsIgnoreCase(Entity.PARENT_ATTRIBUTE_NAME),  "attribute name %s is reserved", Entity.PARENT_ATTRIBUTE_NAME);
        Preconditions.checkArgument(!attributeName.equalsIgnoreCase(Entity.LEVEL_ATTRIBUTE_NAME),   "attribute name %s is reserved", Entity.LEVEL_ATTRIBUTE_NAME);
        Preconditions.checkArgument(!attributeName.equalsIgnoreCase(Entity.ORDINAL_ATTRIBUTE_NAME), "attribute name %s is reserved", Entity.ORDINAL_ATTRIBUTE_NAME);
    }

    public boolean inSameModel(Entity entity) {
        return owner.getOwner().equals(entity.getOwner());
    }

    public boolean inSameModel(Attribute attribute) {
        return owner.getOwner().equals(attribute.owner.getOwner());
    }

    public boolean inSameEntity(Attribute attribute) {
        return owner.equals(attribute.owner);
    }
}
