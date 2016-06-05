package ar.fiuba.tdd.tp.engine.elements;

import java.util.HashMap;
import java.util.Map;

public abstract class Container {
    protected Map<String,Content> contents;
    protected String name;
    private Integer limit;

    public Container(String name, Integer limit) {
        this.name = name;
        this.contents = new HashMap<>();
        this.limit = limit;
    }

    public void put(Content content) {
        if (contents.size() < limit) {
            content.setContainer(this);
            contents.put(content.getName(), content);
        }
    }

    public boolean canPut() {
        return contents.size() < limit;
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
