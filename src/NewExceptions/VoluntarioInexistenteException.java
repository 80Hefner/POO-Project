package NewExceptions;

/**
 * Classe com Uma Excepetion de Voluntário Não existir
 */
public class VoluntarioInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que Voluntário Não Existe
     */
    public VoluntarioInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Voluntário Inexistente";
    }
}
