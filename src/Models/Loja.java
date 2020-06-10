package Models;

import java.io.Serializable;
import java.util.*;

/**
 * Classe que representa cada Loja
 */
public class Loja implements Serializable
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private boolean temFila;
    private String password;


    private Set<String> encomendasPorAceitar;
    private Set<String> encomendasPorEntregar;
    private Map<String, Encomenda> encomendasHistorico; //Acho que faz mais sentido ter todas as encomendas no TrazAqui e mudá-as sempre lá, em vez de ter de mudar em tudo o que é Models.

    /**
     * Construtor por omissão da Loja
     */
    public Loja()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.temFila = false;
        this.password ="";
        this.encomendasPorAceitar = new TreeSet<>();
        this.encomendasPorEntregar = new TreeSet<>();
        this.encomendasHistorico = new HashMap<>();
    }

    /**
     * Construtor parametrizado da Loja
     * @param nome          Nome da loja
     * @param codigo        Código da loja
     * @param password      Password da loja
     * @param coordenadas   Coordenadas GPS da loja
     * @param temFila       Booleano que indica se Loja tem fila de espera
     */
    public Loja(String nome, String codigo, String password, GPS coordenadas, boolean temFila)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.temFila = temFila;
        this.password = password;
        this.encomendasPorAceitar = new TreeSet<>();
        this.encomendasPorEntregar = new TreeSet<>();
        this.encomendasHistorico = new HashMap<>();
    }

    /**
     * Consturtor de cópia da Loja
     * @param u     Loja a copiar
     */
    public Loja(Loja u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
        this.temFila = u.temFila();
        this.password = u.getPassword();
        this.encomendasPorAceitar = new TreeSet<>(u.getEncomendasPorAceitar());
        this.encomendasPorEntregar = new TreeSet<>(u.getEncomendasPorEntregar());
        this.encomendasHistorico = new HashMap<>(u.getEncomendasHistorico());
    }

    /**
     * Getter do nome da Loja
     * @return  Nome da Loja
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Setter do nome da Loja
     * @param nome   Nome da Loja
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * Getter do código da Loja
     * @return  código da Loja
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Setter do código da Loja
     * @param codigo   código da Loja
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Getter das Coordenadas da Loja
     * @return  Coordenadas da Loja
     */
    public GPS getCoordenadas()
    {
        return coordenadas.clone();
    }

    /**
     * Setter das Coordenadas da Loja
     * @param coordenadas   Coordenadas da Loja
     */
    public void setCoordenadas(GPS coordenadas)
    {
        this.coordenadas = coordenadas.clone();
    }

    /**
     * Getter de Booleano de existência de fila na Loja
     * @return  Booleano que indica se Loja tem fila
     */
    public boolean temFila()
    {
        return temFila;
    }

    /**
     * Setter de Booleano de existência de fila na Loja
     * @param temFila   Booleano que indica se Loja tem fila
     */
    public void setTemFila(boolean temFila)
    {
        this.temFila = temFila;
    }

    /**
     * Getter da Password da Loja
     * @return  Password da Loja
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter da Password da Loja
     * @param password   Password da Loja
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter do Conjunto de Códigos de Encomendas que Loja tem por entregar
     * @return      Conjunto de Códigos de Encomendas que Loja tem por entregar
     */
    public Set<String> getEncomendasPorEntregar()
    {
        return new TreeSet<>(encomendasPorEntregar);
    }

    /**
     * Setter do Conjunto de Códigos de Encomendas que Loja tem por entregar
     * @param encomendasPorEntregar       Conjunto de Códigos de Encomendas que Loja tem por entregar
     */
    public void setEncomendasPorEntregar(Set<String> encomendasPorEntregar)
    {
        this.encomendasPorEntregar = new TreeSet<>(encomendasPorEntregar);
    }

    /**
     * Getter do Conjunto de Códigos de Encomendas que Loja tem por aceitar
     * @return      Conjunto de Códigos de Encomendas que Loja tem por aceitar
     */
    public Set<String> getEncomendasPorAceitar() {
        return new TreeSet<>(encomendasPorAceitar);
    }

    /**
     * Setter do Conjunto de Códigos de Encomendas que Loja tem por aceitar
     * @param encomendasPorAceitar       Conjunto de Códigos de Encomendas que Loja tem por aceitar
     */
    public void setEncomendasPorAceitar(Set<String> encomendasPorAceitar) {
        this.encomendasPorAceitar = new TreeSet<>(encomendasPorAceitar);
    }

    /**
     * Getter do Map de encomendas entregadas pela Loja
     * @return  Map de encomendas entregadas pela Loja
     */
    public Map<String, Encomenda> getEncomendasHistorico() {
        return new HashMap<>(encomendasHistorico);
    }

    /**
     * Setter do Map de encomendas entregadas pela Loja
     * @param encomendasHistorico   Map de encomendas entregadas pela Loja
     */
    public void setEncomendasHistorico(Map<String, Encomenda> encomendasHistorico) {
        this.encomendasHistorico = new HashMap<>(encomendasHistorico);
    }


    /**
     * Função de equals da Loja
     * @param o           Objeto ao qual queremos comparar a Loja
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Loja u = (Loja) o;

        return this.nome.equals(u.getNome()) &&
                this.codigo.equals(u.getCodigo()) &&
                this.coordenadas.equals(u.getCoordenadas()) &&
                this.temFila == u.temFila() &&
                this.password.equals(u.getPassword()) &&
                this.encomendasPorEntregar.equals(u.getEncomendasPorEntregar())  &&
                this.encomendasPorAceitar.equals(u.getEncomendasPorAceitar()) &&
                this.encomendasHistorico.equals(u.getEncomendasHistorico());
    }

    /**
     * Função que transforma a Loja e os seus dados numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("LOJA  ->  ").append(this.nome);
        sb.append("\n  Código - ").append(this.codigo);
        sb.append(" | Coordenadas - ").append(this.coordenadas.toString());
        sb.append(" | Tem fila de espera? - ").append(this.temFila);
        sb.append(" | Password - ").append(this.password);
        sb.append("\n  Encomendas por aceitar ").append(this.encomendasPorAceitar.toString());
        sb.append("\n  Encomendas para entregar ").append(this.encomendasPorEntregar.toString());
        sb.append("\n  Encomendas Histórico ").append(this.encomendasHistorico.keySet().toString());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Função que dá clone á Loja
     * @return           Cópia da Loja
     */
    public Loja clone()
    {
        return new Loja(this);
    }

    /**
     * Função que isnere um Pedido de Encomenda na Loja
     * @param e     Encomenda por aceitar por parte da Loja
     */
    public void insereEncomenda(Encomenda e)
    {
        this.encomendasPorAceitar.add(e.getCodigo());
    }

    /**
     * Função que aceita um Pedido de Encomenda na Loja
     * @param codEncomenda     Código da Encomenda aceitada por parte da Loja
     */
    public void aceitaEncomenda(String codEncomenda) {
        this.encomendasPorAceitar.remove(codEncomenda);
        this.encomendasPorEntregar.add(codEncomenda);
    }

    /**
     * Função que verifica se Loja tem uma dada Encomenda em Stock para Entregar
     * @param codEncomenda      Código da Encomenda que queremos verificar
     * @return                  Booleano que indica se Loja tem essa Encomenda para entregar
     */
    public boolean possuiEncomendaCodigo (String codEncomenda) {
        return this.encomendasPorEntregar.contains(codEncomenda);
    }

    /**
     * Função que adiciona uma Encomenda para Entregar na Loja
     * @param codEnc        Código da Encomenda que Loja pode agora Entregar
     */
    public void adicionaEncomendaParaEntregar (String codEnc) {
        this.encomendasPorEntregar.add(codEnc);
    }

    /**
     * Função que realiza a entrega de uma Encomenda por parte de um Voluntário e altera os seus dados
     * @param encomenda     Encomenda que Voluntário irá entregar
     */
    public void realizaEntregaDeVendaVoluntario(Encomenda encomenda) {
        this.encomendasPorEntregar.remove(encomenda.getCodigo());
        encomenda.setEntregue(true);
    }

    /**
     * Função que realiza um pedido de Entrega de uma Encomenda por parte de uma Transportadora e altera os seus dados
     * @param encomenda     Encomenda que Transportadora pretende entregar
     */
    public void realizaEntregaDeVendaTransportadora(Encomenda encomenda) {
        this.encomendasPorEntregar.remove(encomenda.getCodigo());
    }

    /**
     * Função que insere Encomenda Entregue que se encontrava na loja no seu hisórico
     * @param encomendaFeita    Encomenda realizada a inserir no histórico
     */
    public void insereNoHistorico (Encomenda encomendaFeita) {
        this.encomendasHistorico.putIfAbsent(encomendaFeita.getCodigo(), encomendaFeita);
    }

    /**
     * Função que recusa Encomenda pedida por Utilizador por parte da Loja
     * @param codEnc    Código da Encomenda rejeitada
     */
    public void recusaEncomendaPedida (String codEnc) {
        this.encomendasPorAceitar.remove(codEnc);
    }

    /**
     * Função que dá clear ao Set com as Encomendas por aceitar
     */
    public void lojaAceitaOuRecusaTodasEncomenda() {
        this.encomendasPorAceitar.clear();
    }
}
