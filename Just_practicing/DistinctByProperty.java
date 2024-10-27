import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctByProperty {

    public static void main(String[] args) {
        // Sample class with a property
        class Person {
            String name;
            int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }
        }

        // Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", 25));
        people.add(new Person("Jane", 30));
        people.add(new Person("John", 35)); // Duplicate name
        people.add(new Person("Mike", 25));

        // Get distinct objects by name using streams and Collectors.toSet()
        List<Person> distinctPeople = people.stream()
                .distinct()
                .collect(Collectors.toList());

        // Print the distinct people
        distinctPeople.forEach(person -> System.out.println(person.getName() + ", " + person.age));
    }


     public static <T> java.util.function.Predicate<T> distinctByKey(java.util.function.Function<? super T, ?> keyExtractor) {
        java.util.Set<Object> seen = java.util.concurrent.ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}