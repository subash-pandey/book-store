import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparablePractice {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charles", 30));
        people.add(new Person("Charlie", 22));

        // Sort people by age using compareTo
        Collections.sort(people);

        // Print the sorted list
        for (Person p : people) {
            System.out.println(p);
        }
    }
}

class Person implements Comparable<Person> {
    String name;
    int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;


    }

    @Override
    public int compareTo(Person o) {
        int comparison = Integer.compare(this.age, o.age);
        if(comparison == 0){
            return this.name.compareTo(o.name);
        }
        return comparison;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.age + ")";
    }


}
