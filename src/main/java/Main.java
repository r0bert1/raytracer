import java.io.*;

public class Main {
    final static int WIDTH = 200;
    final static int HEIGHT = 100;

    /**
     * Checks if ray (equation: p(t) = a + t*b) intersects the specified
     * sphere (equation: (p-c)*(p-c) = r*r) by inserting the ray expression in
     * the sphere equation, which eventually yields
     *      t*t*(b*b) + 2*t*(b*b-c) + (a-c)*(a-c) - r*r = 0
     * where b is the direction vector of the ray, a is the position vector of
     * the ray's source, c is the center of the circle and r is the radius of
     * the sphere. This equation is then solved with respect to t and the
     * discriminant is used to check if there are intersections i.e. the
     * discriminant is positive.
     *
     * @param center Position of sphere center
     * @param radius Radius of sphere
     * @param ray    The examined ray
     * @return       If the discriminant is positive then the parameter t
     *               at which the ray intersects the sphere, otherwise -1
     */
    public static double intersectsSphere(Vector center, double radius, Ray ray) {
        Vector offsetOrigin = ray.source.subtract(center);
        double a = ray.direction.dot(ray.direction);
        double b = 2.0 * offsetOrigin.dot(ray.direction);
        double c = offsetOrigin.dot(offsetOrigin) - (radius * radius);
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return -1;
        } else {
            return (-b - Math.sqrt(discriminant)) / (2 * a);
        }
    }

    /**
     * Determines the color seen along the provided ray. The color values will
     * be mapped from the components of the normal vector at the point of
     * intersection e.g. normal.x = red. In case of no intersection the color
     * is set to a shade of blue depending on it's height on the screen to
     * create a fading blue background.
     *
     * @param ray The ray whose color will be determined
     * @return    A 'color vector' where vector.x = red, vector.y = green etc.
     */
    public static Vector colorOf(Ray ray) {
        double t = intersectsSphere(new Vector(0, 0 , -1), 0.5, ray);
        if (t > 0) {
            Vector normal = (ray.pointAt(t).subtract(new Vector(0, 0, -1))).normalize();
            // mapping each color value to the interval from 0 to 1
            return new Vector(normal.x + 1, normal.y + 1, normal.z + 1).multiplyBy(0.5);
        }
        // background
        Vector unitVector = ray.direction.normalize();
        t = 0.5 * (unitVector.y + 1.0);
        return new Vector(1.0, 1.0, 1.0).multiplyBy(1.0 - t)
                .add(new Vector(0.5, 0.7, 1.0).multiplyBy(t));
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

            for (int y = HEIGHT - 1; y >= 0; y--) {
                for (int x = 0; x < WIDTH; x++) {
                    Ray ray = new Ray(new Vector(0, 0, 0), rayDirection(x, y));
                    Vector colorVector = colorOf(ray);
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
