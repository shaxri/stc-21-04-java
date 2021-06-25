package part1.lesson12.task01.collection;


import part1.lesson12.task01.model.Animal;

import java.util.Comparator;

public class AnimalsIdComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal animal1, Animal animal2) {
        return animal1.getId() - animal2.getId();
    }
}
