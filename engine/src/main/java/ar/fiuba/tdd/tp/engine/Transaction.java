package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 09/05/2016.
 */
public class Transaction {
    private String name;
    private Container[] containers;
    private String hasContentConditionName;

    public Transaction(String name) {
        this.name = name;
        containers = new Container[2];
        hasContentConditionName = "";
    }

    public String doTransactionWith(String content) {
        if (containers[0] != null && containers[1] != null) {
            if (containers[0].has(content)) {
                return move(content,0,1);
            } else if (containers[1].has(content)) {
                return move(content,1,0);
            } else {
                return "You can't do that";
            }
        } else {
            return "You can't do that";
        }
    }

    private String move(String contentName, int from, int to) {
        Content content = containers[from].take(contentName);
        if (hasContentConditionName == "" || content.has(hasContentConditionName)) {
            containers[to].put(content);
            return contentName + " go from " + containers[from].getName() + " to " + containers[to].getName();
        } else {
            containers[from].put(content);
            return "no cumple la condicion";
        }
    }

    public void setContainers(Container container1, Container container2) {
        this.containers[0] = container1;
        this.containers[1] = container2;
    }

    public void hasContentCondition(String contentName) {
        this.hasContentConditionName = contentName;
    }
}
