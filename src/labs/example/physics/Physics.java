package labs.example.physics;

public class Physics {
    final static double GRAVITY = 9.81;
        
    public static double getDistance(double v, double t){
        double x = v * t;
        return x;
    }

    public static double getLightSpeedInMPH(){
        double lightSpeed = getLightSpeedInMPS() * 60 * 60;
        return lightSpeed;
    }

    public static double getTimeFromSunToEarthInHours(){
        double timeToEarth = getKnownDistanceToEarth() / getSpeedOfLightInMPH();
    }

    public static double getVelocity(double distance, )
}