public class Camera {
    Vector lowerLeftCorner;
    Vector horizontalOffset;
    Vector verticalOffset;
    Vector origin;

    public Camera() {
        this.lowerLeftCorner = new Vector(-2.0, -1.0, -1.0);
        this.horizontalOffset = new Vector(4.0, 0.0, 0.0);
        this.verticalOffset = new Vector(0.0, 2.0, 0.0);
        this.origin = new Vector(0, 0, 0);
    }

    /**
     * Calculates the direction vector of a ray so that it hits the spot on
     * the screen specified by the x and y arguments.
     * The ray is initially directed towards the top left corner of the screen
     * and it's moved around by scaling the offset vectors.
     *
     * @param hScale Specifies the target x coordinate on the screen
     * @param vScale Specifies the target y coordinate on the screen
     * @return       Ray starting from origin and directed according to
     *               horizontal and vertical scale factors.
     */
    public Ray getRay(double hScale, double vScale) {
        Vector rayDirection = lowerLeftCorner
                .add(
                        horizontalOffset.multiplyBy(hScale)
                                .add(
                                        verticalOffset.multiplyBy(vScale)));

        return new Ray(origin, rayDirection);
    }
}
