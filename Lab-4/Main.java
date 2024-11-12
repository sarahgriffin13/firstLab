import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Unsorted Array ---------------------------------------------------");
        ArrayList<Integer> integerList = Lab4.getList();
        Lab4.outputList(integerList);

        // Measure time for bubble sort
        System.out.println("\n\nBubble sort results ----------------------------------------------");
        long startTimeBubble = System.nanoTime();
        ArrayList<Integer> bubbleSortedList = Lab4.bubbleSort(new ArrayList<>(integerList));
        long endTimeBubble = System.nanoTime();
        Lab4.outputList(bubbleSortedList);
        System.out.println("\nTime taken for bubble sort: " + (endTimeBubble - startTimeBubble) + " nanoseconds");

        // Measure time for insertion sort
        System.out.println("\n\nInsertion sort results -------------------------------------------");
        long startTimeInsertion = System.nanoTime();
        ArrayList<Integer> insertionSortedList = Lab4.insertionSort(new ArrayList<>(integerList));
        long endTimeInsertion = System.nanoTime();
        Lab4.outputList(insertionSortedList);
        System.out.println("\nTime taken for insertion sort: " + (endTimeInsertion - startTimeInsertion) + " nanoseconds");
    }
}

class Lab4 {
    public static ArrayList<Integer> insertionSort(ArrayList<Integer> integerList) {
        // Step 1 - Implement insertion sort algorithm here
        for (int i = 1; i < integerList.size(); i++) {
            int key = integerList.get(i);
            int j = i - 1;

            // Move elements that are greater than key to one position ahead of their current position
            while (j >= 0 && integerList.get(j) > key) {
                integerList.set(j + 1, integerList.get(j));
                j = j - 1;
            }
            integerList.set(j + 1, key);
        }
        return integerList;
    }

    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> integerList) {
        // Step 2 - Implement the bubble sort algorithm here
        int n = integerList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (integerList.get(j) > integerList.get(j + 1)) {
                    // Swap integerList[j] and integerList[j+1]
                    int temp = integerList.get(j);
                    integerList.set(j, integerList.get(j + 1));
                    integerList.set(j + 1, temp);
                }
            }
        }
        return integerList;
    }

    public static ArrayList<Integer> getList() {
        ArrayList<Integer> integerList = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("integers.txt"))) {
            while ((line = br.readLine()) != null) {
                integerList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;
    }

    public static void outputList(ArrayList<Integer> integerList) {
        for (int i = 0; i < integerList.size(); i++) {
            System.out.print(integerList.get(i) + " ");
        }
        System.out.println();
    }
}

/*
Answers in comments:
1. If I were implementing a sort algorithm for a new language, I would use insertion sort for small datasets due to its simplicity and efficiency. For larger datasets, I would use more advanced algorithms like quicksort or mergesort.

2. Was there a difference in the time it took for bubble and insertion sort to run? Yes, insertion sort typically runs faster than bubble sort due to fewer swaps and shifts. This aligns with their time complexities: insertion sort has O(n^2) in the worst case but performs better in nearly sorted arrays, while bubble sort has O(n^2) in all cases.

3. Which sort algorithm has an easier implementation (in terms of understanding) to you? Insertion sort has a simpler implementation for me as it intuitively simulates the way we sort cards by picking and placing them in order.
*/
