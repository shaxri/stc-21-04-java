package part1.lesson12.task01.collection;


import part1.lesson12.task01.model.Animal;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal animal1, Animal animal2) {
        int difPerson = animal1.getPerson().getName().compareTo(animal2.getPerson().getName());
        if (difPerson != 0) {
            return difPerson;
        } else {
            int difName = animal1.getName().compareTo(animal2.getName());
            if (difName == 0) {
                return Float.compare(animal1.getWeight(), animal2.getWeight());
            } else {
                return difName;
            }
        }
    }

}
