package part1.lesson03.task03.comparator;

import part1.lesson03.task03.model.Person;

import java.util.Comparator;

public class SexComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getSex().toString().compareTo(o2.getSex().toString());
    }
}
