package pt3;

public class ImageGalery {

    public static void main(String[] args) {
        var image1 = new ImageProxy("src/main/resources/image1.jpg");
        image1.display();

        //la vuelvo a llamar
        image1.display();
    }
}