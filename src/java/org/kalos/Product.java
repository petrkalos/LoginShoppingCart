package org.kalos;

/**
 *
 * @author Agellous
 */
public class Product {
    private String name;
    private double price;
    private int amount;
    private double total;
    
    public Product(String name,double price){
        this.name = name;
        this.price = price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public int getAmount(){
        return this.amount;
    }
    
    public void setAmount(int amount){
        this.amount = amount;
    }
    
    public double calc(){
        this.total = amount*price;
        return this.total;
    }
    
    public double getTotal(){
        return this.total;
    }
    
}
