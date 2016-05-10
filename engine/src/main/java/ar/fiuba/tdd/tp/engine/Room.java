package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 02/05/2016.
 */
public class Room extends Container{
    public Room(String name) {
        super(name);
    }

    public String getItemList() {
        StringBuilder stringBuilder = new StringBuilder();
        final String[] prefix = {""};
        contents.forEach((contentName, content) -> {
                if (contentName != "player") {
                    stringBuilder.append(prefix[0]);
                    prefix[0] = "-";
                    stringBuilder.append(contentName);
                }
            }
        );
        return stringBuilder.toString();
    }
}
