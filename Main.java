import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.IntStream;

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
    System.out.print("\nMenu\n");
    System.out.println("[1] Set current date");
    System.out.println("[2] Add item");
    System.out.println("[3] Remove item");
    System.out.println("[4] List all items");
    System.out.println("[5] Manage needs");
    System.out.println("[-1] To exit");
    System.out.print("Enter your option: ");
  }

  public static void printHeader() {
    System.out.println("| Id  |    Name     |  Price   |  Exp. Date  |");
    System.out.println("|-----+-------------+----------+-------------|");
  }

	/**
	 * Prints the header for the must have menu (no expiration dates shown).
	 *
	 * return void
	 */
	public static void printMustHaveHeader() {
  	System.out.println("| Id  |    Name     |  Price   |");
  	System.out.println("|-----+-------------+----------|");
  }

  /**
   * Creates an array of the IDs in the ItemList passed in.
   *
   * @param itemList ItemList to get array IDs of.
   * @return int[]
   */
  public static int[] getArrayOfIDS( ItemList itemList ) {
		int numberOfItems = itemList.getNumberOfItems();
		int[] arrayOfIds = new int[numberOfItems];

		for ( int c = 0; c < numberOfItems; c++ ) {
			arrayOfIds[c] = c;
		}

		return arrayOfIds;
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
      } finally {
	      input.nextLine();
      }
    } while ( ! priceSet );

    return price;
  }

  /**
   * Returns an integer that the user inputs.
   *
   * @return int
   */
  public static int getIntFromUser() {
    Scanner input = new Scanner( System.in );
    boolean intSet = false;
    int singleInt = 0;

    do {
      try {
        singleInt = input.nextInt();
        intSet = true;
      } catch ( Exception e ) {
        System.out.println( "That doesn't seem right. Try again." );
      } finally {
	      input.nextLine();
      }

    } while ( ! intSet );

    return singleInt;
  }

  public static void main(String[] args) {
    int option;
    int option2;
    int option3;
    int option4;
    String name;
    double price;
    IntStream intStream;
    boolean isInList;
    Date date = new Date();
    Date dateToCompareWith = new Date();
    ItemList ItemsInFridge = new ItemList();
    ItemList mustHaveItemList = new ItemList();

    Scanner input = new Scanner(System.in);

    do {
      printMenu();
      option = getIntFromUser();
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
            option2 = getIntFromUser();
            switch (option2) {
              case 1:
                Item addedItem = new Item();
                System.out.print("\tName: ");
                name = input.nextLine().toLowerCase();
                addedItem.setName( name );
                addedItem.setDate(date);
                System.out.print("\tPrice: ");
                price = getDoubleFromUser();
                addedItem.setPrice( price );
                ItemsInFridge.addItem(addedItem);
                System.out.print( "\tIs this item a must have? (1) yes and any other number for no: " );
				if ( getIntFromUser() == 1 ) {
					Item tmpItem = new Item( date, name, price );
					mustHaveItemList.addItem( tmpItem );
				} else {
					System.out.println( "Item won't be added to the must have list." );
				}
                break;
              case 2:
                PerishableItem perishable = new PerishableItem();
                System.out.print("\tName: ");
                name = input.nextLine().toLowerCase();
                perishable.setName( name );
                perishable.setDate(date);
                System.out.print("\tPrice: ");
                price = getDoubleFromUser();
                perishable.setPrice( price );
                System.out.print("\tExpiration date. ");
                Date expDate = setFormattedDate();
                perishable.setExpirationDate(expDate);
                perishable.setLeftover(false);
                ItemsInFridge.addItem(perishable);
                System.out.print( "\tIs this item a must have? (1) yes and any other number for no: " );
                  if ( getIntFromUser() == 1 ) {
		              Item tmpItem = new Item( date, name, price );
		              mustHaveItemList.addItem( tmpItem );
	              } else {
		              System.out.println( "Item won't be added to the must have list." );
	              }
                break;
              case 3:
                PerishableItem leftover = new PerishableItem();
                System.out.print("\tName: ");
                leftover.setName(input.nextLine().toLowerCase());
                leftover.setDate(date);
                System.out.print("\tPrice: ");
                leftover.setPrice( getDoubleFromUser() );
                System.out.print("\tHow many days can this stay in the fridge: ");
                leftover.setExpirationDate( getIntFromUser() );
                leftover.setLeftover(true);
                ItemsInFridge.addItem(leftover);
                break;
            }

          } while (option2 != -1);
          break;
        case 3:
          System.out.print("\nItems in the fridge\n");
          printHeader();
          System.out.print(ItemsInFridge);
          System.out.print("\n\nEnter the ID of the item you'd like to delete: ");
          int deletedIndex = getIntFromUser();
          intStream = IntStream.of( getArrayOfIDS( ItemsInFridge ) );
          isInList = intStream.anyMatch( x -> x == deletedIndex );
          if (deletedIndex != -1 && isInList ) {
	          ItemsInFridge.removeItem(deletedIndex);
          }
          break;

        case 4:
          System.out.print("\nItems in the fridge\n");
          printHeader();
          System.out.print(ItemsInFridge);
          break;

        case 5:
          do {
            System.out.print("\n\t-->Manage needs\n");
            System.out.println("\t[1] Report spoiled and questionable items");
            System.out.println("\t[2] Manage “must have” items");
            System.out.println("\t[3] Generate grocery list");
            System.out.println("\t[-1] Back to the main menu");
            option3 = getIntFromUser();
            switch (option3) {
              case 1:
                System.out.print("\tWhat is the current date? ");
                dateToCompareWith = setFormattedDate();
                System.out.print("\tList of spoiled and questionable items\n");
                ItemList spoiledItems = ItemsInFridge.getExpired(dateToCompareWith);
                printHeader();
                System.out.print(spoiledItems);
                System.out.printf(
                    "\t\nThe total of wasted money: %.2f\n", spoiledItems.getTotalCost());
                break;
              case 2:
                do {
                  System.out.print("\n\t\t-->--> Manage must have list\n ");
                  System.out.println("\t\t[1] Add to must have list");
                  System.out.println("\t\t[2] Show must have list");
                  System.out.println("\t\t[3] Remove from must have list");
                  System.out.println("\t\t[-1] Back to the main menu");
                  option4 = getIntFromUser();
                  switch (option4) {
                    case 1:
                      Item mustHaveItem = new Item();
                      System.out.print("\t\tName: ");
                      name = input.nextLine().toLowerCase();
                      mustHaveItem.setName(name);
                      System.out.print("\t\tPrice: ");
                      price = getDoubleFromUser();
                      mustHaveItem.setPrice(price);
                      mustHaveItemList.addItem(mustHaveItem);
                      break;
                    case 2:
                      System.out.print("\n\t\tMust Have list\n");
                      printMustHaveHeader();
                      System.out.print(mustHaveItemList);
                      break;
                    case 3:
                      System.out.print("\n\t\tMust Have list\n");
                      printHeader();
                      System.out.print(mustHaveItemList);
                      System.out.print(
                          "\n\t\tEnter the ID for the item you'd like to delete: ");
                      int deletedMustHave = getIntFromUser();
                      intStream = IntStream.of( getArrayOfIDS( mustHaveItemList ) );
                      isInList = intStream.anyMatch( x -> x == deletedMustHave );
                      if (deletedMustHave != -1 && isInList ) {
                        mustHaveItemList.removeItem(deletedMustHave);
                      }
                      break;

                    default:
                      System.out.println( "Oops, something went wrong. Try again!" );
                      break;
                  }
                } while (option4 != -1);
                break;

              case 3:
              	System.out.print("\tWhat is the current date? ");
              	dateToCompareWith = setFormattedDate();
                System.out.print("\n\tMust have item list\n");
                printMustHaveHeader();

                ItemList haveInFridge = ItemsInFridge.gethaveInFridge(mustHaveItemList);
                System.out.println(haveInFridge);

                System.out.print("\n\t Grocery list (to buy list)\n");
                printHeader();
                ItemList shoppingList = ItemsInFridge.getShoppingList(mustHaveItemList, dateToCompareWith );
                System.out.println(shoppingList);
                System.out.printf("The total: %.2f\n", shoppingList.getTotalCost());

                break;

              default:
                System.out.println( "Oops, something went wrong2. Try again!" );
                break;
            }
          } while (option3 != -1);

        default:
          break;
      }
    } while (option != -1);
    System.out.println("Thank you, bye ");
  }
}
