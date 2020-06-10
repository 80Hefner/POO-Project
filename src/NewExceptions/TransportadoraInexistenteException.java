package NewExceptions;

/**
 * Classe com Uma Excepetion de Transportadora Não existir
 */
public class TransportadoraInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que Transportadora Não Existe
     */
    public TransportadoraInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Transportadora Inexistente";
    }
}

