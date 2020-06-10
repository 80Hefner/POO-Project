package NewExceptions;

/**
 * Classe com Uma Excepetion de Utilizador Não existir
 */
public class UtilizadorInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que Utilizador Não Existe
     */
    public UtilizadorInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Utilizador Inexistente";
    }
}
