import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestHit {
    Hit hit1;
    Hit hit2;

    @Before
    public void setup() {
        hit1 = new Hit(new Vector(1, 1, 1), new Vector(1, 1, 1));
        hit2 = new Hit(new Vector(2, 2, 2), new Vector(2, 2, 2));
    }

    @Test
    public void equalsReturnsFalseForDifferentHits() {
        assertNotEquals(hit1, hit2);
    }

    @Test
    public void equalsReturnsTrueForIdenticalHits() {
        assertEquals(hit1, new Hit(new Vector (1, 1, 1), new Vector(1, 1, 1)));
    }
}
