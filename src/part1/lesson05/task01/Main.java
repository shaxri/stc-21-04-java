package part1.lesson05.task01;

import part1.lesson03.task03.generator.PersonGenerator;
import part1.lesson03.task03.model.Person;

import java.util.*;

public class Main {
    public static void main(String[] args) throws DuplicateElementException {
        List<String> nicknames = new ArrayList<>(Arrays.asList("Puppy", "Candy", "Sandy", "Dorah", "Sparky"));
        PersonGenerator generator = new PersonGenerator();
        Person[] persons = generator.getPersons(5);
        Map<String, Animal> animals = new TreeMap<>();
        Random rand = new Random();

        for (int i = 0; i < persons.length; i++) {
            Animal entry = new Animal(nicknames.get(i), persons[i], rand.nextDouble());
            animals.put(entry.getId(), entry);
        }

        System.out.println(animals);

        AnimalBookkeeping zoo = new AnimalBookkeeping(animals);


        //add new animal
        Animal cindy = new Animal("Cindy", persons[rand.nextInt(5)], 4.5d);
        zoo.addAnimal(cindy);
        System.out.println(zoo.findByNickname("Cindy"));

        //new random owner
        Person randomOwner = persons[rand.nextInt(5)];
        randomOwner.setName("Shakhriyor");
        cindy.setOwner(randomOwner);
        zoo.updateAnimal(cindy.getId(), cindy);
        System.out.println(zoo.findByNickname("Cindy"));

    }

}
