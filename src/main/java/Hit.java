public class Hit {
    double t;
    Vector position;
    Vector normal;

    public Hit() {}

    public Hit(Vector position, Vector normal) {
        this.position = position;
        this.normal = normal;
    }

    @Override
    public String toString() {
        return "p: " + position + ", n: " + normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit hit = (Hit) o;
        return Double.compare(hit.t, t) == 0 &&
                position.equals(hit.position) &&
                normal.equals(hit.normal);
    }
}
