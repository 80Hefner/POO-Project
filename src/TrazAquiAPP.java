import Controller.MVC_Controller;
import Models.*;
import NewExceptions.EncomendaInexistenteException;
import Utils.Parser;
import View.MVC_View;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TrazAquiAPP {

    public static void main(String[] args) {
        TrazAqui trazAqui = new TrazAqui();
        MVC_View view = new MVC_View();

        Parser parser = new Parser();


        Path logs_path = Paths.get(System.getProperty("user.dir") + "/Dados");
        if (!Files.isReadable(logs_path)) System.exit(0);

        try {
            parser.parseLogs(logs_path.toString(), trazAqui);
        } catch (EncomendaInexistenteException e) {
            e.printStackTrace();
        }

        MVC_Controller controlador = new MVC_Controller(trazAqui, view);
        controlador.menuPrincipal();
    }
}