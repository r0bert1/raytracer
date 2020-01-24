import java.io.*;

public class Main {
    public static Boolean intersectsSphere(Vector center, double radius, Ray ray) {
        Vector offsetOrigin = ray.origin().subtract(center);
        double a = ray.direction().dot(ray.direction());
        double b = 2.0 * offsetOrigin.dot(ray.direction());
        double c = offsetOrigin.dot(offsetOrigin) - (radius * radius);
        double discriminant = b * b - 4 * a * c;
        return discriminant > 0;
    }

    public static Vector colorOf(Ray ray) {
        if (intersectsSphere(new Vector(0, 0, -1), 0.5, ray)) {
            return new Vector(1, 0, 0);
        }
        Vector unitVector = ray.direction().normalize();
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
                    double r = x / (double) cols;
                    double g = y / (double) rows;
                    // The ray's direction vector's endpoint starts at the top left corner
                    // and is moved from left to right on every row of pixels on the screen.
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
