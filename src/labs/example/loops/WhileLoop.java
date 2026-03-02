//James Burlinson; This program iterates using a while loop; 2/25/26

package labs.example.loops;

public class WhileLoop {
    public static void main(String[] args){
        executeWhileLoop();
    }

private static void executeWhileLoop(){
    int count = 0;
    while (count < 10){
        count++;
    }
    System.out.println("The loop is now complete. Loops:" + count );
}
}