//Nichakul Kongnual 6588178 Sec2
//String filePath = "/Users/pwarkchii/Desktop/java/PRODUCT.csv"; in interface class
//Aj please change filePath before run
package ecommerce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import ecommerce.manage.DressStore;
import ecommerce.product.Dress;

public class Interface{
    private static DressStore dressStore;
    private static Scanner scanner;
    
    public static void main(String[] args){
      Interface ecommerceInterface = new Interface();
      ecommerceInterface.run();
    }

    public void run(){
        dressStore = new DressStore();
        scanner = new Scanner(System.in);
        
        //Get the user's name
        System.out.println("Please enter your name: ");
        String username = scanner.nextLine();
        System.out.println("HELLO "+ username + "!, WELCOME TO PWARKSTORE ><");
        //change before run
        String filePath = "/Users/pwarkchii/Desktop/java/PJ6588178/src/PRODUCT.csv";
        importProductsFromCSV(filePath);

        while(true){
            System.out.println("\n-PLEASE CHOOSE OPTION THAT U WANT-\n");
            //System.out.println("..............................................");
            System.out.println("Option1 : Our available dresses");
            System.out.println("Option2 : Add Dress to your Cart.");
            System.out.println("Option3 : Remove Dress from your Cart.");
            System.out.println("Option4 : Display Cart");
            System.out.println("Option5 : Checkout");
            System.out.println("Option6 : Exit");
            System.out.println("..............................................");

            int op= getUserChoice();
            switch(op){
                case 1:
                    dressStore.displayAvailableProducts();
                    break;
                case 2:
                    System.out.print("Enter Dress ID to add to cart: ");
                    int dressId = getUserChoice();
                    dressStore.addToCart(dressId);
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    dressStore.displayCart();
                    break;
                case 5:
                    dressStore.checkout();
                    break;
                case 6:
                    System.out.println("Thank you for shopping.\n"
                            + "Please come back to support us again :)\n"
                            + "\n");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option, Pls try again :)");
            }
        }
    }
    
    private static int getUserChoice(){
        int choice;
        while(true){
            System.out.print("Press: ");
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            }else{
                System.out.println("Invalid input, Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private void removeFromCart(){
        System.out.print("Enter Dress ID to remove from cart: ");
        int dressId = getUserChoice();
        dressStore.removeFromCart(dressId);
    }
    
    public static void importProductsFromCSV(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while((line = reader.readLine())!=null){
                if(skipHeader){
                   skipHeader = false;
                    continue;
                }         
             String[] parts = line.split(",");
               if(parts.length == 5){
                 try{
                   int id = Integer.parseInt(parts[0]);
                   String name = parts[1];
                   String description = parts[2];
                   double price = Double.parseDouble(parts[3]);
                   int stockCount = Integer.parseInt(parts[4]);
                        
                     Dress product = new Dress(id, name, description, price, stockCount);
                     dressStore.addProductToStore(product);
                   }catch(NumberFormatException e){
                      System.out.println("Invalid numeric format in CSV: " + line);
                   }
                }else{
                   System.out.println("Invalid CSV format: " + line);
                }
            }
        }catch(IOException e){
           System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
