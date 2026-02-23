//James Burlinson;This program is a class with methods that describe math equations;2/12/26

package labs.example.physics;

import java.lang.Math;

public class Physics {
    final static double GRAVITY = 9.81;
        
    public static double getDistance(double v, double t){
        double x = v * t;
        return x;
    }

    public static double getVelocity(double distance, double t){
        return distance / t;
    }

    public static double getMomentum(double m, double v){
        return m * v;
    }

    public static double getTheta(double xVal, double yVal){
        double theta = Math.toDegrees(Math.atan2(yVal, xVal));
        return theta;
    }
    
    // Logs when an angle is not valid for a 3-4-5
    public static void logInvalidAngleInfo(double angle){
        System.out.println("logging the angle " + angle + " degrees. This is not a right angle.");
    }

    // Logs when an angle is valid for a 3-4-5
    public static void logValidAngleInfo(double angle){
        System.out.println("logging the angle " + angle + " degrees. This is a valid 3-4-5 triangle");
    }
    
    public static double getForce(double m, double a){
        return m * a;
    }

    public static double getWork(double f, double d){
        return f * d;
    }

    public static double getKineticEnergy(double m, double v){
        return 0.5 * m * Math.pow(v, 2);
    }

    public static double getPotentialEnergy(double m, double h){
        return m * GRAVITY * h;
    }

    // Returns converted speed of light
    public static double getLightSpeedInMPH(){
        return 186282.0 * 3600.0;
    }

    // Computes time for light to travel from the Sun to Earth
    public static double getTimeFromSunToEarthInHours(){
        return getKnownDistanceToEarth() / getLightSpeedInMPH();
    }

    // Average Earth-Sun distance in miles
    public static double getKnownDistanceToEarth(){
        return 92947266.72;
    }

    // Logs when computed Earth-Sun distance dosen't match known distance
    public static void logEarthToSunInvalidDistance(double distance){
        System.out.println("logging invalid Earth-Sun distance: " + distance);
    }
}