public class Ray {
    Vector A;
    Vector B;

    public Ray(Vector a, Vector b) {
        this.A = a;
        this.B = b;
    }

    public Vector origin() {
        return A;
    }

    public Vector direction() {
        return B;
    }

    public Vector pointAtParameter(double t) {
        return A.add(B.multiplyBy(t));
    }
}
