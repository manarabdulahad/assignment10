import java.io.*;
import java.util.*;

public class GenericSortComparison {

    // Method to create a random array of Integers
    public static Integer[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        Integer[] array = new Integer[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101);  // Random integer between 0 and 100
        }
        return array;
    }

    // Method to write the array to a file
    public static <T> void writeArrayToFile(T[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (T item : array) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // Method to read the array from a file (for Integer type)
    public static Integer[] readFileToArray(String filename) {
        List<Integer> tempList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tempList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
        
        return tempList.toArray(new Integer[0]);
    }

    
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    // Swap elements
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array.length < 2) {
            return;  
        }
        
        int mid = array.length / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);

        
        mergeSort(left);
        mergeSort(right);

        
        merge(array, left, right);
    }

    
    public static <T extends Comparable<T>> void merge(T[] array, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;
        
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        
        
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("Enter the length of the array to create:");
        int length = scanner.nextInt();
        
        
        Integer[] array = createRandomArray(length);
        
        
        System.out.println("Generated random array:");
        System.out.println(Arrays.toString(array));

        
        Integer[] bubbleSortedArray = Arrays.copyOf(array, array.length);  
        long startTime = System.nanoTime();
        bubbleSort(bubbleSortedArray);
        long endTime = System.nanoTime();
        long bubbleSortTime = endTime - startTime;
        System.out.println("Bubble sorted array:");
        System.out.println(Arrays.toString(bubbleSortedArray));
        System.out.println("BubbleSort took " + bubbleSortTime + " nanoseconds");

        
        Integer[] mergeSortedArray = Arrays.copyOf(array, array.length);  
        startTime = System.nanoTime();
        mergeSort(mergeSortedArray);
        endTime = System.nanoTime();
        long mergeSortTime = endTime - startTime;
        System.out.println("Merge sorted array:");
        System.out.println(Arrays.toString(mergeSortedArray));
        System.out.println("MergeSort took " + mergeSortTime + " nanoseconds");
        
        
        System.out.println("Enter a filename to save the array:");
        scanner.nextLine();  
        String filename = scanner.nextLine();
        
        writeArrayToFile(array, filename);
        System.out.println("Array has been written to " + filename);
        
        Integer[] arrayFromFile = readFileToArray(filename);
        System.out.println("Array read from the file:");
        System.out.println(Arrays.toString(arrayFromFile));

        
        System.out.println("\nExample with String array:");
        String[] stringArray = {"banana", "apple", "cherry", "date", "elderberry"};
        System.out.println("Original string array:");
        System.out.println(Arrays.toString(stringArray));
        
        
        String[] bubbleSortedStrings = Arrays.copyOf(stringArray, stringArray.length);
        bubbleSort(bubbleSortedStrings);
        System.out.println("Bubble sorted string array:");
        System.out.println(Arrays.toString(bubbleSortedStrings));
        
        
        String[] mergeSortedStrings = Arrays.copyOf(stringArray, stringArray.length);
        mergeSort(mergeSortedStrings);
        System.out.println("Merge sorted string array:");
        System.out.println(Arrays.toString(mergeSortedStrings));

        scanner.close();
    }
}
