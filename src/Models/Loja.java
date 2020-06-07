package Models;

import java.util.*;
import java.util.stream.Collectors;

public class Loja
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private boolean temFila;
    private LinkedList<Encomenda> fila;
    //private HashMap<String, Encomenda> encomendas; Acho que faz mais sentido ter todas as encomendas no TrazAqui e mudá-as sempre lá, em vez de ter de mudar em tudo o que é Models.
    private Set<String> encomendas;

    public Loja()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.temFila = false;
        this.fila = new LinkedList<>();
        this.encomendas = new TreeSet<>();
    }

    public Loja(String nome, String codigo, GPS coordenadas, boolean temFila)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.temFila = temFila;
        this.fila = new LinkedList<>();
        this.encomendas = new TreeSet<>();
    }

    public Loja(Loja u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
        this.temFila = u.temFila();
        this.fila = new LinkedList<>(u.getPendentes());
        this.encomendas = new TreeSet<>(u.getEncomendas());
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

    public List<Encomenda> getPendentes()
    {
        return fila.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setPendentes(List<Encomenda> fila)
    {
        this.fila = fila.stream().map(Encomenda::clone).collect(Collectors.toCollection(LinkedList::new));
    }

    public Set<String> getEncomendas() {
        return new TreeSet<>(encomendas);
    }

    public void setEncomendas(Set<String> encomendas) {
        this.encomendas = new TreeSet<>(encomendas);
    }

    /*
    public Encomenda getEncomenda (String codEncomenda) {
        return this.getEncomendas().get(codEncomenda);
    }*/

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Loja u = (Loja) o;

        return this.nome.equals(u.getNome()) &&
                this.codigo.equals(u.getCodigo()) &&
                this.coordenadas.equals(u.getCoordenadas()) &&
                this.temFila == u.temFila() &&
                this.fila.equals(new LinkedList<>(u.getPendentes()));
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nTem fila de espera? ").append(this.temFila);
        //sb.append("\nEncomendas em fila de espera: \n").append(this.fila.toString());
        sb.append("\nEncomendas para entregar ").append(this.encomendas.toString());
        sb.append("\n");

        return sb.toString();
    }

    public Loja clone()
    {
        return new Loja(this);
    }

    public void insereEncomenda(Encomenda e)
    {
        this.fila.add(e.clone());
        this.encomendas.add(e.getCodigo());
    }

    public boolean possuiEncomendaCodigo (String codEncomenda, Map<String,Encomenda> mapaEncomendas) {
        if (this.encomendas.contains(codEncomenda))
            return !mapaEncomendas.get(codEncomenda).isEntregue();
        return false;
    }

    public void realizaEntregaDeVenda(Encomenda encomenda, Map<String,Encomenda> mapaEncomendas) {
        this.fila.remove(encomenda);
        mapaEncomendas.get(encomenda.getCodigo()).setEntregue(true);
    }
}
