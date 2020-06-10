package View;

import Models.*;

import java.util.Map;
import java.util.Set;

public class MVC_View {

    public void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void print(Object o)
    {
        System.out.print(o.toString());
    }

    public void print(String string)
    {
        System.out.print(string);
    }

    public void printMenuPrincipal ()
    {
        clearScreen();
        System.out.println("\n----------------------TRAZ AQUI APP--------------------");
        System.out.println("0 -> Sair do programa.");
        System.out.println("1 -> Efetuar login.");
        System.out.println("2 -> Registar nova Entidade.");
        System.out.println("3 -> Save to Disk");
        System.out.println("4 -> Load from Disk");
        System.out.print(" $ OPÇÃO: ");
    }

    public void printMenuEscolheLogin ()
    {
        clearScreen();
        System.out.println("\n----------------------MENU LOGIN--------------------");
        System.out.println("EFETUAR LOGIN COM: ");
        System.out.println("  1 - Utilizador");
        System.out.println("  2 - Voluntário");
        System.out.println("  3 - Transportadora");
        System.out.println("  4 - Loja");
        System.out.println(" ENTER or 0 -> Leave");
        System.out.print(" $ OPÇÃO: ");
    }

    public void printMenuRegistoEntidade ()
    {
        clearScreen();
        System.out.println("\n----------------------MENU REGISTO DE ENTIDADE--------------------\n");
        System.out.println("ENTIDADE A REGISTAR:");
        System.out.println("  1 - Utilizador");
        System.out.println("  2 - Voluntário");
        System.out.println("  3 - Transportadora");
        System.out.println("  4 - Loja");
        System.out.println(" ENTER or 0 -> Leave");
        System.out.print(" $ OPÇÃO: ");
    }

    public void printMenuUtilizador (Integer nrAvaliacoes, Integer nrPropostas)
    {
        clearScreen();
        System.out.println("----------------------MENU UTILIZADOR--------------------\n");
        System.out.println("0 -> Logout.");
        System.out.println("1 -> Listar entidades no sistema.");
        System.out.println("2 -> Fazer pedido de encomenda.");
        System.out.println("3 -> Avaliar Encomendas que foram Entregues (" + nrAvaliacoes +").");
        System.out.println("4 -> Aceitar Encomendas propostas entregar por uma Transportadora (" + nrPropostas +").");
        System.out.println("5 -> Dez utilizadores ordenados com mais encomendas realizadas");
        System.out.print(" $ OPÇÃO: ");
    }

    public void printMenuVoluntário ()
    {
        clearScreen();
        System.out.println("----------------------MENU VOLUNTÁRIO--------------------\n");
        System.out.println("0 -> Logout.");
        System.out.println("1 -> Listar entidades no sistema.");
        System.out.println("2 -> Fazer pedido para entregar encomenda.");
        System.out.println("3 -> Altera disponibilidade de entrega.");
        System.out.print(" $ OPÇÃO: ");
    }

    public void printMenuTransportadora ()
    {
        clearScreen();
        System.out.println("----------------------MENU TRANSPORTADORA--------------------\n");
        System.out.println("0 -> Logout.");
        System.out.println("1 -> Listar entidades no sistema.");
        System.out.println("2 -> Fazer pedido para entregar encomenda.");
        System.out.println("3 -> Altera disponibilidade de entrega.");
        System.out.println("4 -> Dez Transportadoras ordenadas com mais Km feitos");
        System.out.println("5 -> Total faturado pelos Transportes da Transportadora");
        System.out.print(" $ OPÇÃO: ");
    }

    public void printMenuLojas (Integer nrPedidos)
    {
        clearScreen();
        System.out.println("----------------------MENU LOJA--------------------\n");
        System.out.println("0 -> Logout.");
        System.out.println("1 -> Listar entidades no sistema.");
        System.out.println("2 -> Aceitar pedidos de Encomenda (" + nrPedidos +").");
        System.out.print(" $ OPÇÃO: ");
    }

    public void imprimeQuerie10Utilizadores (Set<Map.Entry<String, Integer>> res)
    {
        System.out.println("Utilizadores ordenados por ordem decrescente de encomendas transportadas:");
        int counter = 1;
        for(Map.Entry<String, Integer> val : res) {
            System.out.println("  " + counter + "º - Utilizador " + val.getKey() + " tem transportadas " + val.getValue() + " encomendas.\n");
            counter++;
            if (counter==11)
                break;
        }
    }

    public void imprimeQuerie10Transportadoras (Set<Map.Entry<String, Double>> res)
    {
        System.out.println("Transportadoras ordenados por ordem decrescente de Kilómetros feitos:");
        int counter = 1;
        for(Map.Entry<String, Double> val : res) {
            System.out.println("  " + counter + "º - Transportadora " + val.getKey() + " percorreu " + val.getValue() + " Kilómetros.\n");
            counter++;
            if (counter==11)
                break;
        }
    }


    public void imprimeLojasTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n----------------------------- LOJAS -----------------------------\n");
        for (Loja loja : trazAqui.getLojas()) {
            System.out.println(loja.toString());
        }
    }

    public void imprimeVoluntariosTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n-------------------------- VOLUNTARIOS --------------------------\n");
        for (Voluntario voluntario : trazAqui.getVoluntarios()) {
            System.out.println(voluntario.toString());
        }
    }

    public void imprimeTransportadorasTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n------------------------ TRANSPORTADORAS ------------------------\n");
        for (Transportadora transportadora : trazAqui.getTransportadoras()) {
            System.out.println(transportadora.toString());
        }
    }

    public void imprimeUtilizadoresTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n------------------------- UTILIZADORES -------------------------\n");
        for (Utilizador utilizador : trazAqui.getUtilizadores()) {
            System.out.println(utilizador.toString());
        }
    }

    public void imprimeMenuListagemEntidades ()
    {
        System.out.println("\n#*#*#*#*#*#*#*#*#*#*#*#*#*# TRAZ AQUI #*#*#*#*#*#*#*#*#*#*#*#*#*#");
        System.out.println("  Escolha tipo de entidades que pretende listar");
    }

    public void imprimeEntregaEncomendaVol (Encomenda encomenda)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Tempo demorado a realizar a entrega -> ")
                .append((int) encomenda.getTempoTransporte()/60).append(" Horas e ")
                .append((int) encomenda.getTempoTransporte()%60).append(" minutos ");

        if(encomenda.getCondicoesClimatericas() == 0)
            sb.append("em condições Normais\n");
        else if(encomenda.getCondicoesClimatericas() == 1)
            sb.append("em condições de chuva\n");
        else if(encomenda.getCondicoesClimatericas() == 2)
            sb.append("em condições de Neve e Tempestade\n");

        System.out.println(sb.toString());
    }
}
