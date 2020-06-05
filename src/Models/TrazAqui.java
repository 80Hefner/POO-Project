package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TrazAqui
{
    private static TreeMap<String,Loja> lojas = new TreeMap<>();
    private static TreeMap<String,Voluntario> voluntarios = new TreeMap<>();
    private static TreeMap<String,Transportadora> transportadoras = new TreeMap<>();
    private static TreeMap<String,Utilizador> utilizadores = new TreeMap<>();
    private static ArrayList<String> aceites = new ArrayList<>();
    private static String utilizador_atual = "";


    public static void insereLoja(Loja l)
    {
        TrazAqui.lojas.put(l.getCodigo(), l.clone());
    }

    public static void insereVoluntario(Voluntario v)
    {
        TrazAqui.voluntarios.put(v.getCodigo(), v.clone());
    }

    public static void insereTransportadora(Transportadora t)
    {
        TrazAqui.transportadoras.put(t.getCodigo(), t.clone());
    }

    public static void insereUtilizador(Utilizador u)
    {
        TrazAqui.utilizadores.put(u.getCodigo(), u.clone());
    }

    public static void insereEncomendaAceite(String e)
    {
        TrazAqui.aceites.add(e);
    }

    public static void insereEncomenda(Encomenda e, String codLoja)
    {
        TrazAqui.lojas.get(codLoja).insereEncomenda(e);
    }

    public static void setUtilizador_atual(String utilizador)
    {
        TrazAqui.utilizador_atual = utilizador;
    }

    public static String getUtilizador_atual()
    {
        return utilizador_atual;
    }

    public static List<Loja> getLojas()
    {
        return TrazAqui.lojas.values().stream().map(Loja::clone).collect(Collectors.toList());
    }

    public static List<Voluntario> getVoluntarios()
    {
        return TrazAqui.voluntarios.values().stream().map(Voluntario::clone).collect(Collectors.toList());
    }

    public static List<Transportadora> getTransportadoras()
    {
        return TrazAqui.transportadoras.values().stream().map(Transportadora::clone).collect(Collectors.toList());
    }

    public static List<Utilizador> getUtilizadores()
    {
        return TrazAqui.utilizadores.values().stream().map(Utilizador::clone).collect(Collectors.toList());
    }

    public static List<String> getEncomendasAceites()
    {
        return new ArrayList<>(TrazAqui.aceites);
    }

    public static boolean procuraEncomendaAceite(String codigo)
    {
        return TrazAqui.aceites.contains(codigo);
    }

    public static boolean procuraUtilizador(String utilizador)
    {
        return TrazAqui.utilizadores.containsKey(utilizador);
    }

    public static boolean verificaPassword(String utilizador, String password)
    {
        Utilizador u = TrazAqui.utilizadores.get(utilizador);

        return u.getPassword().equals(password);
    }

}
