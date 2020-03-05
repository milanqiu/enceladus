package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Creation Date: 2017-06-13
 * @author Milan Qiu
 */
public class Model {

    private String name = "ModelName";
    private String description = "";

    private List<Entity> entities = new LinkedList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = Preconditions.checkNotNull(description);
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    public Entity findEntity(String entityName) {
        return Iterables.find(entities, entity -> entity.withName(entityName), null);
    }

    public boolean hasEntity(String entityName) {
        return Iterables.any(entities, entity -> entity.withName(entityName));
    }

    public Entity newEntity(String entityName) {
        Entity result = new Entity(this, entityName);
        entities.add(result);
        return result;
    }
}
