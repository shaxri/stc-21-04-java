package part1.lesson03.task03.sort;

import part1.lesson03.task03.exception.NameAgeEqualException;
import part1.lesson03.task03.model.Person;

import java.util.Comparator;

public class InsertionSortPerson extends MyAbstractCustomSort implements MySortable {
    @Override
    public void sort(Person[] arr, Comparator<Person> c) throws NameAgeEqualException {
        int n = arr.length;
        boolean comparison;
        for (int i = 1; i < n; ++i) {
            Person key = arr[i];
            int j = i - 1;
            throwNameAgeEqualException(arr[j], key);
            if (c == null) {
                comparison = arr[j].getAge() > key.getAge();
            } else {
                comparison = c.compare(arr[j], key) > 0;
            }

            while (j >= 0 && comparison) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
