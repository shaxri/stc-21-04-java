package part1.lesson03.task03.sort;

import part1.lesson03.task03.comparator.NameComparator;
import part1.lesson03.task03.exception.NameAgeEqualException;
import part1.lesson03.task03.model.Person;

import java.util.Comparator;

public class BubbleSortPerson extends MyAbstractCustomSort implements MySortable {
    @Override
    public void sort(Person[] arr, Comparator<Person> c) throws NameAgeEqualException {
        int n = arr.length;
        boolean comparison;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                throwNameAgeEqualException(arr[j], arr[j+1]);
                if (c == null) {
                    comparison = arr[j].getAge() > arr[j + 1].getAge();
                } else {
                    comparison = c.compare(arr[j], arr[j + 1]) > 0;
                }
                if (comparison) {
                    Person temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
