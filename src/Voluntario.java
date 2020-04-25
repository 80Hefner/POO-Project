import javax.accessibility.AccessibleValue;

public class Voluntario
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private double raio;
    private boolean medical;
    private boolean available;
    private boolean availableMedical;

    public Voluntario()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.raio = 0;
        this.medical = false;
        this.available = false;
        this.availableMedical = false;
    }

    public Voluntario(String nome, String codigo, GPS coordenadas, double raio, boolean medical, boolean available, boolean availableMedical)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.raio = raio;
        this.medical = medical;
        this.available = available;
        this.availableMedical = availableMedical;
    }

    public Voluntario(Voluntario v)
    {
        this.nome = v.getNome();
        this.codigo = v.getCodigo();
        this.coordenadas = v.getCoordenadas();
        this.raio = v.getRaio();
        this.medical = v.isMedical();
        this.available = v.isAvailable();
        this.availableMedical = v.isAvailableMedical();
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public GPS getCoordenadas()
    {
        return coordenadas.clone();
    }

    public void setCoordenadas(GPS coordenadas)
    {
        this.coordenadas = coordenadas.clone();
    }

    public double getRaio()
    {
        return raio;
    }

    public void setRaio(double raio)
    {
        this.raio = raio;
    }

    public boolean isMedical()
    {
        return medical;
    }

    public void setMedical(boolean medical)
    {
        this.medical = medical;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    public boolean isAvailableMedical()
    {
        return availableMedical;
    }

    public void setAvailableMedical(boolean availableMedical)
    {
        this.availableMedical = availableMedical;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Voluntario v = (Voluntario) o;

        return this.nome.equals(v.getNome()) &&
                this.codigo.equals(v.getCodigo()) &&
                this.coordenadas.equals(v.getCoordenadas()) &&
                this.raio == v.getRaio() &&
                this.medical == v.isMedical() &&
                this.available == v.isAvailable() &&
                this.availableMedical == v.isAvailableMedical();
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nRaio: ").append(this.raio);
        sb.append("\nIs Medical? ").append(this.medical);
        sb.append("\nIs Available? ").append(this.available);
        sb.append("\nIs Available for Medical? ").append(this.availableMedical);
        sb.append("\n");

        return sb.toString();
    }

    public Voluntario clone()
    {
        return new Voluntario(this);
    }
}
