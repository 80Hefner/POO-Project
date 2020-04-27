import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TrazAqui
{
    private TreeMap<String,Loja> lojas;
    private TreeMap<String,Voluntario> voluntarios;
    private TreeMap<String,Transportadora> transportadoras;
    private TreeMap<String,Utilizador> utilizadores;
    private ArrayList<String> aceites;
    private String utilizador_atual;

    public TrazAqui()
    {
        this.lojas = new TreeMap<>();
        this.voluntarios = new TreeMap<>();
        this.transportadoras = new TreeMap<>();
        this.utilizadores = new TreeMap<>();
        this.aceites = new ArrayList<>();
        this.utilizador_atual = "";
    }

    public void insereLoja(Loja l)
    {
        this.lojas.put(l.getCodigo(), l.clone());
    }

    public void insereVoluntario(Voluntario v)
    {
        this.voluntarios.put(v.getCodigo(), v.clone());
    }

    public void insereTransportadora(Transportadora t)
    {
        this.transportadoras.put(t.getCodigo(), t.clone());
    }

    public void insereUtilizador(Utilizador u)
    {
        this.utilizadores.put(u.getCodigo(), u.clone());
    }

    public void insereEncomendaAceite(String e)
    {
        this.aceites.add(e);
    }

    public void mudarUtilizador(String utilizador)
    {
        this.utilizador_atual = utilizador;
    }

    public List<Loja> getLojas()
    {
        return this.lojas.values().stream().map(Loja::clone).collect(Collectors.toList());
    }

    public List<Voluntario> getVoluntarios()
    {
        return this.voluntarios.values().stream().map(Voluntario::clone).collect(Collectors.toList());
    }

    public List<Transportadora> getTransportadoras()
    {
        return this.transportadoras.values().stream().map(Transportadora::clone).collect(Collectors.toList());
    }

    public List<Utilizador> getUtilizadores()
    {
        return this.utilizadores.values().stream().map(Utilizador::clone).collect(Collectors.toList());
    }

    public List<String> getEncomendasAceites()
    {
        return new ArrayList<>(this.aceites);
    }

}
