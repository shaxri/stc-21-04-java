package part1.lesson12.task01.collection;


import part1.lesson12.task01.model.Animal;
import part1.lesson12.task01.model.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Animals {
    private HashMap<Integer, Animal> animals = new HashMap<>();

    public Animals(List<Animal> petsArray) {
        for (Animal animal : petsArray)
            add(animal.getName(), animal.getWeight(), animal.getPerson());
    }

    public void add(String name, float weight, Person person) {
        try {
            if (animals.values().stream().anyMatch(p->p.getName().equals(name) && p.getPerson().equals(person)))
                throw new Exception("Animal with name = " + name + " and person = " + person.getName() + " already exist");
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
        int id = Objects.hash(name, weight, person);
        Animal animal = new Animal(id, name, weight, person);
        animals.put(id, animal);
    }

    public List<Animal> find(String name) {
        List<Animal> animalsFound =  animals.values().stream().filter((animal -> animal.getName().equals(name))).collect(Collectors.toList());
        System.out.println("find " + name + ":" );
        animalsFound.forEach(System.out::println);
        System.out.println();
        return animalsFound;
    }

    public void update(int id, String name, Float weight, Person person) {
        animals.values().stream().filter(animal -> animal.getId() == id).findFirst().
                ifPresentOrElse(animal->{
                            if(!name.isEmpty()) animal.setName(name);
                            if(Float.compare(weight, 0) != 0) animal.setWeight(weight);
                            if (person != null) animal.setPerson(person);
                            System.out.println("Animal with id = " + id + "  updated successfully");
                        },
                        ()->System.out.println("Animal with id = " + id + " does not exist"));
    }

    public void print() {
        String res = "size = " + animals.size() + "\n";
        Comparator<Animal> comparator = new AnimalComparator();
        animals.values().stream().sorted(comparator).forEach(System.out::println);
    }

    public HashMap<Integer, Animal> getAnimals() {
        return animals;
    }
}
