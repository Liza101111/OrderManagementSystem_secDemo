package menu;

import model.OrderLine;
import model.Product;
import persistence.RepositoryOrderLine;

import java.util.List;
import java.util.Scanner;

public class MenuOrderLine {
    RepositoryOrderLine repositoryOrderLine = new RepositoryOrderLine();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Change quantity");
        System.out.println("2: List all orderLines");
        System.out.println("3: ");
        System.out.println("4: ");
        System.out.println("5: ");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }


    protected void menuChoice(Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    menuUpdateQuantity(input);
                    break;
                case 2:
                    menuListAllOrderLines(input);
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }
        } while (userChoice != 100);
    }
    private void menuUpdateQuantity(Scanner input){
        System.out.println("Type orderLine id:");
        long id =input.nextLong();
        OrderLine orderLine = repositoryOrderLine.findID(id);
        System.out.println("Type the quantity: ");
        long quantity = input.nextLong();
        orderLine.setQuantity(quantity);
        repositoryOrderLine.updateQuantity(orderLine);
        System.out.println("The quantity of product changed!");

    }

    private void menuListAllOrderLines(Scanner input) {
        List<OrderLine> orderLineList = repositoryOrderLine.listAllOrderLines();
        if(orderLineList.size() > 0){
            System.out.println("\nList of orderLines:");
            for(OrderLine orderLine : orderLineList) {
                System.out.println(orderLine.toString());
            }
        } else{
            System.out.println("\nNo orderLines registered!\n");
            menuOptions(input);
        }
    }
}
