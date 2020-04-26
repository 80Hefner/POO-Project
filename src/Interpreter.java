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
            System.out.println("1 -> Criar utilizador.");
            System.out.println("2 -> Ler do ficheiro.");

            opcao = sc.nextInt();

            if (opcao == 0)
                System.exit(0);
            if (opcao == 1) {
                Utilizador u = new Utilizador("Hefner", "80", new GPS(1.0, 2.0));
                System.out.println("Novo user: " + u.toString());
            }
            if (opcao == 2) {
                Parser parser = new Parser();
                parser.parse(data_path);
            }
        }
    }
}
