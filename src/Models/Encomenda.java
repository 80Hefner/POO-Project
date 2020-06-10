package Models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que representa uma Encomenda
 */
public class Encomenda implements Serializable
{
    private String codigo;
    private String codLoja;
    private String codUtilizador;
    private String codTrnasportador;
    private double peso;
    private ArrayList<LinhaEncomenda> produtos;
    private boolean medical;
    private LocalDateTime data;
    private boolean aceiteLoja;
    private boolean entregue;
    private double distanciaTransporte;
    private double precoTransporte;
    private double tempoTransporte;
    private int condicoesClimatericas; //(0-Normais ; 1-Chuva ; 2-Neve E Trevoada)

    /**
     * Construtor por Omissão da Encomenda
     */
    public Encomenda()
    {
        this.codigo = "";
        this.codLoja = "";
        this.codUtilizador = "";
        this.codTrnasportador = "";
        this.peso = 0;
        this.produtos = new ArrayList<>();
        this.medical = false;
        this.data = LocalDateTime.now();
        this.entregue = false;
        this.aceiteLoja = false;
        this.precoTransporte = 0;
        this.tempoTransporte = 0.0;
        this.distanciaTransporte = 0.0;
        this.condicoesClimatericas = 0;
    }

    /**
     * Construtor parametrizado da Encomenda
     * @param codigo                Código da Encomenda
     * @param codLoja               Código da Loja onde se encontra Encomenda
     * @param codUtilizador         Código do Utilizador ao qual Encomenda se destina
     * @param codTrnasportador      Código do Transportador da Encomenda
     * @param peso                  Peso da Encomenda
     * @param produtos              Lista de Linhas de Encomenda com os produtos da Encomenda
     * @param medical               Booleano que indica se Encomenda é médica
     * @param data                  Data da Encomenda
     * @param entregue              Booleano que indica se Encomenda já foi entregue
     * @param aceiteLoja            Bolleano que indica se Encomenda já foi aceite pela Loja
     * @param precoTransporte       Custo de Transporte da Encomenda
     * @param tempoTransporte       Tempo de Transporte das Encomenda
     * @param distanciaTransporte   Distancia de Transporte
     * @param condicoesClimatericas Condicoes em que Entrega foi feit
     */
    public Encomenda(String codigo, String codLoja, String codUtilizador, String codTrnasportador, double peso, ArrayList<LinhaEncomenda> produtos, boolean medical, LocalDateTime data, boolean entregue, boolean aceiteLoja , double precoTransporte, double tempoTransporte, double distanciaTransporte, int condicoesClimatericas)
    {
        this.codigo = codigo;
        this.codLoja = codLoja;
        this.codUtilizador = codUtilizador;
        this.codTrnasportador = codTrnasportador;
        this.peso = peso;
        this.produtos = produtos.stream().map(LinhaEncomenda::clone).collect(Collectors.toCollection(ArrayList::new));
        this.medical = medical;
        this.data = data;
        this.entregue = entregue;
        this.entregue = aceiteLoja;
        this.precoTransporte = precoTransporte;
        this.tempoTransporte = tempoTransporte;
        this.distanciaTransporte = distanciaTransporte;
        this.condicoesClimatericas = condicoesClimatericas;
    }

    /**
     * Construtor de cópia da Encomenda
     * @param l     Encomenda a copair
     */
    public Encomenda(Encomenda l)
    {
        this.codigo = l.getCodigo();
        this.codLoja = l.getCodLoja();
        this.codUtilizador = l.getCodUtilizador();
        this.codTrnasportador = l.getCodTrnasportador();
        this.peso = l.getPeso();
        this.produtos = new ArrayList<>(l.getProdutos());
        this.medical = l.isMedical();
        this.data = l.getData();
        this.entregue = l.isEntregue();
        this.aceiteLoja = l.isAceiteLoja();
        this.precoTransporte = l.getPrecoTransporte();
        this.tempoTransporte = l.getTempoTransporte();
        this.distanciaTransporte = l.getDistanciaTransporte();
        this.condicoesClimatericas = l.getCondicoesClimatericas();
    }

    /**
     * Getter do código da Encomenda
     * @return  código da Encomenda
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Setter do código da Encomenda
     * @param codigo   código da Encomenda
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Getter do código da Loja
     * @return  código da Loja
     */
    public String getCodLoja()
    {
        return codLoja;
    }

    /**
     * Setter do código da Loja
     * @param codLoja   código da Loja
     */
    public void setCodLoja(String codLoja)
    {
        this.codLoja = codLoja;
    }

    /**
     * Getter do código do Utilizador
     * @return  código do Utilizador
     */
    public String getCodUtilizador()
    {
        return codUtilizador;
    }

    /**
     * Setter do código do Utilizador
     * @param codUtilizador   código do Utilizador
     */
    public void setCodUtilizador(String codUtilizador)
    {
        this.codUtilizador = codUtilizador;
    }

    /**
     * Getter do peso da Encomenda
     * @return  Peso da Encomenda
     */
    public double getPeso()
    {
        return peso;
    }

    /**
     * Setter do peso da Encomenda
     * @param peso   Peso da Encomenda
     */
    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    /**
     * Getter da Lista de Linhas de Encomenda (Produtos) da Encomenda
     * @return  Lista de Linhas de Encomenda (Produtos) da Encomenda
     */
    public List<LinhaEncomenda> getProdutos()
    {
        return produtos.stream().map(LinhaEncomenda::clone).collect(Collectors.toList());
    }

    /**
     * Setter da Lista de Linhas de Encomenda (Produtos) da Encomenda
     * @param produtos   Lista de Linhas de Encomenda (Produtos) da Encomenda
     */
    public void setProdutos(List<LinhaEncomenda> produtos)
    {
        this.produtos = produtos.stream().map(LinhaEncomenda::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Getter do Booleano de medical da Encomenda
     * @return  Booleano que indica se Encomenda é medica
     */
    public boolean isMedical()
    {
        return medical;
    }

    /**
     * Setter do Booleano de medical da Encomenda
     * @param medical   Booleano que indica se Encomenda é medica
     */
    public void setMedical(boolean medical)
    {
        this.medical = medical;
    }

    /**
     * Getter da Data em que Encomenda foi pedida
     * @return      Data em que Encomenda foi pedida
     */
    public LocalDateTime getData()
    {
        return data;
    }

    /**
     * Setter da Data em que Encomenda foi pedida
     * @param data      Data em que Encomenda foi pedida
     */
    public void setData(LocalDateTime data)
    {
        this.data = data;
    }

    /**
     * Getter do Booleano de verificação de Entregue da Encomenda
     * @return  Booleano que indica se Encomenda foi entregue
     */
    public boolean isEntregue() {
        return entregue;
    }

    /**
     * Setter do Booleano de verificação de Entregue da Encomenda
     * @param entregue   Booleano que indica se Encomenda foi entregue
     */
    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    /**
     * Getter do Preço de Transporte da Encomenda
     * @return      Preço de Transporte da Encomenda
     */
    public double getPrecoTransporte() {
        return precoTransporte;
    }

    /**
     * Setter do Preço de Transporte da Encomenda
     * @param precoTransporte       Preço de Transporte da Encomenda
     */
    public void setPrecoTransporte(double precoTransporte) {
        this.precoTransporte = precoTransporte;
    }

    /**
     * Getter do Tempo de Transporte da Encomenda
     * @return      Tempo de Transporte da Encomenda
     */
    public double getTempoTransporte() {
        return tempoTransporte;
    }

    /**
     * Setter do Tempo de Transporte da Encomenda
     * @param tempoTransporte       Tempo de Transporte da Encomenda
     */
    public void setTempoTransporte(double tempoTransporte) {
        this.tempoTransporte = tempoTransporte;
    }

    /**
     * Getter das Condições Climatéricas na entrega de uma Encomenda
     * @return      Condições Climatéricas na entrega de uma Encomenda
     */
    public int getCondicoesClimatericas() {
        return condicoesClimatericas;
    }

    /**
     * Setter das Condições Climatéricas na entrega de uma Encomenda
     * @param condicoesClimatericas       Condições Climatéricas na entrega de uma Encomenda
     */
    public void setCondicoesClimatericas(int condicoesClimatericas) {
        this.condicoesClimatericas = condicoesClimatericas;
    }

    /**
     * Getter do Booleano de verificação de Aceite na Loja da Encomenda
     * @return  Booleano que indica se Encomenda foi aceite na Loja
     */
    public boolean isAceiteLoja() {
        return aceiteLoja;
    }

    /**
     * Setter do Booleano de verificação de Aceite na Loja da Encomenda
     * @param aceiteLoja   Booleano que indica se Encomenda foi aceite na Loja
     */
    public void setAceiteLoja(boolean aceiteLoja) {
        this.aceiteLoja = aceiteLoja;
    }

    /**
     * Getter do Código do Transportador
     * @return      Código do Transportador
     */
    public String getCodTrnasportador() {
        return codTrnasportador;
    }

    /**
     * Setter do Código do Transportador
     * @param codTrnasportador      Código do Transportador
     */
    public void setCodTrnasportador(String codTrnasportador) {
        this.codTrnasportador = codTrnasportador;
    }

    /**
     * Getter da Distancia de Transporte da Encomenda
     * @return        Distancia de Transporte da Encomenda
     */
    public double getDistanciaTransporte() {
        return distanciaTransporte;
    }

    /**
     * Setter da Distancia de Transporte da Encomenda
     * @param distanciaTransporte       Distancia de Transporte da Encomenda
     */
    public void setDistanciaTransporte(double distanciaTransporte) {
        this.distanciaTransporte = distanciaTransporte;
    }

    /**
     * Função de equals da Encomenda
     * @param o           Objeto ao qual queremos comparar a Encomenda
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Encomenda e = (Encomenda) o;

        return this.codigo.equals(e.getCodigo()) &&
                this.codLoja.equals(e.getCodLoja()) &&
                this.codUtilizador.equals(e.getCodUtilizador()) &&
                this.codTrnasportador.equals(e.getCodTrnasportador()) &&
                this.peso == e.getPeso() &&
                this.produtos.equals(new ArrayList<>(e.getProdutos())) &&
                this.medical == e.isMedical() &&
                this.data.isEqual(e.getData())&&
                this.entregue == (e.isEntregue()) &&
                this.aceiteLoja == (e.isAceiteLoja()) &&
                this.precoTransporte == (e.getPrecoTransporte()) &&
                this.tempoTransporte == (e.getTempoTransporte()) &&
                this.distanciaTransporte == (e.getDistanciaTransporte()) &&
                this.condicoesClimatericas == (e.getCondicoesClimatericas());
    }

    /**
     * Função que transforma a Encomenda e os seus dados numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");

        sb.append("######################### ENCOMENDA ").append(this.codigo).append(" #########################\n");
        sb.append("Codigo -  ").append(this.codigo);
        sb.append(" | From loja - ").append(this.codLoja);
        sb.append(" | To user - ").append(this.codUtilizador);
        sb.append("\nPeso total - ").append( fmt.format(this.peso)).append(" Kg");
        sb.append(" | Is medical - ").append(this.medical);
        sb.append("\nData da encomenda: ").append(this.data.toString());
        sb.append("\nFoi Aceite pela Loja - ").append(this.aceiteLoja);
        sb.append(" | Foi Entregue - ").append(this.entregue);
        sb.append("\n---------------------------- PRODUTOS ----------------------------\n");
        for (LinhaEncomenda linha : this.produtos)
            sb.append(linha.toString());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Função que dá clone á Encomenda
     * @return           Cópia da Encomenda
     */
    public Encomenda clone()
    {
        return new Encomenda(this);
    }

    /**
     * Função que adiciona Linha de Encomenda (produto) a uma encomenda
     * @param l     Nova linha de Encomenda a introduzir na Encomenda
     */
    public void insereLinhaEncomenda(LinhaEncomenda l)
    {
        this.produtos.add(l.clone());
    }

}
