import java.io.*;

public class Main {
    public static Vector colorOf(Ray r) {
        Vector unitVector = r.direction().normalize();
        double t = 0.5 * (unitVector.y + 1.0);
        return new Vector(1.0, 1.0, 1.0).multiplyBy(1.0 - t)
                .add(new Vector(0.5, 0.7, 1.0).multiplyBy(t));
    }

    public static void main(String[] args) {
        int cols = 200;
        int rows = 100;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.ppm"));
            writer.write("P3\n" + cols + " " + rows + "\n255\n");

            Vector lowerLeftCorner = new Vector(-2.0, -1.0, -1.0);
            Vector horizontal = new Vector(4.0, 0.0, 0.0);
            Vector vertical = new Vector(0.0, 2.0, 0.0);
            Vector origin = new Vector(0.0, 0.0, 0.0);

            for (int y = rows - 1; y >= 0; y--) {
                for (int x = 0; x < cols; x++) {
                    double r = (double) x / (double) cols;
                    double g = (double) y / (double) rows;
                    Ray ray = new Ray(origin, lowerLeftCorner.add(
                            (horizontal.multiplyBy(r).add(
                                    vertical.multiplyBy(g)))));
                    Vector colorVector = colorOf(ray);
                    int ir = (int) (255.99 * colorVector.x);
                    int ig = (int) (255.99 * colorVector.y);
                    int ib = (int) (255.99 * colorVector.z);
                    writer.write(ir + " " + ig + " " + ib + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
