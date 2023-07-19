package VendingMachine;
import java.util.Objects;
import java.util.Scanner;
public class VendingMachine {

    public static String floatMoneyConvert(Float money) {
        if (money < 1) {
            return Math.round(money * 100) + "p";

        } else if (money - Math.round(money) == 0) {

            return "£" + Math.round(money);

        } else {
            return "£" + String.format("%.2f", money);
        }
    }
    public static void displayVendingMachine(String username, Float userBal) {

        System.out.println("\n--------------------");
        System.out.println(username);
        System.out.println("Bal: " + floatMoneyConvert(userBal));

    }
    public static void displayPrices(){
        System.out.println("\nItems:");

        for (Products i:Products.values()) {
            System.out.println(i.getName() + " " + floatMoneyConvert(i.getPrice()));
        }
    }
    public static Boolean purchase(Float userBal, Products product) {
        if (userBal < product.getPrice()) {
            // Out of money
            System.out.println("Sorry not enough money");
            return false;
        }
        if (!Stock.isAvailable(product)) {
            // Not in stock
            System.out.println("Sorry not in stock");
            return false;
        }
        return true;

    }
    public static void restockMode(Scanner inputScanner, String name, Float userBal){
        VendingMachine.displayVendingMachine(name, userBal);
        Stock.display();
        System.out.println("Enter purchase to purchase items.");
        System.out.println("What would you like to restock?");
        String item = inputScanner.next();
        if (Objects.equals(item.toUpperCase(), "PURCHASE")) {
            purchaseMode(inputScanner, name, userBal);
        }
        System.out.println("And how much would you like to restock?");
        int amount = inputScanner.nextInt();
        System.out.println("restock mode checkpoint");
        for (Products i:Products.values()) {
            if (Objects.equals(i.getName().toUpperCase(), item.toUpperCase())) {
                Stock.restock(i, amount);
                restockMode(inputScanner, name, userBal);
            }
        }


    }
    public static void purchaseMode(Scanner inputScanner, String name, Float userBal) {
//        while (true) {
//
//            if (!inputScanner.hasNext()) {System.out.println("Broken");break;}
//            System.out.println("Hello, from the other side!");
//            System.out.println(inputScanner.nextLine());
//            System.out.println(inputScanner.nextLine());
//            System.out.println("Finished inputting");
//        }
        // Display vending machine in purchase mode.
        VendingMachine.displayVendingMachine(name, userBal);
        VendingMachine.displayPrices();
        System.out.println("\nTo restock: enter restock.");
        System.out.println("What would you like to purchase?");
        String requestedItem = inputScanner.next();
        System.out.println(requestedItem);
        // Check for special cases.
        switch (requestedItem.toUpperCase()) {
            case "RESTOCK" -> restockMode(inputScanner, name, userBal);
            case "DEPOSIT" -> userBal += MoneyHandling.moneyValidator(inputScanner);
            case "QUIT" -> System.exit(0);
        }

        // If not a special case, check if it is a product that can be purchased.
        for (Products i:Products.values()) {
            if (Objects.equals(i.getName().toUpperCase(), requestedItem.toUpperCase())){
                if(purchase(userBal, i)){
                    userBal -= i.getPrice();
                    Stock.stockPurchase(i);
                    }
                }
            }
        // Afterwards do it again.
        purchaseMode(inputScanner, name, userBal);
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Stock.stockFill();

        System.out.println("Please enter your name.");
        String name = inputScanner.next();
        Float userBal = MoneyHandling.moneyValidator(inputScanner);

        purchaseMode(inputScanner, name, userBal);

    }
}

