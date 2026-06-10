package pt2;

import java.io.IOException;

public interface FileAccessSubject {

    String readFile() throws IOException;

    String nombreArchivo();
}
