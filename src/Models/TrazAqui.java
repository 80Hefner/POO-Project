package Models;

import NewExceptions.*;
import Utils.ComparatorMapEntryEmpresaKmPercorridos;
import Utils.ComparatorMapEntryUtilizadoresEncomendas;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe TrazAqui que possui todas os dados e é o Model central do Projeto
 */
public class TrazAqui implements Serializable
{
    private Map<String,Loja> lojas;
    private Map<String,Voluntario> voluntarios;
    private Map<String,Transportadora> transportadoras;
    private Map<String,Utilizador> utilizadores;

    private Map<String,Encomenda> catalogoEncomendas;

    private List<String> aceites; //Inútil???
    private String utilizador_atual;


    /**
     * Construtor por omissão do TrazAqui
     */
    public TrazAqui() {
        this.lojas = new TreeMap<>();
        this.voluntarios = new TreeMap<>();
        this.transportadoras = new TreeMap<>();
        this.utilizadores = new TreeMap<>();
        this.catalogoEncomendas = new TreeMap<>();
        this.aceites = new ArrayList<>();
        this.utilizador_atual = "";

    }

    /**
     * Função que insere uma Loja no Map das Lojas
     * @param l     Loja a inserir
     */
    public void insereLoja(Loja l)
    {
        this.lojas.put(l.getCodigo(), l.clone());
    }

    /**
     * Função que insere um Voluntário no Map dos Voluntários
     * @param v     Voluntário a inserir
     */
    public void insereVoluntario(Voluntario v)
    {
        this.voluntarios.put(v.getCodigo(), v.clone());
    }

    /**
     * Função que insere uma Transportadora no Map das Transportadoras
     * @param t     Transportadora a inserir
     */
    public void insereTransportadora(Transportadora t)
    {
        this.transportadoras.put(t.getCodigo(), t.clone());
    }

    /**
     * Função que insere um Utilizador no Map dos Utilizadors
     * @param u     Utilizador a inserir
     */
    public void insereUtilizador(Utilizador u)
    {
        this.utilizadores.put(u.getCodigo(), u.clone());
    }

    /**
     * Função que insere e altera os dados de quando uma Loja aceita o pedido de uma Encomenda
     * @param e     Código da Encomenda aceite
     */
    public void insereEncomendaAceite(String e) throws EncomendaInexistenteException {
        this.aceites.add(e);
        this.lojas.get(this.getEncomenda(e).getCodLoja()).aceitaEncomenda(e);
        this.catalogoEncomendas.get(e).setAceiteLoja(true);
    }

    /**
     * Função que adiciona o pedido de uma Encomenda ao Sistema
     * @param e     Encomenda pedida pelo Utilizador
     */
    public void adicionaEncomendaAoSistema(Encomenda e)
    {
        this.lojas.get(e.getCodLoja()).insereEncomenda(e);
        this.utilizadores.get(e.getCodUtilizador()).insereEncomenda(e);
        this.catalogoEncomendas.putIfAbsent(e.getCodigo(), e.clone());
    }

    /**
     * Setter do Utilizador Atual
     * @param utilizador    Utilizador Atual a introduzir
     */
    public void setUtilizador_atual(String utilizador)
    {
        this.utilizador_atual = utilizador;
    }

    /**
     * Getter do Utilizador Atual
     * @return     Utilizador Atual
     */
    public String getUtilizador_atual()
    {
        return utilizador_atual;
    }

    /**
     * Getter de uma cópia de Loja do Sistema
     * @param codLoja   Loja que queremos receber
     * @return          Loja do Sistema
     */
    public Loja getLoja(String codLoja) throws LojaInexistenteException
    {
        if (this.lojas.containsKey(codLoja)) {
            return this.lojas.get(codLoja).clone();
        } else {
            throw new LojaInexistenteException();
        }
    }

    /**
     * Getter de uma cópia de Voluntario do Sistema
     * @param codVoluntario     Voluntario que queremos receber
     * @return                  Voluntario do Sistema
     */
    public Voluntario getVoluntario(String codVoluntario) throws VoluntarioInexistenteException
    {
        if (this.voluntarios.containsKey(codVoluntario)) {
            return this.voluntarios.get(codVoluntario).clone();
        } else {
            throw new VoluntarioInexistenteException();
        }
    }

    /**
     * Getter de uma cópia de Utilizador do Sistema
     * @param codUtilizador     Utilizador que queremos receber
     * @return                  Utilizador do Sistema
     */
    public Utilizador getUtilizador(String codUtilizador) throws UtilizadorInexistenteException
    {
        if (this.utilizadores.containsKey(codUtilizador)) {
            return this.utilizadores.get(codUtilizador).clone();
        } else {
            throw new UtilizadorInexistenteException();
        }
    }

    /**
     * Getter de uma cópia de Transportadora do Sistema
     * @param codTransportadora   Transportadora que queremos receber
     * @return                    Transportadora do Sistema
     */
    public Transportadora getTransportador(String codTransportadora) throws TransportadoraInexistenteException
    {
        if (this.transportadoras.containsKey(codTransportadora)) {
            return this.transportadoras.get(codTransportadora).clone();
        } else {
            throw new TransportadoraInexistenteException();
        }
    }

    /**
     * Getter de uma Lista de cópias das Lojas do Sistema
     * @return      Lista de cópias das Lojas do Sistema
     */
    public List<Loja> getLojas()
    {
        return this.lojas.values().stream().map(Loja::clone).collect(Collectors.toList());
    }

    /**
     * Getter de uma Lista de cópias dos Voluntários do Sistema
     * @return      Lista de cópias dos Voluntário do Sistema
     */
    public List<Voluntario> getVoluntarios()
    {
        return this.voluntarios.values().stream().map(Voluntario::clone).collect(Collectors.toList());
    }

    /**
     * Getter de uma Lista de cópias das Transportadoras do Sistema
     * @return      Lista de cópias das Transportadoras do Sistema
     */
    public List<Transportadora> getTransportadoras()
    {
        return this.transportadoras.values().stream().map(Transportadora::clone).collect(Collectors.toList());
    }

    /**
     * Getter de uma Lista de cópias dos Utilizadores do Sistema
     * @return      Lista de cópias dos Utilizadores do Sistema
     */
    public List<Utilizador> getUtilizadores()
    {
        return this.utilizadores.values().stream().map(Utilizador::clone).collect(Collectors.toList());
    }

    /**
     * Getter de uma Lista com os códigos de Encomenda Aceites do Sistema
     * @return      Lista com os códigos de Encomenda Aceites do Sistema
     */
    public List<String> getEncomendasAceites()
    {
        return new ArrayList<>(this.aceites);
    }

    /**
     * Getter de um Map com as Transportadoras do Sistema
     * @return      Map com as Transportadoras do Sistema
     */
    public Map<String,Transportadora> getTransportadorasMap ()
    {
        return new TreeMap<>(this.transportadoras);
    }

    /**
     * Getter de um Map com os Utilizadores do Sistema
     * @return      Map com os Utilizadores do Sistema
     */
    public Map<String,Utilizador> getUtilizadoresMap ()
    {
        return new TreeMap<>(this.utilizadores);
    }

    /**
     * Getter de um Map com as Lojas do Sistema
     * @return      Map com as Lojas do Sistema
     */
    public Map<String,Loja> getLojasMap ()
    {
        return new TreeMap<>(this.lojas);
    }

    /**
     * Getter de um Map com as Encomendas do Sistema
     * @return      Map com as Encomendas do Sistema
     */
    public Map<String, Encomenda> getCatalogoEncomendas () {
        return this.catalogoEncomendas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }

    /**
     * Getter de uma Encomenda so Sistema
     * @param codEncomenda      Código da Encomenda pedida
     * @return                  Cópia da Encomenda
     */
    public Encomenda getEncomenda (String codEncomenda) throws EncomendaInexistenteException
    {
        if (this.catalogoEncomendas.containsKey(codEncomenda)) {
            return this.catalogoEncomendas.get(codEncomenda).clone();
        }
        else {
            throw new EncomendaInexistenteException();
        }
    }

    /**
     * Função de verificação da existência de uma Encomenta Aceite no Sistema
     * @param codigo    Código da Encomenda do Sistema
     * @return          Booleano que indica se existe ou não no Sistema
     */
    public boolean procuraEncomendaAceite(String codigo)
    {
        return this.aceites.contains(codigo);
    }

    /**
     * Função de verificação da existência de um Utilizador no Sistema
     * @param utilizador    Código do Utilizador em estudo
     * @return              Booleano que indica se existe ou não no Sistema
     */
    public boolean procuraUtilizador(String utilizador)
    {
        return this.utilizadores.containsKey(utilizador);
    }

    /**
     * Função de verificação da existência de um Voluntário no Sistema
     * @param voluntario    Código do Voluntário em estudo
     * @return              Booleano que indica se existe ou não no Sistema
     */
    public boolean procuraVoluntario(String voluntario)
    {
        return this.voluntarios.containsKey(voluntario);
    }

    /**
     * Função de verificação da existência de uma Transportadora no Sistema
     * @param transportadora    Código da Transportadora em estudo
     * @return                  Booleano que indica se existe ou não no Sistema
     */
    public boolean procuraTransportadora(String transportadora)
    {
        return this.transportadoras.containsKey(transportadora);
    }

    /**
     * Função de verificação da existência de uma Loja no Sistema
     * @param loja    Código da Loja em estudo
     * @return        Booleano que indica se existe ou não no Sistema
     */
    public boolean procuraLoja(String loja)
    {
        return this.lojas.containsKey(loja);
    }

    /**
     * Função que verifica se Utilizador possui uma dada Password
     * @param utilizador    Código do Utilizador a estudar
     * @param password      Password em estudo
     * @return              Booleano que indica se passwords coincidem
     */
    public boolean verificaPasswordUtilizador(String utilizador, String password)
    {
        Utilizador u = this.utilizadores.get(utilizador);
        return u.getPassword().equals(password);
    }

    /**
     * Função que verifica se Voluntário possui uma dada Password
     * @param voluntario    Código do Voluntário a estudar
     * @param password      Password em estudo
     * @return              Booleano que indica se passwords coincidem
     */
    public boolean verificaPasswordVoluntario(String voluntario, String password)
    {
        Voluntario u = this.voluntarios.get(voluntario);
        return u.getPassword().equals(password);
    }

    /**
     * Função que verifica se Transportadora possui uma dada Password
     * @param transportadora    Código da Transportadora a estudar
     * @param password          Password em estudo
     * @return                  Booleano que indica se passwords coincidem
     */
    public boolean verificaPasswordTransportadora(String transportadora, String password)
    {
        Transportadora u = this.transportadoras.get(transportadora);
        return u.getPassword().equals(password);
    }

    /**
     * Função que verifica se Loja possui uma dada Password
     * @param loja          Código da Loja a estudar
     * @param password      Password em estudo
     * @return              Booleano que indica se passwords coincidem
     */
    public boolean verificaPasswordLoja(String loja, String password)
    {
        Loja u = this.lojas.get(loja);
        return u.getPassword().equals(password);
    }

    /**
     * Função que realiza entrega de uma Encomenda por parte de um Voluntário
     * @param codLoja           Código da Loja onde se Enccontra a Encomenda
     * @param codEnc            Código da Encomenda a transportar
     * @param codVoluntario     Código do Voluntário que irá transportar a Encomenda
     * @return                  Encomenda coms os dados alterados ao fim da sua entrega
     */
    public Encomenda realizaEntregaDeVenda(String codLoja, String codEnc, String codVoluntario) throws LojaInexistenteException, EncomendaInexistenteException, UtilizadorInexistenteException {

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

    /**
     * Função que realiza um pedido entrega de uma Encomenda por parte de uma Transportadora
     * @param codLoja               Código da Loja onde se Enccontra a Encomenda
     * @param codEnc                Código da Encomenda a transportar
     * @param codTransportadora     Código da Transportadora que irá transportar a Encomenda se aceite
     * @return                      Encomenda coms os dados alterados estimados ao fim da sua entrega
     */
    public void realizaEntregaDeVendaTransportadora(String codLoja, String codEnc, String codTransportadora) throws LojaInexistenteException, UtilizadorInexistenteException, EncomendaInexistenteException {

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

    /**
     * Função que aceita ou recusa um pedido entrega de uma Encomenda por parte de uma Transportadora
     * @param codEnc     Código da Encomenda a transportar
     * @param status     Booleano que indica se Entrega de Encomenda foi aceite ou recusada por aprte do Utilizador
     */
    public void utilizadorAceitaOuRecusaEntrega(String codEnc, boolean status) throws EncomendaInexistenteException {

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

    /**
     * Função que dá sinal a um Utilizador para limpar a sua árvore de Entregas por aceitar
     * @param codUtilizador     Utilizador que queremos mandar o Sinal
     */
    public void todasEntregasAceitesOuRecusadas (String codUtilizador) {
        this.utilizadores.get(codUtilizador).todasEntregasAceitesOuRecusadas();
    }

    /**
     * Função que determinar o estado de Disponibilidade para Entrega de Encomendas por parte de uma Entidade
     * @param codEntidade   Entidade que vams colocar o estado de Disponibilidade
     * @param status        Estado de disponibilidade a inserir
     */
    public void setAvailable (String codEntidade, boolean status) {
        if(codEntidade.startsWith("v"))
            this.voluntarios.get(codEntidade).setAvailable(status);
        else if (codEntidade.startsWith("t"))
            this.transportadoras.get(codEntidade).setAvailable(status);
    }

    /**
     * Função que determinar o estado de Disponibilidade para Entrega de Encomendas Médicas por parte de uma Entidade
     * @param codEntidade   Entidade que vams colocar o estado de Disponibilidade
     * @param status        Estado de disponibilidade a inserir
     */
    public void setAvailableMedical (String codEntidade, boolean status) {
        if(codEntidade.startsWith("v"))
            this.voluntarios.get(codEntidade).setAvailableMedical(status);
        else if (codEntidade.startsWith("t"))
            this.transportadoras.get(codEntidade).setAvailableMedical(status);
    }

    /**
     * Função que avalia a entrega de uma Dada Encomenda por parte do Utilizador
     * @param codEncomenda      Código da Encomenda cuja entrega foi avaliada
     * @param avaliacao         Avaliação dada pelo Utilizador
     */
    public void avaliaEntregaEncomenda (String codEncomenda, double avaliacao) throws EncomendaInexistenteException {
        Encomenda encomenda = this.getEncomenda(codEncomenda);

        if (encomenda.getCodTrnasportador().startsWith("v")) {
            this.voluntarios.get(encomenda.getCodTrnasportador()).avaliaEncomendaFeita(avaliacao);
        } else if (encomenda.getCodTrnasportador().startsWith("t")) {
            this.transportadoras.get(encomenda.getCodTrnasportador()).avaliaEncomendaFeita(avaliacao);
        }
    }

    /**
     * Função que dá sinal a um Utilizador para limpar a sua árvore de Entregas por avaliar
     * @param codUtilizador     Utilizador que queremos mandar o Sinal
     */
    public void todasEncomendasFeitasAvaliadas (String codUtilizador) {
        this.utilizadores.get(codUtilizador).todasEncomendasFeitasAvaliadas();
    }

    /**
     * Função que aceita ou recusa um pedido de Encomenda a uma Loja por parte do Utilizador
     * @param codEnc        Código da Encomenda que foi aceite ou recusada pela loja
     * @param status        Booleano que determina se Encomenda foi aceite pela loja ou recusada
     */
    public void lojaAceitaOuRecusaEncomenda(String codEnc, boolean status) throws EncomendaInexistenteException {

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

    /**
     * Função que dá sinal a uma Loja para limpar a sua lista de Encomendas por aceitar
     * @param codLoja     Loja que queremos mandar o Sinal
     */
    public void lojaAceitaOuRecusaTodasEncomenda (String codLoja) {
        this.lojas.get(codLoja).lojaAceitaOuRecusaTodasEncomenda();
    }


    /**
     * Query que dá as 10 Transportadoras com mais Km (decrescentemetne) e caso iguais Km, por ordem alfabética
     * @return      Set com Map.Entrys (que servem como pares) que indicam o código da Transportadora e o número e Kilometros feitos
     */
    public Set<Map.Entry<String, Double>> getLista10TransportadorasMaisKilometros () {
        Set<Map.Entry<String, Double>> resultado = new TreeSet<>(new ComparatorMapEntryEmpresaKmPercorridos());
        this.getTransportadorasMap().values().forEach(val -> resultado.add(new AbstractMap.SimpleEntry<>(val.getCodigo(), val.calculaTotalKmFeitos())));
        return resultado;
    }

    /**
     * Query que dá os 10 Utilizadores com mais encomendas entregues (concluídas)
     * @return      Set com Map.Entrys (que servem como pares) que indicam o código do Utilizador e o de Encomenda recebidas
     */
    public Set<Map.Entry<String, Integer>> getLista10UtilizadoresMaisEntregas () {
        Set<Map.Entry<String, Integer>> resultado = new TreeSet<>(new ComparatorMapEntryUtilizadoresEncomendas());
        this.getUtilizadoresMap().values().forEach(val -> resultado.add(new AbstractMap.SimpleEntry<>(val.getCodigo(), val.getEncomendasHistorico().size())));
        return resultado;
    }

    /**
     * Função que dá o total faturado por uma dada Transportadora
     * @param codTransportadora     Código da Transportadora em estudo
     * @return                      Total faturado por uma dada Transportadora
     */
    public double getTotalFaturadoTransportadora (String codTransportadora) throws TransportadoraInexistenteException {
        return  this.getTransportador(codTransportadora).getEncomendasHistorico().values().stream().mapToDouble(Encomenda::getPrecoTransporte).sum();
    }

}
