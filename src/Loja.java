public class Loja
{
    private String nome;
    private String codigo;
    private GPS coordenadas;

    public Loja()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
    }

    public Loja(String nome, String codigo, GPS coordenadas)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
    }

    public Loja(Loja u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public GPS getCoordenadas()
    {
        return coordenadas.clone();
    }

    public void setCoordenadas(GPS coordenadas)
    {
        this.coordenadas = coordenadas.clone();
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Loja u = (Loja) o;

        return this.nome.equals(u.getNome()) &&
                this.codigo.equals(u.getCodigo()) &&
                this.coordenadas.equals(u.getCoordenadas());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\n");

        return sb.toString();
    }

    public Loja clone()
    {
        return new Loja(this);
    }

}
