package part1.lesson03.task03;

import part1.lesson03.task03.comparator.AggregatedComparator;
import part1.lesson03.task03.comparator.NameComparator;
import part1.lesson03.task03.comparator.SexComparator;
import part1.lesson03.task03.exception.NameAgeEqualException;
import part1.lesson03.task03.generator.PersonGenerator;
import part1.lesson03.task03.model.Person;
import part1.lesson03.task03.sort.BubbleSortPerson;
import part1.lesson03.task03.sort.InsertionSortPerson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NameAgeEqualException {
        PersonGenerator generator = new PersonGenerator();
        AggregatedComparator aggregatedComparator = new AggregatedComparator();

        Person[] persons = generator.getPersons(6);

        System.out.println("Bubble sort algorithm");
        BubbleSortPerson bubbleSort = new BubbleSortPerson();

        long start = System.currentTimeMillis();
        bubbleSort.sort(persons, aggregatedComparator);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println(Arrays.toString(persons));
        System.out.println("elapsed time: " + elapsedTime);

        System.out.println();

        System.out.println("Insertion sort algorithm");
        InsertionSortPerson insertionSort = new InsertionSortPerson();
        List<Person> personList = Arrays.asList(persons);
        Collections.shuffle(personList);
        personList.toArray(persons);

        start = System.currentTimeMillis();
        insertionSort.sort(persons, aggregatedComparator);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println(Arrays.toString(persons));
        System.out.println("elapsed time: " + elapsedTime);

    }
}
