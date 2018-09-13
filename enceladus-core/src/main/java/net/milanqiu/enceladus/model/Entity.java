package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

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

    private String name;
    private String description = "";

    private List<Attribute> attributes = new LinkedList<>();

    public Model getOwner() {
        return owner;
    }
    private void setOwner(Model owner) {
        Preconditions.checkNotNull(owner);
        this.owner = owner;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        if (name.equals(this.name))
            return;
        owner.checkEntityName(name);
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        Preconditions.checkNotNull(description);
        this.description = description;
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    Entity(Model owner, String name) {
        setOwner(owner);
        setName(name);
    }

    void checkAttributeName(String attributeName) {
        Preconditions.checkArgument(findAttribute(attributeName) == null, "attribute name %s already exists", attributeName);
    }

    public int indexOfAttribute(Attribute attribute) {
        return attributes.indexOf(attribute);
    }

    public int indexOfAttribute(String attributeName) {
        for (int i = 0; i < attributes.size(); i++)
            if (attributes.get(i).getName().equals(attributeName))
                return i;
        return -1;
    }

    public Attribute findAttribute(String attributeName) {
        for (Attribute attribute : attributes)
            if (attribute.getName().equals(attributeName))
                return attribute;
        return null;
    }

    public Attribute newAttribute(String attributeName) {
        Attribute result = new Attribute(this, attributeName);
        attributes.add(result);
        return result;
    }
}
