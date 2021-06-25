package part1.lesson12.task01.generator;


import part1.lesson12.task01.model.Animal;
import part1.lesson12.task01.model.Person;
import part1.lesson12.task01.model.Sex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalsGenerator {
    static final int minWeight = 1;
    static final int maxWeight = 100;
    static final int maxAge = 100;
    static final int maxNameLen = 5;
    static Random random = new Random();

    public static List<Animal> generateAnimals(int n) {
        List<Animal> animalArray = new ArrayList<>(n);
        List<Person> personArray = generatePersons((int) (n * 0.7));
        for (int i = 0; i < n; i++) {
            animalArray.add(new Animal(0, generateName(maxNameLen), generateWeight(maxWeight, minWeight),
                    personArray.get(random.nextInt(personArray.size()))));
        }
        return animalArray;
    }

    static int generateWeight(int maxWeight, int minWeight) {
        return random.nextInt(maxWeight) + minWeight;
    }

    static List<Person> generatePersons(int n) {
        List<Person> personArray = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            personArray.add(new Person(AnimalsGenerator.generateAge(maxAge), generateSex(), AnimalsGenerator.generateName(maxNameLen)));
        }
        return personArray;
    }

    static byte generateAge(int maxAge) {
        return (byte) random.nextInt(maxAge + 1);
    }

    static Sex generateSex() {
        return Sex.values()[random.nextInt(Sex.values().length)];
    }

    static String generateName(int maxNameLen) {
        int len = random.nextInt(maxNameLen) + 1;
        int dif = 'Z' - 'A';
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) (random.nextInt(dif) + 'A');
        }
        return new String(str);
    }
}
