import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSphere {
    Sphere sphere;

    @Before
    public void setup() {
        sphere = new Sphere(new Vector(0, 0, -1), 0.5);
    }

    @Test
    public void rayThroughCenterOfSphereHitsHeadOn() {
        Ray ray = new Ray(new Vector(0, 0, 0), new Vector(0, 0, -1));
        Hit result = sphere.hitBy(ray, 0, Double.MAX_VALUE);
        assertEquals(new Hit(new Vector(0, 0, -0.5), new Vector(0, 0, 1)), result);
    }

    @Test
    public void rayToUpperLeftCornerDoesNotIntersectSphere() {
        Ray ray = new Ray(new Vector(0, 0, 0), new Vector(-2, 1, -1));
        Hit result = sphere.hitBy(ray, 0, Double.MAX_VALUE);
        assertEquals(null, result);
    }
}
