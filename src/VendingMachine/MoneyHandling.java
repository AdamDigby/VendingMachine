package VendingMachine;
import java.util.InputMismatchException;
import java.util.Scanner;
public class MoneyHandling {
    public static Float moneyValidator(Scanner scanner) {
        System.out.println("How much money(in £) do you want to deposit?");

        Float money;
        while (true) {

            if (scanner.hasNextFloat()) {
                money = scanner.nextFloat();
                if (money < 0) {
                    System.out.println("You cannot insert negative money.");
                    continue;
                }
                return money;
            }else {
                System.out.println("Please enter a number.");
                scanner.next();
            }
        }


    }

}
