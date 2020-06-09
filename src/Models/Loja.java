package Models;

import java.util.*;
import java.util.stream.Collectors;

public class Loja
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private boolean temFila;


    private Set<String> encomendasPorAceitar;
    private Set<String> encomendasPorEntregar;
    private Map<String, Encomenda> encomendasHistorico; //Acho que faz mais sentido ter todas as encomendas no TrazAqui e mudá-as sempre lá, em vez de ter de mudar em tudo o que é Models.

    public Loja()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.temFila = false;
        this.encomendasPorAceitar = new TreeSet<>();
        this.encomendasPorEntregar = new TreeSet<>();
        this.encomendasHistorico = new HashMap<>();
    }

    public Loja(String nome, String codigo, GPS coordenadas, boolean temFila)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.temFila = temFila;
        this.encomendasPorAceitar = new TreeSet<>();
        this.encomendasPorEntregar = new TreeSet<>();
        this.encomendasHistorico = new HashMap<>();
    }

    public Loja(Loja u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
        this.temFila = u.temFila();
        this.encomendasPorAceitar = new TreeSet<>(u.getEncomendasPorAceitar());
        this.encomendasPorEntregar = new TreeSet<>(u.getEncomendasPorEntregar());
        this.encomendasHistorico = new HashMap<>(u.getEncomendasHistorico());
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

    public boolean temFila()
    {
        return temFila;
    }

    public void setTemFila(boolean temFila)
    {
        this.temFila = temFila;
    }

    public Set<String> getEncomendasPorEntregar()
    {
        return new TreeSet<>(encomendasPorEntregar);
    }

    public void setEncomendasPorEntregar(Set<String> encomendasPorEntregar)
    {
        this.encomendasPorEntregar = new TreeSet<>(encomendasPorEntregar);
    }

    public Set<String> getEncomendasPorAceitar() {
        return new TreeSet<>(encomendasPorAceitar);
    }

    public void setEncomendasPorAceitar(Set<String> encomendasPorAceitar) {
        this.encomendasPorAceitar = new TreeSet<>(encomendasPorAceitar);
    }

    public Map<String, Encomenda> getEncomendasHistorico() {
        return new HashMap<>(encomendasHistorico);
    }

    public void setEncomendasHistorico(Map<String, Encomenda> encomendasHistorico) {
        this.encomendasHistorico = new HashMap<>(encomendasHistorico);
    }


    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Loja u = (Loja) o;

        return this.nome.equals(u.getNome()) &&
                this.codigo.equals(u.getCodigo()) &&
                this.coordenadas.equals(u.getCoordenadas()) &&
                this.temFila == u.temFila() &&
                this.encomendasPorEntregar.equals(u.getEncomendasPorEntregar())  &&
                this.encomendasPorAceitar.equals(u.getEncomendasPorAceitar()) &&
                this.encomendasHistorico.equals(u.getEncomendasHistorico());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nTem fila de espera? ").append(this.temFila);
        sb.append("\nEncomendas por aceitar ").append(this.encomendasPorAceitar.toString());
        sb.append("\nEncomendas para entregar ").append(this.encomendasPorEntregar.toString());
        sb.append("\nEncomendas Histórico ").append(this.encomendasHistorico.keySet().toString());
        sb.append("\n");

        return sb.toString();
    }

    public Loja clone()
    {
        return new Loja(this);
    }

    public void insereEncomenda(Encomenda e)
    {
        this.encomendasPorAceitar.add(e.getCodigo());
    }

    public void aceitaEncomenda(String codEncomenda) {
        this.encomendasPorAceitar.remove(codEncomenda);
        this.encomendasPorEntregar.add(codEncomenda);
    }

    public boolean possuiEncomendaCodigo (String codEncomenda) {
        return this.encomendasPorEntregar.contains(codEncomenda);
    }

    public void adicionaEncomendaParaEntregar (String codEnc) {
        this.encomendasPorEntregar.add(codEnc);
    }

    public void realizaEntregaDeVendaVoluntario(Encomenda encomenda) {
        this.encomendasPorEntregar.remove(encomenda.getCodigo());
        encomenda.setEntregue(true);
    }

    public void realizaEntregaDeVendaTransportadora(Encomenda encomenda) {
        this.encomendasPorEntregar.remove(encomenda.getCodigo());
    }

    public void insereNoHistorico (Encomenda encomendaFeita) {
        this.encomendasHistorico.putIfAbsent(encomendaFeita.getCodigo(), encomendaFeita);
    }

    public void recusaEncomendaPedida (String codEnc) {
        this.encomendasPorAceitar.remove(codEnc);
    }

    public void lojaAceitaOuRecusaTodasEncomenda() {
        this.encomendasPorAceitar.clear();
    }
}
