import java.util.Scanner;

public class Interpreter
{
    public static void main(String[] args)
    {
        int opcao = -1;
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("0 -> Sair do programa.");
            System.out.println("1 -> Criar utilizador.");

            opcao = sc.nextInt();

            if (opcao == 0)
                System.exit(0);
            if (opcao == 1) {
                Utilizador u = new Utilizador("Hefner", "80", new GPS(1.0, 2.0));
                System.out.println("Novo user: " + u.toString());
            }
        }
    }
}
