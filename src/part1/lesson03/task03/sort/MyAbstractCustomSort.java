package part1.lesson03.task03.sort;

import part1.lesson03.task03.exception.NameAgeEqualException;
import part1.lesson03.task03.model.Person;

public abstract class MyAbstractCustomSort {
    public void throwNameAgeEqualException(Person o1, Person o2) throws NameAgeEqualException {
        if(o1.getAge() == o2.getAge() && o1.getName().equals(o2.getName()))
        throw new NameAgeEqualException();
    }
}
