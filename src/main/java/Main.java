import java.io.*;

public class Main {
    final static int WIDTH = 200;
    final static int HEIGHT = 100;

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
        Hit hit = hit(spheres, ray, 0.0, Double.MAX_VALUE);
        if (hit != null) {
            return new Vector(hit.normal.x + 1, hit.normal.y + 1, hit.normal.z + 1).multiplyBy(0.5);
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

    /**
     * Calculates the direction vector of a ray so that it hits the spot on
     * the screen specified by the x and y arguments.
     * The ray is initially directed towards the top left corner of the screen
     * and it's moved around by scaling the offset vectors.
     *
     * @param x Specifies the target x coordinate on the screen
     * @param y Specifies the target y coordinate on the screen
     * @return  Direction vector of ray
     */
    public static Vector rayDirection(int x, int y) {
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

            for (int y = HEIGHT - 1; y >= 0; y--) {
                for (int x = 0; x < WIDTH; x++) {
                    Ray ray = new Ray(new Vector(0, 0, 0), rayDirection(x, y));
                    Vector colorVector = colorOf(ray, spheres);

                    // RGB values are scaled to match the range of the .ppm file
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
