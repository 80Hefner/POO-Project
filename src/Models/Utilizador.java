package Models;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que representa Utilizador do Sistema
 */
public class Utilizador implements Serializable
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private String password;

    private Map<String, Map.Entry<Double, Double>> encomendasCompletadasPorAvaliar;
    private Set<String> encomendasPendentes; //Encomendas que faltam aceitar pela Loja e as que faltam ser entregues (englobadas as duas aqui);
    private Map<String, Map.Entry<Double, Double>> encomendasTransportadoraPorAceitar; //Encomendas que falta aceitar receber de uma tranpostadora;
    private Map<String, Encomenda> encomendasHistorico;  //Todas as encomendas feitas e recebidas ou não por este utilizador; (Adicionar só no fim esta parte?

    /**
     * Construtor por Omissão do Utilizador
     */
    public Utilizador()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.password = "";
        this.encomendasCompletadasPorAvaliar = new TreeMap<>();
        this.encomendasHistorico = new HashMap<>();
        this.encomendasPendentes = new TreeSet<>();
        this.encomendasTransportadoraPorAceitar = new TreeMap<>();
    }

    /**
     * Construtor parametrizado do Utilizador
     * @param nome          Nome do Utilizador
     * @param codigo        Código do Utilizador
     * @param coordenadas   Coordenadas do Utilizador
     * @param password      Password do Utilizador
     */
    public Utilizador(String nome, String codigo, GPS coordenadas, String password)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.password = password;
        this.encomendasCompletadasPorAvaliar = new TreeMap<>();
        this.encomendasHistorico = new HashMap<>();
        this.encomendasPendentes = new TreeSet<>();
        this.encomendasTransportadoraPorAceitar = new TreeMap<>();
    }

    /**
     * Construtor de cópia do Utilizador
     * @param u     Utilizador a copiar
     */
    public Utilizador(Utilizador u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
        this.password = u.getPassword();
        this.encomendasCompletadasPorAvaliar = new TreeMap<>(u.getEncomendasCompletadasPorAvaliar());
        this.encomendasHistorico = new HashMap<>(u.getEncomendasHistorico());
        this.encomendasPendentes = new TreeSet<>(u.getEncomendasPendentes());
        this.encomendasTransportadoraPorAceitar = new TreeMap<>(u.getCodsEncomendasTransportadoraPorAceitar());
    }

    /**
     * Getter do nome do Utilizador
     * @return  Nome do Utilizador
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Setter do nome do Utilizador
     * @param nome   Nome do Utilizador
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * Getter do código do Utilizador
     * @return  código do Utilizador
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Setter do código do Utilizador
     * @param codigo   código do Utilizador
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Getter das Coordenadas do Utilizador
     * @return  Coordenadas do Utilizador
     */
    public GPS getCoordenadas()
    {
        return coordenadas.clone();
    }

    /**
     * Setter das Coordenadas do Utilizador
     * @param coordenadas   Coordenadas do Utilizador
     */
    public void setCoordenadas(GPS coordenadas)
    {
        this.coordenadas = coordenadas.clone();
    }

    /**
     * Getter da Password do Utilizador
     * @return  Password do Utilizador
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter da Password do Utilizador
     * @param password   Password do Utilizador
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Getter do Map de encomendas recebidas pelo Utilizador
     * @return  Map de encomendas recebidas pelo Utilizador
     */
    public Map<String, Encomenda> getEncomendasHistorico() {
        return encomendasHistorico
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    /**
     * Setter do Map de encomendas recebidas pelo Utilizador
     * @param encomendasHistorico   Map de encomendas recebidas pelo Utilizador
     */
    public void setEncomendasHistorico(Map<String, Encomenda> encomendasHistorico) {
        this.encomendasHistorico = new HashMap<>();
        encomendasHistorico.forEach((key,value) -> this.encomendasHistorico.put(key,value.clone()));
    }

    /**
     * Getter do Conjunto de Códigos de Encomendas que Utilizador pediu
     * @return      Conjunto de Códigos de Encomendas que Utilizador pediu
     */
    public Set<String> getEncomendasPendentes() {
        return new TreeSet<>(encomendasPendentes);
    }

    /**
     * Setter do Conjunto de Códigos de Encomendas que Utilizador pediu
     * @param encomendasPendentes       Conjunto de Códigos de Encomendas que Utilizador pediu
     */
    public void setEncomendasPendentes(Set<String> encomendasPendentes) {
        this.encomendasPendentes = new TreeSet<>(encomendasPendentes);
    }

    /**
     * Getter de um Map com informações de pedidos de Entrega de uma Encomenda por parte de uma Transportadora
     * @return      Map com informações de pedidos de Entrega de uma Encomenda por parte de uma Transportadora
     */
    public Map<String, Map.Entry<Double, Double>> getCodsEncomendasTransportadoraPorAceitar() {
        return new TreeMap<>(encomendasTransportadoraPorAceitar);
    }

    /**
     * Setter de um Map com informações de pedidos de Entrega de uma Encomenda por parte de uma Transportadora
     * @param encomendasTransportadoraPorAceitar       Map com informações de pedidos de Entrega de uma Encomenda por parte de uma Transportadora
     */
    public void setCodsEncomendasTransportadoraPorAceitar(Map<String, Map.Entry<Double, Double>> encomendasTransportadoraPorAceitar) {
        this.encomendasTransportadoraPorAceitar = new TreeMap<>(encomendasTransportadoraPorAceitar);
    }

    /**
     * Getter de um Map com codigos de Encomenda Entregues, á espera de a Entrega ser avaliada
     * @return      Map com codigos de Encomenda Entregues, á espera de a Entrega ser avaliada
     */
    public Map<String, Map.Entry<Double, Double>> getEncomendasCompletadasPorAvaliar() {
        return new TreeMap<>(encomendasCompletadasPorAvaliar);
    }
    /**
     * Setter de um Map com codigos de Encomenda Entregues, á espera de a Entrega ser avaliada
     * @param encomendasCompletadasPorAvaliar       Map com codigos de Encomenda Entregues, á espera de a Entrega ser avaliada
     */
    public void setEncomendasCompletadasPorAvaliar(Map<String, Map.Entry<Double, Double>> encomendasCompletadasPorAvaliar) {
        this.encomendasCompletadasPorAvaliar = new TreeMap<>(encomendasCompletadasPorAvaliar);
    }


    /**
     * Função de equals do Utilizador
     * @param o           Objeto ao qual queremos comparar o Utilizador
     */
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
                this.encomendasTransportadoraPorAceitar.equals(new TreeMap<>(u.getCodsEncomendasTransportadoraPorAceitar())) &&
                this.encomendasCompletadasPorAvaliar.equals(new TreeMap<>(u.encomendasCompletadasPorAvaliar));
    }

    /**
     * Função que transforma o Utilizador e os seus dados numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("UTILIZADOR  ->  ").append(this.nome);
        sb.append("\n  Codigo - ").append(this.codigo);
        sb.append(" | Coordenadas - ").append(this.coordenadas.toString());
        sb.append(" | Password - ").append(this.password);
        sb.append("\n  Encomendas Pendentes: ").append(this.encomendasPendentes);
        sb.append("\n  Entregas Transportadora Por Aceitar: ").append(this.encomendasTransportadoraPorAceitar.keySet().toString());
        sb.append("\n  Encomendas feitas por avaliar: ").append(this.encomendasCompletadasPorAvaliar.keySet().toString());
        sb.append("\n  Encomendas Historico: ").append(this.encomendasHistorico.keySet().toString());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Função que dá clone ao Utilizador
     * @return           Cópia do Utilizador
     */
    public Utilizador clone()
    {
        return new Utilizador(this);
    }


    /**
     * Função que insere uma Encomenda pedida pelo Utilizador
     * @param enc   Encomenda pedida pelo Utilizador
     */
    public void insereEncomenda (Encomenda enc) {
        this.encomendasPendentes.add(enc.getCodigo());
    }

    /**
     * Função que realiza a entrega de uma Encomenda
     * @param enc       Encomenda Entregue ao Utilizador
     */
    public void realizaEntregaDeVenda(Encomenda enc) {
        this.encomendasPendentes.remove(enc.getCodigo());
    }

    /**
     * Função que insere Encomenda que tem de ser aceite o seu Transporte por parte da Transportadora
     * @param enc       Encomenda que Transportadora pretende entregar
     */
    public void insereEntregaParaAceitar(Encomenda enc) {
        this.encomendasTransportadoraPorAceitar.putIfAbsent(enc.getCodigo(), new AbstractMap.SimpleEntry<>(enc.getTempoTransporte(), enc.getPrecoTransporte()));
    }

    /**
     * Função que isnere Encomenda Entregue no histórico do Utilizador
     * @param encomendaFeita    Encomenda entregue com sucesso
     */
    public void insereNoHistorico (Encomenda encomendaFeita) {
        this.encomendasHistorico.putIfAbsent(encomendaFeita.getCodigo(), encomendaFeita);
    }

    /**
     * Função que adiciona Encomenda Transportadora e a sua entrega para avaliação por parte do Utilizador
     * @param codEncomenda      Código da Encomenda Transportada
     * @param tempoTransporte   Tempo de demora da Entrega da Encomenda
     * @param custoTransporte   Preço da Entrega da Encomenda
     */
    public void aicionaEncomendaParaAvaliar (String codEncomenda, double tempoTransporte, double custoTransporte) {
        this.encomendasCompletadasPorAvaliar.putIfAbsent(codEncomenda, new AbstractMap.SimpleEntry<Double, Double>(tempoTransporte, custoTransporte));
    }

    /**
     * Função que dá clear ao Map com os dados de Entregas por avaliar
     */
    public void todasEncomendasFeitasAvaliadas () {
        this.encomendasCompletadasPorAvaliar.clear();
    }

    /**
     * Função que dá clear ao Map com os dados de Entregas por aceitar
     */
    public void todasEntregasAceitesOuRecusadas () {
        this.encomendasTransportadoraPorAceitar.clear();
    }

    /**
     * Função que recusa Pedido de Entrega de Encomenda por parte da Transportadora
     * @param codEnc    Código da Encomenda da qual entrega foi recusada
     */
    public void recusaEncomendaPedida (String codEnc) {
        this.encomendasPendentes.remove(codEnc);
    }
}
