import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedList {
    private static List<Integer> arrayList = new ArrayList<>();
    private static List<Integer> linkedList = new LinkedList<>();

    public static void main(String[] args) {
        arrayListCompareToLinkedList();
    }

    private static void arrayListCompareToLinkedList() {
        int howManyTimes = 50_000;
        addAtEnd(howManyTimes, arrayList);
        addAtEnd(howManyTimes, linkedList);
        addAtStart(howManyTimes, arrayList);
        addAtStart(howManyTimes, linkedList);
        addAllElements(arrayList);
        addAllElements(linkedList);
        addAllElementsUsingForeach(arrayList);
        addAllElementsUsingForeach(linkedList);
        streamExamples();
    }

    private static void streamExamples() {
        long start = System.currentTimeMillis();
        arrayList.stream()  //to jest tożsame
                .mapToInt(e -> e)
                .sum();

        arrayList.stream() //z tym
                .reduce((a, b) -> a + b).get();

        linkedList.stream()
                .mapToInt(e -> e)
                .sum();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void addAllElementsUsingForeach(List<Integer> list) {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (Integer element : list) {
            sum = sum + element;
        }
        long finish = System.currentTimeMillis();
        System.out.println("Wykonanie sumowania w pętli foreach trwało "
                + list.getClass().getSimpleName()
                + ": " + (finish - start) + " ms");
    }

    private static void addAllElements(List<Integer> list) {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
//            sum = sum + list.get(i); jest tożsame
        }
        long finish = System.currentTimeMillis();
        System.out.println("Wykonanie sumowania po indeksie trwało "
                + list.getClass().getSimpleName()
                + ": " + (finish - start) + " ms");
    }

    private static void addAtStart(int howManyTimes, List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < howManyTimes; i++) {
            list.add(0, i);
        }
        long finish = System.currentTimeMillis();
        System.out.println("Wykonanie wstawiania na początek trwało "
                + list.getClass().getSimpleName()
                + ": " + (finish - start) + " ms");
    }

    private static void addAtEnd(int howManyTimes, List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < howManyTimes; i++) {
            list.add(i);
        }
        long finish = System.currentTimeMillis();
        System.out.println("Wykonanie wstawiania na koniec trwało "
                + list.getClass().getSimpleName()
                + ": " + (finish - start) + " ms");
    }
}
