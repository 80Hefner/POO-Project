package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Encomenda
{
    private String codigo;
    private String codLoja;
    private String codUtilizador;
    private double peso;
    private ArrayList<LinhaEncomenda> produtos;
    private boolean medical;
    private LocalDateTime data;
    private boolean entregue;
    private double precoTransporte;
    private double tempoTransporte;
    private int condicoesClimatericas; //(0-Normais ; 1-Chuva ; 2-Neve E Trevoada)

    public Encomenda()
    {
        this.codigo = "";
        this.codLoja = "";
        this.codUtilizador = "";
        this.peso = 0;
        this.produtos = new ArrayList<>();
        this.medical = false;
        this.data = LocalDateTime.now();
        this.entregue = false;
        this.precoTransporte = 0;
        this.tempoTransporte = 0.0;
        this.condicoesClimatericas = 0;
    }

    public Encomenda(String codigo, String codLoja, String codUtilizador, double peso, ArrayList<LinhaEncomenda> produtos, boolean medical, LocalDateTime data, boolean entregue, double precoTransporte, double tempoTransporte, int condicoesClimatericas)
    {
        this.codigo = codigo;
        this.codLoja = codLoja;
        this.codUtilizador = codUtilizador;
        this.peso = peso;
        this.produtos = produtos.stream().map(LinhaEncomenda::clone).collect(Collectors.toCollection(ArrayList::new));
        this.medical = medical;
        this.data = data;
        this.entregue = entregue;
        this.precoTransporte = precoTransporte;
        this.tempoTransporte = tempoTransporte;
        this.condicoesClimatericas = condicoesClimatericas;
    }

    public Encomenda(Encomenda l)
    {
        this.codigo = l.getCodigo();
        this.codLoja = l.getCodLoja();
        this.codUtilizador = l.getCodUtilizador();
        this.peso = l.getPeso();
        this.produtos = new ArrayList<>(l.getProdutos());
        this.medical = l.isMedical();
        this.data = l.getData();
        this.entregue = l.isEntregue();
        this.precoTransporte = l.getPrecoTransporte();
        this.tempoTransporte = l.getTempoTransporte();
        this.condicoesClimatericas = l.getCondicoesClimatericas();
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getCodLoja()
    {
        return codLoja;
    }

    public void setCodLoja(String codLoja)
    {
        this.codLoja = codLoja;
    }

    public String getCodUtilizador()
    {
        return codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador)
    {
        this.codUtilizador = codUtilizador;
    }

    public double getPeso()
    {
        return peso;
    }

    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    public List<LinhaEncomenda> getProdutos()
    {
        return produtos.stream().map(LinhaEncomenda::clone).collect(Collectors.toList());
    }

    public void setProdutos(List<LinhaEncomenda> produtos)
    {
        this.produtos = produtos.stream().map(LinhaEncomenda::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isMedical()
    {
        return medical;
    }

    public void setMedical(boolean medical)
    {
        this.medical = medical;
    }

    public LocalDateTime getData()
    {
        return data;
    }

    public void setData(LocalDateTime data)
    {
        this.data = data;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public double getPrecoTransporte() {
        return precoTransporte;
    }

    public void setPrecoTransporte(double precoTransporte) {
        this.precoTransporte = precoTransporte;
    }

    public double getTempoTransporte() {
        return tempoTransporte;
    }

    public void setTempoTransporte(double tempoTransporte) {
        this.tempoTransporte = tempoTransporte;
    }

    public int getCondicoesClimatericas() {
        return condicoesClimatericas;
    }

    public void setCondicoesClimatericas(int condicoesClimatericas) {
        this.condicoesClimatericas = condicoesClimatericas;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Encomenda e = (Encomenda) o;

        return this.codigo.equals(e.getCodigo()) &&
                this.codLoja.equals(e.getCodLoja()) &&
                this.codUtilizador.equals(e.getCodUtilizador()) &&
                this.peso == e.getPeso() &&
                this.produtos.equals(new ArrayList<>(e.getProdutos())) &&
                this.medical == e.isMedical() &&
                this.data.isEqual(e.getData())&&
                this.entregue == (e.isEntregue()) &&
                this.precoTransporte == (e.getPrecoTransporte()) &&
                this.tempoTransporte == (e.getTempoTransporte()) &&
                this.condicoesClimatericas == (e.getCondicoesClimatericas());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Codigo: ").append(this.codigo);
        sb.append("\nFrom loja: ").append(this.codLoja);
        sb.append("\nTo user: ").append(this.codUtilizador);
        sb.append("\nPeso: ").append(this.peso);
        sb.append("\nIs medical? ").append(this.medical);
        sb.append("\nData da encomenda: ").append(this.data.toString());
        sb.append("\nFoi Entregue:\n").append(this.entregue);
        sb.append("\nProdutos:\n").append(this.produtos.toString());
        sb.append("\nPreço Transporte:\n").append(this.precoTransporte);
        sb.append("\n");

        return sb.toString();
    }

    public Encomenda clone()
    {
        return new Encomenda(this);
    }

    public void insereLinhaEncomenda(LinhaEncomenda l)
    {
        this.produtos.add(l.clone());
    }

}
