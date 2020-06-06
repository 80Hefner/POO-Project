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
        parser.parseLogs(data_path);

        while(true) {

            if (login == 0)
                menuLogin();
/*            else if (TrazAqui.getUtilizador_atual().equals("admin"))
                menuAdmin();*/
            else if (TrazAqui.getUtilizador_atual().startsWith("u"))
                menuUtilizador();
            else if (TrazAqui.getUtilizador_atual().startsWith("v"))
                menuVoluntario();
        }
    }

    private static void menuLogin()
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
            else if (opcao == 1 && TrazAqui.getUtilizadores().size() != 0) {
                clearScreen();
                System.out.println("\n----------------------LOGIN--------------------");
                while(true) {
                    System.out.print("Nome de utilizador: ");
                    String utilizador = sc.nextLine();
                    if (TrazAqui.procuraUtilizador(utilizador)) {
                        while(true) {
                            System.out.print("Password: ");
                            String password = sc.nextLine();
                            if (TrazAqui.verificaPassword(utilizador, password)) {
                                login = 1;
                                TrazAqui.setUtilizador_atual(utilizador);
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
                registaUtilizador();
            }
            else if (opcao == 3) {
                clearScreen();
                System.out.println("----------------------MENU REGISTO DE ENTIDADE--------------------\n");
                System.out.println("Entidade a registar:");
                System.out.println("1 -> Loja | 2 -> Voluntário | 3 -> Transportadora");
                opcao = Integer.parseInt(sc.nextLine());

                if (opcao == 1)
                    registaLoja();
                else if (opcao == 2)
                    registaVoluntario();
                else if (opcao == 3)
                    registaTransportadora();
            }
            else if (opcao == 4 && TrazAqui.getVoluntarios().size() != 0) {
                clearScreen();
                System.out.println("\n----------------------LOGIN--------------------");
                while(true) {
                    System.out.print("Nome do Voluntario: ");
                    String voluntario = sc.nextLine();
                    if (TrazAqui.procuraVoluntario(voluntario)) {
                        while(true) {
                            System.out.print("Password: ");
                            String password = sc.nextLine();
                            if (password.equals("")) {
                                login = 1;
                                TrazAqui.setUtilizador_atual(voluntario);
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
            else if (opcao == 5 && TrazAqui.getTransportadoras().size() != 0) {

            }
            else {
                System.out.println("Opção inválida!");
                esperaInput();
            }
        }
    }

    /********************* MENUS DO UTILIZADOR *******************/
    private static void menuUtilizador()
    {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        int opcao;

        while(true) {

            clearScreen();
            System.out.println("----------------------MENU UTILIZADOR--------------------\n");
            System.out.println("0 -> Logout.");
            System.out.println("1 -> Listar entidades no sistema.");
            System.out.println("2 -> Fazer pedido de encomenda.");

            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 0) {
                login = 0;
                TrazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                System.out.println("\n-----------------TRAZ AQUI------------------------");
                System.out.println("\n------------------LOJAS-------------------------\n");
                System.out.println(TrazAqui.getLojas().toString());
                System.out.println("\n------------------VOLUNTARIOS-------------------------\n");
                System.out.println(TrazAqui.getVoluntarios().toString());
                System.out.println("\n------------------TRANSPORTADORAS-------------------------\n");
                System.out.println(TrazAqui.getTransportadoras().toString());
                System.out.println("\n------------------UTILIZADORES-------------------------\n");
                System.out.println(TrazAqui.getUtilizadores().toString());
                System.out.println("\n------------------ACEITES-------------------------\n");
                System.out.println(TrazAqui.getEncomendasAceites().toString());
                System.out.print("\n");
                esperaInput();
            }
            else if (opcao == 2) {
                Encomenda e = novaEncomenda();
                TrazAqui.insereEncomenda(e, e.getCodLoja());
            }
        }
    }

    private static Encomenda novaEncomenda()
    {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        Encomenda e = new Encomenda();

        clearScreen();
        System.out.println("------NOVA ENCOMENDA--------");

        while(true) {
            String codigo = "e" + (r.nextInt(8999) + 1000);
            if (!TrazAqui.procuraEncomendaAceite(codigo)) {
                e.setCodigo(codigo);
                break;
            }
        }
        System.out.print("Código da loja: ");
        e.setCodLoja(sc.nextLine());
        e.setCodUtilizador(TrazAqui.getUtilizador_atual());
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
    private static void menuVoluntario()
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

            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 0) {
                login = 0;
                TrazAqui.setUtilizador_atual("");
                break;
            }
            else if (opcao == 1) {
                clearScreen();
                System.out.println("\n-----------------TRAZ AQUI------------------------");
                System.out.println("\n------------------LOJAS-------------------------\n");
                System.out.println(TrazAqui.getLojas().toString());
                System.out.println("\n------------------VOLUNTARIOS-------------------------\n");
                System.out.println(TrazAqui.getVoluntarios().toString());
                System.out.println("\n------------------TRANSPORTADORAS-------------------------\n");
                System.out.println(TrazAqui.getTransportadoras().toString());
                System.out.println("\n------------------UTILIZADORES-------------------------\n");
                System.out.println(TrazAqui.getUtilizadores().toString());
                System.out.println("\n------------------ACEITES-------------------------\n");
                System.out.println(TrazAqui.getEncomendasAceites().toString());
                System.out.print("\n");
                esperaInput();
            }
            else if (opcao == 2) {
                clearScreen();
                realizaEncomendaPedida();
                esperaInput();
                break;
            }
        }
    }

    public static void realizaEncomendaPedida () {
        Scanner sc = new Scanner(System.in);
        Voluntario voluntario = TrazAqui.getVoluntario(TrazAqui.getUtilizador_atual());

        clearScreen();
        System.out.println("\n-----------------PEDIDO ENTREGA ENCOMENDA------------------------");
        while(true) {
            System.out.println("Insira o Código da loja:");
            String codLoja = sc.nextLine();
            if (!codLoja.startsWith("l")) {
                codLoja = "l" + codLoja;
            }
            if (TrazAqui.procuraLoja(codLoja)) {
                Loja loja = TrazAqui.getLoja(codLoja);

                if (loja.getCoordenadas().isReachable( voluntario.getCoordenadas(), voluntario.getRaio())) {

                    while(true) {
                        System.out.println("Insira o Código da Encomenda:");
                        String codEncomenda = sc.nextLine();
                        if (loja.possuiEncomendaCodigo(codEncomenda)) {
                            Encomenda enc = loja.getEncomenda(codEncomenda);
                            if (TrazAqui.getUtilizador(enc.getCodUtilizador()).getCoordenadas().isReachable(voluntario.getCoordenadas(), voluntario.getRaio())) {
                                TrazAqui.realizaEntregaDeVenda(loja, enc, voluntario);
                                System.out.println("Entrega feita com sucesso");
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

        }
    }

    /************* REGISTAR NOVAS ENTIDADES ****************/
    private static void registaLoja()
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
        TrazAqui.insereLoja(newLoja);

        System.out.println("\nLoja registada com sucesso!");
        esperaInput();
    }

    private static void registaVoluntario()
    {
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

        Voluntario newVoluntario = new Voluntario(nome, codigo, new GPS(latitude,longitude), "", raio, medical);
        TrazAqui.insereVoluntario(newVoluntario);

        System.out.println("\nVoluntário registado com sucesso!");
        esperaInput();
    }

    private static void registaTransportadora()
    {
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

        Transportadora newTransportadora = new Transportadora(nome, codigo, new GPS(latitude,longitude), "", nif, raio, preco_km, limite, medical);
        TrazAqui.insereTransportadora(newTransportadora);

        System.out.println("\nTransportadora registada com sucesso!");
        esperaInput();
    }

    private static void registaUtilizador()
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
        TrazAqui.insereUtilizador(newUtilizador);

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