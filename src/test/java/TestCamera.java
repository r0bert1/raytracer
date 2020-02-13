import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCamera {
    Camera camera;

    @Before
    public void setup() {
        camera = new Camera();
    }

    @Test
    public void rayToCenterOfScreenIsReturnedCorrectly() {
        Ray ray = camera.getRay(0.5, 0.5);
        assertEquals(ray.direction, new Vector(0, 0, -1));
    }
}
