//Nichakul Kongnual 6588178 Sec2
package ecommerce;

import ecommerce.product.Dress;

public class Node{
	private Dress product;
    private Node next;

    public Node(Dress product){
        this.product = product;
        this.next = null;
    }

    public Dress getProduct(){
        return product;
    }

    public void setProduct(Dress product){
        this.product = product;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }
}
