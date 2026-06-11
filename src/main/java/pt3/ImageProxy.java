package pt3;

public class ImageProxy implements ImageSubject {

    private ImageSubject realSubject;
    private String path;

    public ImageProxy(String path) {
        this.path = path;
    }

    @Override
    public void display() {
        if (realSubject == null) {
            //agrego sysout para saber si funciona correctamente
            System.out.println("Cargando imagen por primera vez.");
            realSubject = new ImageFile(this.path);
        } else {
            System.out.println("Cargando imagen desde proxy.");
        }
        realSubject.display();
    }
}
