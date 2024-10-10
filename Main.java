//import java.util.*;
/*
 * IT-2660 - Lab 1
 * Student Name: Sarah Griffin
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("hello, world!");
        
        Lab1 lab = new Lab1();
        System.out.println(lab.increment(1));
        
        // Create an array with the given values
        int[] numbers = {5, 9, 3, 12, 7, 3, 11, 5};
        
        // Output the array in order using a while loop
        System.out.println("Array in order:");
        int i = 0;
        while (i < numbers.length) {
            System.out.print(numbers[i] + " ");
            i++;
        }
        System.out.println();
        
        // Output the array in reverse using a for loop
        System.out.println("Array in reverse:");
        for (int j = numbers.length - 1; j >= 0; j--) {
            System.out.print(numbers[j] + " ");
        }
        System.out.println();
        
        // Output the first and last values of the array
        System.out.println("First value: " + numbers[0]);
        System.out.println("Last value: " + numbers[numbers.length - 1]);
        
        // Call the methods created in Lab1
        System.out.println("Max of 5 and 9: " + lab.max(5, 9));
        System.out.println("Min of 5 and 9: " + lab.min(5, 9));
        System.out.println("Sum of array: " + lab.sum(numbers));
        System.out.println("Average of array: " + lab.average(numbers));
        System.out.println("Max value in array: " + lab.max(numbers));
        System.out.println("Min value in array: " + lab.min(numbers));
    }
}

class Lab1 {
    public int increment(int num) {
        return ++num;
    }
    
    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
    
    public int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }
    
    public int sum(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        return total;
    }
    
    public double average(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (double) sum / nums.length;
    }
    
    public int max(int[] nums) {
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }
        return maxVal;
    }
    
    public int min(int[] nums) {
        int minVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
        }
        return minVal;
    }
}