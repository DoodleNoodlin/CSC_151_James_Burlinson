//James Burlinson;This program creates, modifies, and sorts arrays;3/2/26

package labs.example.arrays;

public class ArrayOperations {
    
    // Create integer array and display
    public void createNewArray(int size) {
        int[] array = new int[size];
        
        // Initialize array elements
        for (int i = 0; i < array.length; i++) {
            array[i] = size + i;
        }
        
        // Display the array
        displayArray(array);
    }
    
    // Prints array info
    private void displayArray(int[] array) {
        System.out.println("I created a new array and it now has " + array.length + " items in it.");
        System.out.println("The array items and their values are listed below: ");
        for (int value : array) {
            System.out.println(value);
        }
    }
    
    // Sorts an integer array using bubble sort
    public void sortArray(int[] array) {
        int[] arr = array.clone();
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted array: ");
        for (int value : arr) {
            System.out.println(value);
        }
    }
    
    // Create and print month name arrays and corresponding days
    public void getDaysAndMonths() {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] months = {"January", "February", "March", "April", "May", "June", 
                          "July", "August", "September", "October", "November", "December"};
        
        // Display the number of days in each month.
        for (int i = 0; i < months.length; i++) {
            System.out.println("There are " + days[i] + " days in " + months[i]);
        }
    }
    
    // Sum three arrays and find the one with highest sum
    public void sumAndCompareArrays() {
        int[] arr1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        int[] arr2 = {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
        int[] arr3 = {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
        
        // Sum arr1
        int sum1 = 0;
        for (int num : arr1) {
            sum1 += num;
        }
        System.out.println("Sum of arr1: " + sum1);
        
        // Sum arr2
        int sum2 = 0;
        for (int num : arr2) {
            sum2 += num;
        }
        System.out.println("Sum of arr2: " + sum2);
        
        // Sum arr3
        int sum3 = 0;
        for (int num : arr3) {
            sum3 += num;
        }
        System.out.println("Sum of arr3: " + sum3);
        
        // Find the highest sum
        if (sum1 >= sum2 && sum1 >= sum3) {
            System.out.println("arr1 has the highest sum: " + sum1);
        } else if (sum2 >= sum1 && sum2 >= sum3) {
            System.out.println("arr2 has the highest sum: " + sum2);
        } else {
            System.out.println("arr3 has the highest sum: " + sum3);
        }
    }
    
    // Find the highest value in an array
    public void findHighestValue(int[] myArray) {
        if (myArray.length == 0) {
            System.out.println("Array is empty");
            return;
        }
        int highest = myArray[0];
        for (int i = 1; i < myArray.length; i++) {
            if (myArray[i] > highest) {
                highest = myArray[i];
            }
        }
        System.out.println("The highest value in the myArray object is: " + highest);
    }
}
