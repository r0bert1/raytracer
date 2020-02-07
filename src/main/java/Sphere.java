public class Sphere {
    Vector center;
    double radius;

    public Sphere(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Checks if ray (equation: p(t) = a + t*b) intersects the specified
     * sphere (equation: (p-c)*(p-c) = r*r) by inserting the ray expression in
     * the sphere equation, which eventually yields
     *       t*t*(b*b) + 2*t*(b*b-c) + (a-c)*(a-c) - r*r = 0
     * where b is the direction vector of the ray, a is the position vector of
     * the ray's source, c is the center of the circle and r is the radius of
     * the sphere. This equation is then solved with respect to t and the
     * discriminant is used to check if there are intersections i.e. the
     * discriminant is non-negative.
     *
     * @param ray  The ray that is checked for hitting the sphere
     * @param tMin Minimum threshold for the hit to count
     * @param tMax Maximum threshold for the hit to count
     * @return     Hit object which contains the position and normal vector
     *             of the hit if intersections are found, otherwise null
     */
    public Hit hitBy(Ray ray, double tMin, double tMax) {
        Vector offsetOrigin = ray.source.subtract(center);
        double a = ray.direction.dot(ray.direction);
        double b = 2 * offsetOrigin.dot(ray.direction);
        double c = offsetOrigin.dot(offsetOrigin) - (radius * radius);
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            Hit hit = new Hit();
            double t = (-b - Math.sqrt(discriminant)) / (2 * a);
            if (t < tMax && t > tMin) {
                hit.position = ray.pointAt(t);
                hit.normal = (hit.position.subtract(center)).multiplyBy(1 / radius);
                return hit;
            }
            t = (-b + Math.sqrt(discriminant)) / (2 * a);
            if (t < tMax && t > tMin) {
                hit.position = ray.pointAt(t);
                hit.normal = (hit.position.subtract(center)).multiplyBy(1 / radius);
                return hit;
            }
        }
        return null;
    }
}
