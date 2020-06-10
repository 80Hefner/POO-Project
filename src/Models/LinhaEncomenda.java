package Models;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Classe que representa uma Linha de Encomenda
 */
public class LinhaEncomenda  implements Serializable
{
    private String codigo;
    private String descricao;
    private double quantidade;
    private double valor_unidade;

    /**
     * Construtor por Omissão de uma Linha de Encomenda
     */
    public LinhaEncomenda()
    {
        this.codigo = "";
        this.descricao = "";
        this.quantidade = 0;
        this.valor_unidade = 0;
    }

    /**
     * Construtor parametrizado de uma Linha de Encomenda
     * @param codigo            Código da Linha de Encomenda
     * @param descricao         Descrição do Produto
     * @param quantidade        Quantidade do Produto
     * @param valor_unidade     Valor Unidade do Produto
     */
    public LinhaEncomenda(String codigo, String descricao, double quantidade, double valor_unidade)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor_unidade = valor_unidade;
    }

    /**
     * Construtor de Cópia da Linha de Encomenda
     * @param l     Linha de Encomenda a copair
     */
    public LinhaEncomenda(LinhaEncomenda l)
    {
        this.codigo = l.getCodigo();
        this.descricao = l.getDescricao();
        this.quantidade = l.getQuantidade();
        this.valor_unidade = l.getValor_unidade();
    }

    /**
     * Getter do código do Produto
     * @return      Código do Produto
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Setter do código do Produto
     * @param codigo       Código do Produto
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Getter da Descrição do Produto
     * @return      Descrição do Produto
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * Setter da Descrição do Produto
     * @param descricao       Descrição do Produto
     */
    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    /**
     * Getter da Quantidade do Produto
     * @return      Quantidade do Produto
     */
    public double getQuantidade()
    {
        return quantidade;
    }

    /**
     * Setter da Quantidade do Produto
     * @param quantidade      Quantidade do Produto
     */
    public void setQuantidade(double quantidade)
    {
        this.quantidade = quantidade;
    }

    /**
     * Getter do valor por Unidade do Produto
     * @return  Valor por Unidade do Produto
     */
    public double getValor_unidade()
    {
        return valor_unidade;
    }

    /**
     * Setter do valor por Unidade do Produto
     * @param valor_unidade   Valor por Unidade do Produto
     */
    public void setValor_unidade(double valor_unidade)
    {
        this.valor_unidade = valor_unidade;
    }

    /**
     * Função de equals da Linha de Encomenda
     * @param o           Objeto ao qual queremos comparar a Linha de Encomenda
     */
    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        LinhaEncomenda l = (LinhaEncomenda) o;

        return this.codigo.equals(l.getCodigo()) &&
                this.descricao.equals(l.getDescricao()) &&
                this.quantidade == l.getQuantidade() &&
                this.valor_unidade == l.getValor_unidade();
    }

    /**
     * Função que transforma a Linha de Encomenda e os seus dados numa String
     * @return           String resultante da função
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        DecimalFormat fmt = new DecimalFormat("0.00");

        sb.append(" -> Codigo - ").append(this.codigo);
        sb.append(" | Descrição - ").append(this.descricao);
        sb.append(" | Quantidade - ").append(fmt.format(this.quantidade));
        sb.append(" | Valor por unidade - ").append(fmt.format(this.valor_unidade)).append(" €");
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Função que dá clone á Linha de Encomenda
     * @return           Cópia da Linha de Encomenda
     */
    public LinhaEncomenda clone()
    {
        return new LinhaEncomenda(this);
    }
}
