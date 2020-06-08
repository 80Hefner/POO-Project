package Models;

import java.util.*;
import java.util.stream.Collectors;

//TO do : Falta inserir velocidade, depois fazer aleatoriadade para o clima

public class Voluntario
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private String password;
    private double velocidadeMedia;
    private double raio;
    private double classificacao;
    private int total_entregas;
    private boolean medical;
    private boolean available;
    private boolean availableMedical;

    private Map<String, Encomenda> encomendasHistorico; //Histórico de Encomendas feitas

    public Voluntario()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.password = "";
        this.velocidadeMedia = 0.0;
        this.raio = 0;
        this.medical = false;
        this.available = false;
        this.availableMedical = false;
        this.encomendasHistorico = new HashMap<>();
    }

    public Voluntario(String nome, String codigo, GPS coordenadas, String password, double velocidadeMedia, double raio, boolean medical)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.password = password;
        this.velocidadeMedia = velocidadeMedia;
        this.raio = raio;
        this.classificacao = 0;
        this.total_entregas = 0;
        this.medical = medical;
        this.available = true;
        this.availableMedical = medical;
        this.encomendasHistorico = new HashMap<>();
    }

    public Voluntario(Voluntario e)
    {
        this.nome = e.getNome();
        this.codigo = e.getCodigo();
        this.coordenadas = e.getCoordenadas().clone();
        this.password = e.getPassword();
        this.velocidadeMedia = e.getVelocidadeMedia();
        this.raio = e.getRaio();
        this.classificacao = 0;
        this.total_entregas = 0;
        this.medical = e.isMedical();
        this.available = true;
        this.availableMedical = medical;
        this.encomendasHistorico = new HashMap<>(e.getEncomendasHistorico());
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

    public double getRaio()
    {
        return raio;
    }

    public void setRaio(double raio)
    {
        this.raio = raio;
    }

    public double getClassificacao()
    {
        return classificacao;
    }

    public void setClassificacao(double classificacao)
    {
        this.classificacao = classificacao;
    }

    public int getTotal_entregas()
    {
        return total_entregas;
    }

    public void setTotal_entregas(int total_entregas)
    {
        this.total_entregas = total_entregas;
    }

    public boolean isMedical()
    {
        return medical;
    }

    public void setMedical(boolean medical)
    {
        this.medical = medical;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    public boolean isAvailableMedical()
    {
        return availableMedical;
    }

    public void setAvailableMedical(boolean availableMedical)
    {
        this.availableMedical = availableMedical;
    }

    public Map<String, Encomenda> getEncomendasHistorico()
    {
        return encomendasHistorico
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setEncomendasHistorico(Map<String, Encomenda> encomendasHistorico)
    {
        this.encomendasHistorico = new HashMap<>();
        encomendasHistorico.forEach((key,value) -> this.encomendasHistorico.put(key,value.clone()));
    }

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

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
                this.medical == e.isMedical() &&
                this.available == e.isAvailable() &&
                this.availableMedical == e.isAvailableMedical() &&
                this.encomendasHistorico.equals(new HashMap<>(e.getEncomendasHistorico()));
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nPassword: ").append(this.password);
        sb.append("\nVelocidade Media: ").append(this.velocidadeMedia);
        sb.append("\nRaio: ").append(this.raio);
        sb.append("\nClassificação: ").append(this.classificacao);
        sb.append("\nTotal de entregas efetuadas: ").append(this.total_entregas);
        sb.append("\nIs Medical? ").append(this.medical);
        sb.append("\nIs Available? ").append(this.available);
        sb.append("\nIs Available for Medical? ").append(this.availableMedical);
        sb.append("\nRegistos Históricos: \n").append(this.encomendasHistorico.toString());
        sb.append("\n");

        return sb.toString();
    }

    public Voluntario clone()
    {
        return new Voluntario(this);
    }


    /******* Funções Principais *******/
    public void realizaEntregaDeVenda(Encomenda enc, Loja loja, Utilizador utilizador) {
        Random r = new Random();

        //this.encomendasHistorico.add(enc);

        double distance = this.coordenadas.distanceTo(loja.getCoordenadas()) + this.coordenadas.distanceTo(utilizador.getCoordenadas());
        double tempo = distance/velocidadeMedia * 60.0;
        int temporal = (int) (r.nextDouble() * 3);
        tempo*=(temporal+1);
        //ACRESCENTAR DIFERENÇA DO PESO DA ENCOMENDA NA DEMORA DA ENTREGA

        enc.setTempoTransporte(tempo);
        enc.setCondicoesClimatericas(temporal);
        enc.setCodTrnasportador(this.getCodigo());

    }

    public void insereNoHistorico (Encomenda encomendaFeita) {
        this.encomendasHistorico.putIfAbsent(encomendaFeita.getCodigo(), encomendaFeita);
    }

    public void avaliaEncomendaFeita (double avaliacao) {
        Integer nrAvaliacoes = this.getTotal_entregas();
        Double novaClassificacao = this.getClassificacao()*nrAvaliacoes + avaliacao;

        nrAvaliacoes++;
        novaClassificacao = novaClassificacao/nrAvaliacoes;

        this.setTotal_entregas(nrAvaliacoes);
        this.setClassificacao(novaClassificacao);
    }
}
