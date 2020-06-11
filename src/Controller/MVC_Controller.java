package Controller;

import Models.*;
import NewExceptions.*;
import View.MVC_View;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Classe que possui Controlador
 */
public class MVC_Controller {

    private TrazAqui model;
    private MVC_View view;
    private int login = 0;

    /**
     * Getter do Model do MVC (Modelo Vista Controlador)
     * @return             Modelo que está implementado
     */
    public TrazAqui getModel() {
        return model;
    }

    /**
     * Setter do Model do MVC (Modelo Vista Controlador)
     * @param model            Modelo que queremos implementar
     */
    public void setModel(TrazAqui model) {
        this.model = model;
    }

    /**
     * Getter da View do MVC (Modelo Vista Controlador)
     * @return             View que está implementada
     */
    public MVC_View getView() {
        return view;
    }

    /**
     * Setter da View do MVC (Modelo Vista Controlador)
     * @param view            View que queremos implementar
     */
    public void setView(MVC_View view) {
        this.view = view;
    }

    /**
     * Construtor parametrizado do MVC_Controller
     * @param model           Model que queremos implementar
     * @param view            View que queremos implementar
     */
    public MVC_Controller(TrazAqui model, MVC_View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Menu central e principal do Controlador responsável por gerir todas as escolhas do Utilizador
     */
    public void menuPrincipal()
    {
        while(true) {
            if (login == 0) {
                view.imprimeLogo();
                menuEscolhas(this.model);
            }
            else if (this.model.getUtilizador_atual().startsWith("u")) {
                try {
                    menuUtilizador(this.model);
                } catch (UtilizadorInexistenteException e) {
                    e.printStackTrace();
                }
            }
            else if (this.model.getUtilizador_atual().startsWith("v")) {
                try {
                    menuVoluntario(this.model);
                } catch (UtilizadorInexistenteException e) {
                    e.printStackTrace();
                } catch (EncomendaInexistenteException e) {
                    e.printStackTrace();
                } catch (VoluntarioInexistenteException e) {
                    e.printStackTrace();
                } catch (LojaInexistenteException e) {
                    e.printStackTrace();
                } catch (TransportadoraInexistenteException e) {
                    e.printStackTrace();
                }
            }
            else if (this.model.getUtilizador_atual().startsWith("t")) {
                try {
                    menuTransportadora(this.model);
                } catch (TransportadoraInexistenteException e) {
                    e.printStackTrace();
                } catch (UtilizadorInexistenteException e) {
                    e.printStackTrace();
                } catch (EncomendaInexistenteException e) {
                    e.printStackTrace();
                } catch (LojaInexistenteException e) {
                    e.printStackTrace();
                } catch (VoluntarioInexistenteException e) {
                    e.printStackTrace();
                }
            }
            else if (this.model.getUtilizador_atual().startsWith("l")) {
                try {
                    menuLoja(this.model);
                } catch (LojaInexistenteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * Menu central a partir de onde se vai para todas as outras coisas quando Usuário não está loggado em nenhuma entidade
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void menuEscolhas(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);

        while(true) {
            view.printMenuPrincipal();

            String escolha = sc.nextLine();

            if (escolha.equals("0")) {
                System.exit(0);
            }
            else if (escolha.equals("1")) {
                menuEscolhaLogin(trazAqui);
                break;
            }
            else if (escolha.equals("2")) {
                menuEscolhaRegisto(trazAqui);
                break;
            }
            else if (escolha.equals("3")) {
                view.clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("4") && trazAqui != null) {
                view.clearScreen();
                view.print("\n--------------------- Traz Aqui a ser guardado -------------------\n");
                saveToDisk();
                esperaInput();
                break;
            }
            else if (escolha.equals("5")) {
                view.clearScreen();
                view.print("\n--------------------- Traz Aqui a dar load -------------------\n");
                loadFromDisk();
                esperaInput();
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
                view.clearScreen();
                break;
            }
        }
    }

    /**
     * Menu onde são apresentadas as opções de login numa Entidade
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void menuEscolhaLogin (TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);
        while(true) {
            view.printMenuEscolheLogin();
            String escolha = sc.nextLine();

            if (escolha.equals("0") || escolha.equals("\n")){
                break;
            }
            else if (escolha.equals("1") && trazAqui.getUtilizadores().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                view.print("   Escreva /exit a qualquer momento para abandonar o menu\n");
                while(!escolha.equals("/exit")) {
                    view.print("Nome de utilizador: ");
                    escolha = sc.nextLine();
                    String utilizador = escolha;
                    if (trazAqui.procuraUtilizador(utilizador)) {
                        while(!escolha.equals("/exit")) {
                            view.print("Password: ");
                            escolha = sc.nextLine();
                            String password = escolha;
                            if (trazAqui.verificaPasswordUtilizador(utilizador, password)) {
                                login = 1;
                                trazAqui.setUtilizador_atual(utilizador);
                                break;
                            }
                            else {
                                if (escolha.equals("/exit")){
                                    view.print("Usuário abandonou.\n\n");
                                    esperaInput();
                                }
                                else view.print("Password incorreta!\n");
                            }
                        }
                        break;
                    }
                    else {
                        if (escolha.equals("/exit")){
                            view.print("Usuário abandonou.\n\n");
                            esperaInput();
                        }
                        else view.print("Utilizador inválido!\n");
                    }
                }
                break;
            }
            else if (escolha.equals("2") && trazAqui.getVoluntarios().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                view.print("   Escreva /exit a qualquer momento para abandonar o menu\n");
                while(!escolha.equals("/exit")) {
                    view.print("Nome do Voluntario: ");
                    escolha = sc.nextLine();
                    String voluntario = escolha;
                    if (trazAqui.procuraVoluntario(voluntario)) {
                        while(!escolha.equals("/exit")) {
                            view.print("Password: ");
                            escolha = sc.nextLine();
                            String password = escolha;
                            if (trazAqui.verificaPasswordVoluntario(voluntario, password)) {
                                login = 1;
                                trazAqui.setUtilizador_atual(voluntario);
                                break;
                            }
                            else {
                                if (escolha.equals("/exit")){
                                    view.print("Usuário abandonou.\n\n");
                                    esperaInput();
                                }
                                else view.print("Password incorreta!\n");
                            }
                        }
                        break;
                    }
                    else {
                        if (escolha.equals("/exit")){
                            view.print("Usuário abandonou.\n\n");
                            esperaInput();
                        }
                        else view.print("Voluntario inválido!\n");
                    }
                }
                break;
            }
            else if (escolha.equals("3") && trazAqui.getTransportadoras().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                view.print("   Escreva /exit a qualquer momento para abandonar o menu\n");
                while(!escolha.equals("/exit")) {
                    view.print("Nome da Transportadora: ");
                    escolha = sc.nextLine();
                    String transportadora = escolha;
                    if (trazAqui.procuraTransportadora(transportadora)) {
                        while(!escolha.equals("/exit")) {
                            view.print("Password: ");
                            escolha = sc.nextLine();
                            String password = escolha;
                            if (trazAqui.verificaPasswordTransportadora(transportadora, password)) {
                                login = 1;
                                trazAqui.setUtilizador_atual(transportadora);
                                break;
                            }
                            else {
                                if (escolha.equals("/exit")){
                                    view.print("Usuário abandonou.\n\n");
                                    esperaInput();
                                }
                                else view.print("Password incorreta!\n");
                            }
                        }
                        break;
                    }
                    else {
                        if (escolha.equals("/exit")){
                            view.print("Usuário abandonou.\n\n");
                            esperaInput();
                        }
                        else view.print("Transportadora inválida!\n");
                    }
                }
                break;
            }
            else if (escolha.equals("4") && trazAqui.getLojas().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                view.print("   Escreva /exit a qualquer momento para abandonar o menu\n");
                while(!escolha.equals("/exit")) {
                    view.print("Nome da Loja: ");
                    escolha = sc.nextLine();
                    String loja = escolha;
                    if (trazAqui.procuraLoja(loja)) {
                        while(!escolha.equals("/exit")) {
                            view.print("Password: ");
                            escolha = sc.nextLine();
                            String password = escolha;
                            if (trazAqui.verificaPasswordLoja(loja, password)) {
                                login = 1;
                                trazAqui.setUtilizador_atual(loja);
                                break;
                            }
                            else {
                                if (escolha.equals("/exit")){
                                    view.print("Usuário abandonou.\n\n");
                                    esperaInput();
                                }
                                else view.print("Password incorreta!\n");
                            }
                        }
                        break;
                    }
                    else {
                        if (escolha.equals("/exit")){
                            view.print("Usuário abandonou.\n\n");
                            esperaInput();
                        }
                        else view.print("Loja inválida!\n");
                    }
                }
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }

        }
    }

    /**
     * Menu onde vão ser apresentadas as opções possíveis quando queremos registar uma nova entidade
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void menuEscolhaRegisto (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            view.printMenuRegistoEntidade();
            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                escolha = "-1";
            }

            if (escolha.equals("0")) {
                break;
            }
            else if (escolha.equals("1")) {
                registaUtilizador(trazAqui);
            }
            else if (escolha.equals("2")) {
                registaVoluntario(trazAqui);
            }
            else if (escolha.equals("3")) {
                registaTransportadora(trazAqui);
            }
            else if (escolha.equals("4")) {
                registaLoja(trazAqui);
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }
        }
    }

    /********************* MENUS DO UTILIZADOR *******************/
    /**
     * Menú apresentando todas as opções quando se encontra loggado num Utilizador
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void menuUtilizador(TrazAqui trazAqui) throws UtilizadorInexistenteException
    {
        Scanner sc = new Scanner(System.in);


        while(true) {
            view.printMenuUtilizador(trazAqui.getUtilizador(trazAqui.getUtilizador_atual()).getEncomendasCompletadasPorAvaliar().size(), trazAqui.getUtilizador(trazAqui.getUtilizador_atual()).getCodsEncomendasTransportadoraPorAceitar().size());

            String escolha = sc.nextLine();

            if (escolha.equals("0")) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (escolha.equals("1")) {
                view.clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
            }
            else if (escolha.equals("2")) {
                Encomenda e = novaEncomenda(trazAqui);
                trazAqui.adicionaEncomendaAoSistema(e);
            }
            else if (escolha.equals("3")) {
                view.clearScreen();
                avaliaTodasAsEncomendasFeitas(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("4")) {
                view.clearScreen();
                aceitaOuRecusasTodasAsPropostas(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("5")) {
                view.clearScreen();
                Set<Map.Entry<String, Integer>> res = trazAqui.getLista10UtilizadoresMaisEntregas();
                view.imprimeQuerie10Utilizadores(res);
                esperaInput();
                break;
            }
            else if (escolha.equals("6")) {
                view.clearScreen();
                view.imprimeHistoricoEntidade(trazAqui.getUtilizador(trazAqui.getUtilizador_atual()).getEncomendasHistorico());
                esperaInput();
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }
        }
    }

    /**
     * Menu responsável por mostrar as escolhas possíveis quando alguém peder para serem listadas as Entidades
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void listagemEntidades (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        boolean escolheuCerto = false;
        view.clearScreen();
        view.imprimeMenuListagemEntidades();
        while(true) {
            view.print("    (0 - Lojas | 1 - Voluntários | 2 - Transportadoras | 3 - Utilizadores | 4 - Encomendas | 5 - Aceites): ");
            String escolha = sc.nextLine();

            switch (escolha) {
                case "0":
                    view.imprimeLojasTrazAqui(trazAqui);
                    escolheuCerto = true;
                    break;
                case "1":
                    view.imprimeVoluntariosTrazAqui(trazAqui);
                    escolheuCerto = true;
                    break;
                case "2":
                    view.imprimeTransportadorasTrazAqui(trazAqui);
                    escolheuCerto = true;
                    break;
                case "3":
                    view.imprimeUtilizadoresTrazAqui(trazAqui);
                    escolheuCerto = true;
                    break;
                case "4":
                    view.imprimeEncomendasTrazAqui(trazAqui);
                    escolheuCerto = true;
                    break;
                case "5":
                    view.print("\n---------------------------- ACEITES ----------------------------\n");
                    view.print(trazAqui.getEncomendasAceites().toString());
                    view.print("\n");
                    escolheuCerto = true;
                    break;
                default:
                    view.print("Opção Inválida\n");
            }

            if (escolheuCerto)
                break;
        }
    }

    /**
     * Menu que aparece quando Utilizador pede uma nova Encomenda ao Sistema
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     * @return              Encomenda nova pedida pelo Utilizador loggado na sessão
     */
    private Encomenda novaEncomenda(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        Encomenda e = new Encomenda();

        view.clearScreen();
        view.print("------NOVA ENCOMENDA--------\n");

        while(true) {
            String codigo = "e" + (r.nextInt(8999) + 1000);
            if (!trazAqui.procuraEncomendaAceite(codigo)) {
                e.setCodigo(codigo);
                break;
            }
        }
        view.print("Código da loja: ");
        e.setCodLoja(sc.nextLine());
        e.setCodUtilizador(trazAqui.getUtilizador_atual());
        view.print("Número de produtos a encomendar: ");
        int nr_produtos = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nr_produtos; i++) {
            LinhaEncomenda l = novaLinhaEncomenda();
            e.insereLinhaEncomenda(l);
            e.setPeso(e.getPeso() + l.getQuantidade() * l.getValor_unidade() / 10);
        }
        e.setMedical(e.getProdutos().stream().anyMatch(l -> l.getDescricao().equals("Desinfetante") || l.getDescricao().equals("Água sanitária") || l.getDescricao().equals("Medicamentos")));

        Integer ano,mes,dia;
        while (true) {
            view.print("Ano do Pedido da Encomenda:");
            ano = Integer.parseInt(sc.nextLine());
            if (ano >= 0)
                break;
            else view.print("Ano Inválido.\n");
        }

        while (true) {
            view.print("Horas do Pedido da Encomenda:");
            mes = Integer.parseInt(sc.nextLine());
            if (mes>=0 || mes<=12)
                break;
            else view.print("Mês Inválido.\n");
        }
        while (true) {
            view.print("Horas do Pedido da Encomenda:");
            dia = Integer.parseInt(sc.nextLine());
            if (mes!=2) {
                if (dia>=0 || dia<=31)
                    break;
                else view.print("Dia Inválido.\n");
            }
            else {
                if (dia>=0 || dia<=29)
                    break;
                else view.print("Dia Inválido.\n");
            }
        }
        e.setData(LocalDateTime.of(ano, mes, dia, 0, 0)); //Por opção de meter horas e assim de Entrega

        return e;
    }

    /**
     * Menu que aparece para cada Linha de Encomenda que se quer pedir, quando Utilizador está apedir uma nova Encomenda
     * @return      Linha de Encomenda que fará parte da Encomenda Pedida
     */
    private LinhaEncomenda novaLinhaEncomenda()
    {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        LinhaEncomenda l = new LinhaEncomenda();

        view.clearScreen();
        view.print("------NOVA LINHA ENCOMENDA--------\n");
        view.print("Código do produto: ");
        l.setCodigo(sc.nextLine());
        view.print("Descrição: ");
        l.setDescricao(sc.nextLine());
        view.print("Quantidade: ");
        l.setQuantidade(Integer.parseInt(sc.nextLine()));
        l.setValor_unidade(10*r.nextDouble());

        return l;
    }

    /**
     * Menu que aparece nas notificações do Utilizador e que fará com que este avalie todas as Encomendas que lhe sejam feitas
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void avaliaTodasAsEncomendasFeitas(TrazAqui trazAqui) throws UtilizadorInexistenteException
    {
        Utilizador utilizadorAux = trazAqui.getUtilizador(trazAqui.getUtilizador_atual());
        view.print("Avalie todas as entregas da(s) Encomenda(s) de 0 a 10: \n");
        utilizadorAux.getEncomendasCompletadasPorAvaliar()
                .forEach((key, value) -> {
                    try {
                        avaliaUmaEncomendaFeita(trazAqui, key, value.getKey(), value.getValue());
                    } catch (EncomendaInexistenteException e) {
                        e.printStackTrace();
                    }
                });
        view.print("\nTodas as Encomendas feitas avaliadas com sucesso.");
        trazAqui.todasEncomendasFeitasAvaliadas(trazAqui.getUtilizador_atual());
    }

    /**
     * Menu que aparece quando Utilizador está a avaliar uma Entrega de Encomenda em Específico
     * @param trazAqui              Model principal á volta do qual se trabalha ao longo do projeto
     * @param codEncomenda          Código da Encomenda respetiva á Entrega
     * @param tempoTransporte       Tempo de Transporte demorado na Entrega
     * @param preçoTransporte       Preço do Transporte da Entrega
     */
    private void avaliaUmaEncomendaFeita(TrazAqui trazAqui, String codEncomenda, double tempoTransporte, double preçoTransporte) throws EncomendaInexistenteException
    {
        DecimalFormat fmt = new DecimalFormat("0.00");
        Scanner sc = new Scanner(System.in);

        view.print("Entrega da Encomenda "+codEncomenda+" demorou " + (int)tempoTransporte/60 + "Horas e "
                + (int)tempoTransporte%60 + " minutos. Teve o custo de " + fmt.format(preçoTransporte) + "€.\n" );
        while (true) {
            view.print("Avaliação(0-10): ");
            double avaliacao = sc.nextDouble();
            if (avaliacao >= 0.0 && avaliacao <= 10.0) {
                trazAqui.avaliaEntregaEncomenda(codEncomenda, avaliacao);
                if (preçoTransporte == 0.0) {
                    view.print("Voluntário responsável pela entrega da Encomenda avaliado com sucesso.\n");
                } else {
                    view.print("Transportadora responsável pela entrega da Encomenda avaliada com sucesso.\n");
                }
                break;
            } else {
                view.print("Avaliação Inválida\n");
            }
        }
    }

    /**
     * Menu que aparece nas notificações do Utilizador e que fará com que este aceite ou recuse todas as propostas de Entrega que lhe tenham sido feitos por parte das Transportadoras
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void aceitaOuRecusasTodasAsPropostas(TrazAqui trazAqui) throws UtilizadorInexistenteException
    {
        Utilizador utilizadorAux = trazAqui.getUtilizador(trazAqui.getUtilizador_atual());
        view.print("Aceite ou recuse as seguintes propostas de Entrega:\n");
        utilizadorAux.getCodsEncomendasTransportadoraPorAceitar()
                .forEach((key, value) -> {
                    try {
                        aceitaOuRecusaUmaProposta(trazAqui, key, value.getKey(), value.getValue());
                    } catch (EncomendaInexistenteException e) {
                        e.printStackTrace();
                    }
                });
        view.print("\nTodas as Encomendas feitas avaliadas com sucesso.\n");
        trazAqui.todasEntregasAceitesOuRecusadas(trazAqui.getUtilizador_atual());
    }


    /**
     * Menu que aparece quando Utilizador tem de aceitar ou recusar uma Entrega de Encomenda em Específico
     * @param trazAqui              Model principal á volta do qual se trabalha ao longo do projeto
     * @param codEncomenda          Código da Encomenda respetiva á Entrega
     * @param tempoTransporte       Tempo de Transporte demorado na Entrega
     * @param preçoTransporte       Preço do Transporte da Entrega
     */
    private void aceitaOuRecusaUmaProposta(TrazAqui trazAqui, String codEncomenda, double tempoTransporte, double preçoTransporte) throws EncomendaInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        DecimalFormat fmt = new DecimalFormat("0.00");

        view.print("Entrega da Encomenda "+codEncomenda+" irá demorar cerca de " + (int)tempoTransporte/60 + "Horas e "
                + (int)tempoTransporte%60 + " minutos. Terá um custo de " + fmt.format(preçoTransporte) + "€.\n" );

        while (true) {
            view.print("Aceitar ou Recusar (y-n): ");
            String aceita = sc.nextLine();
            if (aceita.equals("y") || aceita.equals("Y")) {
                trazAqui.utilizadorAceitaOuRecusaEntrega(codEncomenda, true);
                view.print("  Entrega Aceite\n");
                break;
            }
            else if (aceita.equals("n") || aceita.equals("N")) {
                trazAqui.utilizadorAceitaOuRecusaEntrega(codEncomenda, false);
                view.print("  Entrega Recusada\n");
                break;
            }
            else {
                view.print("Aceitação Inválida\n");
            }
        }
    }

    /********************* MENUS DO VOLUNTÁRIO *******************/
    /**
     * Menú apresentando todas as opções quando se encontra loggado num Voluntário
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void menuVoluntario(TrazAqui trazAqui) throws UtilizadorInexistenteException, EncomendaInexistenteException, VoluntarioInexistenteException, LojaInexistenteException, TransportadoraInexistenteException
    {
        Scanner sc = new Scanner(System.in);

        while(true) {

            view.printMenuVoluntário();

            String escolha = sc.nextLine();

            if (escolha.equals("0")) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (escolha.equals("1")) {
                view.clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
            }
            else if (escolha.equals("2")) {
                view.clearScreen();
                realizaEncomendaPedidaVoluntario(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("3")) {
                view.clearScreen();
                alteraDisponibilidadeEntidade(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("4")) {
                view.clearScreen();
                alteraDisponibilidadeMedicaEntidade(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("5")) {
                view.clearScreen();
                view.imprimeHistoricoEntidade(trazAqui.getVoluntario(trazAqui.getUtilizador_atual()).getEncomendasHistorico());
                esperaInput();
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }
        }
    }

    /**
     * Menu que surge quando Voluntário logado pretende entregar uma encomenda
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void realizaEncomendaPedidaVoluntario (TrazAqui trazAqui) throws VoluntarioInexistenteException, LojaInexistenteException, EncomendaInexistenteException, UtilizadorInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        Voluntario voluntario = trazAqui.getVoluntario(this.model.getUtilizador_atual());
        String escolha = "";

        view.clearScreen();
        view.print("\n-----------------PEDIDO ENTREGA ENCOMENDA------------------------\n");
        view.print("   Escreva /exit a qualquer momento para abandonar o menu\n");
        while(!escolha.equals("/exit")) {
            if (voluntario.isAvailable()) {
                view.print("\nLojas no Sistema - " + trazAqui.getLojasMap().keySet().toString() + "\n");
                view.print("    Insira o Código da loja: ");
                escolha = sc.nextLine();
                String codLoja = escolha;
                if (!codLoja.startsWith("l")) {
                    codLoja = "l" + codLoja;
                }
                if (trazAqui.procuraLoja(codLoja)) {

                    if (trazAqui.getLoja(codLoja).getCoordenadas().isReachable( voluntario.getCoordenadas(), voluntario.getRaio())) {

                        if (trazAqui.getLoja(codLoja).getEncomendasPorEntregar().size() > 0) {

                            while(!escolha.equals("/exit")) {
                                view.print("\nEncomendas da Loja - " + trazAqui.getLoja(codLoja).getEncomendasPorEntregar().toString() + "\n");
                                view.print("    Insira o Código da Encomenda: ");
                                escolha = sc.nextLine();
                                String codEncomenda = escolha;
                                if (trazAqui.getLoja(codLoja).possuiEncomendaCodigo(codEncomenda)) {
                                    Encomenda enc = trazAqui.getEncomenda(codEncomenda);
                                    if (!(!voluntario.isMedical() && enc.isMedical())) {
                                        if(!(!voluntario.isAvailableMedical() && enc.isMedical())) {
                                            if (trazAqui.getUtilizador(enc.getCodUtilizador()).getCoordenadas().isReachable(voluntario.getCoordenadas(), voluntario.getRaio())) {
                                                Encomenda res = trazAqui.realizaEntregaDeVenda(codLoja, codEncomenda, trazAqui.getUtilizador_atual());
                                                view.print("\nEntrega feita com sucesso\n");
                                                view.imprimeEntregaEncomendaVol(res);
                                                break;
                                            } else {
                                                view.print("Nao consegue alcançar utilizador!\n");
                                            }
                                        } else {
                                            view.print("Voluntário não se encontra Disponível para transportar Encomendas Médicas.\n");
                                        }
                                    } else {
                                        view.print("Voluntário não possui Capacidades para transportar Encomendas Médicas.\n");
                                    }
                                } else {
                                    if (escolha.equals("/exit")) {
                                        view.print("Usuário abandonou o menu.\n");
                                        break;
                                    }
                                    else view.print("Encomenda Pendente inexistente!\n");
                                }
                            }
                            break;

                        } else {
                            view.print("Loja não possui Encomendas por Entregar!\n");
                        }

                    } else {
                        view.print("Não consegue alcançar esta loja!\n");
                    }
                } else {
                    if (escolha.equals("/exit")) {
                        view.print("Usuário abandonou o menu.\n");
                        break;
                    }
                    else view.print("Loja Inexistente!\n");
                }
            } else {
                view.print("Voluntário Não Disponível para realizar Entregas.\n");
                break;
            }
        }
    }

    /**
     * Menu que aparece quando Voluntário ou Transportadora logados pretendem alterar a sua disponibilidade para entrega de Encomendas
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void alteraDisponibilidadeEntidade (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        String codEntidade = trazAqui.getUtilizador_atual();
        String escolha = "";

        while(!escolha.equals("/exit")) {
            view.print("Qual disponibilidade quer colocar na Entidade?\n");
            view.print("(y - Disponível | n - Não Disponível) : ");
            escolha = sc.nextLine();
            String disponibilidade = escolha;
            if (disponibilidade.startsWith("y") || disponibilidade.startsWith("Y")) {
                trazAqui.setAvailable(codEntidade, true);
                view.print("Entidade definida como Disponível para entregas!\n");
                break;
            } else if (disponibilidade.startsWith("n") || disponibilidade.startsWith("N")) {
                trazAqui.setAvailable(codEntidade, false);
                view.print("Entidade definida como Não Disponível para entregas!\n");
                break;
            }
        }
    }

    /**
     * Menu que aparece quando Voluntário ou Transportadora logados pretendem alterar a sua disponibilidade para entrega de Encomendas Médicas
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void alteraDisponibilidadeMedicaEntidade (TrazAqui trazAqui) throws VoluntarioInexistenteException, TransportadoraInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        String codEntidade = trazAqui.getUtilizador_atual();
        String escolha = "";

        if (codEntidade.startsWith("v")) {
            if (!trazAqui.getVoluntario(codEntidade).isMedical()) {
                view.print("Voluntário não possui Capacidades para transportar Encomendas Médicas");
                return;
            }
        }
        else if (codEntidade.startsWith("t")) {
            if (!trazAqui.getTransportador(codEntidade).isMedical()) {
                view.print("Transportadora não possui Capacidades para transportar Encomendas Médicas");
                return;
            }
        }

        while(!escolha.equals("/exit")) {
            view.print("Qual disponibilidade Médica quer colocar na Entidade?\n");
            view.print("(y - Disponível | n - Não Disponível) : ");
            escolha = sc.nextLine();
            String disponibilidade = escolha;
            if (disponibilidade.startsWith("y") || disponibilidade.startsWith("Y")) {
                trazAqui.setAvailableMedical(codEntidade, true);
                view.print("Entidade definida como Disponível para entregas médicas!\n");
                break;
            } else if (disponibilidade.startsWith("n") || disponibilidade.startsWith("N")) {
                trazAqui.setAvailableMedical(codEntidade, false);
                view.print("Entidade definida como Não Disponível para entregas médicas!\n");
                break;
            }
        }
    }

    /********************* MENUS DO TRANSPORTADORA *******************/
    /**
     * Menú apresentando todas as opções quando se encontra loggado numa Transportadora
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void menuTransportadora(TrazAqui trazAqui) throws TransportadoraInexistenteException, UtilizadorInexistenteException, EncomendaInexistenteException, LojaInexistenteException, VoluntarioInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        DecimalFormat fmt = new DecimalFormat("0.00");

        while(true) {
            view.printMenuTransportadora();

            String escolha = sc.nextLine();


            if (escolha.equals("0")) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (escolha.equals("1")) {
                view.clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("2")) {
                view.clearScreen();
                realizaEncomendaPedidaTransportadora(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("3")) {
                view.clearScreen();
                alteraDisponibilidadeEntidade(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("4")) {
                view.clearScreen();
                alteraDisponibilidadeMedicaEntidade(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("5")) {
                view.clearScreen();
                Set<Map.Entry<String, Double>> res = trazAqui.getLista10TransportadorasMaisKilometros();
                view.imprimeQuerie10Transportadoras(res);
                esperaInput();
                break;
            }
            else if (escolha.equals("6")) {
                view.clearScreen();
                Double res = trazAqui.getTotalFaturadoTransportadora(trazAqui.getUtilizador_atual());
                view.print("Empresa " + trazAqui.getUtilizador_atual() + " faturou " + fmt.format(res) + " €\n");
                esperaInput();
                break;
            }
            else if (escolha.equals("7")) {
                view.clearScreen();
                view.imprimeHistoricoEntidade(trazAqui.getTransportador(trazAqui.getUtilizador_atual()).getEncomendasHistorico());
                esperaInput();
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
                break;
            }
        }
    }

    /**
     * Menu que surge quando Transportadora logada fazer um pedido de Entrega de uma Encomenda a um Utilizador
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void realizaEncomendaPedidaTransportadora (TrazAqui trazAqui) throws TransportadoraInexistenteException, LojaInexistenteException, EncomendaInexistenteException, UtilizadorInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        Transportadora transportadora = trazAqui.getTransportador(trazAqui.getUtilizador_atual());
        String escolha = "";

        view.clearScreen();
        view.print("\n-----------------PEDIDO ENTREGA ENCOMENDA------------------------\n");
        view.print("   Escreva /exit a qualquer momento para abandonar o menu\n");
        while(!escolha.equals("/exit")) {
            if (transportadora.isAvailable()) {
                view.print("\nLojas no Sistema - " + trazAqui.getLojasMap().keySet().toString() + "\n");
                view.print("    Insira o Código da loja: ");
                escolha = sc.nextLine();
                String codLoja = escolha;
                if (!codLoja.startsWith("l")) {
                    codLoja = "l" + codLoja;
                }
                if (trazAqui.procuraLoja(codLoja)) {

                    if (trazAqui.getLoja(codLoja).getCoordenadas().isReachable( transportadora.getCoordenadas(), transportadora.getRaio())) {

                        if (trazAqui.getLoja(codLoja).getEncomendasPorEntregar().size() > 0) {

                            while(!escolha.equals("/exit")) {
                                view.print("\nEncomendas da Loja - " + trazAqui.getLoja(codLoja).getEncomendasPorEntregar().toString() + "\n");
                                view.print("    Insira o Código da Encomenda: ");
                                escolha = sc.nextLine();
                                String codEncomenda = escolha;
                                if (trazAqui.getLoja(codLoja).possuiEncomendaCodigo(codEncomenda)) {
                                    Encomenda enc = trazAqui.getEncomenda(codEncomenda);
                                    if (!(!transportadora.isMedical() && enc.isMedical())) {
                                        if(!(!transportadora.isAvailableMedical() && enc.isMedical())) {
                                            if (trazAqui.getUtilizador(enc.getCodUtilizador()).getCoordenadas().isReachable(transportadora.getCoordenadas(), transportadora.getRaio())) {
                                                trazAqui.realizaEntregaDeVendaTransportadora(codLoja, codEncomenda, trazAqui.getUtilizador_atual());
                                                view.print("\nPedido de Entrega efetuado com sucesso!\n");
                                                break;
                                            } else {
                                                view.print("Nao consegue alcançar utilizador!\n");
                                            }
                                        } else {
                                            view.print("Transportadora não se encontra Disponível para transportar Encomendas Médicas");
                                        }
                                    } else {
                                        view.print("Transportadora não possui Capacidades para transportar Encomendas Médicas.\n");
                                    }
                                } else {
                                    if (escolha.equals("/exit")) {
                                        view.print("Usuário abandonou o menu.\n");
                                        break;
                                    }
                                    else view.print("Encomenda Pendente inexistente!\n");
                                }
                            }
                            break;

                        } else {
                            view.print("Loja não possui Encomendas por entregar!\n");
                        }

                    } else {
                        view.print("Não consegue alcançar esta loj!a\n");
                    }
                } else {
                    if (escolha.equals("/exit")) {
                        view.print("Usuário abandonou o menu.\n");
                        break;
                    }
                    else view.print("Loja Inexistente!\n");
                }
            } else {
                view.print("Transportadora Não Disponível para realizar Entregas.\n");
                break;
            }
        }
    }


    /********************* MENUS DAS LOJAS *******************/
    /**
     * Menú apresentando todas as opções quando se encontra loggado numa Loja
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void menuLoja(TrazAqui trazAqui) throws LojaInexistenteException
    {
        Scanner sc = new Scanner(System.in);

        while(true) {
            view.printMenuLojas(trazAqui.getLoja(trazAqui.getUtilizador_atual()).getEncomendasPorAceitar().size());

            String escolha = sc.nextLine();

            if (escolha.equals("0")) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (escolha.equals("1")) {
                view.clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
            }
            else if (escolha.equals("2")) {
                view.clearScreen();
                aceitaOuRecusaTodosPedidosEncomenda(trazAqui);
                esperaInput();
                break;
            }
            else if (escolha.equals("3")) {
                view.clearScreen();
                view.imprimeHistoricoEntidade(trazAqui.getLoja(trazAqui.getUtilizador_atual()).getEncomendasHistorico());
                esperaInput();
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }
        }
    }

    /**
     * Menu que surge quando Loja terá de aceitar ou recusar as todas as Encomendas que lhe foram pedidas por um Utilizador e ainda não foram revistas
     * @param trazAqui      Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void aceitaOuRecusaTodosPedidosEncomenda(TrazAqui trazAqui) throws LojaInexistenteException
    {
        Loja lojaAux = trazAqui.getLoja(trazAqui.getUtilizador_atual());
        view.print("Aceite ou recuse os pedidos de entrega por parte dos Utilizadores (y-n): \n");
        lojaAux.getEncomendasPorAceitar()
                .forEach(key -> {
                    try {
                        aceitaOuRecusaUmPedidoEncomenda(trazAqui, key);
                    } catch (EncomendaInexistenteException e) {
                        e.printStackTrace();
                    }
                });
        view.print("\nTodas as Encomendas pedidas aceitadas ou recusadas com sucesso.");
        trazAqui.lojaAceitaOuRecusaTodasEncomenda(trazAqui.getUtilizador_atual());
    }


    /**
     * Menu que surge quando Loja possui as opções de aceitar ou recusar o pedido de uma Encomenda em específico
     * @param trazAqui          Model principal á volta do qual se trabalha ao longo do projeto
     * @param codEncomenda      Código da Encomenda a aceitar ou recusar
     */
    private void aceitaOuRecusaUmPedidoEncomenda(TrazAqui trazAqui, String codEncomenda) throws EncomendaInexistenteException
    {
        Scanner sc = new Scanner(System.in);

        view.print("\n  Pedido da seguinte Encomenda " +codEncomenda+ ":");
        view.print(trazAqui.getEncomenda(codEncomenda));
        while (true) {
            view.print("Aceitar ou Recusar (y-n): ");
            String aceita = sc.nextLine();
            if (aceita.equals("y") || aceita.equals("Y")) {
                trazAqui.lojaAceitaOuRecusaEncomenda(codEncomenda, true);
                break;
            }
            else if (aceita.equals("n") || aceita.equals("N")) {
                trazAqui.lojaAceitaOuRecusaEncomenda(codEncomenda, false);
                break;
            }
            else {
                view.print("Aceitação Inválida\n");
            }
        }
    }

    /************* REGISTAR NOVAS ENTIDADES ****************/
    /**
     * Menu onde constam as opções de registo de uma nova Loja no Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void registaLoja(TrazAqui trazAqui)
    {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        view.clearScreen();
        view.print("------REGISTO DE LOJA--------\n");
        view.print("Nome: ");
        String nome = sc.nextLine();
        String codigo;
        while(true) {
            codigo = "l" + (r.nextInt(250) + 1);
            if (!trazAqui.procuraLoja(codigo)) {
                break;
            }
        }
        view.print("Coordenadas:\n");
        view.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        view.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());
        view.print("Fila de espera? [y/n]: ");
        char c = sc.nextLine().toCharArray()[0];
        boolean temFila = c == 'y';

        Loja newLoja = new Loja(nome, codigo, codigo, new GPS(latitude,longitude), temFila);
        trazAqui.insereLoja(newLoja);

        view.print("\nLoja registada com sucesso!\n");
        esperaInput();
    }

    /**
     * Menu onde constam as opções de registo de um novo Voluntário no Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void registaVoluntario(TrazAqui trazAqui)
    {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        view.clearScreen();
        view.print("------REGISTO DE VOLUNTARIO--------\n");
        view.print("Nome: ");
        String nome = sc.nextLine();
        String codigo;
        while(true) {
            codigo = "v" + (r.nextInt(250) + 1);
            if (!trazAqui.procuraVoluntario(codigo)) {
                break;
            }
        }
        view.print("Coordenadas:\n");
        view.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        view.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());
        view.print("Raio: ");
        double raio = Double.parseDouble(sc.nextLine());
        view.print("Medical? [y/n]: ");
        char c = sc.nextLine().toCharArray()[0];
        boolean medical = c == 'y';
        double velocidadeMedia = 40.0 + (60.0 - 40.0)*r.nextDouble();

        Voluntario newVoluntario = new Voluntario(nome, codigo, new GPS(latitude,longitude), codigo, velocidadeMedia, raio, medical);
        trazAqui.insereVoluntario(newVoluntario);

        view.print("\nVoluntário registado com sucesso!\n");
        esperaInput();
    }

    /**
     * Menu onde constam as opções de registo de uma nova Transportadora no Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void registaTransportadora(TrazAqui trazAqui)
    {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        view.clearScreen();
        view.print("------REGISTO DE TRANSPORTADORA--------\n");
        view.print("Nome: ");
        String nome = sc.nextLine();
        String codigo;
        while(true) {
            codigo = "t" + (r.nextInt(250) + 1);
            if (!trazAqui.procuraTransportadora(codigo)) {
                break;
            }
        }
        view.print("Coordenadas:\n");
        view.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        view.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());
        view.print("NIF: ");
        int nif = Integer.parseInt(sc.nextLine());
        view.print("Raio: ");
        double raio = Double.parseDouble(sc.nextLine());
        view.print("Preço por km: ");
        double preco_km = Double.parseDouble(sc.nextLine());
        view.print("Limite de encomendas: ");
        int limite = Integer.parseInt(sc.nextLine());
        view.print("Medical? [y/n]: ");
        char c = sc.nextLine().toCharArray()[0];
        boolean medical = c == 'y';
        double velocidadeMedia = 70.0 + (90.0 - 70.0)*r.nextDouble();

        Transportadora newTransportadora = new Transportadora(nome, codigo, new GPS(latitude,longitude), codigo, velocidadeMedia, nif, raio, preco_km, limite, medical);
        trazAqui.insereTransportadora(newTransportadora);

        view.print("\nTransportadora registada com sucesso!\n");
        esperaInput();
    }

    /**
     * Menu onde constam as opções de registo de um novo Utilizador no Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    private void registaUtilizador(TrazAqui trazAqui)
    {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        view.clearScreen();
        view.print("------REGISTO DE UTILIZADOR--------\n");
        view.print("Nome: ");
        String nome = sc.nextLine();
        String codigo;
        while(true) {
            codigo = "u" + (r.nextInt(250) + 1);
            if (!trazAqui.procuraUtilizador(codigo)) {
                break;
            }
        }
        view.print("Coordenadas:\n");
        view.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        view.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());

        Utilizador newUtilizador = new Utilizador(nome, codigo, new GPS(latitude,longitude), codigo);
        trazAqui.insereUtilizador(newUtilizador);

        view.print("\nUtilizador registado com sucesso!\n");
        esperaInput();
    }

    /**
     * Função que espera um Input por parte do Usuário
     */
    private void esperaInput()
    {
        Scanner sc = new Scanner(System.in);
        view.print("\nPressione <ENTER> para continuar.\n");
        sc.nextLine();
    }

    /**
     * Função que grava o estado atual do Model do MVC num ficheiro
     */
    private void saveToDisk()
    {
        Scanner sc = new Scanner(System.in);
        view.print("Introduza o nome do Ficheiro com o model que pretende guardar\n");
        view.print("Nome do ficheiro : ");
        String filename = sc.nextLine();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename + ".dat"));
            out.writeObject(this.model);
            view.print("Model guardado com sucesso\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Função que dá load, se possível para o Model do MVC de um estado do Model guardado em ficheiro anteriormente
     */
    private void loadFromDisk()
    {
        Scanner sc = new Scanner(System.in);
        TrazAqui new_TrazAqui = null;
        view.print("Introduza o nome do Ficheiro com o model que pretende ler\n");
        view.print("Nome do ficheiro : ");
        String filename = sc.nextLine();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".dat"));
            new_TrazAqui = (TrazAqui) in.readObject();
            this.model = new_TrazAqui;
            view.print("Model carregado com sucesso\n");
        } catch (IOException e) {
            view.print("IMPOSSÍVEL CARREGAR FICHEIRO\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
