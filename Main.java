
import java.util.Scanner;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Main {
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
		// write your code here
		// Connection connection = dbConnect.getConnection(); // Connect to the
		// database.
		// Connection connection = dbConnect.getDBConnectionUsingIam();
		int option;
		int option2;
		int numberOfItems = 0;
		//Item[] ItemsInFridge = new Item[30];
		ItemList ItemsInFridge = new ItemList(); //= new Item[30];
		
		Scanner input = new Scanner(System.in);

		do {
			printMenu();
			option = input.nextInt();
			input.nextLine();
			switch (option) {
			case 1:
				boolean isValid = false;
				do {
					System.out.print("Please enter the date in mm/dd/yyyy format: ");
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					formatter.setLenient(false);

					try {
						// (2) give the formatter a String that matches the SimpleDateFormat pattern
						String current = input.nextLine();

						Date parsedDate = formatter.parse(current);
						String formattedCurrentDate = formatter.format(parsedDate);
						isValid = true;
						System.out.println(formattedCurrentDate);
					} catch (ParseException e) {
						System.out.println("Please enter the valid date\n");
						// execution will come here if the String that is given
						// does not match the expected format.
					}
				} while (!isValid);
				break;
			case 2:

			do {System.out.printf("\nsubMenu Add Item \n");
				System.out.print("What kind of item do you want insert into the fridge\n");
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
					
					System.out.print("What the price:\n ");
					addedItem.setPrice(input.nextDouble());
					//ItemsInFridge[numberOfItems++] = addedItem;
					ItemsInFridge.addItem(addedItem);
						numberOfItems++;
						
					//ItemList itemList = new ItemList(ItemsInFridge);
					

				case 2:
					
				
				break;
				case 3:
				break;}
				}while (option2 !=-1);	
			case 3:

					 System.out.printf("\n\tItems in the fridge\n");
					 System.out.println("| Id  |    Name     |  Price   |  Exp. Date  |");
					 System.out.println("|-----+-------------+----------+-------------|"); //header
					 System.out.print(ItemsInFridge);

					 System.out.print("\nWhat item you'd like to delete, please etner the ID: ");
					 int deletedeIndex = input.nextInt();
					  //input.nextLine();
					ItemsInFridge.removeItem(deletedeIndex);

					  
					break;
			}
		} while (option != -1);
		System.out.println("Thank you, bye ");

	}
}