package part1.lesson03.task03.sort;

import part1.lesson03.task03.exception.NameAgeEqualException;
import part1.lesson03.task03.model.Person;

import java.util.Comparator;

public interface MySortable {
    void sort(Person[] arr, Comparator<Person> c) throws NameAgeEqualException;
}
