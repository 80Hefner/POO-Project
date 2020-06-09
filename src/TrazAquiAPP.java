import Controller.MVC_Controller;
import Models.*;
import View.MVC_View;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TrazAquiAPP {

    public static void main(String[] args) {
        TrazAqui trazAqui = new TrazAqui();
        MVC_View view = new MVC_View();
        MVC_Controller controlador = new MVC_Controller(trazAqui, view);

        Path logs_path = Paths.get(System.getProperty("user.dir") + "/src");
        if (!Files.isReadable(logs_path)) System.exit(0);

        controlador.menuPrincipal(logs_path.toString());
    }
}