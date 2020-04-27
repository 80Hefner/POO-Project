import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Voluntario
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private double raio;
    private double classificacao;
    private int total_entregas;
    private boolean medical;
    private boolean available;
    private boolean availableMedical;
    private ArrayList<Encomenda> registos;

    public Voluntario()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.raio = 0;
        this.medical = false;
        this.available = false;
        this.availableMedical = false;
        this.registos = new ArrayList<>();
    }

    public Voluntario(String nome, String codigo, GPS coordenadas, double raio, boolean medical)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.raio = raio;
        this.classificacao = 0;
        this.total_entregas = 0;
        this.medical = medical;
        this.available = true;
        this.availableMedical = medical;
        this.registos = new ArrayList<>();
    }

    public Voluntario(Voluntario e)
    {
        this.nome = e.getNome();
        this.codigo = e.getCodigo();
        this.coordenadas = e.getCoordenadas().clone();
        this.raio = e.getRaio();
        this.classificacao = 0;
        this.total_entregas = 0;
        this.medical = e.isMedical();
        this.available = true;
        this.availableMedical = medical;
        this.registos = new ArrayList<>();
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

    public double getClassificacao()
    {
        return classificacao;
    }

    public void setClassificacao(double classificacao)
    {
        this.classificacao = classificacao;
    }

    public int getTotal_entregas()
    {
        return total_entregas;
    }

    public void setTotal_entregas(int total_entregas)
    {
        this.total_entregas = total_entregas;
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

    public List<Encomenda> getRegistos()
    {
        return registos.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setRegistos(ArrayList<Encomenda> registos)
    {
        this.registos = registos.stream().map(Encomenda::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Voluntario e = (Voluntario) o;

        return this.nome.equals(e.getNome()) &&
                this.codigo.equals(e.getCodigo()) &&
                this.coordenadas.equals(e.getCoordenadas()) &&
                this.raio == e.getRaio() &&
                this.classificacao == e.getClassificacao() &&
                this.total_entregas == e.getTotal_entregas() &&
                this.medical == e.isMedical() &&
                this.available == e.isAvailable() &&
                this.availableMedical == e.isAvailableMedical() &&
                this.registos.equals(new ArrayList<>(e.getRegistos()));
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nRaio: ").append(this.raio);
        sb.append("\nClassificação: ").append(this.classificacao);
        sb.append("\nTotal de entregas efetuadas: ").append(this.total_entregas);
        sb.append("\nIs Medical? ").append(this.medical);
        sb.append("\nIs Available? ").append(this.available);
        sb.append("\nIs Available for Medical? ").append(this.availableMedical);
        sb.append("\nRegistos: \n").append(this.registos.toString());
        sb.append("\n");

        return sb.toString();
    }

    public Voluntario clone()
    {
        return new Voluntario(this);
    }
}
