
import models.Person;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrayListExample {

    public static void main(String[] args) {
        basicListOperations();
        //doesContainAll();
        //sortList();
        //basicExampleOfStream();
        // additionalExampleOfStream();
        //legacyUseOfIterator();
    }

    private static void basicListOperations() {
        //niefajnie  ArrayList<String> firstList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        System.out.println("Is list empty? " + list.isEmpty());
        System.out.println("List size: " + list.size());
        list.add("JAVA");
        list.add("C++");
        list.add("PERL");
        list.add("PYTHON");

        System.out.println(list);
        System.out.println(list.toString());

        System.out.println("Element at index 3: " + list.get(3));
        System.out.println("Does contain PERL?" + list.contains("PERL"));
        list.add(1, "JAVASCRIPT");
        System.out.println("After JAVASCRIPT added:" + list);
        System.out.println("Is list empty? " + list.isEmpty());
        System.out.println("List size: " + list.size());

        list.remove("JAVA");
        System.out.println("After \"JAVA\" remove: " + list);

        for (String value : list) {
            System.out.println(value);
        }
        list.stream().forEach(el -> System.out.println(el));

        for (String value : list) {
            boolean lastItem = list.indexOf(value) + 1 == list.size();
            System.out.print(value + (lastItem ? "" : ", "));
        }
        System.out.println();

        String collect = list.stream().collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    private static void doesContainAll() {
        List<String> firstList = new ArrayList<>();
        firstList.add("1");
        firstList.add("2");
        firstList.add("3");

        List<String> secondList = new ArrayList<>();
        secondList.add("1");
        secondList.add("2");

        System.out.println(firstList.containsAll(secondList));

        secondList.add("3");

        System.out.println(firstList.containsAll(secondList));

        secondList.add("4");

        System.out.println(firstList.containsAll(secondList));
        System.out.println(secondList.containsAll(firstList));

        System.out.println("Pierwsza lista: " + firstList);
        System.out.println("Druga lista: " + secondList);

        Collections.swap(firstList, 1, 2);

        System.out.println("Pierwsza lista: " + firstList);
        System.out.println("Druga lista: " + secondList);
    }

    private static void sortList() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        list.add(3L);

        list.sort(Comparator.naturalOrder());
        Collections.sort(list);
        System.out.println(list);
    }


    private static void basicExampleOfStream() {
        List<Person> people = new ArrayList<>();
        people.add(new Person(123, "Ania"));
        people.add(new Person(122, "Tomek"));
        people.add(new Person(15, "Adam"));
        people.add(new Person(12, "Ola"));

        Collections.sort(people);
        System.out.println(people);

        System.out.println(people.stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .map(e -> e.getName())
                .collect(Collectors.joining(", ")));

        List<Integer> pesels =
                people.stream()
                        .filter(person -> !person.getName().equals("Adam"))
                        .sorted((a, b) -> a.getName().compareTo(b.getName()))
                        .map(e -> e.getId())
                        .collect(Collectors.toList());
        System.out.println(pesels);
    }

    private static void additionalExampleOfStream() {
        List<Person> people = new ArrayList<>();
        people.add(new Person(123, "Ania"));
        people.add(new Person(122, "Tomek"));
        people.add(new Person(15, "Adam"));
        people.add(new Person(12, "Ola"));
        people.add(new Person(1234, "Ania"));

        Collections.sort(people);
        System.out.println(people);

        System.out.println(people.stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .map(e -> e.getName())
                .collect(Collectors.joining(", ")));

        Predicate<Person> adamTest = person -> {
            System.out.println("LAMBDA Się odpaliła dla " + person.getName());
            return !person.getName().equals("Adam");
        };

        List<Integer> pesels =
                people.stream() //strumień ludzi
                        .filter(e -> e.getName().equals("Ania"))
                        .filter(adamTest)
                        .sorted((a, b) -> a.getName().compareTo(b.getName()))
                        .map(e -> e.getId())
                        .collect(Collectors.toList());
        System.out.println(pesels);
    }

    private static void legacyUseOfIterator() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");

        System.out.println(arrayList);

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }

        for (String s : arrayList) {
            System.out.println(s);
        }

        arrayList.stream().forEach(e -> System.out.println(e));
    }
}
