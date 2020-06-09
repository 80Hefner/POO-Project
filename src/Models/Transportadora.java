package Models;

import java.util.Random;

public class Transportadora extends Voluntario
{
    private int nif;
    private double preco_km;
    private int limite;
    //Colocar aqui um set com os códigos das Encomendas á espera de resposta para entregar?

    public Transportadora()
    {
        super();
        this.nif = 0;
        this.preco_km = 0;
        this.limite = 0;
    }

    public Transportadora(String nome, String codigo, GPS coordenadas, String password, double velocidadeMedia, int nif, double raio, double preco_km, int limite, boolean medical)
    {
        super(nome,codigo,coordenadas,password, velocidadeMedia,raio,medical);
        this.nif = nif;
        this.preco_km = preco_km;
        this.limite = limite;
    }

    public Transportadora(Transportadora t)
    {
        super(t);
        this.nif = t.getNif();
        this.preco_km = t.getPreco_km();
        this.limite = t.getLimite();
    }

    public int getNif()
    {
        return nif;
    }

    public void setNif(int nif)
    {
        this.nif = nif;
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

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Transportadora t = (Transportadora) o;

        return super.equals(t) &&
                this.nif == t.getNif() &&
                this.preco_km == t.getPreco_km() &&
                this.limite == t.getLimite();
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(super.getNome());
        sb.append("\nCodigo: ").append(super.getCodigo());
        sb.append("\nCoordenadas: ").append(super.getCoordenadas().toString());
        sb.append("\nPaswword: ").append(super.getPassword());
        sb.append("\nVelocidade Média: ").append(super.getVelocidadeMedia());
        sb.append("\nNIF: ").append(this.nif);
        sb.append("\nRaio: ").append(super.getRaio());
        sb.append("\nPreço por km: ").append(this.preco_km);
        sb.append("\nLimite de encomendas: ").append(this.limite);
        sb.append("\nClassificação: ").append(super.getClassificacao());
        sb.append("\nTotal de entregas efetuadas: ").append(super.getTotal_entregas());
        sb.append("\nIs Medical? ").append(super.isMedical());
        sb.append("\nIs Available? ").append(super.isAvailable());
        sb.append("\nIs Available for Medical? ").append(super.isAvailableMedical());
        sb.append("\nRegistos: \n").append(super.getEncomendasHistorico().toString());
        sb.append("\n");

        return sb.toString();
    }

    public Transportadora clone()
    {
        return new Transportadora(this);
    }


    public void realizaEntregaDeVenda(Encomenda enc, Loja loja, Utilizador utilizador) {

        super.realizaEntregaDeVenda(enc, loja, utilizador);

        double distance = super.getCoordenadas().distanceTo(loja.getCoordenadas()) + super.getCoordenadas().distanceTo(utilizador.getCoordenadas());
        double precoTransporte = this.getPreco_km() * distance * ((enc.getPeso() / 25)*0.25 + 1);

        enc.setPrecoTransporte(precoTransporte);
    }
}
