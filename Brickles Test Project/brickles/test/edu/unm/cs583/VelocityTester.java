package edu.unm.cs583;

import static org.junit.Assert.*;
import java.security.InvalidParameterException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class VelocityTester {
	
	@Before
	public void setUp() throws Exception {
		Velocity v = new Velocity();
		// check for initial values
		assertEquals(v.getSpeedX(), 0);
		assertEquals(v.getSpeedY(), 0);
		assertEquals(v.getDirection(), 0);
	}
	
	@Test
	public void testReverseX() {
		// testing when direction is smaller than 180
		Velocity v = new Velocity(50, 50);
		v.reverseX();
		assertEquals(v.getSpeedX(), -32);
		assertEquals(v.getDirection(), 130);
		
		// testing when direction is greater than 180
		Velocity a = new Velocity(100, 300);
		a.reverseX();
		assertEquals(a.getSpeedX(), -45);
		assertEquals(a.getDirection(), 240);

	}
	
	@Test
	public void testReverseY() {
		Velocity v = new Velocity(50, 50);
		v.reverseY();
		assertEquals(v.getSpeedY(), -38);
		assertEquals(v.getDirection(), 310);
	}
	
	@Test
	public void testSetSpeed() {
		Velocity v = new Velocity();
		v.setDirection(300);
		v.setSpeed(100);
		assertEquals(v.getSpeedX(), 45);
		assertEquals(v.getSpeedY(), -89);
//		System.out.println((int)(Math.sin(300 / 57.9) *100));
	}
	
	@Test
	/** public void test() {
		fail("Not yet implemented"); **/
	public void testSetDirection() {
		Velocity out = new Velocity();
		out.setDirection(90);
		assertEquals("Result", 90, out.getDirection());
		System.out.println("The direction is "+out.getDirection());
		out.setDirection(180);
		assertEquals("Result", 180, out.getDirection());
		System.out.println("The direction is "+out.getDirection());
		out.setDirection(270);
		assertEquals("Result", 270, out.getDirection());
		System.out.println("The direction is "+out.getDirection());
		out.setDirection(360); // boundary case
		assertEquals("Result", 0, out.getDirection());
		System.out.println("The direction is "+out.getDirection());
		
	}
	
	@Test
	public void testDecomposeSpeed() {
		Velocity v = new Velocity(50, 50);
		assertEquals(v. getSpeedX(), 32);
		assertEquals(v. getSpeedY(), 38);
	}
	
	@After
	public void tearDown() throws Exception {
		Velocity v = new Velocity();
		// check for final values
        if (v.xSpeed != 0 || v.ySpeed != 0 || v.speed != 0 || v.direction != 0) {
			throw new InvalidParameterException("Final values must be 0.");
		}
	}

}