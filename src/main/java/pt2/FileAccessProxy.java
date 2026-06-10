package pt2;

import java.io.IOException;

public class FileAccessProxy implements FileAccessSubject {

    private String archivoAProteger;
    private Usuario usuario;
    private FileAccessSubject sujeto;


    public FileAccessProxy(Usuario usuario, FileAccessSubject sujeto) {
        this.sujeto = sujeto;
        this.usuario = usuario;
        archivoAProteger = sujeto.nombreArchivo();
    }

    @Override
    public String readFile() throws IOException {
        if (nombreArchivo().startsWith("i")) {
            if (usuario.poseePermiso(Permiso.ADMIN)) {
                return sujeto.readFile();
            } else {
                throw new RuntimeException("Necesita permiso de Administrador para acceder");
            }
        } else if (nombreArchivo().startsWith("m")) {
            if (usuario.poseePermiso(Permiso.ADMIN) || usuario.poseePermiso(Permiso.INTERMEDIO)) {
                return sujeto.readFile();
            } else {
                throw new RuntimeException("Necesita permiso de Administrador o Intermedio para acceder");
            }
        }
        return sujeto.readFile();
    }

    @Override
    public String nombreArchivo() {
        return sujeto.nombreArchivo();
    }


}
