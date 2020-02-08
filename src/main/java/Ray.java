public class Ray {
    Vector source;
    Vector direction;

    public Ray(Vector source, Vector direction) {
        this.source = source;
        this.direction = direction;
    }

    public Vector pointAt(double t) {
        return source.add(direction.multiplyBy(t));
    }

    public String toString() {
        return "s: " + source + ", d: " + direction;
    }
}
