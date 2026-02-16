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
}