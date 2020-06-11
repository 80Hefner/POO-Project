package View;

import Models.*;

import java.util.Map;
import java.util.Set;

/**
 * Classe que possui View
 */
public class MVC_View {

    /**
     * Função que limpa o ecrã
     */
    public void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Função que imprime um dado objeto
     * @param o     Objeto que pretendemos imprimir
     */
    public void print(Object o)
    {
        System.out.print(o.toString());
    }

    /**
     * Função que imprime uma dada String
     * @param string    String que pretendemos imprimir
     */
    public void print(String string)
    {
        System.out.print(string);
    }

    public void imprimeLogo ()
    {
        clearScreen();
        System.out.println("┌────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│ ████████╗██████╗  █████╗ ███████╗     █████╗  ██████╗ ██╗   ██╗██╗ │");
        System.out.println("│ ╚══██╔══╝██╔══██╗██╔══██╗╚══███╔╝    ██╔══██╗██╔═══██╗██║   ██║██║ │");
        System.out.println("│    ██║   ██████╔╝███████║  ███╔╝     ███████║██║   ██║██║   ██║██║ │");
        System.out.println("│    ██║   ██╔══██╗██╔══██║ ███╔╝      ██╔══██║██║▄▄ ██║██║   ██║██║ │");
        System.out.println("│    ██║   ██║  ██║██║  ██║███████╗    ██║  ██║╚██████╔╝╚██████╔╝██║ │");
        System.out.println("│    ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝    ╚═╝  ╚═╝ ╚══▀▀═╝  ╚═════╝ ╚═╝ │");
        System.out.println("│                                                                    │");
        System.out.print("└────────────────────────────────────────────────────────────────────┘");

    }
    /**
     * Função que imprime o menu principal
     */
    public void printMenuPrincipal ()
    {
        System.out.println("\n┌─────────────────────────── TrazAqui APP ───────────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│    0 -> Sair do programa.                                          │");
        System.out.println("│    1 -> Efetuar login.                                             │");
        System.out.println("│    2 -> Registar nova Entidade.                                    │");
        System.out.println("│    3 -> Listar entidades no sistema.                               │");
        System.out.println("│    4 -> Save to Disk                                               │");
        System.out.println("│    5 -> Load from Disk                                             │");
        System.out.println("│                                                                    │");
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.print("  $ OPÇÃO: ");
    }

    /**
     * Função que imprime o meno de logins
     */
    public void printMenuEscolheLogin ()
    {
        clearScreen();
        System.out.println("\n┌──────────────────────────── MENU LOGIN ────────────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│  EFETUAR LOGIN COM:                                                │");
        System.out.println("│     1 - Utilizador                                                 │");
        System.out.println("│     2 - Voluntário                                                 │");
        System.out.println("│     3 - Transportadora                                             │");
        System.out.println("│     4 - Loja                                                       │");
        System.out.println("│   0 -> Leave                                                       │");
        System.out.println("│                                                                    │");
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.print("  $ OPÇÃO: ");
    }

    /**
     * Função que imprime o menu de registos de Entidade
     */
    public void printMenuRegistoEntidade ()
    {
        clearScreen();
        System.out.println("\n┌───────────────────── MENU REGISTO DE ENTIDADE ─────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│  ENTIDADE A REGISTAR:                                              │");
        System.out.println("│     1 - Utilizador                                                 │");
        System.out.println("│     2 - Voluntário                                                 │");
        System.out.println("│     3 - Transportadora                                             │");
        System.out.println("│     4 - Loja                                                       │");
        System.out.println("│   0 -> Leave                                                       │");
        System.out.println("│                                                                    │");
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.print("  $ OPÇÃO: ");
    }


    /**
     * Função que imprime o menu do um Utilizador
     * @param nrAvaliacoes  Número de Entregas que tem de avaliar
     * @param nrPropostas   Número de Propostas qe tem de aceitar ou recusas
     */
    public void printMenuUtilizador (Integer nrAvaliacoes, Integer nrPropostas)
    {
        clearScreen();
        System.out.println("\n┌────────────────────────── MENU UTILIZADOR ─────────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│    0 -> Logout.                                                    │");
        System.out.println("│    1 -> Listar entidades no sistema.                               │");
        System.out.println("│    2 -> Fazer pedido de encomenda.                                 │");
        System.out.println("│    3 -> Avaliar Encomendas que foram Entregues (" + nrAvaliacoes +").                │");
        System.out.println("│    4 -> Aceitar Entregas propostas por uma Transportadora (" + nrPropostas +").     │");
        System.out.println("│    5 -> Dez utilizadores ordenados com mais encomendas realizadas. │");
        System.out.println("│    6 -> Mostrar Histórico.                                         │");
        System.out.println("│                                                                    │");
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.print("  $ OPÇÃO: ");
    }

    /**
     * Função que imprime menu de um Voluntário
     */
    public void printMenuVoluntário ()
    {
        clearScreen();
        System.out.println("\n┌────────────────────────── MENU VOLUNTÁRIO ─────────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│    0 -> Logout.                                                    │");
        System.out.println("│    1 -> Listar entidades no sistema.                               │");
        System.out.println("│    2 -> Fazer pedido para entregar encomenda.                      │");
        System.out.println("│    3 -> Altera disponibilidade de entrega.                         │");
        System.out.println("│    4 -> Altera disponibilidade de entrega médica.                  │");
        System.out.println("│    5 -> Mostrar Histórico.                                         │");
        System.out.println("│                                                                    │");
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.print("  $ OPÇÃO: ");
    }

    /**
     * Função que imprime menu de uma Transportadora
     */
    public void printMenuTransportadora ()
    {
        clearScreen();
        System.out.println("\n┌──────────────────────── MENU TRANSPORTADORA ───────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│    0 -> Logout.                                                    │");
        System.out.println("│    1 -> Listar entidades no sistema.                               │");
        System.out.println("│    2 -> Fazer pedido para entregar encomenda.                      │");
        System.out.println("│    3 -> Altera disponibilidade de entrega.                         │");
        System.out.println("│    4 -> Altera disponibilidade de entrega médica.                  │");
        System.out.println("│    5 -> Dez Transportadoras ordenadas com mais Km feitos.          │");
        System.out.println("│    6 -> Total faturado pelos Transportes da Transportadora.        │");
        System.out.println("│    7 -> Mostrar Histórico.                                         │");
        System.out.println("│                                                                    │");
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.print("  $ OPÇÃO: ");
    }

    /**
     * Função que imprime menu de uma Loja
     * @param nrPedidos     Número de pedidos de Encomenda que loja tem de aceitar ou recusar
     */
    public void printMenuLojas (Integer nrPedidos)
    {
        clearScreen();
        System.out.println("\n┌───────────────────────────── MENU LOJA ────────────────────────────┐");
        System.out.println("│                                                                    │");
        System.out.println("│    0 -> Logout.                                                    │");
        System.out.println("│    1 -> Listar entidades no sistema.                               │");
        System.out.println("│    2 -> Aceitar pedidos de Encomenda (" + nrPedidos +").                          │");
        System.out.println("│    3 -> Mostrar Histórico.                                         │");
        System.out.println("│                                                                    │");
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.print("  $ OPÇÃO: ");
    }

    /**
     * Função que imprime querie dos 10 Utilizadores com mais entregas realizadas
     * @param res   Resultado onde constão dados necessários a imprimir
     */
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

    /**
     * Função que imprime querie dos 10 Transportadora com mais kilometros feitos nas suas entregas de Encomendas
     * @param res   Resultado onde constão dados necessários a imprimir
     */
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

    /**
     * Função que lista o conjunto de lojas do Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void imprimeLojasTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n----------------------------- LOJAS -----------------------------\n");
        for (Loja loja : trazAqui.getLojas()) {
            System.out.println(loja.toString());
        }
    }

    /**
     * Função que lista o conjunto de Voluntários do Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void imprimeVoluntariosTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n-------------------------- VOLUNTARIOS --------------------------\n");
        for (Voluntario voluntario : trazAqui.getVoluntarios()) {
            System.out.println(voluntario.toString());
        }
    }

    /**
     * Função que lista o conjunto de Transportadoras do Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void imprimeTransportadorasTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n------------------------ TRANSPORTADORAS ------------------------\n");
        for (Transportadora transportadora : trazAqui.getTransportadoras()) {
            System.out.println(transportadora.toString());
        }
    }

    /**
     * Função que lista o conjunto de Utilizadores do Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void imprimeUtilizadoresTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n------------------------- UTILIZADORES -------------------------\n");
        for (Utilizador utilizador : trazAqui.getUtilizadores()) {
            System.out.println(utilizador.toString());
        }
    }

    /**
     * Função que lista o conjunto de Encomendas do Sistema
     * @param trazAqui  Model principal á volta do qual se trabalha ao longo do projeto
     */
    public void imprimeEncomendasTrazAqui (TrazAqui trazAqui) {
        System.out.println("\n------------------------- ENCOMENDAS -------------------------\n");
        for (Encomenda encomenda : trazAqui.getCatalogoEncomendas().values()) {
            System.out.println(encomenda.toString());
        }
    }

    /**
     * Função que imprime menu de Listagem de entidades
     */
    public void imprimeMenuListagemEntidades ()
    {
        System.out.println("\n╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳ TRAZ AQUI ╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳");
        System.out.println("  Escolha tipo de entidades que pretende listar");
    }

    /**
     * Função que imprime resultado da Entrega de uma encomenda
     * @param encomenda     Encomenda entregue
     */
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

    /**
     * Função que imprime histórico de uma Entidade
     */
    public void imprimeHistoricoEntidade(Map<String, Encomenda> historico)
    {
        if (historico.size() == 0) {
            System.out.println("  Entidade ainda não possui nada no seu Histórico!\n");
        }
        else {
            System.out.println("\n------------------------- HISTÓRICO -------------------------\n");
            for (Encomenda encomenda : historico.values()) {
                System.out.println(encomenda.toString());
            }
        }
    }
}
