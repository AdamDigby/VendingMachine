package VendingMachine;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Stock {

    public static void stockFill() {
        try {
            File stockFile = new File("C:\\Users\\boarda\\IdeaProjects\\AdamsVendingMachine\\src\\VendingMachine\\Stock.txt");
            Scanner stockScanner = new Scanner(stockFile);
            // Take the text file (non-volatile) and input it to the hashmap "stock".
            for (Products i : Products.values()) {
                inventory.put(i, Integer.valueOf(stockScanner.nextLine()));
            }
            stockScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        }
    }
    public static void stockUpdate() {
        try {
            // Taking the file info from the hashmap "stock" and writing it to the text file.
            FileWriter stockWrite = new FileWriter("C:\\Users\\boarda\\IdeaProjects\\AdamsVendingMachine\\src\\VendingMachine\\Stock.txt");
            for (Products i : Products.values()) {
                stockWrite.append(String.valueOf(Stock.inventory.get(i)) + "\n");
            }
            stockWrite.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        } catch (IOException e) {
            System.out.println("ERROR: Could not write.");

        }
    }
    static Map<Products, Integer> inventory = new HashMap<Products, Integer>();

    public static void stockPurchase(Products selectedProduct)
    {
        inventory.put(selectedProduct, (inventory.get(selectedProduct) - 1));
        Stock.stockUpdate();
        System.out.println("You bought a " + selectedProduct.getName() + ".");

    }
    public static void restock(Products selectedProduct, Integer amount){
        inventory.put(selectedProduct, (inventory.get(selectedProduct) + amount));
        System.out.println("restock checkpoint");
        Stock.stockUpdate();
    }

    public static void display(){
        for (Products i:Products.values()) {
            System.out.println(i.getName() + " " + inventory.get(i));
        }
    }
    public static boolean isAvailable(Products product){
        return inventory.get(product) > 0;
    }


}
