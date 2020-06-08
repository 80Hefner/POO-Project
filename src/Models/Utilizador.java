package Models;

import java.util.*;
import java.util.stream.Collectors;

public class Utilizador
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private String password;

    //Fazer aqui uma list de encomendas feitas que o Utilizador deve avaliar
    private Map<String, Map.Entry<Double, Double>> encomendasCompletadasPorAvaliar;
    private Set<String> encomendasPendentes; //Encomendas que faltam aceitar pela Loja e as que faltam ser entregues (englobadas as duas aqui);
    private Set<String> codsEncomendasTransportadoraPorAceitar; //Encomendas que falta aceitar receber de uma tranpostadora;
    private Map<String, Encomenda> encomendasHistorico;  //Todas as encomendas feitas e recebidas ou não por este utilizador; (Adicionar só no fim esta parte?

    public Utilizador()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.password = "";
        this.encomendasCompletadasPorAvaliar = new TreeMap<>();
        this.encomendasHistorico = new HashMap<>();
        this.encomendasPendentes = new TreeSet<>();
        this.codsEncomendasTransportadoraPorAceitar = new TreeSet<>();
    }

    public Utilizador(String nome, String codigo, GPS coordenadas, String password)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.password = password;
        this.encomendasCompletadasPorAvaliar = new TreeMap<>();
        this.encomendasHistorico = new HashMap<>();
        this.encomendasPendentes = new TreeSet<>();
        this.codsEncomendasTransportadoraPorAceitar = new TreeSet<>();
    }

    public Utilizador(Utilizador u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
        this.password = u.getPassword();
        this.encomendasCompletadasPorAvaliar = new TreeMap<>();
        this.encomendasHistorico = new HashMap<>(u.getEncomendasHistorico());
        this.encomendasPendentes = new TreeSet<>(u.getEncomendasPendentes());
        this.codsEncomendasTransportadoraPorAceitar = new TreeSet<>(u.getCodsEncomendasTransportadoraPorAceitar());
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Map<String, Encomenda> getEncomendasHistorico() {
        return encomendasHistorico
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setEncomendasHistorico(Map<String, Encomenda> encomendasHistorico) {
        this.encomendasHistorico = new HashMap<>();
        encomendasHistorico.forEach((key,value) -> this.encomendasHistorico.put(key,value.clone()));
    }

    public Set<String> getEncomendasPendentes() {
        return new TreeSet<>(encomendasPendentes);
    }

    public void setEncomendasPendentes(Set<String> encomendasPendentes) {
        this.encomendasPendentes = new TreeSet<>(encomendasPendentes);
    }

    public Set<String> getCodsEncomendasTransportadoraPorAceitar() {
        return new TreeSet<>(codsEncomendasTransportadoraPorAceitar);
    }

    public void setCodsEncomendasTransportadoraPorAceitar(Set<String> codsEncomendasTransportadoraPorAceitar) {
        this.codsEncomendasTransportadoraPorAceitar = new TreeSet<>(codsEncomendasTransportadoraPorAceitar);
    }

    public Map<String, Map.Entry<Double, Double>> getEncomendasCompletadasPorAvaliar() {
        return new TreeMap<>(encomendasCompletadasPorAvaliar);
    }

    public void setEncomendasCompletadasPorAvaliar(Map<String, Map.Entry<Double, Double>> encomendasCompletadasPorAvaliar) {
        this.encomendasCompletadasPorAvaliar = new TreeMap<>(encomendasCompletadasPorAvaliar);
    }


    public void insereEncomenda (Encomenda e) {
        this.encomendasHistorico.put(e.getCodigo(), e);
        this.encomendasPendentes.add(e.getCodigo());
    }

    public boolean verificaPossuiVendaeRemovePendente (String codEncomenda) {
        boolean possui = this.encomendasHistorico.containsKey(codEncomenda);
        if (possui)
            this.encomendasPendentes.remove(codEncomenda);

        return possui;

    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Utilizador u = (Utilizador) o;

        return this.nome.equals(u.getNome()) &&
                this.codigo.equals(u.getCodigo()) &&
                this.coordenadas.equals(u.getCoordenadas()) &&
                this.password.equals(u.getPassword()) &&
                this.encomendasHistorico.equals(new HashMap<>(u.getEncomendasHistorico())) &&
                this.encomendasPendentes.equals(new TreeSet<>(u.getEncomendasPendentes())) &&
                this.codsEncomendasTransportadoraPorAceitar.equals(new TreeSet<>(u.getCodsEncomendasTransportadoraPorAceitar())) &&
                this.encomendasCompletadasPorAvaliar.equals(new TreeMap<>(u.encomendasCompletadasPorAvaliar));
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nPassword: ").append(this.password);
        sb.append("\nEncomendas Pendentes: ").append(this.encomendasPendentes);
        sb.append("\nEncomendas Por Aceitar: ").append(this.codsEncomendasTransportadoraPorAceitar);
        sb.append("\nEncomendas Historico: ").append(this.encomendasHistorico.keySet());
        sb.append("\n");

        return sb.toString();
    }

    public Utilizador clone()
    {
        return new Utilizador(this);
    }

    public void realizaEntregaDeVenda(Encomenda enc) {
        this.encomendasHistorico.get(enc.getCodigo()).setEntregue(true);
    }

    public void insereNoHistorico (Encomenda encomendaFeita) {
        this.encomendasHistorico.putIfAbsent(encomendaFeita.getCodigo(), encomendaFeita);
    }

    public void avaliaEncomendaFeita (String codEncomenda) {
        this.encomendasCompletadasPorAvaliar.remove(codEncomenda);
    }
}
