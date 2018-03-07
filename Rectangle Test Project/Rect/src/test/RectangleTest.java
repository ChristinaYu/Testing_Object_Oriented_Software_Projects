package test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Random;
import org.junit.Ignore;

public class RectangleTest {

	// Made Rectangle throw an exception for negative parameters Mario LoPrinzi
	@Test(expected = InvalidParameterException.class)
	public void testInit() {
		Rectangle r = new Rectangle(1, 2, 3, 4);
		assertEquals(r.getX(), 1);
		assertEquals(r.getY(), 2);
		assertEquals(r.getWidth(), 3);
		assertEquals(r.getHeight(), 4);

		// testing with negative numbers, tested by Yu
		new Rectangle(-1, -1, -1, -1);
	}

	@Test
	public void testArea() {
		Rectangle z = new Rectangle(0, 0, 0, 0);
		// Testing the zero Rectangle
		assertEquals(z.area(), 0);

		Rectangle r = new Rectangle(0, 0, 2, 2);
		// Testing basic rectangle
		assertEquals(r.area(), 4);

		Rectangle s = new Rectangle(1, 2, 3, 4);
		// Testing away from origin
		assertEquals(s.area(), 12);

		// Testing the area of rectangle starting from random point with random width
		// and height, by Yu
		Random random = new Random();
		int a = random.nextInt(10000);
		int b = random.nextInt(10000);
		int c = Math.abs(random.nextInt(10000));
		int d = Math.abs(random.nextInt(10000));
		Rectangle t = new Rectangle(a, b, c, d);
		assertEquals(t.area(), c * d);

		// Testing the area value which is greater than the max value of int, by Yu
		Rectangle x = new Rectangle(a, b, (int) Math.pow(2, 32), (int) Math.pow(2, 32));
		// System.out.println("Expected Area of x is: "+ MAX_INT*MAX_INT);

		// throws exception if area turns negative from integer overflow. Mario LoPrinzi
		try {
			x.area();
		} catch (ArithmeticException error) {
			assertEquals("Width*Height has caused and integer overflow.", error.getMessage());
		}

	}

	@Test
	public void testDiagonal() {
		Rectangle z = new Rectangle(0, 0, 0, 0);
		assertEquals(z.diagonal(), 0.0, 0.0);

		Rectangle r = new Rectangle(1, 1, 1, 1);
		assertEquals(r.diagonal(), 1.414, .001);
		assertEquals(r.diagonal(), Math.sqrt(2), 0.001);

		// testing diagonal using random width and height, by Yu
		Random random = new Random();
		int a = Math.abs(random.nextInt());
		int b = Math.abs(random.nextInt());
		Rectangle s = new Rectangle(0, 0, a, b);
		assertEquals(s.diagonal(), Math.sqrt(a * a + b * b), 0.0);

	}

	@Test
	@Ignore
	public void testEquals() {
		Rectangle r = new Rectangle(1, 2, 3, 4);
		Rectangle s = new Rectangle(1, 2, 3, 4);
		assertEquals(r, s);
		assertEquals(r.equals(s), true);

		Rectangle t = new Rectangle(4, 3, 2, 1);
		assertEquals(r.equals(t), false);

		assertEquals(r.equals(new Object()), false);

		// Testing equality on the negative values. Mario LoPrinzi
		Rectangle lowerLeftR = new Rectangle(-2, -3, 4, 5);
		assertEquals(lowerLeftR.equals(new Rectangle(-2, -3, 4, 5)), true);
	}

	@Test
	public void testToString() {
		Rectangle r = new Rectangle(1, 2, 3, 4);
		assertEquals("(1,2), width = 3, height = 4", r.toString());
		// Testing lower left quadrant rectangle. Mario LoPrinzi
		Rectangle lowerLeftR = new Rectangle(-2, -3, 4, 5);
		assertEquals("(-2,-3), width = 4, height = 5", lowerLeftR.toString());
	}

	@Test
	public void testUnion() {
		Rectangle r4 = new Rectangle(5, 5, 10, 10);
		Rectangle s4 = new Rectangle(8, 8, 4, 4);
		assertEquals(r4.union(s4), r4);
		assertEquals(s4.union(r4), r4);

		Rectangle r5 = new Rectangle(5, 5, 10, 10);
		Rectangle s5 = new Rectangle(8, 0, 4, 20);
		assertEquals(r5.union(s5), new Rectangle(5, 0, 10, 20));

		Rectangle r1 = new Rectangle(2, 3, 5, 6);
		Rectangle s1 = new Rectangle(4, 7, 8, 9);
		assertEquals(r1.union(s1), new Rectangle(2, 3, 10, 13));

		// testing the rectangle union with empty, by Yu
		Rectangle r = new Rectangle(0, 0, 1, 1);
		Rectangle s = new Rectangle(0, 0, 0, 0);
		assertEquals(r.union(s), r);
	}

	/* The following test methods were not written in class */

	@Test
	public void testContains() {
		Rectangle r = new Rectangle(1, 2, 3, 4);

		int x = 1;
		int y = 2;
		assertEquals(r.contains(x, y), true);
		assertEquals(r.contains(x - 1, y), false);
		assertEquals(r.contains(x, y - 1), false);
		assertEquals(r.contains(x + 1, y + 1), true);

		x = 4;
		y = 2;
		assertEquals(r.contains(x, y), true);
		assertEquals(r.contains(x + 1, y), false);
		assertEquals(r.contains(x, y - 1), false);
		assertEquals(r.contains(x - 1, y + 1), true);

		x = 1;
		y = 6;
		assertEquals(r.contains(x, y), true);
		assertEquals(r.contains(x - 1, y), false);
		assertEquals(r.contains(x, y + 1), false);
		assertEquals(r.contains(x + 1, y - 1), true);

		x = 4;
		y = 6;
		assertEquals(r.contains(x, y), true);
		assertEquals(r.contains(x + 1, y), false);
		assertEquals(r.contains(x, y + 1), false);
		assertEquals(r.contains(x - 1, y - 1), true);

		// Testing a zero area Rectangle for expected behavior
		// Mario LoPrinzi
		x = 1;
		y = 2;
		r = new Rectangle(0, 0, 0, 0);
		assertEquals(r.contains(x, y), false);
		assertEquals(r.contains(x + 1, y), false);
		assertEquals(r.contains(x, y + 1), false);
		assertEquals(r.contains(x - 1, y - 1), false);
		x = 0;
		y = 0;
		assertEquals(r.contains(x, y), false);
		// Testing a zero area Rectangle for expected behavior
		// Mario LoPrinzi
		r = new Rectangle(0, 0, 0, 1);
		assertEquals(r.contains(x, y), false);
		assertEquals(r.contains(x + 1, y), false);
		assertEquals(r.contains(x, y + 1), false);
		assertEquals(r.contains(x - 1, y - 1), false);
	}

	@Test
	@Ignore
	public void testIntersects() {
		Rectangle r = new Rectangle(0, 0, 4, 4);

		Rectangle notIntersectR = new Rectangle(6, 6, 4, 4);
		assertEquals(r.intersects(notIntersectR), false);
		assertEquals(notIntersectR.intersects(r), false);

		Rectangle ULIntersectsR = new Rectangle(2, 2, 3, 5);
		assertEquals(r.intersects(ULIntersectsR), true);
		assertEquals(ULIntersectsR.intersects(r), true);

		Rectangle URIntersectsR = new Rectangle(-2, 2, 3, 5);
		assertEquals(r.intersects(URIntersectsR), true);
		// Symmetry for LL corner intersect
		assertEquals(URIntersectsR.intersects(r), true);

		Rectangle leftIntersectsR = new Rectangle(1, 2, 4, 1);
		assertEquals(r.intersects(leftIntersectsR), true);
		assertEquals(leftIntersectsR.intersects(r), true);

		Rectangle insideR = new Rectangle(1, 1, 2, 2);
		assertEquals(r.intersects(insideR), true);
		assertEquals(insideR.intersects(r), true);

		Rectangle s = new Rectangle(0, 5, 15, 5);
		Rectangle noCornersInSButIntersects = new Rectangle(5, 0, 5, 15);
		assertEquals(s.intersects(noCornersInSButIntersects), true);
		assertEquals(noCornersInSButIntersects.intersects(s), true);

		Rectangle t = new Rectangle(2, 0, 2, 1);
		Rectangle horizontallyInsideTButNotIntersecting = new Rectangle(0, 5, 5, 3);
		assertEquals(t.intersects(horizontallyInsideTButNotIntersecting), false);
		assertEquals(horizontallyInsideTButNotIntersecting.intersects(t), false);

		// modified by Yu
		Rectangle topIntersectsR = new Rectangle(1, 1, 2, 5);
		assertEquals(r.intersects(topIntersectsR), true);
		// System.out.println(topIntersectsR.intersects(r));
		assertEquals(topIntersectsR.intersects(r), true);
		if (topIntersectsR.intersects(r) == false) {
			fail("Result should not be false");
		}

		// testing if rectangle intersects with itself by Yu
		Rectangle x = r;
		assertEquals(x.intersects(r), true);

	}

	@Test
	@Ignore
	public void testIntersection() {
		Rectangle r = new Rectangle(0, 0, 4, 4);

		Rectangle notIntersectR = new Rectangle(6, 6, 4, 4);
		assertEquals(r.intersection(notIntersectR), null);
		assertEquals(notIntersectR.intersection(r), null);

		Rectangle ULIntersectsR = new Rectangle(2, 2, 3, 5);
		assertEquals(r.intersection(ULIntersectsR), new Rectangle(2, 2, 2, 2));
		assertEquals(ULIntersectsR.intersection(r), new Rectangle(2, 2, 2, 2));

		Rectangle URIntersectsR = new Rectangle(-2, 2, 3, 5);
		assertEquals(r.intersection(URIntersectsR), new Rectangle(0, 2, 1, 2));
		assertEquals(URIntersectsR.intersection(r), new Rectangle(0, 2, 1, 2));

		Rectangle topIntersectsR = new Rectangle(1, 1, 2, 5);
		assertEquals(r.intersection(topIntersectsR), new Rectangle(1, 1, 2, 3));
		assertEquals(topIntersectsR.intersection(r), new Rectangle(1, 1, 2, 3));

		Rectangle leftIntersectsR = new Rectangle(1, 2, 4, 1);
		assertEquals(r.intersection(leftIntersectsR), new Rectangle(1, 2, 3, 1));
		assertEquals(leftIntersectsR.intersection(r), new Rectangle(1, 2, 3, 1));

		Rectangle insideR = new Rectangle(1, 1, 2, 2);
		assertEquals(r.intersection(insideR), insideR);
		assertEquals(insideR.intersection(r), insideR);

		Rectangle s = new Rectangle(0, 5, 15, 5);
		Rectangle noCornersInSButIntersects = new Rectangle(5, 0, 5, 15);
		assertEquals(s.intersection(noCornersInSButIntersects), new Rectangle(5, 5, 5, 5));
		assertEquals(noCornersInSButIntersects.intersection(s), new Rectangle(5, 5, 5, 5));

		Rectangle t = new Rectangle(2, 0, 2, 1);
		Rectangle horizontallyInsideTButNotIntersecting = new Rectangle(0, 5, 5, 3);
		assertEquals(t.intersection(horizontallyInsideTButNotIntersecting), null);
		assertEquals(horizontallyInsideTButNotIntersecting.intersection(t), null);

		// Test if a random rectangle intersection with itself, by Yu
		Random random = new Random();
		int a = Math.abs(random.nextInt(1000));
		int b = Math.abs(random.nextInt(1000));
		int c = Math.abs(random.nextInt(1000));
		int d = Math.abs(random.nextInt(1000));
		Rectangle x = new Rectangle(a, b, c, d);
		Rectangle y = new Rectangle(a, b, c, d);
		assertEquals(x.intersection(y), new Rectangle(a, b, c, d));
	}

}