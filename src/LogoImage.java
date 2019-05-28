import javax.swing.ImageIcon;

public class LogoImage {

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = LogoImage.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}