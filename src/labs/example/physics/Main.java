//James Burlinson;This program runs methods defined in other programs in the physics folder;2/12/26

package labs.example.physics;

public class Main {
    public static void main(String[] args) {
        System.out.println("Distance: " + Physics.getDistance(60, 2) + " miles");
        System.out.println("Velocity: " + Physics.getVelocity(100, 2) + " mph");
        System.out.println("Momentum: " + Physics.getMomentum(10, 5) + " kg m/s");
        System.out.println("Force: " + Physics.getForce(10, 2) + " kg m/s^2");
        System.out.println("Work: " + Physics.getWork(10, 5) + " Joules");
        System.out.println("Kinetic Energy: " + Physics.getKineticEnergy(10, 5) + " Joules");
        System.out.println("Potential Energy: " + Physics.getPotentialEnergy(10, 10) + " Joules");
    }
}
