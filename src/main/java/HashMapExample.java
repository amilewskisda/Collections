import models.Person;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {
        basicMapOperations();
    }

    private static void basicMapOperations() {
        Person ania = new Person(123, "Ania");
        Person ola = new Person(234, "Ola");
        Person tomek = new Person(345, "Tomek");
        Person michal = new Person(123, "Michał"); // TEN SAM PESEL CO ANIA !

        Map<Integer, Person> peopleByPesel = new HashMap<>();

        // MAP nie moze zawierać tych samych kluczy
        peopleByPesel.put(ania.getId(), ania);
        peopleByPesel.put(ola.getId(), ola);
        peopleByPesel.put(tomek.getId(), tomek);
        peopleByPesel.put(michal.getId(), michal);

        System.out.println(peopleByPesel);

/*        properWayOfAddingPeople(ania);
        properWayOfAddingPeople(ania);
        properWayOfAddingPeople(ola);
        properWayOfAddingPeople(tomek);
        properWayOfAddingPeople(michal);*/
    }

/*    private static void properWayOfAddingPeople(Person person) {
        Map<String, List<Person>> map = new HashMap<>();
        if (map.containsKey(person.getName())) {
            String name = person.getName();
            List<Person> people = map.get(name);
            people.add(person);
        } else {
            List<Person> people = new ArrayList<>();
            people.add(person);
            map.put(person.getName(), people);
        }
    }*/
}
