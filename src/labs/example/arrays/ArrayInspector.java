//James Burlinson, this program compares arrays, 3/18/22

package labs.example.arrays;

public class ArrayInspector {

    public static void main(String[] args) {
        // Define two arrays
        int[] firstArray = {1, 2, 3, 4, 5};
        int[] secondArray = {1, 2, 3, 4, 5};

        // Check if arrays have same length
        boolean sameLength = firstArray.length == secondArray.length;

        // Check if arrays have the same values
        boolean sameValues = true;
        if (sameLength) {
            for (int i = 0; i < firstArray.length; i++) {
                if (firstArray[i] != secondArray[i]) {
                    sameValues = false;
                    break;
                }
            }
        } else {
            sameValues = false;
        }

        if (sameLength) {
            System.out.println("Yes these arrays are the same length");
        } else {
            System.out.println("No these arrays are not the same length");
        }

        if (sameValues) {
            System.out.println("Yes these arrays do contain the same values");
        } else {
            System.out.println("No these arrays do not contain the same values");
        }
    }
}
