import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transportadora
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private int nif;
    private double raio;
    private double classificacao;
    private int total_entregas;
    private double preco_km;
    private int limite;
    private boolean medical;
    private boolean available;
    private boolean availableMedical;
    private ArrayList<Encomenda> registos;

    public Transportadora()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.nif = 0;
        this.raio = 0;
        this.preco_km = 0;
        this.limite = 0;
        this.medical = false;
        this.available = false;
        this.availableMedical = false;
        this.registos = new ArrayList<>();
    }

    public Transportadora(String nome, String codigo, GPS coordenadas, int nif, double raio, double preco_km, int limite, boolean medical)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.nif = nif;
        this.raio = raio;
        this.classificacao = 0;
        this.total_entregas = 0;
        this.preco_km = preco_km;
        this.limite = limite;
        this.medical = medical;
        this.available = true;
        this.availableMedical = medical;
        this.registos = new ArrayList<>();
    }

    public Transportadora(Transportadora t)
    {
        this.nome = t.getNome();
        this.codigo = t.getCodigo();
        this.coordenadas = t.getCoordenadas();
        this.nif = t.getNif();
        this.raio = t.getRaio();
        this.classificacao = t.getClassificacao();
        this.total_entregas = t.getTotal_entregas();
        this.preco_km = t.getPreco_km();
        this.limite = t.getLimite();
        this.medical = t.isMedical();
        this.available = t.isAvailable();
        this.availableMedical = t.isAvailableMedical();
        this.registos = new ArrayList<>(t.getRegistos());
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

    public double getPreco_km()
    {
        return preco_km;
    }

    public void setPreco_km(double preco_km)
    {
        this.preco_km = preco_km;
    }

    public int getLimite()
    {
        return limite;
    }

    public void setLimite(int limite)
    {
        this.limite = limite;
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
        Transportadora t = (Transportadora) o;

        return this.nome.equals(t.getNome()) &&
                this.codigo.equals(t.getCodigo()) &&
                this.coordenadas.equals(t.getCoordenadas()) &&
                this.nif == t.getNif() &&
                this.raio == t.getRaio() &&
                this.classificacao == t.getClassificacao() &&
                this.total_entregas == t.getTotal_entregas() &&
                this.preco_km == t.getPreco_km() &&
                this.limite == t.getLimite() &&
                this.medical == t.isMedical() &&
                this.available == t.isAvailable() &&
                this.availableMedical == t.isAvailableMedical() &&
                this.registos.equals(new ArrayList<>(t.getRegistos()));
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nNIF: ").append(this.nif);
        sb.append("\nRaio: ").append(this.raio);
        sb.append("\nClassificação: ").append(this.classificacao);
        sb.append("\nTotal de entregas efetuadas: ").append(this.total_entregas);
        sb.append("\nPreço por km: ").append(this.preco_km);
        sb.append("\nLimite de encomendas: ").append(this.limite);
        sb.append("\nIs Medical? ").append(this.medical);
        sb.append("\nIs Available? ").append(this.available);
        sb.append("\nIs Available for Medical? ").append(this.availableMedical);
        sb.append("\nRegistos: \n").append(this.registos.toString());
        sb.append("\n");

        return sb.toString();
    }

    public Transportadora clone()
    {
        return new Transportadora(this);
    }
}
