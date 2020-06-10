package Models;

import Utils.ComparatorMapEntryEmpresaKmPercorridos;
import Utils.ComparatorMapEntryUtilizadoresEncomendas;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class TrazAqui implements Serializable
{
    private Map<String,Loja> lojas;
    private Map<String,Voluntario> voluntarios;
    private Map<String,Transportadora> transportadoras;
    private Map<String,Utilizador> utilizadores;

    private Map<String,Encomenda> catalogoEncomendas;

    private List<String> aceites; //Inútil???
    private String utilizador_atual;


    public TrazAqui() {
        this.lojas = new TreeMap<>();
        this.voluntarios = new TreeMap<>();
        this.transportadoras = new TreeMap<>();
        this.utilizadores = new TreeMap<>();
        this.catalogoEncomendas = new TreeMap<>();
        this.aceites = new ArrayList<>();
        this.utilizador_atual = "";

    }

    public void insereLoja(Loja l)
    {
        this.lojas.put(l.getCodigo(), l.clone());
    }

    public void insereVoluntario(Voluntario v)
    {
        this.voluntarios.put(v.getCodigo(), v.clone());
    }

    public void insereTransportadora(Transportadora t)
    {
        this.transportadoras.put(t.getCodigo(), t.clone());
    }

    public void insereUtilizador(Utilizador u)
    {
        this.utilizadores.put(u.getCodigo(), u.clone());
    }

    public void insereEncomendaAceite(String e)
    {
        this.aceites.add(e);
        this.lojas.get(this.getEncomenda(e).getCodLoja()).aceitaEncomenda(e);
        this.catalogoEncomendas.get(e).setAceiteLoja(true);
    }

    public void adicionaEncomendaAoSistema(Encomenda e)
    {
        this.lojas.get(e.getCodLoja()).insereEncomenda(e);
        this.utilizadores.get(e.getCodUtilizador()).insereEncomenda(e);
        this.catalogoEncomendas.putIfAbsent(e.getCodigo(), e.clone());
    }

    public void setUtilizador_atual(String utilizador)
    {
        this.utilizador_atual = utilizador;
    }

    public String getUtilizador_atual()
    {
        return utilizador_atual;
    }

    public List<Loja> getLojas()
    {
        return this.lojas.values().stream().map(Loja::clone).collect(Collectors.toList());
    }

    public Loja getLoja(String codLoja)
    {
        return this.lojas.get(codLoja).clone();
    }

    public Voluntario getVoluntario(String codVoluntario)
    {
        return this.voluntarios.get(codVoluntario).clone();
    }

    public Utilizador getUtilizador(String codUtilizador)
    {
        return this.utilizadores.get(codUtilizador).clone();
    }

    public Transportadora getTransportador(String codTransportadora)
    {
        return this.transportadoras.get(codTransportadora).clone();
    }

    public List<Voluntario> getVoluntarios()
    {
        return this.voluntarios.values().stream().map(Voluntario::clone).collect(Collectors.toList());
    }

    public List<Transportadora> getTransportadoras()
    {
        return this.transportadoras.values().stream().map(Transportadora::clone).collect(Collectors.toList());
    }

    public Map<String,Transportadora> getTransportadorasMap ()
    {
        return new TreeMap<>(this.transportadoras);
    }

    public List<Utilizador> getUtilizadores()
    {
        return this.utilizadores.values().stream().map(Utilizador::clone).collect(Collectors.toList());
    }

    public Map<String,Utilizador> getUtilizadoresMap ()
    {
        return new TreeMap<>(this.utilizadores);
    }

    public List<String> getEncomendasAceites()
    {
        return new ArrayList<>(this.aceites);
    }

    public Map<String, Encomenda> getCatalogoEncomendas () {
        return this.catalogoEncomendas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }

    public Encomenda getEncomenda (String codEncomenda) {
        if (this.catalogoEncomendas.containsKey(codEncomenda)) {
            return this.catalogoEncomendas.get(codEncomenda).clone();
        }
        return null;
    }

    public boolean procuraEncomendaAceite(String codigo)
    {
        return this.aceites.contains(codigo);
    }

    public boolean procuraUtilizador(String utilizador)
    {
        return this.utilizadores.containsKey(utilizador);
    }

    public boolean procuraVoluntario(String voluntario)
    {
        return this.voluntarios.containsKey(voluntario);
    }

    public boolean procuraTransportadora(String transportadora)
    {
        return this.transportadoras.containsKey(transportadora);
    }

    public boolean procuraLoja(String loja)
    {
        return this.lojas.containsKey(loja);
    }

    public boolean verificaPassword(String utilizador, String password)
    {
        Utilizador u = this.utilizadores.get(utilizador);

        return u.getPassword().equals(password);
    }

    public Encomenda realizaEntregaDeVenda(String codLoja, String codEnc, String codVoluntario) {

        //TO DO: Trocar o return desta função para um void e depois imprimir as ceanas dadas da encomenda
        StringBuilder sb = new StringBuilder();
        Encomenda enc = this.getEncomenda(codEnc);

        //Realiza e altera uma encomenda
        this.lojas.get(codLoja).realizaEntregaDeVendaVoluntario(enc);//Done
        this.voluntarios.get(codVoluntario).realizaEntregaDeVenda(enc, this.getLoja(codLoja), this.getUtilizador(enc.getCodUtilizador()));
        this.utilizadores.get(enc.getCodUtilizador()).realizaEntregaDeVenda(enc);

        //Insere venda alterada depois da entrega no catálogo das Encomendas
        this.catalogoEncomendas.put(codEnc, enc); //Replace da Encomenda antiga para n partilhar apontadores e ser sempre cópias

        //Insere nos históricos de cada cena
        this.lojas.get(codLoja).insereNoHistorico(enc.clone());
        this.voluntarios.get(codVoluntario).insereNoHistorico(enc.clone());
        this.utilizadores.get(enc.getCodUtilizador()).insereNoHistorico(enc.clone());

        this.utilizadores.get(enc.getCodUtilizador()).aicionaEncomendaParaAvaliar(enc.getCodigo(), enc.getTempoTransporte(), enc.getPrecoTransporte());

        return enc.clone();
    }

    public void realizaEntregaDeVendaTransportadora(String codLoja, String codEnc, String codTransportadora) {

        //TO DO: Trocar o return desta função para um void e depois imprimir as ceanas dadas da encomenda
        StringBuilder sb = new StringBuilder();
        Encomenda enc = this.getEncomenda(codEnc);

        //Realiza e altera uma encomenda
        this.lojas.get(codLoja).realizaEntregaDeVendaTransportadora(enc);//Done
        this.transportadoras.get(codTransportadora).realizaEntregaDeVenda(enc, this.getLoja(codLoja), this.getUtilizador(enc.getCodUtilizador()));
        this.utilizadores.get(enc.getCodUtilizador()).insereEntregaParaAceitar(enc);

        //Insere venda alterada depois da entrega no catálogo das Encomendas
        this.catalogoEncomendas.put(codEnc, enc); //Replace da Encomenda antiga para n partilhar apontadores e ser sempre cópias

    }


    public void utilizadorAceitaOuRecusaEntrega(String codEnc, boolean status) {

        Encomenda enc = this.getEncomenda(codEnc);
        if( status ) { //Acontece quando é true, utilizador aceita
            this.lojas.get(enc.getCodLoja()).insereNoHistorico(enc.clone());
            this.transportadoras.get(enc.getCodTrnasportador()).insereNoHistorico(enc.clone());
            this.utilizadores.get(enc.getCodUtilizador()).realizaEntregaDeVenda(enc.clone());
            this.utilizadores.get(enc.getCodUtilizador()).insereNoHistorico(enc.clone());

            enc.setEntregue(true);
            this.catalogoEncomendas.put(codEnc, enc);
            this.utilizadores.get(enc.getCodUtilizador()).aicionaEncomendaParaAvaliar(enc.getCodigo(), enc.getTempoTransporte(), enc.getPrecoTransporte());
        }
        else {
            this.lojas.get(enc.getCodLoja()).adicionaEncomendaParaEntregar(codEnc);

            enc.setPrecoTransporte(0.0);
            enc.setCondicoesClimatericas(0);
            enc.setTempoTransporte(0.0);
            enc.setDistanciaTransporte(0.0);
            enc.setCodTrnasportador("");
            this.catalogoEncomendas.put(codEnc, enc);
        }
    }

    public void todasEntregasAceitesOuRecusadas (String codUtilizador) {
        this.utilizadores.get(codUtilizador).todasEntregasAceitesOuRecusadas();
    }


    public void setAvailable (String codEntidade, boolean status) {
        if(codEntidade.startsWith("v"))
            this.voluntarios.get(codEntidade).setAvailable(status);
        else if (codEntidade.startsWith("t"))
            this.transportadoras.get(codEntidade).setAvailable(status);
    }


    public void avaliaEntregaEncomenda (String codEncomenda, double avaliacao) {
        Encomenda encomenda = this.getEncomenda(codEncomenda);

        if (encomenda.getCodTrnasportador().startsWith("v")) {
            this.voluntarios.get(encomenda.getCodTrnasportador()).avaliaEncomendaFeita(avaliacao);
        } else if (encomenda.getCodTrnasportador().startsWith("t")) {
            this.transportadoras.get(encomenda.getCodTrnasportador()).avaliaEncomendaFeita(avaliacao);
        }
    }

    public void todasEncomendasFeitasAvaliadas (String codUtilizador) {
        this.utilizadores.get(codUtilizador).todasEncomendasFeitasAvaliadas();
    }

    public void lojaAceitaOuRecusaEncomenda(String codEnc, boolean status) {

        Encomenda enc = this.getEncomenda(codEnc);
        if( status ) { //Acontece quando é true, utilizador aceita
            this.insereEncomendaAceite(codEnc);
        }
        else {
            this.lojas.get(enc.getCodLoja()).recusaEncomendaPedida(codEnc);
            this.utilizadores.get(enc.getCodUtilizador()).recusaEncomendaPedida(codEnc);
            this.catalogoEncomendas.remove(codEnc); //Remover do catálogo de Encomendas ou deixar com tudo a false?
        }
    }

    public void lojaAceitaOuRecusaTodasEncomenda (String codLoja) {
        this.lojas.get(codLoja).lojaAceitaOuRecusaTodasEncomenda();
    }


    public Set<Map.Entry<String, Double>> getLista10TransportadorasMaisKilometros () {
        Set<Map.Entry<String, Double>> resultado = new TreeSet<>(new ComparatorMapEntryEmpresaKmPercorridos());
        this.getTransportadorasMap().values().forEach(val -> resultado.add(new AbstractMap.SimpleEntry<>(val.getCodigo(), val.calculaTotalKmFeitos())));
        return resultado;
    }


    public Set<Map.Entry<String, Integer>> getLista10UtilizadoresMaisEntregas () {
        Set<Map.Entry<String, Integer>> resultado = new TreeSet<>(new ComparatorMapEntryUtilizadoresEncomendas());
        this.getUtilizadoresMap().values().forEach(val -> resultado.add(new AbstractMap.SimpleEntry<>(val.getCodigo(), val.getEncomendasHistorico().size())));
        return resultado;
    }

}
