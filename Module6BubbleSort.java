//Carmen Mendoza
//CSD420
//Module 6
// Write a program with the two following generic methods using a bubble sort 
//Resource:https://www.geeksforgeeks.org/java-program-for-bubble-sort/

import java.util.Comparator;

public class Module6BubbleSort {

    // Bubble sort using Comparable 
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    // Bubble sort using Comparator
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    // Helper method to print array
    public static <E> void printArray(E[] array) {
        System.out.print("{ ");
        for (E e : array) {
            System.out.print(e + " ");
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        // Test 1: Sorting Integer array using Comparable
        Integer[] testNumbers = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        System.out.println("Original array (Comparable):");
        printArray(testNumbers);

        bubbleSort(testNumbers); // Using Comparable
        System.out.println("Sorted array (Comparable):");
        printArray(testNumbers);

        // Test 2: Sorting Integer array using Comparator
        Integer[] testNumbers2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("\nOriginal array (Comparator):");
        printArray(testNumbers2);

        bubbleSort(testNumbers2, Comparator.naturalOrder()); // Using Comparator
        System.out.println("Sorted array (Comparator):");
        printArray(testNumbers2); 

    }
}
