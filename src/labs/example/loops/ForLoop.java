package labs.example.loops;

public class ForLoop {
    public static void main(String[] args){
        // Call executeForLoop to print loop iterations
        executeForLoop();
        
        // Call sumTwoNumbers with two arguments and decrement result
        int sum = sumTwoNumbers(5, 10);
        int decremented = --sum; // Use prefix decrement to decrease sum by 1
        System.out.println("Sum of 5 and 10 decremented: " + decremented);
        
        // Call sumLoopCounter to sum values
        int loopSum = sumLoopCounter(10);
        System.out.println("The sum of the loop counter is: " + loopSum);
        
        printMultiplicationTable(7);
    }

private static void executeForLoop(){
    for(int count = 0; count <= 20; count++)
        System.out.println("Loops:" + count);
}

private static int sumTwoNumbers(int a, int b){
    // Takes two integers and returns the sum
    return a + b;
}

private static int sumLoopCounter(int n){
    // Sums loop counter values
    int sum = 0;
    for(int count = 0; count < n; count++){
        sum += count;
    }
    return sum;
}

private static void printMultiplicationTable(int n){
    // Generates and prints multiplication table for given value
    for(int i = 0; i <= 12; i++){
        // Loops from 0 to 12 and prints multiplication result for each value
        System.out.println(n + " x " + i + " = " + (n * i));
    }
}
}