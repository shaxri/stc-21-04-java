package part1.lesson03.task03.model;

import part1.lesson03.task03.enumerator.Sex;

/**
 * This class is a model of person object
 */
public class Person {
    /**
     * Person's age
     */
    private int age;
    /**
     * Person's sex
     */
    private Sex sex;
    /**
     * Person's name
     */
    private String name;

    /**
     * @param age Person's age
     * @param sex Person's sex
     */
    public Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    /**
     * @return To get person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age To set person's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return To get person's sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * @param sex To set person's sex
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * @return To get person's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return To set person's name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
