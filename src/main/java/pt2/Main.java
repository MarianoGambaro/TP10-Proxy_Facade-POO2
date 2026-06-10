package pt2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    static void main() {

        var adminIntermedio = new Usuario("Juan", List.of(Permiso.ADMIN, Permiso.INTERMEDIO));
        var admin = new Usuario("Javier", List.of(Permiso.ADMIN));
        var comun = new Usuario("Guille", List.of(Permiso.BASICO));

        //cree una archivo desde aca para probarlo
        String rutaDePrueba = ".";
        String nombreArchivoImportante = "importante.txt";

        try {
            //texto adentro del archivo de prueba
            Files.writeString(Paths.get(rutaDePrueba + "/" + nombreArchivoImportante), "Archivo muy importante.");

            FileAccess archivoReal = new FileAccess(rutaDePrueba, nombreArchivoImportante);
            var proxy = new FileAccessProxy(comun, archivoReal);

            //leemos
            System.out.println("Contenido del archivo: " + proxy.readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

