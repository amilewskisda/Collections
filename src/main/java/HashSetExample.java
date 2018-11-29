import models.Person;
import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        System.out.println("Example1: addingPersonToHashSet");
        addingPersonToHashSet();
        System.out.println("Example2: basicHashSetOperations");
        basicHashSetOperations();
    }

    private static void addingPersonToHashSet() {
        Person tomek = new Person(123, "Tomek");
        Person ania = new Person(4534, "Ania");
        Person ola = new Person(123, "Ola");
        Person magda1 = new Person(45, "Magda");
        Person magda2 = new Person(45, "Magda");

        Set<Person> people = new HashSet<>();
        people.add(tomek);
        people.add(ania);
        people.add(ola);
        people.add(magda1);
        people.add(magda2);
        System.out.println(people); // WYSTAPI TYLKO 1 MAGDA O ID 45
    }

    private static void basicHashSetOperations() {
        Set<String> firstSet = new HashSet<>();
        firstSet.add("1");
        firstSet.add("2");
        firstSet.add("3");
        boolean isAddedToSet1 = firstSet.add("4");
        boolean isAddedToSet2 = firstSet.add("4");
        System.out.println("Pierwsze 4 zostało dodane do Set: " + isAddedToSet1 + " Drugie 4 zostało dodane do Set: " + isAddedToSet2);
        System.out.println("Nasz set: " + firstSet);
        System.out.println("set.size: " + firstSet.size());
        System.out.println("set.contains(\"2\"): " + firstSet.contains("2"));
    }
}
