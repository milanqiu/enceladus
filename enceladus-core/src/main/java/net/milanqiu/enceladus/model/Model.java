package net.milanqiu.enceladus.model;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Creation Date: 2017-06-13
 * @author Milan Qiu
 */
public class Model {

    private List<Entity> entities = new LinkedList<>();

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    void checkEntityName(String entityName) {
        Preconditions.checkArgument(findEntity(entityName) == null, "entity name %s already exists", entityName);
    }

    public int indexOfEntity(Entity entity) {
        return entities.indexOf(entity);
    }

    public int indexOfEntity(String entityName) {
        for (int i = 0; i < entities.size(); i++)
            if (entities.get(i).getName().equals(entityName))
                return i;
        return -1;
    }

    public Entity findEntity(String entityName) {
        for (Entity entity : entities)
            if (entity.getName().equals(entityName))
                return entity;
        return null;
    }

    public Entity newEntity(String entityName) {
        Entity result = new Entity(this, entityName);
        entities.add(result);
        return result;
    }
}
