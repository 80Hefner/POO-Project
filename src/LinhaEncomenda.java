public class LinhaEncomenda
{
    private String codigo;
    private String descricao;
    private double quantidade;
    private double valor_unidade;

    public LinhaEncomenda()
    {
        this.codigo = "";
        this.descricao = "";
        this.quantidade = 0;
        this.valor_unidade = 0;
    }

    public LinhaEncomenda(String codigo, String descricao, double quantidade, double valor_unidade)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor_unidade = valor_unidade;
    }

    public LinhaEncomenda(LinhaEncomenda l)
    {
        this.codigo = l.getCodigo();
        this.descricao = l.getDescricao();
        this.quantidade = l.getQuantidade();
        this.valor_unidade = l.getValor_unidade();
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public double getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(double quantidade)
    {
        this.quantidade = quantidade;
    }

    public double getValor_unidade()
    {
        return valor_unidade;
    }

    public void setValor_unidade(double valor_unidade)
    {
        this.valor_unidade = valor_unidade;
    }

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

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Codigo: ").append(this.codigo);
        sb.append("\nDescrição: ").append(this.descricao);
        sb.append("\nQuantidade: ").append(this.quantidade);
        sb.append("\nValor por unidade: ").append(this.valor_unidade);
        sb.append("\n");

        return sb.toString();
    }

    public LinhaEncomenda clone()
    {
        return new LinhaEncomenda(this);
    }
}
