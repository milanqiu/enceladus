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

    private String idAttributeName = "Id";
    private BtDomainId idAttributeType = new BtInt32Id();

    private boolean hasNameAttribute = false;
    private String nameAttributeName = "Name";
    private BtDomainChar nameAttributeType = new BtString50();

    private boolean isTree = false;
    public static final String PARENT_ATTRIBUTE_NAME = "ParentId";
    public static final String LEVEL_ATTRIBUTE_NAME = "TreeLevel";

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
        if (this.name.equalsIgnoreCase(name))
            return;
        checkEntityName(name);
        this.name = name;
        idAttributeName = name.concat("Id");
        nameAttributeName = name.concat("Name");
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = Preconditions.checkNotNull(description);
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }
    public Attribute getAttribute(String attributeName) {
        return Iterables.find(attributes, attribute -> attribute.getName().equalsIgnoreCase(attributeName), null);
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

    Entity(Model owner, String name) {
        setOwner(owner);
        setName(name);
    }

    void checkEntityName(String entityName) {
        Preconditions.checkArgument(owner.getEntity(entityName) == null, "entity name %s already exists", entityName);
        Preconditions.checkArgument(getAttribute(entityName.concat("Id")) == null, "reserved attribute name %s already exists", entityName.concat("Id"));
        Preconditions.checkArgument(getAttribute(entityName.concat("Name")) == null, "reserved attribute name %s already exists", entityName.concat("Name"));
    }

    public Attribute newAttribute(String attributeName) {
        Attribute result = new Attribute(this, attributeName);
        attributes.add(result);
        return result;
    }
}
