public class Transportadora
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private int nif;
    private double raio;
    private double preco_km;
    private boolean medical;
    private boolean available;
    private boolean availableMedical;

    public Transportadora()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.nif = 0;
        this.raio = 0;
        this.preco_km = 0;
        this.medical = false;
        this.available = false;
        this.availableMedical = false;
    }

    public Transportadora(String nome, String codigo, GPS coordenadas, int nif, double raio, double preco_km, boolean medical, boolean available, boolean availableMedical)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.nif = nif;
        this.raio = raio;
        this.preco_km = preco_km;
        this.medical = medical;
        this.available = available;
        this.availableMedical = availableMedical;
    }

    public Transportadora(Transportadora t)
    {
        this.nome = t.getNome();
        this.codigo = t.getCodigo();
        this.coordenadas = t.getCoordenadas();
        this.nif = t.getNif();
        this.raio = t.getRaio();
        this.preco_km = t.getPreco_km();
        this.medical = t.isMedical();
        this.available = t.isAvailable();
        this.availableMedical = t.isAvailableMedical();
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

    public int getNif()
    {
        return nif;
    }

    public void setNif(int nif)
    {
        this.nif = nif;
    }

    public double getRaio()
    {
        return raio;
    }

    public void setRaio(double raio)
    {
        this.raio = raio;
    }

    public double getPreco_km()
    {
        return preco_km;
    }

    public void setPreco_km(double preco_km)
    {
        this.preco_km = preco_km;
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
        Transportadora t = (Transportadora) o;

        return this.nome.equals(t.getNome()) &&
                this.codigo.equals(t.getCodigo()) &&
                this.coordenadas.equals(t.getCoordenadas()) &&
                this.nif == t.getNif() &&
                this.raio == t.getRaio() &&
                this.preco_km == t.getPreco_km() &&
                this.medical == t.isMedical() &&
                this.available == t.isAvailable() &&
                this.availableMedical == t.isAvailableMedical();
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nNIF: ").append(this.nif);
        sb.append("\nRaio: ").append(this.raio);
        sb.append("\nPreço por km: ").append(this.preco_km);
        sb.append("\nIs Medical? ").append(this.medical);
        sb.append("\nIs Available? ").append(this.available);
        sb.append("\nIs Available for Medical? ").append(this.availableMedical);
        sb.append("\n");

        return sb.toString();
    }

    public Transportadora clone()
    {
        return new Transportadora(this);
    }
}
