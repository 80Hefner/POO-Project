package NewExceptions;

/**
 * Classe com Uma Excepetion de Encomenda Não existir
 */
public class EncomendaInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que Encomenda Não Existe
     */
    public EncomendaInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Encomenda Inexistente";
    }
}

