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
}
