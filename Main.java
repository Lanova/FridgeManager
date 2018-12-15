
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

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
		Date date;
		Date expirationDate;
		String name;
		double price;
		ItemList ItemsInFridge = new ItemList( "Stadard List" ); //= new Item[30];
		ItemList mustHaveItems = new ItemList( "Must have items" );

		Scanner input = new Scanner(System.in);
		date = setFormattedDate( "12/28/2018" );
		expirationDate = setFormattedDate( "01/02/2019" );
		Date systemDate = setFormattedDate( "01/10/2019" );
		name = "Bread";
		price = 4.25;

		String name2 = "Milk";
		String name3 = "Water";

		Item addedItem = new Item( date, name, price );
		PerishableItem perishableItem = new PerishableItem( date, name, price, expirationDate );

		Item addedItem2 = new Item( date, name, price );
		PerishableItem perishableItem2 = new PerishableItem( date, name, price, 7 );

		Item addedItem3 = new Item( date, name2, price );

		Item addedItem4 = new Item( date, name3, price );

		System.out.println( perishableItem );

		System.out.println( perishableItem2 );

		System.out.println( getFormattedDate( systemDate ) );

		if ( perishableItem.isExpired( systemDate ) ) {
			System.out.println( "Checks if the item is expired." );
			System.out.println( perishableItem.getName() + " is expired but not a leftover." );
		} else if ( perishableItem.isSpoiled( systemDate ) ) {
			System.out.println( "Checks if the item is expired and a leftover." );
			System.out.println( perishableItem.getName() + " is expired and a leftover." );
		} else {
			System.out.println( perishableItem.getName() + " is still good!" );
		}

		if ( perishableItem2.isExpired( systemDate ) ) {
			System.out.println( "Checks if the item is expired." );
			System.out.println( perishableItem2.getName() + " is expired but not a leftover." );
		} else if ( perishableItem2.isSpoiled( systemDate ) ) {
			System.out.println( "Checks if the item is expired and a leftover." );
			System.out.println( perishableItem2.getName() + " is expired and a leftover." );
		} else {
			System.out.println( perishableItem2.getName() + " is still good!" );
		}

		if ( perishableItem.equals( perishableItem2 ) ) {
			System.out.println( "The items are the same." );
		} else {
			System.out.println( "The items are not the same." );
		}

		System.out.println( "\n\n\n\n\n" );

		ItemsInFridge.addItem( addedItem );
		ItemsInFridge.addItem( addedItem2 );
		ItemsInFridge.addItem( perishableItem );
		ItemsInFridge.addItem( perishableItem2 );

		mustHaveItems.addItem( addedItem );
		mustHaveItems.addItem( addedItem3 );
		mustHaveItems.addItem( addedItem4 );
		mustHaveItems.addItem( perishableItem );

		ItemList perishableItems = ItemsInFridge.getPerishables();

		System.out.println( "Items in fridge:\n" + ItemsInFridge );

		System.out.println( "Perishable items:\n" + perishableItems );
		System.out.println( "Number of perishables is " + perishableItems.getNumberOfItems() );

		System.out.println( "Must have items:\n" + mustHaveItems );
		ItemList shoppingList = ItemsInFridge.getShoppingList( mustHaveItems );

		System.out.println( "Shopping List:\n" + shoppingList );

		System.out.println( "\n\n\n\n\n" );
		System.out.println( "The total cost of the shopping list is " + shoppingList.getTotalCost() );

	}
}
