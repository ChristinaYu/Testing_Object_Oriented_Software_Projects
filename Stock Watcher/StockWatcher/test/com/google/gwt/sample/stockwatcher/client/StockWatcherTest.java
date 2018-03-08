package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;

import org.junit.Before;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


    /**
     * GWT JUnit tests must extend GWTTestCase.
 */
public class StockWatcherTest extends GWTTestCase {                       // <span style="color:black;">**(1)**</span>
	
    /**
     * Must refer to a valid module that sources this class.
    */
	  public String getModuleName() {                                         // <span style="color:black;">**(2)**</span>
		  return "com.google.gwt.sample.stockwatcher.StockWatcher";
	  }
	
	  /**  * Verify that the instance fields in the StockPrice class are set correctly.  */ 
	  public void testStockPriceCtor() {  
		  String symbol = "AAPL";  
		  double price = 70.0;  
		  double change = 2.0;  
		  double changePercent = 100.0 * change / price;
		  StockPrice sp = new StockPrice(symbol, price, change);  
		  assertNotNull(sp);  
		  assertEquals(symbol, sp.getSymbol());  
		  assertEquals(price, sp.getPrice(), 0.001);  
		  assertEquals(change, sp.getChange(), 0.001);  
		  assertEquals(changePercent, sp.getChangePercent(), 0.001); 
	  }
	  
	  public void testSimple() {                                              // <span style="color:black;">**(3)**</span>
	      assertTrue(true);
	    }
	  
	  
//	  public void testStockPriceName(){
//		  String symbol1 = " ";
//		  double price1 = 70.0;  
//		  double change1 = 2.0; 
//		  StockPrice sp1 = new StockPrice(symbol1, price1, change1); 
//		  assertFalse(sp1 instanceof StockPrice);
//		  
//		  String symbol2 = "ABCDEFG";
//		  double price2 = 70.0;  
//		  double change2 = 2.0; 
//		  StockPrice sp2 = new StockPrice(symbol2, price2, change2);
//		  assertFalse(sp2 instanceof StockPrice);
//
//		  String symbol3 = "!@#$%^&*()_+";
//		  double price3 = 70.0;  
//		  double change3 = 2.0; 
//		  StockPrice sp3 = new StockPrice(symbol3, price3, change3); 
//		  assertFalse(sp3 instanceof StockPrice);
//	  }
	  
	  public void testStockPriceName(){
		  try{
		  String symbol = " ";
		  double price = 70.0;  
		  double change = 2.0; 
		  StockPrice sp1 = new StockPrice(symbol, price, change); 
		  }catch(IllegalArgumentException e){
			  assertEquals("' ' is not a valid symbol.", e.getMessage());
		  }
		  
		  try{
			  String symbol = "ABCDEFG";
			  double price = 70.0;  
			  double change = 2.0; 
			  StockPrice sp2 = new StockPrice(symbol, price, change); 
			  }catch(IllegalArgumentException e){
				  assertEquals("Stock Name Must Be between 1 and 5 chars long.", e.getMessage());
			  }
		  
		  try{
			  String symbol = "!@#$%^&*()_+";
			  double price = 70.0;  
			  double change = 2.0; 
			  StockPrice sp3 = new StockPrice(symbol, price, change); 
			  }catch(IllegalArgumentException e){
				  assertEquals("'!@#$%^&*()_+' is not a valid symbol.", e.getMessage());
			  }
	  }
	  
	   /**
		* To test if the stock can be added correctly
	   */
	  
	   public void testAddStock(){
		   String symbol = "AAPL";  
		   double price = 70.0;  
		   double change = 2.0;  
		   double changePercent = 100.0 * change / price;
		   StockPrice sp = new StockPrice(symbol, price, change);
		   StockWatcher.addStock(sp.getSymbol());
		   assertEquals(1, StockWatcher.stocks.size());
	   }
	   
	   /**
        * To test if the stock can be removed correctly
       */
	   public void testRemoveStock(){
		   String symbol = "AAPL";  
		   double price = 70.0;  
		   double change = 2.0;  
		   double changePercent = 100.0 * change / price;
		   StockPrice sp = new StockPrice(symbol, price, change);
		   StockWatcher.stocks.add(sp.getSymbol());
		   assertEquals(1, StockWatcher.stocks.size());
	   
		   StockWatcher.removeStock(sp.getSymbol());
		   assertEquals(0, StockWatcher.stocks.size());
	   }  
	  
	   public void testAddPrices() {
			  StockWatcher.stocks.add("GOOG");
			  StockWatcher.stocks.add("HPQ");
			  StockWatcher.stocks.add("INTC");
			  StockWatcher.stocks.add("KO");
			  StockPrice[] prices = StockWatcher.addPrices();
			  String symbol = "TGT";  
			  double price = 70.0;  
			  double change = 2.0;  
			  double changePercent = 100.0 * change / price;
			  StockPrice sp = new StockPrice(symbol, price, change);
			  prices[4] = sp;
			  
			  assertEquals(5, prices.length);
			  assertTrue(prices[1].getSymbol().equals("GOOG"));
			  }
  
}