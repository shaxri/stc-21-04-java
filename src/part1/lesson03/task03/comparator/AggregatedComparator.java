package part1.lesson03.task03.comparator;

import part1.lesson03.task03.model.Person;

import java.util.Comparator;

public class AggregatedComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        int sexComparison = o1.getSex().toString().compareTo(o2.getSex().toString());

        if(sexComparison == 0){
            int ageComparison = Integer.compare(o1.getAge(), o2.getAge());
            if(ageComparison == 0){
                return o1.getName().compareTo(o2.getName());
            }else{
                return ageComparison;
            }
        }
        return sexComparison;
    }
}
