//Nichakul Kongnual 6588178 Sec2
package ecommerce.product;

public class Dress{
	
    private int id;
    private String name;
    private String description;
    private double price;
    private int stockCount;

  public Dress(int id, String name, String description, double price, int stockCount){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockCount = stockCount;
  }

  public int getId(){
        return id;
  }

  public String getName(){
        return name;
  }
    
  public String getDescription(){
        return description;
  }

  public double getPrice(){
        return price;
  }

  public void setStockCount(int stockCount){
      this.stockCount = stockCount;
  }
  
  public int getStockCount(){
        return stockCount;
  }

  public void decreaseStockCount(){ //update
       if(stockCount > 0){
          stockCount--;
       }
  }

  public void increaseStockCount(){ //update
       stockCount++;
  }
  
}

