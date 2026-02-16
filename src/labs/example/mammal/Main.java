//James Burlinson;This program runs methods defined in other programs in the mammal folder;2/3/26

package labs.example.mammal;

public class Main {
    public static void main(String[] args) throws Exception{
    Dog dog = new Dog();
    
    dog.setName("Dog");
    String myDogName = dog.getName();
    System.out.println(myDogName);
    dog.sit();
    dog.bark();
}
}