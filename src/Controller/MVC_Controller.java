package Controller;

import Models.*;
import Utils.Parser;
import View.MVC_View;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

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

    public void menuPrincipal(String data_path)
    {
        int opcao;
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        parser.parseLogs(data_path, this.model);

        while(true) {

            if (login == 0)
                menuLogin(this.model);
/*            else if (TrazAqui.getUtilizador_atual().equals("admin"))
                menuAdmin();*/
            else if (this.model.getUtilizador_atual().startsWith("u"))
                menuUtilizador(this.model);
            else if (this.model.getUtilizador_atual().startsWith("v"))
                menuVoluntario(this.model);
            else if (this.model.getUtilizador_atual().startsWith("t"))
                menuTransportadora(this.model);
            else if (this.model.getUtilizador_atual().startsWith("l"))
                menuLoja(this.model);
        }
    }

    private void menuLogin(TrazAqui trazAqui)
    {
        int opcao;
        Scanner sc = new Scanner(System.in);

        while(true) {

            clearScreen();
            System.out.println("----------------------MENU LOGIN--------------------\n");
            System.out.println("0 -> Sair do programa.");
            System.out.println("1 -> Efetuar login.");
            System.out.println("2 -> Registar novo utilizador.");
            System.out.println("3 -> Registar entidade.");
            System.out.println("4 -> Efetuar login com Voluntario.");
            System.out.println("5 -> Efetuar login com Transportadora.");
            System.out.println("6 -> Efetuar login com Loja.");
            System.out.println("7 -> Save to Disk");
            System.out.println("8 -> Load from Disk");
            System.out.print("OPÇÃO: ");

            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(escolha);
            }

            if (opcao == 0)
                System.exit(0);
            else if (opcao == 1 && trazAqui.getUtilizadores().size() != 0) {
                clearScreen();
                System.out.println("\n----------------------LOGIN--------------------");
                while(true) {
                    System.out.print("Nome de utilizador: ");
                    String utilizador = sc.nextLine();
                    if (trazAqui.procuraUtilizador(utilizador)) {
                        while(true) {
                            System.out.print("Password: ");
                            String password = sc.nextLine();
                            if (trazAqui.verificaPassword(utilizador, password)) {
                                login = 1;
                                trazAqui.setUtilizador_atual(utilizador);
                                break;
                            }
                            else System.out.println("Password incorreta!");
                        }
                        break;
                    }
                    else System.out.println("Utilizador inválido!");
                }
                break;
            }
            else if (opcao == 2) {
                registaUtilizador(trazAqui);
            }
            else if (opcao == 3) {
                clearScreen();
                System.out.println("----------------------MENU REGISTO DE ENTIDADE--------------------\n");
                System.out.println("Entidade a registar:");
                System.out.println("1 -> Loja | 2 -> Voluntário | 3 -> Transportadora");
                opcao = Integer.parseInt(sc.nextLine());

                if (opcao == 1)
                    registaLoja(trazAqui);
                else if (opcao == 2)
                    registaVoluntario(trazAqui);
                else if (opcao == 3)
                    registaTransportadora(trazAqui);
            }
            else if (opcao == 4 && trazAqui.getVoluntarios().size() != 0) {
                clearScreen();
                System.out.println("\n----------------------LOGIN--------------------");
                while(true) {
                    System.out.print("Nome do Voluntario: ");
                    String voluntario = sc.nextLine();
                    if (trazAqui.procuraVoluntario(voluntario)) {
                        while(true) {
                            System.out.print("Password: ");
                            String password = sc.nextLine();
                            if (password.equals("")) {
                                login = 1;
                                trazAqui.setUtilizador_atual(voluntario);
                                break;
                            }
                            else System.out.println("Password incorreta!");
                        }
                        break;
                    }
                    else System.out.println("Voluntario inválido!");
                }
                break;
            }
            else if (opcao == 5 && trazAqui.getTransportadoras().size() != 0) {
                clearScreen();
                System.out.println("\n----------------------LOGIN--------------------");
                while(true) {
                    System.out.print("Nome da Transportadora: ");
                    String transportadora = sc.nextLine();
                    if (trazAqui.procuraTransportadora(transportadora)) {
                        while(true) {
                            System.out.print("Password: ");
                            String password = sc.nextLine();
                            if (password.equals("")) {
                                login = 1;
                                trazAqui.setUtilizador_atual(transportadora);
                                break;
                            }
                            else System.out.println("Password incorreta!");
                        }
                        break;
                    }
                    else System.out.println("Transportadora inválida!");
                }
                break;
            }
            else if (opcao == 6 && trazAqui.getLojas().size() != 0) {
                clearScreen();
                System.out.println("\n----------------------LOGIN--------------------");
                while(true) {
                    System.out.print("Nome da Loja: ");
                    String loja = sc.nextLine();
                    if (trazAqui.procuraLoja(loja)) {
                        while(true) {
                            System.out.print("Password: ");
                            String password = sc.nextLine();
                            if (password.equals("")) {
                                login = 1;
                                trazAqui.setUtilizador_atual(loja);
                                break;
                            }
                            else System.out.println("Password incorreta!");
                        }
                        break;
                    }
                    else System.out.println("Loja inválida!");
                }
                break;
            }
            else if (opcao == 7 && trazAqui != null) {
                clearScreen();
                System.out.println("\n----------------------Traz Aqui a ser guardado--------------------");
                saveToDisk();
                break;
            }
            else if (opcao == 8) {
                clearScreen();
                System.out.println("\n----------------------Traz Aqui a dar load--------------------");
                loadFromDisk();
                break;
            }
            else {
                System.out.println("Opção inválida!");
                esperaInput();
            }
        }
    }

    /********************* MENUS DO UTILIZADOR *******************/
    private void menuUtilizador(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        int opcao;

        while(true) {
            //Falta fazer a 3 e a 4
            clearScreen();
            System.out.println("----------------------MENU UTILIZADOR--------------------\n");
            System.out.println("0 -> Logout.");
            System.out.println("1 -> Listar entidades no sistema.");
            System.out.println("2 -> Fazer pedido de encomenda.");
            System.out.println("3 -> Avaliar Encomendas que foram Entregues (" + trazAqui.getUtilizador(trazAqui.getUtilizador_atual()).getEncomendasCompletadasPorAvaliar().size() +").");
            System.out.println("4 -> Aceitar Encomendas propostas entregar por uma Transportadora (" + trazAqui.getUtilizador(trazAqui.getUtilizador_atual()).getCodsEncomendasTransportadoraPorAceitar().size() +").");

            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(escolha);
            }

            if (opcao == 0) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
            }
            else if (opcao == 2) {
                Encomenda e = novaEncomenda(trazAqui);
                trazAqui.adicionaEncomendaAoSistema(e);
            }
            else if (opcao == 3) {
                clearScreen();
                avaliaTodasAsEncomendasFeitas(trazAqui);
                esperaInput();
                break;
            }
            else if (opcao == 4) {
                clearScreen();
                aceitaOuRecusasTodasAsPropostas(trazAqui);
                esperaInput();
                break;
            }
            else {
                System.out.println("Opção inválida!");
                esperaInput();
            }
        }
    }

    private void listagemEntidades (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        int escolhaInt;
        boolean escolheuCerto = false;
        clearScreen();
        System.out.println("\n#*#*#*#*#*#*#*#*#*#*#*#*#*# TRAZ AQUI #*#*#*#*#*#*#*#*#*#*#*#*#*#");
        System.out.println("Escolha tipo de entidades que pretende listar");
        while(true) {
            System.out.print("(0 - Lojas | 1 - Voluntários | 2 - Transportadoras | 3 - Utilizadores | 4 - Aceites): ");
            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                escolhaInt = -1;
            } else {
                escolhaInt = Integer.parseInt(escolha);
            }

            switch (escolhaInt) {
                case 0:
                    System.out.println("\n----------------------------- LOJAS -----------------------------\n");
                    for (Loja loja : trazAqui.getLojas()) {
                        System.out.println(loja.toString());
                    }
                    escolheuCerto = true;
                    break;
                case 1:
                    System.out.println("\n-------------------------- VOLUNTARIOS --------------------------\n");
                    for (Voluntario voluntario : trazAqui.getVoluntarios()) {
                        System.out.println(voluntario.toString());
                    }
                    escolheuCerto = true;
                    break;
                case 2:
                    System.out.println("\n------------------------ TRANSPORTADORAS ------------------------\n");
                    for (Transportadora transportadora : trazAqui.getTransportadoras()) {
                        System.out.println(transportadora.toString());
                    }
                    escolheuCerto = true;
                    break;
                case 3:
                    System.out.println("\n------------------------- UTILIZADORES -------------------------\n");
                    for (Utilizador utilizador : trazAqui.getUtilizadores()) {
                        System.out.println(utilizador.toString());
                    }
                    escolheuCerto = true;
                    break;
                case 4:
                    System.out.println("\n---------------------------- ACEITES ----------------------------\n");
                    System.out.println(trazAqui.getEncomendasAceites().toString());
                    escolheuCerto = true;
                    break;
                default:
                    System.out.println("Opção Inválida");
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

        clearScreen();
        System.out.println("------NOVA ENCOMENDA--------");

        while(true) {
            String codigo = "e" + (r.nextInt(8999) + 1000);
            if (!trazAqui.procuraEncomendaAceite(codigo)) {
                e.setCodigo(codigo);
                break;
            }
        }
        System.out.print("Código da loja: ");
        e.setCodLoja(sc.nextLine());
        e.setCodUtilizador(trazAqui.getUtilizador_atual());
        System.out.print("Número de produtos a encomendar: ");
        int nr_produtos = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nr_produtos; i++) {
            LinhaEncomenda l = novaLinhaEncomenda();
            e.insereLinhaEncomenda(l);
            e.setPeso(e.getPeso() + l.getQuantidade() * l.getValor_unidade() / 10);
        }
        e.setMedical(e.getProdutos().stream().anyMatch(l -> l.getDescricao().equals("Desinfetante") || l.getDescricao().equals("Água sanitária")));
        e.setData(LocalDateTime.now());

        return e;
    }

    private LinhaEncomenda novaLinhaEncomenda()
    {
        Scanner sc = new Scanner(System.in);
        LinhaEncomenda l = new LinhaEncomenda();

        clearScreen();
        System.out.println("------NOVA LINHA ENCOMENDA--------");
        System.out.print("Código do produto: ");
        l.setCodigo(sc.nextLine());
        System.out.print("Descrição: ");
        l.setDescricao(sc.nextLine());
        System.out.print("Quantidade: ");
        l.setQuantidade(Integer.parseInt(sc.nextLine()));
        System.out.print("Valor por unidade: ");
        l.setValor_unidade(Integer.parseInt(sc.nextLine()));

        return l;
    }

    private void avaliaTodasAsEncomendasFeitas(TrazAqui trazAqui)
    {
        Utilizador utilizadorAux = trazAqui.getUtilizador(trazAqui.getUtilizador_atual());
        System.out.println("Avalie todas as entregas da(s) Encomenda(s) de 0 a 10: ");
        utilizadorAux.getEncomendasCompletadasPorAvaliar()
                .forEach((key, value) -> avaliaUmaEncomendaFeita(trazAqui, key, value.getKey(), value.getValue()));
        System.out.println("\nTodas as Encomendas feitas avaliadas com sucesso.");
        trazAqui.todasEncomendasFeitasAvaliadas(trazAqui.getUtilizador_atual());
    }


    private void avaliaUmaEncomendaFeita(TrazAqui trazAqui, String codEncomenda, double tempoTransporte, double preçoTransporte)
    {
        DecimalFormat fmt = new DecimalFormat("0.00");
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrega da Encomenda "+codEncomenda+" demorou " + (int)tempoTransporte/60 + "Horas e "
                + (int)tempoTransporte%60 + " minutos. Teve o custo de " + fmt.format(preçoTransporte) + "€." );
        while (true) {
            System.out.print("\nAvaliação(0-10): ");
            double avaliacao = sc.nextDouble();
            if (avaliacao >= 0.0 && avaliacao <= 10.0) {
                trazAqui.avaliaEntregaEncomenda(codEncomenda, avaliacao);
                if (preçoTransporte == 0.0) {
                    System.out.println("Voluntário responsável pela entrega da Encomenda avaliado com sucesso.");
                } else {
                    System.out.println("Transportadora responsável pela entrega da Encomenda avaliada com sucesso.");
                }
                break;
            } else {
                System.out.println("Avaliação Inválida");
            }
        }
    }

    private void aceitaOuRecusasTodasAsPropostas(TrazAqui trazAqui)
    {
        Utilizador utilizadorAux = trazAqui.getUtilizador(trazAqui.getUtilizador_atual());
        System.out.println("Aceite ou recuse as seguintes propostas de Entrega:");
        utilizadorAux.getCodsEncomendasTransportadoraPorAceitar()
                .forEach((key, value) -> aceitaOuRecusaUmaProposta(trazAqui, key, value.getKey(), value.getValue()));
        System.out.println("\nTodas as Encomendas feitas avaliadas com sucesso.");
        trazAqui.todasEntregasAceitesOuRecusadas(trazAqui.getUtilizador_atual());
    }


    private void aceitaOuRecusaUmaProposta(TrazAqui trazAqui, String codEncomenda, double tempoTransporte, double preçoTransporte)
    {
        Scanner sc = new Scanner(System.in);
        DecimalFormat fmt = new DecimalFormat("0.00");

        System.out.println("Entrega da Encomenda "+codEncomenda+" irá demorar cerca de " + (int)tempoTransporte/60 + "Horas e "
                + (int)tempoTransporte%60 + " minutos. Terá um custo de " + fmt.format(preçoTransporte) + "€." );

        while (true) {
            System.out.print("\nAceitar ou Recusar (y-n): ");
            String aceita = sc.nextLine();
            if (aceita.equals("y") || aceita.equals("Y")) {
                trazAqui.utilizadorAceitaOuRecusaEntrega(codEncomenda, true);
                break;
            }
            else if (aceita.equals("n") || aceita.equals("N")) {
                trazAqui.utilizadorAceitaOuRecusaEntrega(codEncomenda, false);
                break;
            }
            else {
                System.out.println("Aceitação Inválida");
            }
        }
    }

    /********************* MENUS DO VOLUNTÁRIO *******************/
    private void menuVoluntario(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        int opcao;

        while(true) {

            clearScreen();
            System.out.println("----------------------MENU VOLUNTÁRIO--------------------\n");
            System.out.println("0 -> Logout.");
            System.out.println("1 -> Listar entidades no sistema.");
            System.out.println("2 -> Fazer pedido para entregar encomenda.");
            System.out.println("3 -> Altera disponibilidade de entrega.");

            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(escolha);
            }

            if (opcao == 0) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
            }
            else if (opcao == 2) {
                clearScreen();
                realizaEncomendaPedidaVoluntario(trazAqui);
                esperaInput();
                break;
            }
            else if (opcao == 3) {
                clearScreen();
                alteraDisponibilidadeEntidade(trazAqui);
                esperaInput();
                break;
            }
            else {
                System.out.println("Opção inválida!");
                esperaInput();
            }
        }
    }

    public void realizaEncomendaPedidaVoluntario (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        Voluntario voluntario = trazAqui.getVoluntario(trazAqui.getUtilizador_atual());

        clearScreen();
        System.out.println("\n-----------------PEDIDO ENTREGA ENCOMENDA------------------------");
        while(true) {
            if (voluntario.isAvailable()) {
                System.out.println("Insira o Código da loja:");
                String codLoja = sc.nextLine();
                if (!codLoja.startsWith("l")) {
                    codLoja = "l" + codLoja;
                }
                if (trazAqui.procuraLoja(codLoja)) {

                    if (trazAqui.getLoja(codLoja).getCoordenadas().isReachable( voluntario.getCoordenadas(), voluntario.getRaio())) {

                        while(true) {
                            System.out.println("Insira o Código da Encomenda:");
                            String codEncomenda = sc.nextLine();
                            if (trazAqui.getLoja(codLoja).possuiEncomendaCodigo(codEncomenda)) {
                                Encomenda enc = trazAqui.getEncomenda(codEncomenda);
                                if (trazAqui.getUtilizador(enc.getCodUtilizador()).getCoordenadas().isReachable(voluntario.getCoordenadas(), voluntario.getRaio())) {
                                    String res = trazAqui.realizaEntregaDeVenda(codLoja, codEncomenda, trazAqui.getUtilizador_atual());
                                    System.out.println("Entrega feita com sucesso");
                                    System.out.println(res);
                                    break;
                                } else {
                                    System.out.println("Nao consegue alcançar utilizador");
                                }
                            } else {
                                System.out.println("Encomenda Pendente inexistente");
                            }
                        }
                        break;
                    } else {
                        System.out.println("Não consegue alcançar esta loja");
                    }
                } else {
                    System.out.println("Loja Inexistente");
                }
            } else {
                System.out.println("Voluntário Não Disponível para realizar Entregas.");
                break;
            }
        }
    }

    public void alteraDisponibilidadeEntidade (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        String codEntidade = trazAqui.getUtilizador_atual();

        while(true) {
            System.out.println("Qual disponibilidade quer colocar no Voluntario?");
            System.out.print("(y - Disponível | n - Não Disponível) : ");
            String disponibilidade = sc.nextLine();
            if (disponibilidade.startsWith("y") || disponibilidade.startsWith("Y")) {
                trazAqui.setAvailable(codEntidade, true);
                System.out.println("Entidade definida como Disponível para entregas!");
                break;
            } else if (disponibilidade.startsWith("n") || disponibilidade.startsWith("N")) {
                trazAqui.setAvailable(codEntidade, false);
                System.out.println("Entidade definida como Não Disponível para entregas!");
                break;
            }
        }
    }

    /********************* MENUS DO TRANSPORTADORA *******************/
    private void menuTransportadora(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        int opcao;

        while(true) {

            clearScreen();
            System.out.println("----------------------MENU TRANSPORTADORA--------------------\n");
            System.out.println("0 -> Logout.");
            System.out.println("1 -> Listar entidades no sistema.");
            System.out.println("2 -> Fazer pedido para entregar encomenda.");
            System.out.println("3 -> Altera disponibilidade de entrega.");

            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(escolha);
            }

            if (opcao == 0) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
            }
            else if (opcao == 2) {
                clearScreen();
                realizaEncomendaPedidaTransportadora(trazAqui);
                esperaInput();
                break;
            }
            else if (opcao == 3) {
                clearScreen();
                alteraDisponibilidadeEntidade(trazAqui);
                esperaInput();
                break;
            }
            else {
                System.out.println("Opção inválida!");
                esperaInput();
            }
        }
    }

    public void realizaEncomendaPedidaTransportadora (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        Transportadora transportadora = trazAqui.getTransportador(trazAqui.getUtilizador_atual());

        clearScreen();
        System.out.println("\n-----------------PEDIDO ENTREGA ENCOMENDA------------------------");
        while(true) {
            if (transportadora.isAvailable()) {
                System.out.println("Insira o Código da loja:");
                String codLoja = sc.nextLine();
                if (!codLoja.startsWith("l")) {
                    codLoja = "l" + codLoja;
                }
                if (trazAqui.procuraLoja(codLoja)) {

                    if (trazAqui.getLoja(codLoja).getCoordenadas().isReachable( transportadora.getCoordenadas(), transportadora.getRaio())) {

                        while(true) {
                            System.out.println("Insira o Código da Encomenda:");
                            String codEncomenda = sc.nextLine();
                            if (trazAqui.getLoja(codLoja).possuiEncomendaCodigo(codEncomenda)) {
                                Encomenda enc = trazAqui.getEncomenda(codEncomenda);
                                if (trazAqui.getUtilizador(enc.getCodUtilizador()).getCoordenadas().isReachable(transportadora.getCoordenadas(), transportadora.getRaio())) {
                                    String res = trazAqui.realizaEntregaDeVendaTransportadora(codLoja, codEncomenda, trazAqui.getUtilizador_atual());
                                    System.out.println(res);
                                    break;
                                } else {
                                    System.out.println("Nao consegue alcançar utilizador");
                                }
                            } else {
                                System.out.println("Encomenda Pendente inexistente");
                            }
                        }
                        break;
                    } else {
                        System.out.println("Não consegue alcançar esta loja");
                    }
                } else {
                    System.out.println("Loja Inexistente");
                }
            } else {
                System.out.println("Transportadora Não Disponível para realizar Entregas.");
                break;
            }
        }
    }


    /********************* MENUS DAS LOJAS *******************/
    private void menuLoja(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);
        int opcao;

        while(true) {

            clearScreen();
            System.out.println("----------------------MENU LOJA--------------------\n");
            System.out.println("0 -> Logout.");
            System.out.println("1 -> Listar entidades no sistema.");
            System.out.println("2 -> Aceitar pedidos de Encomenda (" + trazAqui.getLoja(trazAqui.getUtilizador_atual()).getEncomendasPorAceitar().size()+").");

            String escolha = sc.nextLine();
            if (escolha.equals("")) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(escolha);
            }

            if (opcao == 0) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                listagemEntidades(trazAqui);
                esperaInput();
            }
            else if (opcao == 2) {
                clearScreen();
                aceitaOuRecusaTodosPedidosEncomenda(trazAqui);
                esperaInput();
                break;
            }
            else {
                System.out.println("Opção inválida!");
                esperaInput();
            }
        }
    }

    private void aceitaOuRecusaTodosPedidosEncomenda(TrazAqui trazAqui)
    {
        Loja lojaAux = trazAqui.getLoja(trazAqui.getUtilizador_atual());
        System.out.println("Aceite ou recuse os pedidos de entrega por parte dos Utilizadores (y-n): ");
        lojaAux.getEncomendasPorAceitar()
                .forEach(key -> aceitaOuRecusaUmPedidoEncomenda(trazAqui, key));
        System.out.println("\n\nTodas as Encomendas pedidas aceitadas ou recusadas com sucesso.");
        trazAqui.lojaAceitaOuRecusaTodasEncomenda(trazAqui.getUtilizador_atual());
    }


    private void aceitaOuRecusaUmPedidoEncomenda(TrazAqui trazAqui, String codEncomenda)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nPedido da seguinte Encomenda " +codEncomenda+ ":");
        System.out.println(trazAqui.getEncomenda(codEncomenda));
        while (true) {
            System.out.print("\nAceitar ou Recusar (y-n): ");
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
                System.out.println("Aceitação Inválida");
            }
        }
    }

    /************* REGISTAR NOVAS ENTIDADES ****************/
    private void registaLoja(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);

        clearScreen();
        System.out.println("------REGISTO DE LOJA--------");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.println("Coordenadas:");
        System.out.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        System.out.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());
        System.out.print("Fila de espera? [y/n]: ");
        char c = sc.nextLine().toCharArray()[0];
        boolean temFila = c == 'y';

        Loja newLoja = new Loja(nome, codigo, new GPS(latitude,longitude), temFila);
        trazAqui.insereLoja(newLoja);

        System.out.println("\nLoja registada com sucesso!");
        esperaInput();
    }

    private void registaVoluntario(TrazAqui trazAqui)
    {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        clearScreen();
        System.out.println("------REGISTO DE VOLUNTARIO--------");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.println("Coordenadas:");
        System.out.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        System.out.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());
        System.out.print("Raio: ");
        double raio = Double.parseDouble(sc.nextLine());
        System.out.print("Medical? [y/n]: ");
        char c = sc.nextLine().toCharArray()[0];
        boolean medical = c == 'y';
        double velocidadeMedia = 40.0 + (60.0 - 40.0)*r.nextDouble();

        Voluntario newVoluntario = new Voluntario(nome, codigo, new GPS(latitude,longitude), "", velocidadeMedia, raio, medical);
        trazAqui.insereVoluntario(newVoluntario);

        System.out.println("\nVoluntário registado com sucesso!");
        esperaInput();
    }

    private void registaTransportadora(TrazAqui trazAqui)
    {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        clearScreen();
        System.out.println("------REGISTO DE TRANSPORTADORA--------");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.println("Coordenadas:");
        System.out.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        System.out.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());
        System.out.print("NIF: ");
        int nif = Integer.parseInt(sc.nextLine());
        System.out.print("Raio: ");
        double raio = Double.parseDouble(sc.nextLine());
        System.out.print("Preço por km: ");
        double preco_km = Double.parseDouble(sc.nextLine());
        System.out.print("Limite de encomendas: ");
        int limite = Integer.parseInt(sc.nextLine());
        System.out.print("Medical? [y/n]: ");
        char c = sc.nextLine().toCharArray()[0];
        boolean medical = c == 'y';
        double velocidadeMedia = 70.0 + (90.0 - 70.0)*r.nextDouble();

        Transportadora newTransportadora = new Transportadora(nome, codigo, new GPS(latitude,longitude), "", velocidadeMedia, nif, raio, preco_km, limite, medical);
        trazAqui.insereTransportadora(newTransportadora);

        System.out.println("\nTransportadora registada com sucesso!");
        esperaInput();
    }

    private void registaUtilizador(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);

        clearScreen();
        System.out.println("------REGISTO DE UTILIZADOR--------");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.println("Coordenadas:");
        System.out.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        System.out.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());
        System.out.print("Password: ");
        String password = sc.nextLine();

        Utilizador newUtilizador = new Utilizador(nome, codigo, new GPS(latitude,longitude), password);
        trazAqui.insereUtilizador(newUtilizador);

        System.out.println("\nUtilizador registado com sucesso!");
        esperaInput();
    }

    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void esperaInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPressione <ENTER> para continuar.");
        sc.nextLine();
    }

    private void saveToDisk()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o nome do Ficheiro com o model que pretende guardar");
        String filename = sc.nextLine();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename + ".dat"));
            out.writeObject(this.model);
            System.out.println("Model guardado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromDisk()
    {
        Scanner sc = new Scanner(System.in);
        TrazAqui new_TrazAqui = null;
        System.out.println("Introduza o nome do Ficheiro com o model que pretende ler");
        String filename = sc.nextLine();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".dat"));
            new_TrazAqui = (TrazAqui) in.readObject();
            this.model = new_TrazAqui;
            System.out.println("Model carregado com sucesso");
        } catch (IOException e) {
            System.out.println("Impossível carregar ficheiro");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
