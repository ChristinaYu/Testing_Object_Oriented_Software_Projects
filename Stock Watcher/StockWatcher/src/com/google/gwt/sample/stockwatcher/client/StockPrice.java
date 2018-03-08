package com.google.gwt.sample.stockwatcher.client;

public class StockPrice {

  private String symbol;
  private double price;
  private double change;

  public StockPrice() {
  }

  public StockPrice(String symbol, double price, double change) {
//	if(symbol.length()>4 && symbol.length() <= 0){
//		throw new IllegalArgumentException("Stock Name Must Be between 1 and 4 chars long.");  
//	}
//	else if(!symbol.matches("^[0-9a-zA-Z\\.]{1,10}$")){
//		throw new IllegalArgumentException("'" + symbol + "' is not a valid symbol.");
//	}
//	else{this.symbol = symbol;}
	  this.symbol = symbol;  
    this.price = price;
    this.change = change;
  }

  public String getSymbol() {
    return this.symbol;
  }

  public double getPrice() {
    return this.price;
  }

  public double getChange() {
    return this.change;
  }

  public double getChangePercent() {
    return 100.0 * this.change / this.price;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setChange(double change) {
    this.change = change;
  }
}
