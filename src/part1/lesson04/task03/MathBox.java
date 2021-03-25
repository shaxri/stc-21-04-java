package part1.lesson04.task03;

import java.util.*;

public class MathBox<T extends Number> extends ObjectBox {

    private Set<T> objectCollection;

    public MathBox(T[] objectCollection) {
        this.objectCollection = new HashSet<>(Arrays.asList(objectCollection));
    }

    Number summator() {
        double sum = 0D;
        for (Number number : objectCollection)
            sum += number.doubleValue();
        return sum;
    }

    public void splitter(Number divider) {
        Set<T> newNumbers = new HashSet<>();

        for (T number : objectCollection) {
            Number someNumber = number.doubleValue() / divider.doubleValue();
            newNumbers.add((T) someNumber);
            System.out.println(someNumber);
        }
        objectCollection.clear();
        this.objectCollection = newNumbers;
    }

    public Set<T> getObjectCollection() {
        return objectCollection;
    }

    public void setObjectCollection(Set<T> objectCollection) {
        this.objectCollection = objectCollection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.objectCollection);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !obj.getClass().equals(MathBox.class)) return false;

        Iterator itr = this.objectCollection.iterator();
        Iterator itr2 = ((MathBox) obj).objectCollection.iterator();

        while (itr.hasNext() && itr2.hasNext()) {
            if(!itr.next().equals(itr2.next())){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "objectCollection=" + objectCollection +
                '}';
    }
}
