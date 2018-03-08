package edu.unm.cs583;


import junit.framework.*;

/**
 * Some simple tests.
 *
 */
public class SimpleTest extends TestCase {
	Velocity v;

	protected void setUp() {
			}
	public static Test suite() {

		/*
		 * the type safe way
		 *
		TestSuite suite= new TestSuite();
		suite.addTest(
			new SimpleTest("add") {
				 protected void runTest() { testAdd(); }
			}
		);

		suite.addTest(
			new SimpleTest("testDivideByZero") {
				 protected void runTest() { testDivideByZero(); }
			}
		);
		return suite;
		*/

		/*
		 * the dynamic way
		 */
		return new TestSuite(SimpleTest.class);
	}
	public void testVelocity1() {
		v = new Velocity();		
		assertTrue(v.getDirection() == 0);
	}
		public static void main (String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}