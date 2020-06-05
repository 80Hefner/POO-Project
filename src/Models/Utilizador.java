package Models;

public class Utilizador
{
    private String nome;
    private String codigo;
    private GPS coordenadas;
    private String password;

    public Utilizador()
    {
        this.nome = "";
        this.codigo = "";
        this.coordenadas = new GPS();
        this.password = "";
    }

    public Utilizador(String nome, String codigo, GPS coordenadas, String password)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.coordenadas = coordenadas.clone();
        this.password = password;
    }

    public Utilizador(Utilizador u)
    {
        this.nome = u.getNome();
        this.codigo = u.getCodigo();
        this.coordenadas = u.getCoordenadas();
        this.password = u.getPassword();
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        Utilizador u = (Utilizador) o;

        return this.nome.equals(u.getNome()) &&
                this.codigo.equals(u.getCodigo()) &&
                this.coordenadas.equals(u.getCoordenadas()) &&
                this.password.equals(u.getPassword());
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(this.nome);
        sb.append("\nCodigo: ").append(this.codigo);
        sb.append("\nCoordenadas: ").append(this.coordenadas.toString());
        sb.append("\nPassword: ").append(this.password);
        sb.append("\n");

        return sb.toString();
    }

    public Utilizador clone()
    {
        return new Utilizador(this);
    }
}
