package part1.lesson05.task01;

import java.util.*;

public class AnimalBookkeeping {

    private Map<String, Animal> animals;

    public AnimalBookkeeping(Map<String, Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal animal) throws DuplicateElementException {
        if(animals.containsValue(animal)){
            throw new DuplicateElementException();
        }else{
            animals.put(animal.getId(), animal);
        }
    }

    public Collection<Animal> findByNickname(String nickname) {
        List<Animal> nicknameMatchedAnimals = new ArrayList<>(5);
        for (Animal animal: animals.values()) {
            if(animal.getNickname().equals(nickname)){
               nicknameMatchedAnimals.add(animal);
            }
        }
        return nicknameMatchedAnimals;
    }

    public Animal updateAnimal(String id, Animal animal){
        return animals.put(id, animal);
    }

}
