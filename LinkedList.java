//Nichakul Kongnual 6588178 Sec2
package ecommerce;

import ecommerce.product.Dress;
import java.util.HashSet;
import java.util.Set;


public class LinkedList{ //User Cart Management
    private Node head;
    private int size;
    private Set<Integer> purchasedDressIds; 
    // Keep track of purchased dress IDs
    
    public LinkedList(){
        head = null;
        size = 0;
        purchasedDressIds = new HashSet<>();
    }

    public void removeProduct(int productId){
        if(head == null){
          System.out.println("Cart is empty.");
           return;
        }
        if(head.getProduct().getId()==productId){
          head = head.getNext();
          size--;
           return;
        }
        Node current = head;
        Node prev = null;
        while(current!=null && current.getProduct().getId()!=productId){
          prev = current;
          current = current.getNext();
        }
        if(current == null){
          System.out.println("Dress with ID " + productId + " not found in the cart.");
           return;
        }
        	prev.setNext(current.getNext());
        	size--;
    }

    public void displayCart(){
        if(head == null){
          System.out.println("Cart is empty:(");
        }else{
           double total = 0.0;
           Node current = head;
           while (current != null) {
             Dress product = current.getProduct();
             System.out.println(product.getName() + " - Price: ฿" + product.getPrice());
             total += product.getPrice();
             current = current.getNext();
           }
            //System.out.println("Total Price: $" + total);
        }
    }

    public int getSize(){
        return size;
    }
    
    public void clear(){
        head = null;
        size = 0;
    }
    
    public Node getHead(){
        return head;
    }
    
    public double calculateTotal(){
       double total =0.0;
       Node current =head;
        while(current != null){
           Dress product = current.getProduct();
           total += product.getPrice();
           current = current.getNext();
        }
       return total;
    }
    
    public void reduceStockCount(){
        Node current=head;
        while(current != null){
           Dress product = current.getProduct();
           product.decreaseStockCount();
           current = current.getNext();
        }
    }
    
    public Dress findProductById(int productId){
        Node current = head;
        while (current != null){
            Dress product = current.getProduct();
            if (product.getId() == productId) {
                return product;
            }
            current = current.getNext();
        }
        return null; //specified ID not found
    }
    
    public boolean contains(int productId){
        Node current = head;
        while (current != null){
            Dress product = current.getProduct();
            if (product.getId() == productId){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    public void addProduct(Dress product){
        Node newNode = new Node(product);
        if(head == null){
            head = newNode;
        }else{
            Node current = head;
            while(current.getNext()!=null){
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    
    public void addToCart(int productId){
        Dress product = findProductById(productId);
        if(product!=null){
         if(purchasedDressIds.contains(productId)){
            System.out.println("You already have a dress with ID " + productId + " in your cart.");
         }else{
            int stockCount = product.getStockCount();
            if(stockCount > 0){
              Node newNode = new Node(product);
                if(head == null){
                   head = newNode;
                }else{
                   Node current = head;
                     while(current.getNext()!=null){
                        current = current.getNext();
                     }
                   current.setNext(newNode);
                 }
              size++;
       purchasedDressIds.add(productId); // Mark the dress as purchased
           product.decreaseStockCount();
           System.out.println("Added " + product.getName() + " to your cart.");
       }else{
           System.out.println("Sorry, " + product.getName() + " is out of stock:(");
        }
          }
        }else{
            System.out.println("Dress with ID: " + productId + " not found, we only have ID: 1-14");
        }
    }
}