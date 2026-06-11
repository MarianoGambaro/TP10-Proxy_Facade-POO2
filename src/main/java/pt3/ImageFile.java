package pt3;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFile implements ImageSubject {

    BufferedImage image;

    public ImageFile(String path) {
        image = load(path);
    }

    public void display() {
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        JOptionPane.showMessageDialog(null, label);
    }

    BufferedImage load(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}