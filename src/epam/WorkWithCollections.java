package epam;

import java.util.LinkedList;
import java.util.List;

public class WorkWithCollections {
    
    public static void main(String[] args) {
        // Создаем и заполняем каждый раз новый список:
        testLinkedList();
    }

    // Есть некоторые вещи, которые
    // может делать только LinkedList:
    private static void testLinkedList() {
        LinkedList ll = new LinkedList();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        ll.add("d");
        ll.add("e");
        ll.add("f");
        ll.add("g");
        ll.add("h");
        ll.add("k");
        System.out.println(ll);
        // Трактуем его, как стек, вталкиваем:
        ll.addFirst("one");
        ll.addFirst("two");
        System.out.println(ll);
        // Аналогично "заглядыванию" в вершину стека:
        System.out.println(ll.getFirst());
        // Аналогично выталкиванию из стека:
        System.out.println(ll.removeFirst());
        System.out.println(ll.removeFirst());
        // Трактуем, как очередь, вталкиваем элементы
        // и вытаскиваем с конца:
        System.out.println(ll.removeLast());
        // С обеими приведенными выше операциями - это двойная очередь!
        System.out.println(ll);
    }
}
