package com.qa.bluesquare.pages;

import org.openqa.selenium.By;

public class CartPage {
	
	private By locator1=By.name("q");
	private By locator2=By.id("id123");
	
 public void CartPage()
 {
	 System.out.println("cart page construtor");
 }
 
 /**
  * This method will add the item to cart
  */
 public void addToCart()
 {
	 System.out.println("add to card developed");
 }
}
