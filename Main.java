
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	/**
	 * Set the formatted date defined by user input.
	 *
	 * @return Date
	 */
	public static Date setFormattedDate() {
		Scanner input = new Scanner( System.in );
		Date date = new Date();
		boolean isValid = false;
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat( expectedPattern );
		formatter.applyPattern( expectedPattern );
		formatter.setLenient( false );

		do {
			System.out.print( "Please enter the date in mm/dd/yyyy format: " );
			String userDate = input.nextLine();
			try {
				date = formatter.parse( userDate );
				isValid = true;
			} catch ( ParseException e ) {
				System.out.println( "That date isn't correct. Please try again." );
			}
		} while ( ! isValid );

		return date;
	}

	/**
	 * Sets the formatted date using a string.
	 *
	 * @param userDate (maybe) String of text in the MM/dd/yyyy format.
	 * @return Date
	 */
	public static Date setFormattedDate( String userDate ) {
		Scanner input = new Scanner( System.in );
		Date date = new Date();
		boolean isValid = false;
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat( expectedPattern );
		formatter.applyPattern( expectedPattern );
		formatter.setLenient( false );

		do {
			try {
				date = formatter.parse( userDate );
				isValid = true;
			} catch ( ParseException e ) {
				System.out.println( "That date isn't correct. Please try again." );
			}
		} while ( ! isValid );

		return date;
	}

	/**
	 * Returns a date passed in formatted to "MM/dd/yyyy".
	 *
	 * @param date Date to format.
	 * @return String
	 */
	public static String getFormattedDate( Date date ) {
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat( expectedPattern );
		formatter.applyPattern( "MM/dd/yyyy" );
		formatter.setLenient( false );

		String formattedDate = formatter.format( date );

		return formattedDate;
	}

	public static void printMenu() {
		System.out.printf("\nMenu\n");
		System.out.println("[1] Set current date");
		System.out.println("[2] Add item");
		System.out.println("[3] Remove item");
		System.out.println("[4] Manage needs");/** left for the next week */
		System.out.println("[-1] To exit");
		System.out.print("Enter your option: ");
	}

	public static void main(String[] args) {
		int option;
		int option2;
		int option3;
		int numberOfItems = 0;
		Date date = new Date();
		ItemList ItemsInFridge = new ItemList(); 

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

					do {System.out.printf("\nsubMenu Add Item \n");
						System.out.print("What kind of items add into the fridge\n");
						System.out.println("[1] Item without expiration date");
						System.out.println("[2] Perishable");
						System.out.println("[3] Leftover item");
						System.out.println("[-1]  Back to the main menu");
						option2 = input.nextInt();
						input.nextLine();
						switch (option2) {
							case 1:
								Item addedItem = new Item();
								System.out.print("What the name of the item\n ");
								addedItem.setName(input.nextLine());
								addedItem.setDate( date );
								System.out.print("What the price:\n ");
								addedItem.setPrice(input.nextDouble());
								ItemsInFridge.addItem(addedItem);
								numberOfItems++;
								break;
							case 2:
								PerishableItem perishable = new PerishableItem();
								System.out.print("What the name of the item\n ");
								perishable.setName(input.nextLine());
								perishable.setDate( date );
								System.out.print("What the price:\n ");
								perishable.setPrice(input.nextDouble());
								System.out.print("What the expiration date. ");
								Date expDate = setFormattedDate();
								perishable.setExpirationDate(expDate);
								perishable.setLeftover(false);
								ItemsInFridge.addItem(perishable);
								numberOfItems++;
								break;
							case 3:
							PerishableItem leftover = new PerishableItem();
								System.out.print("What the name of the item\n ");
								leftover.setName(input.nextLine());
								leftover.setDate( date );
								System.out.print("What the price:\n ");
								leftover.setPrice(input.nextDouble());
								System.out.print("How many days should stay in the fridge:\n ");
								leftover.setExpirationDate(input.nextInt());
								leftover.setLeftover(true);
								ItemsInFridge.addItem(leftover);
								numberOfItems++;
								break;	}
						
					} while (option2 !=-1);
				case 3:

					System.out.printf("\n\tItems in the fridge\n");
					System.out.println("| Id  |    Name     |  Price   |  Exp. Date  |");
					System.out.println("|-----+-------------+----------+-------------|"); //header
					System.out.print(ItemsInFridge);
					System.out.print("\nWhat item you'd like to delete, please etner the ID: ");
					int deletedeIndex = input.nextInt();
					if(deletedeIndex != -1){
						ItemsInFridge.removeItem(deletedeIndex);
					}
					break;

					default:
					do {System.out.printf("\nsubMenu Manage needs\n");
						System.out.println("[1] Report spoiled and quetionable items");
						System.out.println("[2] Manage “must have” items");
						System.out.println("[3] Generate grocery list");
						System.out.println("[-1]  Back to the main menu");
						option3 = input.nextInt();
						input.nextLine();
						switch (option3) {
							case 1:
							System.out.println("What the current date?");
							Date dateToCompereWith = setFormattedDate();
							System.out.println("List of spoiled and quetionable items");
							System.out.println("Please remove them from fridge");
							ItemList spoiledItems = ItemsInFridge.getExpired(dateToCompereWith);
							System.out.println(spoiledItems);
							System.out.printf("The total of wasted money: %.2f", spoiledItems.getTotalCost());
							break;
							case 2:
							break;
							case 3: 
							break;
						}



						break;
					}while(option3 !=-1);
					
			}
		} while (option != -1);
		System.out.println("Thank you, bye ");
	}
}
