package Utils;
import Models.*;
import NewExceptions.EncomendaInexistenteException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe Responsável pelo Parsing de dados recebidos de um ficheiro
 */
public class Parser
{
    /**
     * Função que dá Parsing a todos os dados presentes num ficheiro e os insere no Model
     * @param data_path     Path onde se encontra os ficheiros de dados
     * @param trazAqui      Função de Model principal onde irão ser colocados dados recebidos ao fim do Parsing de Dados
     */
    public void parseLogs(String data_path, TrazAqui trazAqui) throws EncomendaInexistenteException {
        List<String> linhas = lerFicheiro(data_path + "/logs.txt");
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Utilizador":
                    Utilizador u = parseUtilizador(linhaPartida[1]); // criar um Utilizador
                    trazAqui.insereUtilizador(u);
                    //System.out.println(u.toString()); //enviar para o ecra, apenas para teste
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntario(linhaPartida[1]);
                    trazAqui.insereVoluntario(v);
                    //System.out.println(v.toString());
                    break;
                case "Transportadora":
                    Transportadora t = parseTransportadora(linhaPartida[1]);
                    trazAqui.insereTransportadora(t);
                    //System.out.println(t.toString());
                    break;
                case "Loja":
                    Loja l = parseLoja(linhaPartida[1]);
                    trazAqui.insereLoja(l);
                    //System.out.println(l.toString());
                    break;
                case "Encomenda":
                    Encomenda e = parseEncomenda(linhaPartida[1]);
                    trazAqui.adicionaEncomendaAoSistema(e);
                    //System.out.println(e.toString());
                    break;
                case "Aceite":
                    String s = linhaPartida[1];
                    trazAqui.insereEncomendaAceite(s);
                    //System.out.println(s);
                    break;
                default:
                    System.out.println("Linha inválida.");
                    break;
            }

        }
    }

    /**
     * Função que dá Parsing de um Utilizador
     * @param input     Linha á qual vamos dar Parsging
     * @return          Utilizador obtido pelo Parsing da linha
     */
    public Utilizador parseUtilizador(String input)
    {
        String[] campos = input.split(",");
        String codUtilizador = campos[0];
        String nome = campos[1];
        double latitude = Double.parseDouble(campos[2]);
        double longitude = Double.parseDouble(campos[3]);
        GPS gps = new GPS(latitude, longitude);

        return new Utilizador(nome, codUtilizador, gps, codUtilizador);
    }

    /**
     * Função que dá Parsing de um Voluntário
     * @param input     Linha á qual vamos dar Parsging
     * @return          Voluntário obtido pelo Parsing da linha
     */
    public Voluntario parseVoluntario(String input)
    {
        String[] campos = input.split(",");
        String codVoluntario = campos[0];
        String nome = campos[1];
        double latitude = Double.parseDouble(campos[2]);
        double longitude = Double.parseDouble(campos[3]);
        GPS gps = new GPS(latitude, longitude);
        double raio = Double.parseDouble(campos[4]);
        Random r = new Random();
        boolean medical = r.nextBoolean();
        double velocidadeMedia = 40.0 + ((60.0 - 40.0) * r.nextDouble());

        return new Voluntario(nome,codVoluntario,gps, codVoluntario, velocidadeMedia,raio,medical);
    }

    /**
     * Função que dá Parsing de uma Transportadora
     * @param input     Linha á qual vamos dar Parsging
     * @return          Transportadora obtida pelo Parsing da linha
     */
    public Transportadora parseTransportadora(String input)
    {
        String[] campos = input.split(",");
        String codTransportadora = campos[0];
        String nome = campos[1];
        double latitude = Double.parseDouble(campos[2]);
        double longitude = Double.parseDouble(campos[3]);
        GPS gps = new GPS(latitude, longitude);
        int nif = Integer.parseInt(campos[4]);
        double raio = Double.parseDouble(campos[5]);
        double preco_km = Double.parseDouble(campos[6]);
        Random r = new Random();
        int limite = r.nextInt(10) + 1;
        boolean medical = r.nextBoolean();
        double velocidadeMedia = 70.0 + (90.0 - 70.0)*r.nextDouble();

        return new Transportadora(nome,codTransportadora,gps, codTransportadora, velocidadeMedia,nif,raio,preco_km,limite,medical);
    }

    /**
     * Função que dá Parsing de uma Loja
     * @param input     Linha á qual vamos dar Parsging
     * @return          Loja obtido pelo Parsing da linha
     */
    public Loja parseLoja(String input)
    {
        String[] campos = input.split(",");
        String codLoja = campos[0];
        String nomeLoja = campos[1];
        double latitude = Double.parseDouble(campos[2]);
        double longitude = Double.parseDouble(campos[3]);
        GPS gps = new GPS(latitude, longitude);
        Random r = new Random();
        boolean temFila = r.nextBoolean();

        return new Loja(nomeLoja, codLoja, codLoja, gps, temFila);
    }

    /**
     * Função que dá Parsing de uma Encomenda
     * @param input     Linha á qual vamos dar Parsging
     * @return          Encomenda obtido pelo Parsing da linha
     */
    public Encomenda parseEncomenda(String input)
    {
        String[] campos = input.split(",", 5);
        String codEncomenda = campos[0];
        String codUtilizador = campos[1];
        String codLoja = campos[2];
        double peso = Double.parseDouble(campos[3]);
        ArrayList<LinhaEncomenda> produtos = new ArrayList<>(parseProdutos(campos[4]));
        LocalDateTime data = LocalDateTime.of(2000, 1, 1, 0, 0);
        boolean medical = produtos.stream().anyMatch(l -> l.getDescricao().equals("Desinfetante") || l.getDescricao().equals("Água sanitária") || l.getDescricao().equals("Medicamentos"));

        return new Encomenda(codEncomenda, codLoja, codUtilizador, "", peso, produtos, medical, data, false, false, 0, 0.0,0.0, 0);
    }

    /**
     * Função que dá Parsing de uma Linha de Encomenda
     * @param input     Linha á qual vamos dar Parsging
     * @return          LinhaEncomenda obtido pelo Parsing da linha
     */
    public List<LinhaEncomenda> parseProdutos(String input)
    {
        String[] campos = input.split(",");
        List<LinhaEncomenda> produtos = new ArrayList<>();

        for (int i = 0; i < campos.length; i+=4) {
            String codigo = campos[i];
            String descricao = campos[i+1];
            double quantidade = Double.parseDouble(campos[i+2]);
            double valor_unidade = Double.parseDouble(campos[i+3]);
            LinhaEncomenda l = new LinhaEncomenda(codigo, descricao, quantidade, valor_unidade);
            produtos.add(l);
        }

        return produtos;
    }

    /**
     * Função que lê um ficheiro e divide as linhas por uma List de String
     * @param ficheiro      Path para o ficheiro que queremos ler
     * @return              Lista de String, onde cada posição corresponde a cada linha do ficheiro
     */
    public List<String> lerFicheiro(String ficheiro)
    {
        List<String> linhas = new ArrayList<>();
        try {
            linhas = Files.readAllLines(Paths.get(ficheiro), StandardCharsets.UTF_8);
        }
        catch(IOException exc) {
            System.out.println("Impossível abrir ficheiro " + ficheiro);
        }

        return linhas;
    }

}
