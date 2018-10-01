package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

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
        if (this.name.equals(name))
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

    public Attribute getAttribute(String attributeName) {
        return Iterables.find(attributes, attribute -> attribute.getName().equals(attributeName), null);
    }

    Entity(Model owner, String name) {
        setOwner(owner);
        setName(name);
    }

    void checkAttributeName(String attributeName) {
        Preconditions.checkArgument(getAttribute(attributeName) == null, "attribute name %s already exists", attributeName);
    }

    public Attribute newAttribute(String attributeName) {
        Attribute result = new Attribute(this, attributeName);
        attributes.add(result);
        return result;
    }
}
