package labs.example.mammal;

public class Mammal {
    
    private String hairColor = "Blonde";
    private String eyeColor = "Brown";
    private String bodyTemp = "98'";
    private String height = "4 Feet";
    private String food = "Meat";
    private String weight = "95 LB";

    public void getMammalDetails(){
        System.out.println(this.eyeColor);
        System.out.println(this.hairColor);
        System.out.println(this.bodyTemp);
        System.out.println(this.height);
        System.out.println(this.food);
        System.out.println(this.weight);
    }
    public void walk() {
    }

    public void eat() {
    }

    public void sleep() {
    }

    public void sit() throws Exception{
        Thread.sleep(15000);
        System.out.println("The mammal is no longer sitting");
        Thread.sleep(3000);
        stand();
    }

    public void stand() {
        System.out.println("The mammal is now up and barking");
    }

    public void scratch() {
    }

    public void jump() {
    }

    public void growl() {
    }

    public void shake() {
    }

    public void bite() {
    }

    public void stretch() {
    }
}