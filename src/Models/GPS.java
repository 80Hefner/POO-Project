package Models;

import java.io.Serializable;
import java.lang.Math;

import static java.lang.Math.pow;

public class GPS  implements Serializable
{
    private double latitude;
    private double longitude;

    public GPS()
    {
        this.latitude = 0;
        this.longitude = 0;
    }

    public GPS(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GPS(GPS gps)
    {
        this.latitude = gps.getLatitude();
        this.longitude = gps.getLongitude();
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        GPS gps = (GPS) o;

        return this.latitude == gps.getLatitude() &&
                this.latitude == gps.getLatitude();
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("(").append(this.latitude).append(", ").append(this.longitude).append(")");

        return sb.toString();
    }

    public GPS clone()
    {
        return new GPS(this);
    }

    //Mudar para plano XY em princípio
    /*
    public double distanceTo(GPS that)
    {
        double KMS_PER_NAUTICAL_MILE = 1.852;
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(that.latitude);
        double lon2 = Math.toRadians(that.longitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        return KMS_PER_NAUTICAL_MILE * nauticalMiles;
    }
    */

    public double distanceTo(GPS that)
    {
        double distancia = Math.sqrt(
                pow((this.getLatitude() - that.getLatitude()), 2) +
                pow((this.getLongitude() - that.getLongitude()), 2)
        );
        return distancia;
    }

    public boolean isReachable(GPS that, double raioDeDistribuicao)
    {
        return (this.distanceTo(that) <= raioDeDistribuicao);
    }
}
