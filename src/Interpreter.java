import Models.*;
import Utils.Parser;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class Interpreter
{
    private static int login = 0;
    private static final String data_path = "/home/besta80/IdeaProjects/POO-Project/src";

    public static void main(String[] args)
    {
        int opcao;
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        TrazAqui trazAqui = new TrazAqui();
        parser.parseLogs(data_path, trazAqui);

        while(true) {

            if (login == 0)
                menuLogin(trazAqui);
/*            else if (TrazAqui.getUtilizador_atual().equals("admin"))
                menuAdmin();*/
            else if (trazAqui.getUtilizador_atual().startsWith("u"))
                menuUtilizador(trazAqui);
            else if (trazAqui.getUtilizador_atual().startsWith("v"))
                menuVoluntario(trazAqui);
        }
    }

    private static void menuLogin(TrazAqui trazAqui)
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
            System.out.println("5 -> Efetuar login com Loja.");
            System.out.print("OPÇÃO: ");
            opcao = Integer.parseInt(sc.nextLine());

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

            }
            else {
                System.out.println("Opção inválida!");
                esperaInput();
            }
        }
    }

    /********************* MENUS DO UTILIZADOR *******************/
    private static void menuUtilizador(TrazAqui trazAqui)
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

            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 0) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                System.out.println("\n-----------------TRAZ AQUI------------------------");
                System.out.println("\n------------------LOJAS-------------------------\n");
                System.out.println(trazAqui.getLojas().toString());
                System.out.println("\n------------------VOLUNTARIOS-------------------------\n");
                System.out.println(trazAqui.getVoluntarios().toString());
                System.out.println("\n------------------TRANSPORTADORAS-------------------------\n");
                System.out.println(trazAqui.getTransportadoras().toString());
                System.out.println("\n------------------UTILIZADORES-------------------------\n");
                System.out.println(trazAqui.getUtilizadores().toString());
                System.out.println("\n------------------ACEITES-------------------------\n");
                System.out.println(trazAqui.getEncomendasAceites().toString());
                System.out.print("\n");
                esperaInput();
            }
            else if (opcao == 2) {
                Encomenda e = novaEncomenda(trazAqui);
                trazAqui.insereEncomenda(e, e.getCodLoja());
            }
        }
    }

    private static Encomenda novaEncomenda(TrazAqui trazAqui)
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

    private static LinhaEncomenda novaLinhaEncomenda()
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

    /********************* MENUS DO VOLUNTÁRIO *******************/
    private static void menuVoluntario(TrazAqui trazAqui)
    {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        int opcao;

        while(true) {

            clearScreen();
            System.out.println("----------------------MENU UTILIZADOR--------------------\n");
            System.out.println("0 -> Logout.");
            System.out.println("1 -> Listar entidades no sistema.");
            System.out.println("2 -> Fazer pedido para entregar encomenda.");
            System.out.println("3 -> Altera disponibilidade de entrega.");

            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 0) {
                login = 0;
                trazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                System.out.println("\n-----------------TRAZ AQUI------------------------");
                System.out.println("\n------------------LOJAS-------------------------\n");
                System.out.println(trazAqui.getLojas().toString());
                System.out.println("\n------------------VOLUNTARIOS-------------------------\n");
                System.out.println(trazAqui.getVoluntarios().toString());
                System.out.println("\n------------------TRANSPORTADORAS-------------------------\n");
                System.out.println(trazAqui.getTransportadoras().toString());
                System.out.println("\n------------------UTILIZADORES-------------------------\n");
                System.out.println(trazAqui.getUtilizadores().toString());
                System.out.println("\n------------------ACEITES-------------------------\n");
                System.out.println(trazAqui.getEncomendasAceites().toString());
                System.out.print("\n");
                esperaInput();
            }
            else if (opcao == 2) {
                clearScreen();
                realizaEncomendaPedida(trazAqui);
                esperaInput();
                break;
            }
            else if (opcao == 3) {
                clearScreen();
                alteraDisponibilidadeVoluntario(trazAqui);
                esperaInput();
                break;
            }
        }
    }

    public static void realizaEncomendaPedida (TrazAqui trazAqui) {
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
                            if (trazAqui.getLoja(codLoja).possuiEncomendaCodigo(codEncomenda, trazAqui.getCatalogoEncomendas())) {
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

    public static void alteraDisponibilidadeVoluntario (TrazAqui trazAqui) {
        Scanner sc = new Scanner(System.in);
        String codVoluntario = trazAqui.getUtilizador_atual();

        while(true) {
            System.out.println("Qual disponibilidade quer colocar no Voluntario?");
            System.out.print("(y - Disponível | n - Não Disponível) : ");
            String disponibilidade = sc.nextLine();
            if (disponibilidade.startsWith("y") || disponibilidade.startsWith("Y")) {
                trazAqui.setAvailable(codVoluntario, true);
                System.out.println("Voluntário definido como Disponível para entregas!");
                break;
            } else if (disponibilidade.startsWith("n") || disponibilidade.startsWith("N")) {
                trazAqui.setAvailable(codVoluntario, false);
                System.out.println("Voluntário definido como Não Disponível para entregas!");
                break;
            }
        }
    }

    /************* REGISTAR NOVAS ENTIDADES ****************/
    private static void registaLoja(TrazAqui trazAqui)
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

    private static void registaVoluntario(TrazAqui trazAqui)
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
        double velocidadeMedia = 60.0 + (120.0 - 60.0)*r.nextDouble();

        Voluntario newVoluntario = new Voluntario(nome, codigo, new GPS(latitude,longitude), "", velocidadeMedia, raio, medical);
        trazAqui.insereVoluntario(newVoluntario);

        System.out.println("\nVoluntário registado com sucesso!");
        esperaInput();
    }

    private static void registaTransportadora(TrazAqui trazAqui)
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
        double velocidadeMedia = 60.0 + (120.0 - 60.0)*r.nextDouble();

        Transportadora newTransportadora = new Transportadora(nome, codigo, new GPS(latitude,longitude), "", velocidadeMedia, nif, raio, preco_km, limite, medical);
        trazAqui.insereTransportadora(newTransportadora);

        System.out.println("\nTransportadora registada com sucesso!");
        esperaInput();
    }

    private static void registaUtilizador(TrazAqui trazAqui)
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

    private static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void esperaInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPressione <ENTER> para continuar.");
        sc.nextLine();
    }
}