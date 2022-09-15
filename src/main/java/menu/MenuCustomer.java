package menu;

import model.Customer;
import persistence.RepositoryCustomer;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuCustomer {
    RepositoryCustomer repositoryCustomer = new RepositoryCustomer();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create customer");
        System.out.println("2: List all customer");
        System.out.println("3: Update customer");
        System.out.println("4: Delete customer");
        System.out.println("5: Find customer by id");
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
                    menuCreateCustomer(input);
                    break;
                case 2:
                    menuListAllCustomers(input);
                    break;
                case 3:
                    menuUpdateCustomer(input);
                    break;
                case 4:
                    menuDeleteCustomer(input);
                    break;
                case 5:
                    menuFindCustomerById(input);
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
    private void menuCreateCustomer(Scanner input){
        System.out.println("Type firstName: ");
        String firstName = input.next();

        System.out.println("Type lastName: ");
        String lastName = input.next();

        System.out.println("Type phoneNum: ");
        String phoneNum = input.next();

        System.out.println("Type email: ");
        String email = input.next();

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNum(phoneNum);
        customer.setEmail(email);
        repositoryCustomer.createCustomer(customer);
        System.out.println("New customer created!");
        System.out.println(customer.toString());


    }

    private void menuListAllCustomers(Scanner input){
        List<Customer> customerList = repositoryCustomer.listAllCustomers();
        if(customerList.size() > 0){
            System.out.println("\nList of customers:");
            for(Customer customer : customerList) {
                System.out.println(customer.toString());
            }
        } else{
            System.out.println("\nNo customers registered!\n");
            menuOptions(input);
        }

    }

    private void menuUpdateCustomer(Scanner input) {

        System.out.println("Type customer id:");
        long id = input.nextLong();
        Customer customer = repositoryCustomer.findID(id);
        System.out.println("Type customer firstName:");
        String firstName = input.next();

        System.out.println("Type lastName: ");
        String lastName = input.next();

        System.out.println("Type phoneNum: ");
        String phoneNum = input.next();

        System.out.println("Type email: ");
        String email = input.next();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNum(phoneNum);
        customer.setEmail(email);

        repositoryCustomer.updateCustomer(customer);
        System.out.println("customer " + id + " updated!");
    }

    public void menuDeleteCustomer(Scanner input) {
        try {
            System.out.println("Type customer id:");
            long id = input.nextLong();

            Customer customer = new Customer();
            customer.setId(id);

            repositoryCustomer.deleteCustomer(customer);
            System.out.println("customer " + id + " deleted!");

        } catch (NoResultException e) {
            System.out.println("\nNo customers registered with inserted Id\n");
            menuOptions(input);
        }
    }

    public void menuFindCustomerById(Scanner input){
        System.out.println("Type customer id: ");
        long id = input.nextLong();
        Customer customer = repositoryCustomer.findCustomerById(id);
        System.out.println(customer.toString());

    }


}
