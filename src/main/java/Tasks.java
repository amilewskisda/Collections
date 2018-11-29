import models.Customer;

import java.util.*;
import java.util.stream.Collectors;

// 1. Napisz klasę Customer, która będzie reprezentować dane : imie, nazwisko, wiek, wypłata (może byc podane zarówno jako String i double)
//      + będzie dobierać kolejne id dla kolejnych tworzonych obiektów. Utwórz listę którą wypełnisz 8 nowo utworzonymi obiektami typu Customer

// 2. Napisz metodę, która przyjmie utworzoną liste obiektów Customer i zwróci listę Stringów <imię nazwisko> (elementem listy będzie np "Anna Nowak")
// 3. Napisz taką sama metode jak w pkt 2) przy pomocy strumieni
// 4. Napisz metodę, która zwróci statystykę ile jest osób z danymi zarobkami <zarobki,liczba_osób> Map<Double,Integer>
// 5. Napisz taką sama metode jak w pkt 3) przy pomocy strumieni
// 6. Napisz metodę, która zwróci mapę map <imię,<zarobki,liczba_osób_z_takimi_zarobkami>>
public class Tasks {

    public static void main(String[] args) {
        // 1)
        List<Customer> customerList = getCustomers();
        // 2)
        List<String> nameAndSurnameList = giveMeNameAndSurnameList(customerList);
        // 3)
        List<String> nameAndSurnameListWithStream = giveMeNameAndSurnameListWithStream(customerList);
        // 4)
        Map<Double, Long> salaryStats = getSalaryStats(customerList);
        // 5)
        Map<Double, Long> salaryStatsWithStream = getSalaryStatsWithStream(customerList);
        // 6)
        Map<String, Map<Double, Long>> nameSalaryStats = getNameSalaryStats(customerList);
        iterateOverMap(nameSalaryStats);
    }

    // *Dodaj małe utrudnienie w postaci spacji w poszczególnych polach, zadbaj o to żeby kilka osób miały taka sama wypłatę
    public static List<Customer> getCustomers() {
        Customer[] people = new Customer[]{
                new Customer("Anna", "Nowak   ", 33, "1200"),
                new Customer("Beata", "Kowalska", 22, "1200"),
                new Customer("Marek", " Nowak", 25, "1250"),
                new Customer("Adam", "Twardowski", 33, "1100"),
                new Customer("Monika  ", "Kos", 25, "2500"),
                new Customer("Adam ", "Rudy", 45, "3333"),
                new Customer("Marek", "Rudy", 15, 2210),
                new Customer("Adam", "Madej", 15, 3333)
        };
        return Arrays.asList(people);
    }

    private static List<String> giveMeNameAndSurnameList(List<Customer> customerList) {
        List<String> result = new ArrayList<>();
        for (Customer customer : customerList) {
            String nameWithSurname = customer.getName().trim() + " " + customer.getSurname().trim();
            result.add(nameWithSurname);
        }
        return result;
    }

    private static List<String> giveMeNameAndSurnameListWithStream(List<Customer> customerList) {
        return customerList.stream()
                .map(e -> e.getName().trim() + " " + e.getSurname().trim())
                .collect(Collectors.toList());
    }

    private static Map<Double, Long> getSalaryStats(List<Customer> customerList) {
        Map<Double, Long> map = new HashMap<>();
        for (Customer customer : customerList) {
            if (map.containsKey(customer.getSalary())) {
                Long counter = map.get(customer.getSalary());
                map.replace(customer.getSalary(), counter + 1);
            } else {
                map.put(customer.getSalary(), 1L);
            }
        }
        return map;
    }

    private static Map<Double, Long> getSalaryStatsWithStream(List<Customer> customerList) {
        return customerList.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getSalary(),
                        Collectors.counting()
                        )
                );
    }

    private static Map<String, Map<Double, Long>> getNameSalaryStats(List<Customer> customerList) {
        Map<String, Map<Double, Long>> resultMap = new HashMap<>();
        customerList.stream().forEach(e -> e.setName(e.getName().trim()));   // pozbywamy się białych znaków

        for (Customer customer : customerList) {
            if (resultMap.containsKey(customer.getName())) {                 // sprawdzamy czy imię istnieje w mapie
                Map<Double, Long> innerMap = resultMap.get(customer.getName());
                if (innerMap.containsKey(customer.getSalary())) {            // fragment if else jak w getSalaryStats()
                    Long counter = innerMap.get(customer.getSalary());
                    innerMap.replace(customer.getSalary(), counter + 1);
                } else {
                    innerMap.put(customer.getSalary(), 1L);
                }
            } else {                                                         // sytuacja kiedy imię nie istnieje w mapie
                Map<Double, Long> newMap = new HashMap<>();                  // tworzymy nową mapę
                newMap.put(customer.getSalary(), 1L);                        // umieszczamy w niej odpowiednie dane
                resultMap.put(customer.getName(), newMap);                   // umieszczamy nową mapę do wynikowej mapy resultMap
            }
        }
        return resultMap;
    }

    private static void iterateOverMap(Map<String, Map<Double, Long>> nameSalaryStats) {
        for (String name : nameSalaryStats.keySet()) {
            System.out.println(name);
        }
        System.out.println("+--------------------------");
        for (Map<Double, Long> innerMap : nameSalaryStats.values()) {
            System.out.println(innerMap);
        }
        System.out.println("+--------------------------");
        for (Map.Entry<String, Map<Double, Long>> mapEntry : nameSalaryStats.entrySet()) {
            System.out.println(mapEntry);
        }
    }
}