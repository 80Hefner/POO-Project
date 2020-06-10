package Controller;

import Models.*;
import NewExceptions.*;
import Utils.Parser;
import View.MVC_View;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class MVC_Controller {

    private TrazAqui model;
    private MVC_View view;
    private int login = 0;

    public TrazAqui getModel() {
        return model;
    }

    public void setModel(TrazAqui model) {
        this.model = model;
    }

    public MVC_View getView() {
        return view;
    }

    public void setView(MVC_View view) {
        this.view = view;
    }

    public MVC_Controller(TrazAqui model, MVC_View view) {
        this.model = model;
        this.view = view;
    }

    public void menuPrincipal(String data_path) throws EncomendaInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        parser.parseLogs(data_path, this.model);

        while(true) {
            if (login == 0)
                menuEscolhas(this.model);
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
            else if (escolha.equals("3") && trazAqui != null) {
                view.clearScreen();
                view.print("\n--------------------- Traz Aqui a ser guardado -------------------\n");
                saveToDisk();
                break;
            }
            else if (escolha.equals("4")) {
                view.clearScreen();
                view.print("\n--------------------- Traz Aqui a dar load -------------------\n");
                loadFromDisk();
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
                break;
            }
        }
    }

    private void menuEscolhaLogin (TrazAqui trazAqui)
    {
        view.printMenuEscolheLogin();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String escolha = sc.nextLine();

            if (escolha.equals("0") || escolha.equals("\n")){
                break;
            }
            else if (escolha.equals("1") && trazAqui.getUtilizadores().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                while(true) {
                    view.print("Nome de utilizador: ");
                    String utilizador = sc.nextLine();
                    if (trazAqui.procuraUtilizador(utilizador)) {
                        while(true) {
                            view.print("Password: ");
                            String password = sc.nextLine();
                            if (trazAqui.verificaPassword(utilizador, password)) {
                                login = 1;
                                trazAqui.setUtilizador_atual(utilizador);
                                break;
                            }
                            else view.print("Password incorreta!\n");
                        }
                        break;
                    }
                    else view.print("Utilizador inválido!\n");
                }
                break;
            }
            else if (escolha.equals("2") && trazAqui.getVoluntarios().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                while(true) {
                    view.print("Nome do Voluntario: ");
                    String voluntario = sc.nextLine();
                    if (trazAqui.procuraVoluntario(voluntario)) {
                        while(true) {
                            view.print("Password: ");
                            String password = sc.nextLine();
                            if (password.equals("")) {
                                login = 1;
                                trazAqui.setUtilizador_atual(voluntario);
                                break;
                            }
                            else view.print("Password incorreta!\n");
                        }
                        break;
                    }
                    else view.print("Voluntario inválido!\n");
                }
                break;
            }
            else if (escolha.equals("3") && trazAqui.getTransportadoras().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                while(true) {
                    view.print("Nome da Transportadora: ");
                    String transportadora = sc.nextLine();
                    if (trazAqui.procuraTransportadora(transportadora)) {
                        while(true) {
                            view.print("Password: ");
                            String password = sc.nextLine();
                            if (password.equals("")) {
                                login = 1;
                                trazAqui.setUtilizador_atual(transportadora);
                                break;
                            }
                            else view.print("Password incorreta!\n");
                        }
                        break;
                    }
                    else view.print("Transportadora inválida!\n");
                }
                break;
            }
            else if (escolha.equals("4") && trazAqui.getLojas().size() != 0) {
                view.clearScreen();
                view.print("\n----------------------LOGIN--------------------\n");
                while(true) {
                    view.print("Nome da Loja: ");
                    String loja = sc.nextLine();
                    if (trazAqui.procuraLoja(loja)) {
                        while(true) {
                            view.print("Password: ");
                            String password = sc.nextLine();
                            if (password.equals("")) {
                                login = 1;
                                trazAqui.setUtilizador_atual(loja);
                                break;
                            }
                            else view.print("Password incorreta!\n");
                        }
                        break;
                    }
                    else view.print("Loja inválida!\n");
                }
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }

        }
    }

    public void menuEscolhaRegisto (TrazAqui trazAqui) {
        view.printMenuRegistoEntidade();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                escolha = "-1";
            }

            if (escolha.equals("0") || escolha.equals("\n")) {
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
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }
        }
    }

    private void listagemEntidades (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        boolean escolheuCerto = false;
        view.clearScreen();
        view.imprimeMenuListagemEntidades();
        while(true) {
            view.print("(0 - Lojas | 1 - Voluntários | 2 - Transportadoras | 3 - Utilizadores | 4 - Aceites): ");
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
                view.print("Aceitação Inválida");
            }
        }
    }

    /********************* MENUS DO VOLUNTÁRIO *******************/
    private void menuVoluntario(TrazAqui trazAqui) throws UtilizadorInexistenteException, EncomendaInexistenteException, VoluntarioInexistenteException, LojaInexistenteException
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
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }
        }
    }

    public void realizaEncomendaPedidaVoluntario (TrazAqui trazAqui) throws VoluntarioInexistenteException, LojaInexistenteException, EncomendaInexistenteException, UtilizadorInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        Voluntario voluntario = trazAqui.getVoluntario(trazAqui.getUtilizador_atual());
        String escolha = "";

        view.clearScreen();
        view.print("\n-----------------PEDIDO ENTREGA ENCOMENDA------------------------\n");
        while(!escolha.equals("exit")) {
            if (voluntario.isAvailable()) {
                view.print("Insira o Código da loja:\n");
                escolha = sc.nextLine();
                String codLoja = escolha;
                if (!codLoja.startsWith("l")) {
                    codLoja = "l" + codLoja;
                }
                if (trazAqui.procuraLoja(codLoja)) {

                    if (trazAqui.getLoja(codLoja).getCoordenadas().isReachable( voluntario.getCoordenadas(), voluntario.getRaio())) {

                        while(!escolha.equals("exit")) {
                            view.print("Insira o Código da Encomenda:\n");
                            escolha = sc.nextLine();
                            String codEncomenda = escolha;
                            if (trazAqui.getLoja(codLoja).possuiEncomendaCodigo(codEncomenda)) {
                                Encomenda enc = trazAqui.getEncomenda(codEncomenda);
                                if (!(!voluntario.isMedical() && enc.isMedical())) {
                                    if (trazAqui.getUtilizador(enc.getCodUtilizador()).getCoordenadas().isReachable(voluntario.getCoordenadas(), voluntario.getRaio())) {
                                        Encomenda res = trazAqui.realizaEntregaDeVenda(codLoja, codEncomenda, trazAqui.getUtilizador_atual());
                                        view.print("Entrega feita com sucesso\n");
                                        view.imprimeEntregaEncomendaVol(res);
                                        break;
                                    } else {
                                        view.print("Nao consegue alcançar utilizador\n");
                                    }
                                } else {
                                    view.print("Voluntário não se encontra preparado para transportar Encomendas Médicas.\n");
                                }
                            } else {
                                view.print("Encomenda Pendente inexistente\n");
                            }
                        }
                        break;
                    } else {
                        view.print("Não consegue alcançar esta loja\n");
                    }
                } else {
                    view.print("Loja Inexistente\n");
                }
            } else {
                view.print("Voluntário Não Disponível para realizar Entregas.\n");
                break;
            }
        }
    }

    public void alteraDisponibilidadeEntidade (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        String codEntidade = trazAqui.getUtilizador_atual();
        String escolha = "";

        while(!escolha.equals("exit")) {
            view.print("Qual disponibilidade quer colocar no Voluntario?\n");
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

    /********************* MENUS DO TRANSPORTADORA *******************/
    private void menuTransportadora(TrazAqui trazAqui) throws TransportadoraInexistenteException, UtilizadorInexistenteException, EncomendaInexistenteException, LojaInexistenteException
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
                Set<Map.Entry<String, Double>> res = trazAqui.getLista10TransportadorasMaisKilometros();
                view.imprimeQuerie10Transportadoras(res);
                esperaInput();
                break;
            }
            else if (escolha.equals("5")) {
                view.clearScreen();
                Double res = trazAqui.getTotalFaturadoTransportadora(trazAqui.getUtilizador_atual());
                view.print("Empresa " + trazAqui.getUtilizador_atual() + " faturou " + fmt.format(res) + " €\n");
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

    public void realizaEncomendaPedidaTransportadora (TrazAqui trazAqui) throws TransportadoraInexistenteException, LojaInexistenteException, EncomendaInexistenteException, UtilizadorInexistenteException
    {
        Scanner sc = new Scanner(System.in);
        Transportadora transportadora = trazAqui.getTransportador(trazAqui.getUtilizador_atual());
        String escolha = "";

        view.clearScreen();
        view.print("\n-----------------PEDIDO ENTREGA ENCOMENDA------------------------\n");
        while(!escolha.equals("exit")) {
            if (transportadora.isAvailable()) {
                view.print("Insira o Código da loja:\n");
                escolha = sc.nextLine();
                String codLoja = escolha;
                if (!codLoja.startsWith("l")) {
                    codLoja = "l" + codLoja;
                }
                if (trazAqui.procuraLoja(codLoja)) {

                    if (trazAqui.getLoja(codLoja).getCoordenadas().isReachable( transportadora.getCoordenadas(), transportadora.getRaio())) {

                        while(!escolha.equals("exit")) {
                            view.print("Insira o Código da Encomenda:\n");
                            escolha = sc.nextLine();
                            String codEncomenda = escolha;
                            if (trazAqui.getLoja(codLoja).possuiEncomendaCodigo(codEncomenda)) {
                                Encomenda enc = trazAqui.getEncomenda(codEncomenda);
                                if (!(!transportadora.isMedical() && enc.isMedical())) {
                                    if (trazAqui.getUtilizador(enc.getCodUtilizador()).getCoordenadas().isReachable(transportadora.getCoordenadas(), transportadora.getRaio())) {
                                        trazAqui.realizaEntregaDeVendaTransportadora(codLoja, codEncomenda, trazAqui.getUtilizador_atual());
                                        view.print("Pedido de Entrega efetuado com sucesso");
                                        break;
                                    } else {
                                        view.print("Nao consegue alcançar utilizador\n");
                                    }
                                } else {
                                    view.print("Transportadora não se encontra preparada para transportar Encomendas Médicas.\n");
                                }
                            } else {
                                view.print("Encomenda Pendente inexistente\n");
                            }
                        }
                        break;
                    } else {
                        view.print("Não consegue alcançar esta loja\n");
                    }
                } else {
                    view.print("Loja Inexistente\n");
                }
            } else {
                view.print("Transportadora Não Disponível para realizar Entregas.\n");
                break;
            }
        }
    }


    /********************* MENUS DAS LOJAS *******************/
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
            else if (escolha.equals("3")) {
                view.clearScreen();
                aceitaOuRecusaTodosPedidosEncomenda(trazAqui);
                esperaInput();
                break;
            }
            else {
                view.print("Opção inválida!\n");
                esperaInput();
            }
        }
    }

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

        Loja newLoja = new Loja(nome, codigo, new GPS(latitude,longitude), temFila);
        trazAqui.insereLoja(newLoja);

        view.print("\nLoja registada com sucesso!\n");
        esperaInput();
    }

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

        Voluntario newVoluntario = new Voluntario(nome, codigo, new GPS(latitude,longitude), "", velocidadeMedia, raio, medical);
        trazAqui.insereVoluntario(newVoluntario);

        view.print("\nVoluntário registado com sucesso!\n");
        esperaInput();
    }

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

        Transportadora newTransportadora = new Transportadora(nome, codigo, new GPS(latitude,longitude), "", velocidadeMedia, nif, raio, preco_km, limite, medical);
        trazAqui.insereTransportadora(newTransportadora);

        view.print("\nTransportadora registada com sucesso!\n");
        esperaInput();
    }

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
        view.print("Password: ");
        String password = sc.nextLine();

        Utilizador newUtilizador = new Utilizador(nome, codigo, new GPS(latitude,longitude), password);
        trazAqui.insereUtilizador(newUtilizador);

        view.print("\nUtilizador registado com sucesso!\n");
        esperaInput();
    }

    private void esperaInput()
    {
        Scanner sc = new Scanner(System.in);
        view.print("\nPressione <ENTER> para continuar.\n");
        sc.nextLine();
    }

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
