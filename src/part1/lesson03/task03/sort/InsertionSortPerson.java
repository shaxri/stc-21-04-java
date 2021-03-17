package part1.lesson03.task03.sort;

import part1.lesson03.task03.exception.NameAgeEqualException;
import part1.lesson03.task03.model.Person;

import java.util.Comparator;

public class InsertionSortPerson extends MyAbstractCustomSort implements MySortable {
    @Override
    public void sort(Person[] arr, Comparator<Person> c) throws NameAgeEqualException {
        int n = arr.length;
        boolean comparison;
        for (int j = 1; j < n; j++) {
            Person key = arr[j];
            int i = j - 1;

            throwNameAgeEqualException(arr[i], key);
            if (c == null) {
                comparison = arr[i].getAge() > key.getAge();
            } else {
                comparison = c.compare(arr[i], key) > 0;
            }

            while ((i > -1) && (comparison)) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }
}
