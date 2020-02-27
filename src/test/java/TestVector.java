import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestVector {
    Vector v1;
    Vector v2;

    @Before
    public void setup() {
        v1 = new Vector(1, 2, 3);
        v2 = new Vector(3, 2, 1);
    }

    @Test
    public void testAdding() {
        assertEquals(new Vector(4, 4, 4), v1.add(v2));
    }

    @Test
    public void testSubtracting() {
        assertEquals(new Vector(-2, 0, 2), v1.subtract(v2));
    }

    @Test
    public void testMultiplyingByScalar() {
        assertEquals(new Vector(3, 6, 9), v1.multiplyBy(3));
    }

    @Test
    public void testDotProduct() {
        assertEquals(10, v1.dot(v2), 0.001);
    }

    @Test
    public void testMagnitude() {
        assertEquals(3.741, v1.magnitude(), 0.001);
    }

    @Test
    public void testNormalizing() {
        assertEquals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), v1.normalize());
    }

    @Test
    public void equalsReturnsFalseForDifferentVectors() {
        assertNotEquals(v1, v2);
    }

    @Test
    public void equalsReturnsTrueForIdenticalVectors() {
        assertEquals(v1, new Vector(1, 2, 3));
    }

    @Test
    public void toStringTest() {
        assertEquals("(1.0, 2.0, 3.0)", v1.toString());
    }
}
