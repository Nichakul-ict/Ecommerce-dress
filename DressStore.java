//Nichakul Kongnual 6588178 Sec2
package ecommerce.manage;

import ecommerce.LinkedList;
import ecommerce.Node;
import ecommerce.ArrayList;
import ecommerce.product.Dress;
import java.util.HashMap;
import java.util.Map;

public class DressStore{
    private ArrayList<Dress> productList; 
    private LinkedList cart;
    private Map<Integer, Dress> productMap;

    public DressStore(){
    	//capacity dresses
        productList = new ArrayList<>(200);
        cart = new LinkedList();
        productMap = new HashMap<>();
    }
    
    public ArrayList<Dress>getProductList(){
    	return productList;
    }
    
    public void addProductToStore(Dress product){
        productList.addProduct(product); // Use the ProductList to manage available products
        productMap.put(product.getId(), product);
    }

    //show our dresses in stock case1
    public void displayAvailableProducts(){
        System.out.println("\nAvailable dresses in PWARKSTORE :)\n");
        System.out.println(productList.getAllProductsAsString());
    }
    
    public void addToCart(int productId){
        Dress product = findProductById(productId);
        if(product!=null){
            if(!cart.contains(productId)){ //Check if the dress is already in the cart
                int initialStockCount = product.getStockCount();
                if (initialStockCount > 0){
                    cart.addProduct(product); //Add a single dress to the cart
                    product.decreaseStockCount();
                    int updatedStockCount = product.getStockCount();
                    System.out.println("Now the " + product.getName() + " is in your cart.");
                    System.out.println("Stock count before adding to cart: " + initialStockCount);
                    System.out.println("Updated stock count: " + updatedStockCount);
                }else{
                  System.out.println("Sorry, " + product.getName() + " is out of stock:(");
                }
            }else{ //condition only 1 id dress in 1 cart
              System.out.println("You have already purchased " + product.getName() + ",You can't add more to your cart:(");
            }
        }else{
            System.out.println("Dress with ID: " + productId + " not found, we only have ID: 1-14");
        }
    }

    public void removeFromCart(int productId){ //case 3 remove
        cart.removeProduct(productId);
    }
    
    public void displayCart(){
    	System.out.println("==============================================");
        System.out.println("Items in Cart:");
        System.out.println("==============================================");
        Node current = cart.getHead();
        while (current != null) {
            Dress dress = current.getProduct();
            System.out.println("(ID: " + dress.getId() + ") " + dress.getName() + "- Price: ฿" + dress.getPrice());
            current = current.getNext();
            System.out.println("----------------------------------------------");
        }
    }
    
    public void checkout(){
        double total = 0.0;
        System.out.println("==============================================");
        System.out.println("Checkout Summary:");
        System.out.println("----------------------------------------------");
        	Node current = cart.getHead();
        while(current != null){
            Dress dress = current.getProduct();
            System.out.println("(ID: " + dress.getId() + ") " + dress.getName() + " - Price: ฿" + dress.getPrice());
            total += dress.getPrice();
            current = current.getNext();
        }
        System.out.println("----------------------------------------------");
        System.out.printf("Total Price: ฿%.2f%n", total); // Format total to two decimal places
        System.out.println("==============================================");

        cart.reduceStockCount(); //reduce stock count
        cart.clear();
    }

    private Dress findProductById(int productId){
        return productMap.get(productId); //map to find products by ID
    }
}





