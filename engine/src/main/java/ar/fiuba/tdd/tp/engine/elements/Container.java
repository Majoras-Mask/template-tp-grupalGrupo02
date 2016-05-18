package ar.fiuba.tdd.tp.engine.elements;

import java.util.HashMap;
import java.util.Map;

public abstract class Container {
    protected Map<String,Content> contents;
    protected String name;

    public Container(String name) {
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

    public Content get(String contentName) {
        return contents.get(contentName);
    }

    public String getName() {
        return name;
    }

    public String getContentsList() {
        StringBuilder stringBuilder = new StringBuilder();
        final String[] prefix = {""};
        contents.forEach((contentName, content) -> {
                stringBuilder.append(prefix[0]);
                prefix[0] = "-";
                stringBuilder.append(contentName);
            }
        );
        return stringBuilder.toString();
    }
}
