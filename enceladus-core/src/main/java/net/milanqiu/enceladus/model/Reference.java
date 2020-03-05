package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;

/**
 * <p>
 * Creation Date: 2018-10-10
 * @author Milan Qiu
 */
public class Reference {

    private Attribute owner;
    private String description = "";

    private Entity refEntity = null;

    public static final int REFERENCE_TYPE_ID = 0;
    public static final int REFERENCE_TYPE_OTHER = 1;
    private int type = REFERENCE_TYPE_ID;
    private Attribute refAttribute = null;

    public Attribute getOwner() {
        return owner;
    }
    private void setOwner(Attribute owner) {
        this.owner = Preconditions.checkNotNull(owner);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = Preconditions.checkNotNull(description);
    }

    public Entity getRefEntity() {
        return refEntity;
    }
    private void setRefEntity(Entity refEntity) {
        Preconditions.checkNotNull(refEntity);
        Preconditions.checkArgument(refEntity.inSameModel(owner), "reference entity should be in same model");
        Preconditions.checkArgument(refEntity != owner.getOwner(), "reference entity should not be owner");
        this.refEntity = refEntity;
        setTypeToId();
    }
    private void setRefEntity(String refEntityName) {
        Preconditions.checkNotNull(refEntityName);
        Entity refEntity = owner.getModel().findEntity(refEntityName);
        Preconditions.checkArgument(refEntity != null, "can't find reference entity name: %s", refEntityName);
        Preconditions.checkArgument(refEntity != owner.getOwner(), "reference entity should not be owner");
        this.refEntity = refEntity;
        setTypeToId();
    }

    public int getType() {
        return type;
    }
    private void setTypeToId() {
        this.type = REFERENCE_TYPE_ID;
        this.refAttribute = null;
    }
    private void setTypeToOther(Attribute refAttribute) {
        Preconditions.checkNotNull(refAttribute);
        Preconditions.checkArgument(refAttribute.belongsTo(refEntity), "reference attribute should belongs to reference entity");
        this.type = REFERENCE_TYPE_OTHER;
        this.refAttribute = refAttribute;
    }
    private void setTypeToOther(String refAttributeName) {
        Preconditions.checkNotNull(refAttributeName);
        Attribute refAttribute = refEntity.findAttribute(refAttributeName);
        Preconditions.checkArgument(refAttribute != null, "can't find reference attribute name: %s", refAttributeName);
        this.type = REFERENCE_TYPE_OTHER;
        this.refAttribute = refAttribute;
    }
    public Attribute getRefAttribute() {
        return refAttribute;
    }

    private Reference() {}

    public static Reference createIdType(Attribute owner, Entity refEntity) {
        Reference result = new Reference();
        result.setOwner(owner);
        result.updateToIdType(refEntity);
        return result;
    }

    public static Reference createIdType(Attribute owner, String refEntityName) {
        Reference result = new Reference();
        result.setOwner(owner);
        result.updateToIdType(refEntityName);
        return result;
    }

    public static Reference createOtherType(Attribute owner, Entity refEntity, Attribute refAttribute) {
        Reference result = new Reference();
        result.setOwner(owner);
        result.updateToOtherType(refEntity, refAttribute);
        return result;
    }

    public static Reference createOtherType(Attribute owner, String refEntityName, String refAttributeName) {
        Reference result = new Reference();
        result.setOwner(owner);
        result.updateToOtherType(refEntityName, refAttributeName);
        return result;
    }

    public void updateToIdType(Entity refEntity) {
        setRefEntity(refEntity);
        setTypeToId();
    }

    public void updateToIdType(String refEntityName) {
        setRefEntity(refEntityName);
        setTypeToId();
    }

    public void updateToOtherType(Entity refEntity, Attribute refAttribute) {
        setRefEntity(refEntity);
        setTypeToOther(refAttribute);
    }

    public void updateToOtherType(String refEntityName, String refAttributeName) {
        setRefEntity(refEntityName);
        setTypeToOther(refAttributeName);
    }
}
