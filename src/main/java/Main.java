import java.io.*;

public class Main {
    final static int width = 200;
    final static int height = 100;

    public static Boolean intersectsSphere(Vector center, double radius, Ray ray) {
        Vector offsetOrigin = ray.source.subtract(center);
        double a = ray.direction.dot(ray.direction);
        double b = 2.0 * offsetOrigin.dot(ray.direction);
        double c = offsetOrigin.dot(offsetOrigin) - (radius * radius);
        double discriminant = b * b - 4 * a * c;
        return discriminant > 0;
    }

    public static Vector colorOf(Ray ray) {
        if (intersectsSphere(new Vector(0, 0, -1), 0.5, ray)) {
            return new Vector(1, 0, 0);
        }
        Vector unitVector = ray.direction.normalize();
        double t = 0.5 * (unitVector.y + 1.0);
        return new Vector(1.0, 1.0, 1.0).multiplyBy(1.0 - t)
                .add(new Vector(0.5, 0.7, 1.0).multiplyBy(t));
    }

    public static Vector rayDirection(int x, int y) {
        // The ray is initially directed towards the top left corner of the screen
        // and it's moved around by scaling the offset vectors.
        Vector lowerLeftCorner = new Vector(-2.0, -1.0, -1.0);
        Vector horizontalOffset = new Vector(4.0, 0.0, 0.0);
        Vector verticalOffset = new Vector(0.0, 2.0, 0.0);

        double hScale = x / (double) 200;
        double vScale = y / (double) 100;

        return lowerLeftCorner
                .add(
                        horizontalOffset.multiplyBy(hScale)
                                .add(
                                        verticalOffset.multiplyBy(vScale)));
    }

    public static void writeImageToFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("P3\n" + width + " " + height + "\n255\n");

            for (int y = height - 1; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    Ray ray = new Ray(new Vector(0, 0, 0), rayDirection(x, y));
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

    public static void main(String[] args) {
        writeImageToFile("output.ppm");
    }
}
