//Nichakul Kongnual 6588178 Sec2
package ecommerce;

import ecommerce.product.Dress;

public class ArrayList<T>{ // Arraylist
    private Dress[] products;
    private int size;
    
    public boolean contains(T item){
        for(int i=0; i<size; i++){
            if(products[i].equals(item)){
              return true;
            }
        }
        return false;
    }

    public ArrayList(int initialCapacity){
        products = new Dress[initialCapacity];
        size = 0;
    }

    public void addProduct(Dress product){
       if(size == products.length){
         resizeArray();
       }
       products[size++]=product;
    }

    public void deleteProduct(int productId){
        try{
            int index = findProductIndex(productId);
            if (index != -1){
                shiftProductsLeft(index);
                size--;
            }else{
              throw new Exception("Product with ID " + productId + " not found.");
            }
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    private void shiftProductsLeft(int index){
        for(int i = index; i < size - 1; i++){
            products[i]= products[i + 1];
        }
      products[size-1] = null;
    }

    public void updateProduct(int productId, Dress updatedProduct){
        try{
            int index = findProductIndex(productId);
            if(index != -1){
               products[index] = updatedProduct;
            }else{
              throw new Exception("Product with ID " + productId + " not found.");
            }
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    private int findProductIndex(int productId){
        for(int i=0; i<size; i++){
            if(products[i].getId()==productId){
              return i;
            }
        }
      return -1;
    }
    
    public String getAllProductsAsString(){
        StringBuilder result = new StringBuilder();
        int idWidth = 4;
        int nameWidth = 16;
        int descriptionWidth = 28;
        int priceWidth = 10;
        int stockWidth = 5; //column widths
        
        System.out.println("***************************************************************");

        //table header
        result.append(String.format("%-" + idWidth + "s", "ID"));
        result.append(String.format("%-" + nameWidth + "s", "Name"));
        result.append(String.format("%-" + descriptionWidth + "s", "Description"));
        result.append(String.format("%-" + priceWidth + "s", "Price(฿)"));
        result.append(String.format("%-" + stockWidth + "s", "Stock"));
        result.append("\n");
        for (int i = 0; i < idWidth + nameWidth + descriptionWidth + priceWidth + stockWidth; i++) {
            result.append("*");
        }
        result.append("\n\n");

        //rows dresses
        for(int i=0; i<size; i++){
            Dress product = products[i];
            result.append(String.format("%-" + idWidth + "d", product.getId()));
            result.append(String.format("%-" + nameWidth + "s", product.getName()));
            result.append(String.format("%-" + descriptionWidth + "s", product.getDescription()));
            result.append(String.format("%-" + priceWidth + ".2f", product.getPrice()));
            result.append(String.format("%-" + stockWidth + "d", product.getStockCount()));
            result.append("\n");
        }
       return result.toString();
    }  

    private void resizeArray(){
        int newCapacity = products.length * 2;
        Dress[] newProducts = new Dress[newCapacity];
        System.arraycopy(products, 0, newProducts, 0, size);
        products = newProducts;
    }

    public Dress getProduct(int index){
        if(index<0 || index>=size){
          throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
       return products[index];
    }
}
