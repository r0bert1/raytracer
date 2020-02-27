import java.io.*;
import java.util.Random;

public class Main {
    final static int WIDTH = 800;
    final static int HEIGHT = 400;
    final static int SAMPLES = 100;
    static Random random = new Random();

    /**
     * Determines the color seen along the provided ray. The color values will
     * be mapped from the components of the normal vector at the point of
     * intersection e.g. normal.x = red. In case of no hit the color
     * is set to a shade of blue depending on it's height on the screen to
     * create a fading blue background.
     *
     * @param ray The ray whose color will be determined
     * @return    A 'color vector' where vector.x = red, vector.y = green etc.
     */
    public static Vector colorOf(Ray ray, Sphere[] spheres) {
        Hit hit = hit(spheres, ray, 0.001, Double.MAX_VALUE);
        if (hit != null) {
            Vector target = hit.position.add(hit.normal).add(randomPointFromUnitSphere());
            return colorOf(new Ray(hit.position, target.subtract(hit.position)), spheres).multiplyBy(0.5);
        } else {
            Vector unitVector = ray.direction.normalize();
            double t = 0.5 * (unitVector.y + 1.0);
            return new Vector(1.0, 1.0, 1.0).multiplyBy(1.0 - t)
                    .add(new Vector(0.5, 0.7, 1.0).multiplyBy(t));
        }
    }

    /**
     * Goes through every sphere in the list and checks if the ray hits it.
     *
     * @param spheres Spheres in the scene
     * @param ray     The examined ray
     * @param tMin    Minimum threshold for the hit to count
     * @param tMax    Maximum threshold for the hit to count
     * @return        Hit object of the nearest hit
     */
    public static Hit hit(Sphere[] spheres, Ray ray, double tMin, double tMax) {
        double closest = tMax;
        Hit hit = null;
        for (Sphere sphere : spheres) {
            Hit temp = sphere.hitBy(ray, tMin, closest);
            if (temp != null) {
                closest = temp.t;
                hit = temp;
            }
        }
        return hit;
    }

    public static Vector randomPointFromUnitSphere() {
        Vector point;
        do {
            point = new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble())
                    .multiplyBy(2)
                    .subtract(new Vector(1, 1, 1));
        } while (point.dot(point) >= 1);
        return point;
    }

    /**
     * Writes the image data to a file in plain ppm format.
     *
     * @param fileName The name of the output file
     */
    public static void writeImageToFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("P3\n" + WIDTH + " " + HEIGHT + "\n255\n");

            Sphere[] spheres = new Sphere[2];
            spheres[0] = new Sphere(new Vector(0, 0, -1), 0.5);
            spheres[1] = new Sphere(new Vector(0, -100.5, -1), 100);

            Camera camera = new Camera();

            for (int y = HEIGHT - 1; y >= 0; y--) {
                for (int x = 0; x < WIDTH; x++) {
                    Vector color = new Vector(0, 0, 0);
                    for (int s = 0; s < SAMPLES; s++) {
                        double hScale = (x + random.nextDouble()) / (double) WIDTH;
                        double vScale = (y + random.nextDouble()) / (double) HEIGHT;
                        Ray ray = camera.getRay(hScale, vScale);
                        color = color.add(colorOf(ray, spheres));
                    }
                    color = color.multiplyBy(1 / (double) SAMPLES);
                    color = new Vector(Math.sqrt(color.x), Math.sqrt(color.y), Math.sqrt(color.z));

                    // RGB values are scaled to match the range of the .ppm file
                    int ir = (int) (255.99 * color.x);
                    int ig = (int) (255.99 * color.y);
                    int ib = (int) (255.99 * color.z);
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
