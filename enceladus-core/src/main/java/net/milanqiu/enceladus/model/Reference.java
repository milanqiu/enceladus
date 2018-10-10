package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;

/**
 * <p>
 * Creation Date: 2018-10-10
 * @author Milan Qiu
 */
public class Reference {

    private Attribute owner;
    private Entity refEntity = null;
    private Attribute refAttribute = null;

    public Attribute getOwner() {
        return owner;
    }
    private void setOwner(Attribute owner) {
        this.owner = Preconditions.checkNotNull(owner);
    }
    public Entity getRefEntity() {
        return refEntity;
    }
    private void setRefEntity(Entity refEntity) {
        Preconditions.checkNotNull(refEntity);
        Preconditions.checkArgument(refEntity.inSameModel(owner), "reference entity should be in same model");
        Preconditions.checkArgument(refEntity != owner.getOwner(), "reference entity equals to owner entity");
        this.refEntity = refEntity;
    }
    private void setRefEntity(String refEntityName) {
        Preconditions.checkNotNull(refEntityName);
        Entity refEntity = owner.getModel().getEntity(refEntityName);
        Preconditions.checkArgument(refEntity != null, "can't find reference entity name: %s", refEntityName);
        Preconditions.checkArgument(refEntity != owner.getOwner(), "reference entity equals to owner entity");
        this.refEntity = refEntity;
    }
    public Attribute getRefAttribute() {
        return refAttribute;
    }
    private void setRefAttribute(Attribute refAttribute) {
        Preconditions.checkNotNull(refAttribute);
        Preconditions.checkArgument(refAttribute.belongsTo(refEntity), "reference attribute should belongs to reference entity");
        this.refAttribute = refAttribute;
    }
    private void setRefAttribute(String refAttributeName) {
        Preconditions.checkNotNull(refAttributeName);
        Attribute refAttribute = refEntity.getAttribute(refAttributeName);
        Preconditions.checkArgument(refAttribute != null, "can't find reference attribute name: %s", refAttributeName);
        this.refAttribute = refAttribute;
    }

    void set(Entity refEntity) {
        setRefEntity(refEntity);
    }

    void set(String refEntityName) {
        setRefEntity(refEntityName);
    }

    void set(Entity refEntity, Attribute refAttribute) {
        setRefEntity(refEntity);
        setRefAttribute(refAttribute);
    }

    void set(String refEntityName, String refAttributeName) {
        setRefEntity(refEntityName);
        setRefAttribute(refAttributeName);
    }

    Reference(Attribute owner, Entity refEntity) {
        setOwner(owner);
        set(refEntity);
    }

    Reference(Attribute owner, String refEntityName) {
        setOwner(owner);
        set(refEntityName);
    }

    Reference(Attribute owner, Entity refEntity, Attribute refAttribute) {
        setOwner(owner);
        set(refEntity, refAttribute);
    }

    Reference(Attribute owner, String refEntityName, String refAttributeName) {
        setOwner(owner);
        set(refEntityName, refAttributeName);
    }
}
