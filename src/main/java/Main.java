import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        int cols = 400;
        int rows = 200;

        BufferedImage image = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                double r = (double) x / (double) cols;
                double g = (double) y / (double) rows;
                double b = 0.2;
                int ir = (int) (255.99 * r);
                int ig = (int) (255.99 * g);
                int ib = (int) (255.99 * b);
                Color color = new Color(ir, ig, ib);
                image.setRGB(x, y, color.getRGB());
            }
        }

        try {
            File output = new File("output.png");
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
