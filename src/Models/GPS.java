package Models;

import java.io.Serializable;
import java.lang.Math;

import static java.lang.Math.pow;

/**
 * Classe que representa GPS/Coordenadas das Entidades
 */
public class GPS  implements Serializable
{
    private double latitude;
    private double longitude;

    /**
     * Construtor por omissão do GPS
     */
    public GPS()
    {
        this.latitude = 0;
        this.longitude = 0;
    }

    /**
     * Construtor Parametrizado do GPS
     * @param latitude      latitude das Coordenadas
     * @param longitude     longitude das Coordenadas
     */
    public GPS(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Construtor de cópia do GPS
     * @param gps   GPS que se pretende copiar
     */
    public GPS(GPS gps)
    {
        this.latitude = gps.getLatitude();
        this.longitude = gps.getLongitude();
    }

    /**
     * Getter da Latitude do GPS
     * @return  Latitude do GPS
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Setter da Latitude do GPS
     * @param latitude   Latitude do GPS
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * Getter da Longitude do GPS
     * @return  Longitude do GPS
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * Setter da Longitude do GPS
     * @param longitude   Longitude do GPS
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * Função de equals do GPS
     * @param o           Objeto ao qual queremos comparar o GPS
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        GPS gps = (GPS) o;

        return this.latitude == gps.getLatitude() &&
                this.latitude == gps.getLatitude();
    }

    /**
     * Função que transforma o GPS e os seus dados numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("(").append(this.latitude).append(", ").append(this.longitude).append(")");

        return sb.toString();
    }

    /**
     * Função que dá clone ao GPS
     * @return           Cópia do GPS
     */
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

    /**
     * Função que calcula a distancia entre dois pontos GPS
     * @param that      Ponto GPS ao qual querems calcular a distância
     * @return          Distancia entre os dois pontos
     */
    public double distanceTo(GPS that)
    {
        double distancia = Math.sqrt(
                pow((this.getLatitude() - that.getLatitude()), 2) +
                pow((this.getLongitude() - that.getLongitude()), 2)
        );
        return distancia;
    }

    /**
     * Função que determina se é capaz de se alcançar um dado ponto, dado um raio de alcance
     * @param that                  Ponto GPS que queremos estudar
     * @param raioDeDistribuicao    Raio de alcance possuído
     * @return                      Booleano que determina se é possivel alcancar o ponto dado ou não
     */
    public boolean isReachable(GPS that, double raioDeDistribuicao)
    {
        return (this.distanceTo(that) <= raioDeDistribuicao);
    }
}
