package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;

public class InventoryTest extends TestCase {
	private Inventory inv;
	private Recipe r;
	
	protected void setUp() throws Exception {
		inv = new Inventory();	
		
		r = new Recipe();
		r.setName("Yummy~");
		r.setAmtChocolate("0");
		r.setAmtCoffee("5");
		r.setAmtMilk("3");
		r.setAmtSugar("3");
		super.setUp();
		
		assertEquals(15, inv.getCoffee());
		assertEquals(15, inv.getMilk());
		assertEquals(15, inv.getSugar());
		assertEquals(15, inv.getChocolate());
	}
	
	public void testAccessors(){
		inv.setCoffee(3);
		inv.setMilk(2);
		inv.setSugar(1);
		inv.setChocolate(0);
		assertEquals(3, inv.getCoffee());
		assertEquals(2, inv.getMilk());
		assertEquals(1, inv.getSugar());
		assertEquals(0, inv.getChocolate());
	}
	
	public void testAddInv(){
		try {
			inv.addCoffee("1");
			inv.addMilk("1");
//			inv.addSugar("1");
			inv.addChocolate("1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
		
		assertEquals(16, inv.getCoffee());
		assertEquals(16, inv.getMilk());
//		assertEquals(16, inv.getSugar());
		assertEquals(16, inv.getChocolate());
		
		// negative tests
		try {
			inv.addCoffee("-1");
			inv.addMilk("abc");
			inv.addChocolate("1.5");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			//success if thrown
		}
		
	}
	
	public void testenoughIngredients(){
		// r requires 30 units of coffee
		assertEquals(true, inv.enoughIngredients(r));
		
		try {
			r.setAmtCoffee("30");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertEquals(false, inv.enoughIngredients(r));
	}
	
	public void testUseIngredients(){
		System.out.println("Before making drink: \n"+inv.toString());
		assertEquals(true, inv.useIngredients(r));
		System.out.println("After making drink: \n"+inv.toString());
		assertEquals(10, inv.getCoffee()); // FOUND ERROR!
		assertEquals(12, inv.getMilk());
		assertEquals(12, inv.getSugar());
		assertEquals(15, inv.getChocolate());
	}
	
	public void testCheckInv(){
		assertEquals("Coffee: "+inv.getCoffee()+"\nMilk: "+inv.getMilk()+"\nSugar: "
	    +inv.getSugar()+"\nChocolate: "+inv.getChocolate()+"\n", inv.toString());
//		System.out.println(inv.toString());
	}
	
}
