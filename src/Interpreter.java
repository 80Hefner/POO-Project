import java.util.Scanner;

public class Interpreter
{
    public static void main(String[] args)
    {
        int opcao;
        Scanner sc = new Scanner(System.in);
        String data_path = "/home/besta80/IdeaProjects/POO-Project/src";

        while(true) {
            System.out.println("0 -> Sair do programa.");
            System.out.println("1 -> Ler do ficheiro.");
            System.out.println("2 -> Registar entidade.");
            System.out.println("3 -> Listar entidades no sistema.");

            opcao = sc.nextInt();

            if (opcao == 0)
                System.exit(0);
            else if (opcao == 1) {
                Parser parser = new Parser();
                parser.parseLogs(data_path);
            }
            else if (opcao == 2) {
                System.out.println("Entidade a registar:");
                System.out.println("1 -> Loja | 2 -> Voluntário | 3 -> Transportadora | 4 -> Utilizador");
                opcao = sc.nextInt();

                if (opcao == 1)
                    registaLoja();
                else if (opcao == 2)
                    registaVoluntario();
                else if (opcao == 3)
                    registaTransportadora();
                else if (opcao == 4)
                    registaUtilizador();

            }
            else if (opcao == 3) {
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
                System.out.println("\n-----------------------END OF LIST-----------------------------\n");

            }
        }
    }

    private static void registaLoja()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n------REGISTO DE LOJA--------");
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
    }

    private static void registaVoluntario()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n------REGISTO DE VOLUNTARIO--------");
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

        Voluntario newVoluntario = new Voluntario(nome, codigo, new GPS(latitude,longitude), raio, medical);
        TrazAqui.insereVoluntario(newVoluntario);
    }

    private static void registaTransportadora()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n------REGISTO DE TRANSPORTADORA--------");
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

        Transportadora newTransportadora = new Transportadora(nome, codigo, new GPS(latitude,longitude), nif, raio, preco_km, limite, medical);
        TrazAqui.insereTransportadora(newTransportadora);
    }

    private static void registaUtilizador()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n------REGISTO DE UTILIZADOR--------");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.println("Coordenadas:");
        System.out.print("\tLatitude: ");
        double latitude = Double.parseDouble(sc.nextLine());
        System.out.print("\tLongitude: ");
        double longitude = Double.parseDouble(sc.nextLine());

        Utilizador newUtilizador = new Utilizador(nome, codigo, new GPS(latitude,longitude));
        TrazAqui.insereUtilizador(newUtilizador);
    }
}
