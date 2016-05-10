package ar.fiuba.tdd.tp.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manuelcruz on 09/05/2016.
 */
public abstract class Container {
    protected Map<String,Content> contents;
    protected String name;

    Container() {
        this.name = "";
        this.contents = new HashMap<>();
    }

    Container(String name) {
        this.name = name;
        this.contents = new HashMap<>();
    }

    public void put(Content content) {
        content.setContainer(this);
        contents.put(content.getName(), content);
    }

    public boolean has(String contentName) {
        return contents.containsKey(contentName);
    }

    public Content take(String contentName) {
        Content content = contents.get(contentName);
        contents.remove(contentName);
        content.setContainer(null);
        return content;
    }

    public String getName() {
        return name;
    }
}
