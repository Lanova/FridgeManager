import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
	private Date date;
	private int id;
	private String name;
	private double price;

	/**
	 * Default constructor.
	 */
	public Item() {
		id    = -1;
		name  = "";
		price = 0.00;
		date  = new Date();
	}

	/**
	 * Constructor that sets the name and the price.
	 *
	 * @param name Name of the item.
	 * @param price Price of the item.
	 */
	public Item( String name, double price ) {
		id         = -1;
		date       = new Date();
		this.name  = name;
		this.price = price;
	}

	/**
	 * Constructor that sets the ID, name, and price.
	 *
	 * @param id ID of the item.
	 * @param name Name of the item.
	 * @param price Price of the item.
	 */
	public Item( int id, String name, double price ) {
		this.id    = id;
		date       = new Date();
		this.name  = name;
		this.price = price;
	}

	/**
	 * Constructor that sets all of the properties of the item.
	 *
	 * @param id ID of the item.
	 * @param date Date of the item.
	 * @param name Name of the item.
	 * @param price Price of the item.
	 */
	public Item( int id, Date date, String name, double price ) {
		this.id    = id;
		this.date  = date;
		this.name  = name;
		this.price = price;
	}

	/**
	 * Copy constructor.
	 *
	 * @param item Item to be copied.
	 */
	public Item( Item item ) {
		id     = item.getId();
		date   = item.getDate();
		name   = item.getName();
		price  = item.getPrice();
	}

	/**
	 * Sets the ID of the item.
	 *
	 * @param id (maybe) Updated item ID.
	 */
	public void setId( int id ) {
		this.id = id;
	}

	/**
	 * Returns the ID of the item.
	 *
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the date of entry into the system.
	 *
	 * @param date (maybe) Updated item date.
	 */
	public void setDate( Date date ) {
		this.date = date;
	}

	/**
	 * Returns the date of the item.
	 *
	 * @return Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Returns the date of the item formatted as a string.
	 *
	 * @return String
	 */
	public String getDateAsString() {
		String expectedPattern = "mm/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat( expectedPattern );
		formatter.applyPattern( "mm/dd/yyyy" );
		formatter.setLenient( false );

		String formattedDate = formatter.format( date );

		return formattedDate;
	}

	/**
	 * Sets the name of the item.
	 *
	 * @param name (maybe) Updated name of the item.
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * Returns the name of the item.
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the price of the item.
	 *
	 * @param price (maybe) Updated item price.
	 */
	public void setPrice( double price ) {
		this.price = price;
	}

	/**
	 * Returns the price of the item.
	 *
	 * @return double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Check if an item is equivalent to another.
	 *
	 * @param item Item object to check against.
	 * @return boolean
	 */
	public boolean equals( Item item ) {
		return ( item.getId() == id ) &&
				( item.getDate().equals( date ) ) &&
				( item.getName().equals( name ) ) &&
				( item.getPrice() == price );
	}

	/**
	 *
	 * @return String
	 */
	public String toString() {
		return String.format( "%s %s %.2f", name, getDateAsString(), price );
	}
}
