package Models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que representa cada Voluntário
 */
public class Voluntario implements Serializable
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private String password;
    private double velocidadeMedia;
    private double raio;
    private double classificacao;
    private int total_avaliacoes;
    private int total_entregas;
    private boolean medical;
    private boolean available;
    private boolean availableMedical;

    private Map<String, Encomenda> encomendasHistorico; //Histórico de Encomendas feitas

    /**
     * Construtor por omissão do Voluntário
     */
    public Voluntario()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.password = "";
        this.velocidadeMedia = 0.0;
        this.raio = 0;
        this.classificacao = 0.0;
        this.total_avaliacoes = 0;
        this.total_entregas = 0;
        this.medical = false;
        this.available = false;
        this.availableMedical = false;
        this.encomendasHistorico = new HashMap<>();
    }

    /**
     * Construtor parametrizado do Voluntário
     * @param nome              Nome do Voluntário
     * @param codigo            Código referente ao Voluntário
     * @param coordenadas       Coordenadas iniciais do Voluntário
     * @param password          Password do Voluntário
     * @param velocidadeMedia   Velocidade Média do Voluntário
     * @param raio              Raio de distribuiçao do Voluntário
     * @param medical           Booleano que indica se Voluntário está preparado para entregar Encomendas Médicas
     */
    public Voluntario(String nome, String codigo, GPS coordenadas, String password, double velocidadeMedia, double raio, boolean medical)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.password = password;
        this.velocidadeMedia = velocidadeMedia;
        this.raio = raio;
        this.classificacao = 0.0;
        this.total_avaliacoes = 0;
        this.total_entregas = 0;
        this.medical = medical;
        this.available = true;
        this.availableMedical = medical;
        this.encomendasHistorico = new HashMap<>();
    }

    /**
     * Construtor de cópia do Voluntário
     * @param e     Voluntário a copiar
     */
    public Voluntario(Voluntario e)
    {
        this.nome = e.getNome();
        this.codigo = e.getCodigo();
        this.coordenadas = e.getCoordenadas().clone();
        this.password = e.getPassword();
        this.velocidadeMedia = e.getVelocidadeMedia();
        this.raio = e.getRaio();
        this.classificacao = e.getClassificacao();
        this.total_entregas = e.getTotal_entregas();
        this.total_avaliacoes = e.getTotal_avaliacoes();
        this.medical = e.isMedical();
        this.available = e.isAvailable();
        this.availableMedical = e.isAvailableMedical();
        this.encomendasHistorico = new HashMap<>(e.getEncomendasHistorico());
    }

    /**
     * Getter do nome do Voluntário
     * @return  Nome do Voluntário
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Setter do nome do Voluntário
     * @param nome   Nome do Voluntário
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * Getter do código do Voluntário
     * @return  código do Voluntário
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Setter do código do Voluntário
     * @param codigo   código do Voluntário
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Getter das Coordenadas do Voluntário
     * @return  Coordenadas do Voluntário
     */
    public GPS getCoordenadas()
    {
        return coordenadas.clone();
    }

    /**
     * Setter das Coordenadas do Voluntário
     * @param coordenadas   Coordenadas do Voluntário
     */
    public void setCoordenadas(GPS coordenadas)
    {
        this.coordenadas = coordenadas.clone();
    }

    /**
     * Getter da Password do Voluntário
     * @return  Password do Voluntário
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter da Password do Voluntário
     * @param password   Password do Voluntário
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Getter do Raio do Voluntário
     * @return  Raio do Voluntário
     */
    public double getRaio()
    {
        return raio;
    }

    /**
     * Setter do Raio do Voluntário
     * @param raio   Raio do Voluntário
     */
    public void setRaio(double raio)
    {
        this.raio = raio;
    }

    /**
     * Getter do Classificacao do Voluntário
     * @return  Classificacao do Voluntário
     */
    public double getClassificacao()
    {
        return classificacao;
    }

    /**
     * Setter do Classificacao do Voluntário
     * @param classificacao   Classificacao do Voluntário
     */
    public void setClassificacao(double classificacao)
    {
        this.classificacao = classificacao;
    }

    /**
     * Getter do Total de entregas do Voluntário
     * @return  Total de entregas do Voluntário
     */
    public int getTotal_entregas()
    {
        return total_entregas;
    }

    /**
     * Setter do Total de entregas do Voluntário
     * @param total_entregas   Total de entregas do Voluntário
     */
    public void setTotal_entregas(int total_entregas)
    {
        this.total_entregas = total_entregas;
    }

    /**
     * Getter do Total de avaliações do Voluntário
     * @return  Total de avaliações do Voluntário
     */
    public int getTotal_avaliacoes() {
        return total_avaliacoes;
    }

    /**
     * Setter do Total de avaliações do Voluntário
     * @param total_avaliacoes   Total de avaliações do Voluntário
     */
    public void setTotal_avaliacoes(int total_avaliacoes) {
        this.total_avaliacoes = total_avaliacoes;
    }

    /**
     * Getter do Booleano de medical do Voluntário
     * @return  Booleano que indica se Voluntário é medica
     */
    public boolean isMedical()
    {
        return medical;
    }

    /**
     * Setter do Booleano de medical do Voluntário
     * @param medical   Booleano que indica se Voluntário é medica
     */
    public void setMedical(boolean medical)
    {
        this.medical = medical;
    }

    /**
     * Getter de Booleano de disponível do Voluntário
     * @return  Booleano que indica se Voluntário está Disponível
     */
    public boolean isAvailable()
    {
        return available;
    }

    /**
     * Setter de Booleano de disponível do Voluntário
     * @param available   Booleano que indica se Voluntário está Disponível
     */
    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    /**
     * Getter do  Booleano de disponível médico do Voluntário
     * @return  Booleano que indica se Voluntário está Disponível para entregas Médicas
     */
    public boolean isAvailableMedical()
    {
        return availableMedical;
    }

    /**
     * Setter do  Booleano de disponível médico do Voluntário
     * @param availableMedical   Booleano que indica se Voluntário está Disponível para entregas Médicas
     */
    public void setAvailableMedical(boolean availableMedical)
    {
        this.availableMedical = availableMedical;
    }

    /**
     * Getter do Map de encomendas entregadas pelo Voluntário
     * @return  Map de encomendas entregadas pelo Voluntário
     */
    public Map<String, Encomenda> getEncomendasHistorico()
    {
        return encomendasHistorico
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    /**
     * Setter do Map de encomendas entregadas pelo Voluntário
     * @param encomendasHistorico   Map de encomendas entregadas pelo Voluntário
     */
    public void setEncomendasHistorico(Map<String, Encomenda> encomendasHistorico)
    {
        this.encomendasHistorico = new HashMap<>();
        encomendasHistorico.forEach((key,value) -> this.encomendasHistorico.put(key,value.clone()));
    }

    /**
     * Getter da velocidade Média do Voluntário
     * @return  Velocidade Média do Voluntário
     */
    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    /**
     * Setter da velocidade Média do Voluntário
     * @param velocidadeMedia   Velocidade Média do Voluntário
     */
    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    /**
     * Função de equals do Voluntário
     * @param o           Objeto ao qual queremos comparar o Voluntário
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Voluntario e = (Voluntario) o;

        return this.nome.equals(e.getNome()) &&
                this.codigo.equals(e.getCodigo()) &&
                this.coordenadas.equals(e.getCoordenadas()) &&
                this.password.equals(e.getPassword()) &&
                this.velocidadeMedia == e.getVelocidadeMedia() &&
                this.raio == e.getRaio() &&
                this.classificacao == e.getClassificacao() &&
                this.total_entregas == e.getTotal_entregas() &&
                this.total_avaliacoes == e.getTotal_avaliacoes() &&
                this.medical == e.isMedical() &&
                this.available == e.isAvailable() &&
                this.availableMedical == e.isAvailableMedical() &&
                this.encomendasHistorico.equals(new HashMap<>(e.getEncomendasHistorico()));
    }

    /**
     * Função que transforma o Voluntário e os seus dados numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");

        sb.append("VOLUNTARIO  ->  ").append(this.nome);
        sb.append("\n  Codigo - ").append(this.codigo);
        sb.append(" | Coordenadas - ").append(this.coordenadas.toString());
        sb.append(" | Password - ").append(this.password);
        sb.append("\n  Velocidade Media - ").append(fmt.format(this.velocidadeMedia)).append(" Km/h");
        sb.append(" | Raio - ").append(this.raio).append(" Km");
        sb.append("\n  Classificação - ").append(this.classificacao);
        sb.append(" | Total de entregas efetuadas - ").append(this.total_entregas);
        sb.append("\n  Is Medical - ").append(this.isMedical());
        sb.append("\n  Is Available - ").append(this.isAvailable());
        sb.append("\n  Is Available Medical - ").append(this.isAvailableMedical());
        sb.append("\n  Registos Históricos ").append(this.encomendasHistorico.keySet().toString());
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Função que dá clone ao Voluntário
     * @return           Cópia do Voluntário
     */
    public Voluntario clone()
    {
        return new Voluntario(this);
    }


    /******* Funções Principais *******/
    /**
     * Função que realiza a entrega de uma encomenda e calcula os dados da da entrega da mesma
     * @param enc           Encomenda a Entregar por parte do Voluntário
     * @param loja          Loja onde se encontra a encomenda a transportar
     * @param utilizador    Utilizador para o qual a Encomenda vais er entregue
     */
    public void realizaEntregaDeVenda(Encomenda enc, Loja loja, Utilizador utilizador) {
        Random r = new Random();

        double distance = this.coordenadas.distanceTo(loja.getCoordenadas()) + this.coordenadas.distanceTo(utilizador.getCoordenadas());
        double tempo = distance/velocidadeMedia * 60.0;

        int temporal = (int) (r.nextDouble() * 3);
        tempo*=(temporal+1);

        double tempoPeso = ((enc.getPeso() / 25)*0.25 + 1);
        tempo*=tempoPeso;

        enc.setDistanciaTransporte(distance);
        enc.setTempoTransporte(tempo);
        enc.setCondicoesClimatericas(temporal);
        enc.setCodTrnasportador(this.getCodigo());

    }

    /**
     * Função que incrementa o numero de entregas do Voluntário
     */
    public void incrementaNumeroEntregas () {
        this.setTotal_entregas(this.getTotal_entregas() + 1);
    }

    /**
     * Função que insere uma Encomenda entregue por um Voluntário no seu Histórico e incrementa o seu numero de Entregas feitas
     * @param encomendaFeita    Encomenda entregue pelo Voluntário
     */
    public void insereNoHistorico (Encomenda encomendaFeita) {
        this.incrementaNumeroEntregas();
        this.encomendasHistorico.putIfAbsent(encomendaFeita.getCodigo(), encomendaFeita);
    }

    /**
     * Função que acrescenta uma avaliação ao Voluntário
     * @param avaliacao     Avaliação dada pelo Utilizador que recebeu encomenda entregue pelo Voluntário
     */
    public void avaliaEncomendaFeita (double avaliacao) {
        Integer nrAvaliacoes = this.getTotal_avaliacoes();
        Double novaClassificacao = this.getClassificacao()*nrAvaliacoes + avaliacao;

        nrAvaliacoes++;
        novaClassificacao = novaClassificacao/nrAvaliacoes;

        this.setTotal_avaliacoes(nrAvaliacoes);
        this.setClassificacao(novaClassificacao);
    }

    /**
     * Função que calcula o total de Kilómetros feitos pelo Voluntário em todas as suas entregas
     * @return      Total de Km feitos
     */
    public double calculaTotalKmFeitos () {
        return  this.getEncomendasHistorico().values().stream().mapToDouble(Encomenda::getDistanciaTransporte).sum();
    }
}
