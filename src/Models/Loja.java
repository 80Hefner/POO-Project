package Models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Loja
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private boolean temFila;
    private LinkedList<Encomenda> fila;
    private HashMap<String, Encomenda> encomendas;

    public Loja()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.temFila = false;
        this.fila = new LinkedList<>();
        this.encomendas = new HashMap<>();
    }

    public Loja(String nome, String codigo, GPS coordenadas, boolean temFila)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.temFila = temFila;
        this.fila = new LinkedList<>();
        this.encomendas = new HashMap<>();
    }

    public Loja(Loja u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
        this.temFila = u.temFila();
        this.fila = new LinkedList<>(u.getPendentes());
        this.encomendas = new HashMap<>(u.getEncomendas());
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

    public Map<String, Encomenda> getEncomendas() {
        return encomendas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setEncomendas(HashMap<String, Encomenda> encomendas) {
        this.encomendas = new HashMap<>();
        encomendas.forEach((key,value) -> this.encomendas.put(key,value.clone()));
    }

    public Encomenda getEncomenda (String codEncomenda) {
        return this.getEncomendas().get(codEncomenda);
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
        this.encomendas.put(e.getCodigo(), e.clone());
    }

    public boolean possuiEncomendaCodigo (String codEncomenda) {
        if (this.encomendas.containsKey(codEncomenda))
            return !this.encomendas.get(codEncomenda).isEntregue();
        return false;
    }

    public void realizaEntregaDeVenda(Encomenda encomenda) {
        this.fila.remove(encomenda);
        this.encomendas.get(encomenda.getCodigo()).setEntregue(true);
    }
}
