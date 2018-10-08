package net.milanqiu.enceladus.model;

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

    private List<Entity> entities = new LinkedList<>();

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }
    public Entity getEntity(String entityName) {
        return Iterables.find(entities, entity -> entity.getName().equalsIgnoreCase(entityName), null);
    }

    public Entity newEntity(String entityName) {
        Entity result = new Entity(this, entityName);
        entities.add(result);
        return result;
    }
}
