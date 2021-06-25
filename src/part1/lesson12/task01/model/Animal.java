package part1.lesson12.task01.model;


public class Animal {
    private int id;
    private String name;
    private float weight;
    private Person person;

    public Animal(int id, String name, float weight, Person person) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + this.getId() + " person=" + this.getPerson().getName() + " name=" + this.getName() + " weight=" + this.getWeight();
    }
}
