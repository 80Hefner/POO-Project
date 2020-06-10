package NewExceptions;

/**
 * Classe com Uma Excepetion de Loja Não existir
 */
public class LojaInexistenteException extends Exception
{
    /**
     * Função que emite a Exception de que Loja Não Existe
     */
    public LojaInexistenteException()
    {
        super();
    }

    /**
     * Função que transforma a Exception feita numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        return "Loja Inexistente";
    }
}

