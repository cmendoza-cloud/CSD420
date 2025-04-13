// Carmen Mendoza 
// CSD420 
// Module 5 

// Write a test program that reads words from a text file and displays all non-duplicate words 
// in ascending order and then in descending order.  

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordSorter {

    public static void main(String[] args) {
        String filename = "collection_of_words.txt";

        // Read words from file
        List<String> words = readWordsFromFile(filename);

        // Exit early if no words were read
        if (words.isEmpty()) {
            System.out.println("No words read from the file.");
            return;
        }

        // Use TreeSet to remove duplicates and sort automatically (case-insensitive)
        Set<String> uniqueWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        uniqueWords.addAll(words);

        // Display in ascending order
        System.out.println("Ascending order:");
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        // Display in descending order
        System.out.println("\nDescending order:");
        List<String> descendingList = new ArrayList<>(uniqueWords);
        Collections.reverse(descendingList);
        for (String word : descendingList) {
            System.out.println(word);
        }

        // Test to verify functionality
        test(uniqueWords);
    }

    // Reads words from the file and returns a list
    public static List<String> readWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File(filename));
            while (input.hasNext()) {
                words.add(input.next());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return words;
    }

    // Testing to confirm the code is working
    public static void test(Set<String> uniqueWords) {
        System.out.println("\n[Test] Total unique words: " + uniqueWords.size());
        if (uniqueWords.contains("dog")) {
            System.out.println("[Test Passed] Word 'dog' found");
        } else {
            System.out.println("[Test Failed] Word 'dog' not found");
        }
    }
}