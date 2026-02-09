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
    public int walk() {
        int southDistance = 3;
        
        int eastDistance = 4;
        
        int distance = (int) Math.sqrt(Math.pow(southDistance, 2) + Math.pow(eastDistance, 2));
        
        double angleRadians = Math.atan2(southDistance, eastDistance);
        double angleDegrees = Math.toDegrees(angleRadians);
        
        System.out.println("Mammal walked 3 miles South, then 4 miles East");
        System.out.println("Straight-line distance from start to end: " + distance + " miles");
        System.out.println("Angle theta at the longest leg: " + angleDegrees + " degrees");
        
        return distance;
    }

    public void eat() {
    }

    public void mammal(String name){

    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
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