package Models;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Classe que representa cada Transportadora e que extende o Voluntário
 */
public class Transportadora extends Voluntario implements Serializable
{
    private int nif;
    private double preco_km;
    private int limite;

    /**
     * Construtor por omissão da Transportadora
     */
    public Transportadora()
    {
        super();
        this.nif = 0;
        this.preco_km = 0;
        this.limite = 0;
    }

    /**
     * Construtor parametrizado da Transportadora
     * @param nome              Nome da Transportadora
     * @param codigo            Código referente á Transportadora
     * @param coordenadas       Coordenadas iniciais da Transportadora
     * @param password          Password da Transportadora
     * @param velocidadeMedia   Velocidade Média da Transportadora
     * @param nif               Nif da Transportadora
     * @param raio              Raio de distribuiçao da Transportadora
     * @param preco_km          Preco por Km feito exercido pela Transportadora
     * @param limite            Limite de encomendas que Transportadora pode transportar de uma só vez
     * @param medical           Booleano que indica se Transportadora está preparado para entregar Encomendas Médicas
     */
    public Transportadora(String nome, String codigo, GPS coordenadas, String password, double velocidadeMedia, int nif, double raio, double preco_km, int limite, boolean medical)
    {
        super(nome,codigo,coordenadas,password, velocidadeMedia,raio,medical);
        this.nif = nif;
        this.preco_km = preco_km;
        this.limite = limite;
    }

    /**
     * Construtor de cópia da Transportadora
     * @param t     Transportadora a copiar
     */
    public Transportadora(Transportadora t)
    {
        super(t);
        this.nif = t.getNif();
        this.preco_km = t.getPreco_km();
        this.limite = t.getLimite();
    }

    /**
     * Getter do Nif da Transportadora
     * @return  Nif da Transportadora
     */
    public int getNif()
    {
        return nif;
    }

    /**
     * Setter do Nif da Transportadora
     * @param nif   Nif da Transportadora
     */
    public void setNif(int nif)
    {
        this.nif = nif;
    }

    /**
     * Getter do Preco por Km da Transportadora
     * @return  Preco por Km da Transportadora
     */
    public double getPreco_km()
    {
        return preco_km;
    }

    /**
     * Setter do Preco por Km da Transportadora
     * @param preco_km   Preco por Km da Transportadora
     */
    public void setPreco_km(double preco_km)
    {
        this.preco_km = preco_km;
    }

    /**
     * Getter do Limite de entregas simultaneas da Transportadora
     * @return  Limite de entregas simultaneas da Transportadora
     */
    public int getLimite()
    {
        return limite;
    }

    /**
     * Setter do Limite de entregas simultaneas da Transportadora
     * @param limite   Limite de entregas simultaneas da Transportadora
     */
    public void setLimite(int limite)
    {
        this.limite = limite;
    }

    /**
     * Função de equals da Transportadora
     * @param o           Objeto ao qual queremos comparar a Transportadora
     */
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

    /**
     * Função que transforma a Transportadora e os seus dados numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");

        sb.append("TRANSPORTADORA  ->  ").append(super.getNome());
        sb.append("\n  Codigo - ").append(super.getCodigo());
        sb.append(" | Coordenadas - ").append(super.getCoordenadas().toString());
        sb.append(" | NIF - ").append(this.nif);
        sb.append(" | Password - ").append(super.getPassword());
        sb.append("\n  Velocidade Média - ").append(fmt.format(super.getVelocidadeMedia())).append(" Km/h");
        sb.append(" | Raio: ").append(super.getRaio()).append(" Km");
        sb.append("\n  Preço por km - ").append(this.preco_km).append("€");
        sb.append(" | Limite de encomendas - ").append(this.limite);
        sb.append("\n  Classificação - ").append(super.getClassificacao());
        sb.append(" | Total de entregas efetuadas - ").append(super.getTotal_entregas());
        sb.append("\n  Is Medical - ").append(super.isMedical());
        sb.append(" | Is Available - ").append(super.isAvailable());
        sb.append(" | Is Available Medical - ").append(super.isAvailableMedical());
        sb.append("\n  Registos Históricos ").append(super.getEncomendasHistorico().keySet().toString());
        sb.append("\n");

        return sb.toString();
    }


    /**
     * Função que dá clone á Transportadora
     * @return           Cópia da Transportadora
     */
    public Transportadora clone()
    {
        return new Transportadora(this);
    }

    /**
     * Função que realiza a o calculo de dados de uma possível entrega de Encomenda para um Utilizador
     * @param enc           Encomenda a Entregar por parte do Voluntário
     * @param loja          Loja onde se encontra a encomenda a transportar
     * @param utilizador    Utilizador para o qual a Encomenda vais er entregue
     */
    public void realizaEntregaDeVenda(Encomenda enc, Loja loja, Utilizador utilizador) {

        super.realizaEntregaDeVenda(enc, loja, utilizador);

        double distance = super.getCoordenadas().distanceTo(loja.getCoordenadas()) + super.getCoordenadas().distanceTo(utilizador.getCoordenadas());
        double precoTransporte = this.getPreco_km() * distance * ((enc.getPeso() / 25)*0.25 + 1);

        enc.setPrecoTransporte(precoTransporte);
    }

    /**
     * Função que calcula o total de Kilómetros feitos pela Transportadora em todas as suas entregas
     * @return      Total de Km feitos
     */
    public double calculaTotalKmFeitos () {
        return  super.calculaTotalKmFeitos();
    }
}
