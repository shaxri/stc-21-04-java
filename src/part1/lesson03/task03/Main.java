package part1.lesson03.task03;

import part1.lesson03.task03.comparator.NameComparator;
import part1.lesson03.task03.comparator.SexComparator;
import part1.lesson03.task03.exception.NameAgeEqualException;
import part1.lesson03.task03.generator.PersonGenerator;
import part1.lesson03.task03.model.Person;
import part1.lesson03.task03.sort.BubbleSortPerson;
import part1.lesson03.task03.sort.InsertionSortPerson;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NameAgeEqualException {
        PersonGenerator generator = new PersonGenerator();
        SexComparator sexComparator = new SexComparator();
        NameComparator nameComparator = new NameComparator();

        Person[] persons = generator.getPersons(4);

        System.out.println("Bubble sort algorithm");
        BubbleSortPerson bubbleSort = new BubbleSortPerson();

        System.out.println("Sort by sex: ");
        bubbleSort.sort(persons, sexComparator);
        System.out.println(Arrays.toString(persons));

        System.out.println("Sort by age (default): ");
        bubbleSort.sort(persons, null);
        System.out.println(Arrays.toString(persons));

        System.out.println("Sort by name: ");
        bubbleSort.sort(persons, nameComparator);
        System.out.println(Arrays.toString(persons));

        System.out.println();

        System.out.println("Insertion sort algorithm");
        InsertionSortPerson insertionSort = new InsertionSortPerson();

        System.out.println("Sort by sex: ");
        insertionSort.sort(persons, sexComparator);
        System.out.println(Arrays.toString(persons));

        System.out.println("Sort by age (default): ");
        insertionSort.sort(persons, null);
        System.out.println(Arrays.toString(persons));

        System.out.println("Sort by name: ");
        insertionSort.sort(persons, nameComparator);
        System.out.println(Arrays.toString(persons));

    }
}
