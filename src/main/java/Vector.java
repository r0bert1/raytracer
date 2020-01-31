public class Vector {
    double x;
    double y;
    double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y, z - v.z);
    }

    public Vector multiplyBy(double k) {
        return new Vector(x * k, y * k, z * k);
    }

    public double dot(Vector v) {
        return (x * v.x) + (y * v.y) + (z * v.z);
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public Vector normalize() {
        return multiplyBy(1 / magnitude());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 &&
                Double.compare(vector.y, y) == 0 &&
                Double.compare(vector.z, z) == 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
