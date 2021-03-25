package part1.lesson04.task02;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Object> objList = new LinkedList<>();
        objList.add(new Object() {
            final String name = "Object1";

            @Override
            public String toString() {
                return "$classname{" +
                        "name='" + name + '\'' +
                        '}';
            }
        });
        objList.add(new Object() {
            final String name = "Object2";

            @Override
            public String toString() {
                return "$classname{" +
                        "name='" + name + '\'' +
                        '}';
            }
        });
        objList.add(new Object() {
            final String name = "Object3";

            @Override
            public String toString() {
                return "$classname{" +
                        "name='" + name + '\'' +
                        '}';
            }
        });
        ObjectBox objBox = new ObjectBox(objList);

        objBox.dump();
    }
}
