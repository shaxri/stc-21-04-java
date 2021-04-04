package part1.lesson05.task01;

import part1.lesson03.task03.model.Person;

import java.util.Objects;
import java.util.UUID;

public class Animal implements Comparable<Animal>{
    private String id;
    private String nickname;
    private Person owner;
    private double weight;

    public Animal(String nickname, Person owner, double weight) {
        this.id = UUID.randomUUID().toString();
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) &&
                Objects.equals(nickname, animal.nickname) &&
                Objects.equals(owner, animal.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, owner);
    }

    @Override
    public int compareTo(Animal o) {
        int ownerComparison = this.getOwner().getName().compareTo(o.owner.getName());
        if( ownerComparison == 0){
            int nicknameComparison = this.getNickname().compareTo(o.getNickname());
            if(nicknameComparison == 0){
                return Double.compare(this.getWeight(), o.getWeight());
            }
            return nicknameComparison;
        }
        return ownerComparison;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", owner=" + owner +
                ", weight=" + weight +
                '}';
    }
}
