import java.util.Scanner;

public class Interpreter
{
    public static void main(String[] args)
    {
        int opcao;
        Scanner sc = new Scanner(System.in);
        String data_path = "/home/besta80/IdeaProjects/POO-Project/src";
        TrazAqui trazAqui = new TrazAqui();

        while(true) {
            System.out.println("0 -> Sair do programa.");
            System.out.println("1 -> Criar utilizador.");
            System.out.println("2 -> Ler do ficheiro.");
            System.out.println("3 -> Listar entidades no sistema.");

            opcao = sc.nextInt();

            if (opcao == 0)
                System.exit(0);
            if (opcao == 1) {
                Utilizador u = new Utilizador("Hefner", "80", new GPS(1.0, 2.0));
                System.out.println("Novo user: " + u.toString());
            }
            if (opcao == 2) {
                Parser parser = new Parser();
                parser.parse(data_path, trazAqui);
            }
            if (opcao == 3) {
                System.out.println("-----------------TRAZ AQUI------------------------");
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

            }
        }
    }
}
