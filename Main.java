import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

  /**
   * Set the formatted date defined by user input.
   *
   * @return Date
   */
  public static Date setFormattedDate() {
    Scanner input = new Scanner(System.in);
    Date date = new Date();
    boolean isValid = false;
    String expectedPattern = "MM/dd/yyyy";
    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
    formatter.applyPattern(expectedPattern);
    formatter.setLenient(false);

    do {
      System.out.print("Please enter the date in mm/dd/yyyy format: ");
      String userDate = input.nextLine();
      try {
        date = formatter.parse(userDate);
        isValid = true;
      } catch (ParseException e) {
        System.out.println("That date isn't correct. Please try again.");
      }
    } while (!isValid);

    return date;
  }

  /**
   * Sets the formatted date using a string.
   *
   * @param userDate (maybe) String of text in the MM/dd/yyyy format.
   * @return Date
   */
  public static Date setFormattedDate(String userDate) {
    Scanner input = new Scanner(System.in);
    Date date = new Date();
    boolean isValid = false;
    String expectedPattern = "MM/dd/yyyy";
    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
    formatter.applyPattern(expectedPattern);
    formatter.setLenient(false);

    do {
      try {
        date = formatter.parse(userDate);
        isValid = true;
      } catch (ParseException e) {
        System.out.println("That date isn't correct. Please try again.");
      }
    } while (!isValid);

    return date;
  }

  /**
   * Returns a date passed in formatted to "MM/dd/yyyy".
   *
   * @param date Date to format.
   * @return String
   */
  public static String getFormattedDate(Date date) {
    String expectedPattern = "MM/dd/yyyy";
    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
    formatter.applyPattern("MM/dd/yyyy");
    formatter.setLenient(false);

    String formattedDate = formatter.format(date);

    return formattedDate;
  }

  public static void printMenu() {
    System.out.printf("\nMenu\n");
    System.out.println("[1] Set current date");
    System.out.println("[2] Add item");
    System.out.println("[3] Remove item");
    System.out.println("[4] Manage needs");
    System.out.println("[-1] To exit");
    System.out.print("Enter your option: ");
  }

  public static void printHeader() {
    System.out.println("| Id  |    Name     |  Price   |  Exp. Date  |");
    System.out.println("|-----+-------------+----------+-------------|");
  }

  /**
   * Asks the user for a double and checks that the input is correct.
   *
   * @return double
   */
  public static double getDoubleFromUser() {
    Scanner input = new Scanner( System.in );
    boolean priceSet = false;
    double price = 0.00;
    do {
      try {
        price = input.nextDouble();
        priceSet = true;
      } catch ( Exception e ) {
        System.out.println( "That doesn't seem right. Try again." );
      }
    } while ( ! priceSet );

    return price;
  }

  public static void main(String[] args) {
    int option;
    int option2;
    int option3;
    int option4;
    int numberOfItems = 0;
    boolean priceSet;
    Date date = new Date();
    ItemList ItemsInFridge = new ItemList();
    ItemList mustHaveItemList = new ItemList();

    Scanner input = new Scanner(System.in);

    do {
      printMenu();
      option = input.nextInt();
      input.nextLine();
      switch (option) {
        case 1:
          date = setFormattedDate();
          break;
        case 2:
          do {
            System.out.printf("\n\t-->Add Item \n");
            System.out.print("\tWhat type of items would you like to add into the fridge?\n");
            System.out.println("\t[1] Item without expiration date");
            System.out.println("\t[2] Perishable");
            System.out.println("\t[3] Leftover item");
            System.out.println("\t[-1]  Back to the main menu");
            System.out.print("\tEnter your option: ");
            option2 = input.nextInt();
            input.nextLine();
            switch (option2) {
              case 1:
                Item addedItem = new Item();
                System.out.print("\tName: ");
                addedItem.setName(input.nextLine().toUpperCase());
                addedItem.setDate(date);
                System.out.print("\tPrice: ");
                addedItem.setPrice( getDoubleFromUser() );
                ItemsInFridge.addItem(addedItem);
                numberOfItems++;
                break;
              case 2:
                PerishableItem perishable = new PerishableItem();
                System.out.print("\tName: ");
                perishable.setName(input.nextLine().toLowerCase());
                perishable.setDate(date);
                System.out.print("\tPrice: ");
                perishable.setPrice( getDoubleFromUser() );
                System.out.print("\tExpiration date. ");
                Date expDate = setFormattedDate();
                perishable.setExpirationDate(expDate);
                perishable.setLeftover(false);
                ItemsInFridge.addItem(perishable);
                numberOfItems++;
                break;
              case 3:
                PerishableItem leftover = new PerishableItem();
                System.out.print("\tName: ");
                leftover.setName(input.nextLine().toLowerCase());
                leftover.setDate(date);
                System.out.print("\tPrice: ");
                leftover.setPrice( getDoubleFromUser() );
                System.out.print("\tHow many days can this stay in the fridge: ");
                leftover.setExpirationDate(input.nextInt());
                leftover.setLeftover(true);
                ItemsInFridge.addItem(leftover);
                numberOfItems++;
                break;
            }

          } while (option2 != -1);
          break;
        case 3:
          System.out.printf("\nItems in the fridge\n");
          printHeader();
          System.out.print(ItemsInFridge);
          System.out.print("\n\nWhat item you'd like to delete, please etner the ID: ");
          int deletedeIndex = input.nextInt();
          if (deletedeIndex != -1) {
            ItemsInFridge.removeItem(deletedeIndex);
          }
          break;

        default:
          do {
            System.out.printf("\n\t-->Manage needs\n");
            System.out.println("\t[1] Report spoiled and quetionable items");
            System.out.println("\t[2] Manage “must have” items");
            System.out.println("\t[3] Generate grocery list");
            System.out.println("\t[-1]  Back to the main menu");
            option3 = input.nextInt();
            input.nextLine();
            switch (option3) {
              case 1:
                System.out.print("\tWhat the current date? ");
                Date dateToCompereWith = setFormattedDate();
                System.out.print("\tList of spoiled and quetionable items to remove\n");
                ItemList spoiledItems = ItemsInFridge.getExpired(dateToCompereWith);
                printHeader();
                System.out.print(spoiledItems);
                System.out.printf(
                    "\t\nThe total of wasted money: %.2f\n", spoiledItems.getTotalCost());
                break;
              case 2:
                do {
                  System.out.print("\n\t\t-->--> Manage mustHave\n ");
                  System.out.println("\t\t[1] Add to mustHave list");
                  System.out.println("\t\t[2] Show must have list");
                  System.out.println("\t\t[3] Remove from mustHave list");
                  System.out.println("\t\t[-1]  Back to the main menu");
                  option4 = input.nextInt();
                  input.nextLine();
                  switch (option4) {
                    case 1:
                      Item mustHaveItem = new Item();
                      System.out.print("\t\tWhat the name of the mustHave item: ");
                      mustHaveItem.setName(input.nextLine().toLowerCase());
                      System.out.print("\t\tWhat the approximate price: ");
                      mustHaveItem.setPrice(input.nextDouble());
                      mustHaveItemList.addItem(mustHaveItem);
                      numberOfItems++;
                      break;
                    case 2:
                      System.out.printf("\n\t\tMustHave list\n");
                      printHeader();
                      System.out.print(mustHaveItemList);
                      break;
                    case 3:
                      System.out.printf("\n\t\tMustHave list\n");
                      printHeader();
                      System.out.print(mustHaveItemList);
                      System.out.print(
                          "\n\t\tWhat item you'd like to delete, please etner the ID: ");
                      int deletedMustHave = input.nextInt();
                      if (deletedMustHave != -1) {
                        mustHaveItemList.removeItem(deletedMustHave);
                      }
                      break;
                  }
                } while (option4 != -1);
                break;

              case 3:
                System.out.printf("\n\t You have mustHave items in the fridge\n");
                printHeader();

                ItemList haveInFridge = ItemsInFridge.gethaveInFridge(mustHaveItemList);
                System.out.println(haveInFridge);

                System.out.printf("\n\t Grocery list (to buy list)\n");
                printHeader();
                ItemList shoppingList = ItemsInFridge.getShoppingList(mustHaveItemList);
                System.out.println(shoppingList);
                System.out.printf("The total: %.2f\n", shoppingList.getTotalCost());

                break;
            }

            break;
          } while (option3 != -1);
      }
    } while (option != -1);
    System.out.println("Thank you, bye ");
  }
}
