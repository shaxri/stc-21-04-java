package part1.lesson03.task03.generator;

import part1.lesson03.task03.enumerator.Sex;
import part1.lesson03.task03.model.Person;

import java.util.ArrayList;
import java.util.Random;

public class PersonGenerator {
    /**
     * @param n number of persons to generate
     * @return array of generated persons
     */
    public Person[] getPersons(int n) {
        Random rand = new Random();
        ArrayList<Person> persons = new ArrayList<>(n);
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();



        for (int i = 0; i < n; i++) {
            String name = random.ints(leftLimit, rightLimit + 1)
                    .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            persons.add(new Person(rand.nextInt(101), Sex.values()[rand.nextInt(2)], name));
        }

        Person[] a = new Person[persons.size()];
        int i = 0;
        for (Object person : persons) {
            a[i++] = (Person) person;
        }

        return a;
    }

}
