package part1.lesson04.task03;

import java.util.Collection;

public class ObjectBox {
    private Collection<Object> objectCollection;

    public ObjectBox() {
    }

    public ObjectBox(Collection<Object> objectCollection) {
        this.objectCollection = objectCollection;
    }

    public boolean addObject(Object obj) {
        return objectCollection.add(obj);
    }

    public boolean deleteObject(Object obj) {
        return objectCollection.remove(obj);
    }

    public void dump(){
        System.out.println(objectCollection.toString());
    }
}
