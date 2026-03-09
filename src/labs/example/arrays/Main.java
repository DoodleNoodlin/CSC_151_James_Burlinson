package labs.example.arrays;

public class Main {
    
    public static void main(String[] args) {
        ArrayOperations operations = new ArrayOperations();
        
        System.out.println("=== Testing createNewArray ===");
        operations.createNewArray(5);
        
        System.out.println("\n=== Testing sortArray ===");
        // Create an array of 50 values in random order
        int[] unsortedArray = {
            45, 23, 89, 12, 56, 34, 78, 90, 11, 67,
            2, 99, 33, 54, 22, 77, 8, 100, 19, 41,
            61, 15, 73, 50, 28, 98, 5, 84, 39, 76,
            10, 52, 63, 25, 88, 7, 69, 36, 82, 47,
            60, 14, 72, 31, 92, 43, 29, 85, 13, 74
        };
        operations.sortArray(unsortedArray);
        
        System.out.println("\n=== Testing getDaysAndMonths ===");
        operations.getDaysAndMonths();
    }
}
