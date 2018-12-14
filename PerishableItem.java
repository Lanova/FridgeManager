import java.text.SimpleDateFormat;
import java.util.Date;

public class PerishableItem extends Item {
	private Date expirationDate;

	/**
	 * Default PerishableItem constructor.
	 */
	public PerishableItem() {
		super();
		expirationDate = new Date();
	}

	/**
	 * Constructor that uses the name, price, and expiration date.
	 *
	 * @param name Name of the item.
	 * @param price Price of the item.
	 * @param expirationDate Expiration date of the item.
	 */
	public PerishableItem( String name, double price, Date expirationDate ) {
		super( name, price );
		this.expirationDate = expirationDate;
	}

	/**
	 * Constructor that takes in all properties for a perishable item.
	 *
	 * @param date Date of the item.
	 * @param name Name of the item.
	 * @param price Price of the item.
	 * @param expirationDate Expiration date of the item.
	 */
	public PerishableItem( Date date, String name, double price, Date expirationDate ) {
		super( date, name, price );
		this.expirationDate = expirationDate;
	}

	/**
	 * Copy constructor for perishable items.
	 *
	 * @param item Perishable item to be copied.
	 */
	public PerishableItem( PerishableItem item ) {
		date           = item.getDate();
		name           = item.getName();
		price          = item.getPrice();
		expirationDate = item.getExpirationDate();
	}

	/**
	 * Sets the expiration date.
	 *
	 * @param expirationDate (maybe) Updated expiration date.
	 */
	public void setExpirationDate( Date expirationDate ) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Returns the expiration date.
	 *
	 * @return Date
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Returns the expiration date as a string.
	 *
	 * @return String
	 */
	public String getExpirationDateAsString() {
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat( expectedPattern );
		formatter.applyPattern( expectedPattern );
		formatter.setLenient( false );

		return formatter.format( expirationDate );
	}

	/**
	 * Check if an item is equivalent to another.
	 *
	 * @param item Perishable item object to check against.
	 * @return boolean
	 */
	public boolean equals( PerishableItem item ) {
		return ( item.getDate().equals( date ) ) &&
				( item.getName().equals( name ) ) &&
				( item.getPrice() == price ) &&
				(item.getExpirationDate() == getExpirationDate() );
	}

	/**
	 * Returns the perishable item as a string.
	 *
	 * @return String
	 */
	public String toString() {
		return String.format( "%s %s %.2f %s", name, getDateAsString(), price, getExpirationDateAsString() );
	}
}
