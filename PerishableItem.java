import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PerishableItem extends Item {
	private Date expirationDate;
	private boolean leftover;

	/**
	 * Default PerishableItem constructor.
	 */
	public PerishableItem() {
		super();
		expirationDate      = new Date();
		leftover            = false;
	}

	/**
	 * Constructor that uses the name, price, and expiration date.
	 *
	 * @param name Name of the item.
	 * @param price Price of the item.
	 * @param expirationDate Expiration date of the item.
	 * @param leftover Whether item is a leftover or not.
	 */
	public PerishableItem( String name, double price, Date expirationDate, boolean leftover ) {
		super( name, price );
		this.expirationDate = expirationDate;
		this.leftover = leftover;
	}

	/**
	 * Constructor that adds date, name, price, and whether item is a leftover.
	 *
	 * @param date Date item was added.
	 * @param name Name of the item.
	 * @param price Price paid of the item.
	 * @param leftover Whether the item is a leftover.
	 */
	public PerishableItem( Date date, String name, double price, boolean leftover ) {
		super( date, name, price );
		expirationDate = new Date();
		this.leftover = leftover;
	}

	/**
	 * Constructor that adds date, name, price, and days until expiration.
	 *
	 * @param date Date item was added.
	 * @param name Name of the item.
	 * @param price Price paid for the item.
	 * @param daysUntilExpiration Days until expiration.
	 */
	public PerishableItem( Date date, String name, double price, int daysUntilExpiration ) {
		super( date, name, price );
		expirationDate = setExpirationDate( daysUntilExpiration );
		leftover = true;
	}

	/**
	 * Constructor that takes takes date, name, price, and expiration date.
	 *
	 * @param date Date item was added.
	 * @param name Name of the item.
	 * @param price Price of the item.
	 * @param expirationDate Expiration date of the item.
	 */
	public PerishableItem( Date date, String name, double price, Date expirationDate ) {
		super( date, name, price );
		this.expirationDate = expirationDate;
		leftover = false;
	}

	/**
	 * Constructor that takes in all properties for a perishable item.
	 *
	 * @param date Date of the item.
	 * @param name Name of the item.
	 * @param price Price of the item.
	 * @param expirationDate Expiration date of the item.
	 * @param leftover Sets whether the item is a leftover or not.
	 */
	public PerishableItem( Date date, String name, double price, Date expirationDate, boolean leftover ) {
		super( date, name, price );
		this.expirationDate = expirationDate;
		this.leftover       = leftover;
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
		leftover       = item.getLeftover();
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
	 * Sets the expiration date by adding days until expiration.
	 *
	 * @param daysUntilExpiration The number of days until expiration.
	 */
	private Date setExpirationDate( int daysUntilExpiration ) {
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.setTime( getDate() );
		tempCalendar.add( Calendar.DATE, daysUntilExpiration );

		// Set and return the expiration date.
		return expirationDate = ( tempCalendar.getTime() );
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
	 * Sets whether the item is a leftover or not.
	 *
	 * @param leftover (maybe) Updated boolean whether the item is a leftover.
	 */
	public void setLeftover( boolean leftover ) {
		this.leftover = leftover;
	}

	/**
	 * Returns the status of the leftover.
	 *
	 * @return boolean
	 */
	public boolean getLeftover() {
		return leftover;
	}

	/**
	 * Returns the status of the leftover.
	 *
	 * @return boolean
	 */
	public boolean isLeftover() {
		return leftover;
	}

	/**
	 * Checks if the perishable item is expired, but not a leftover.
	 *
	 * @param date Date to check the expiration date against.
	 * @return boolean
	 */
	public boolean isExpired( Date date ) {
		return ( date.after( expirationDate ) && ! leftover );
	}

	/**
	 * Checks if the leftover item has passed its expiration date.
	 *
	 * @param date The date object to check the expiration date against.
	 * @return boolean
	 */
	public boolean isSpoiled( Date date ) {
		return date.after( expirationDate ) && leftover;
	}

	/**
	 * Check if an item is equivalent to another.
	 *
	 * @param item Perishable item object to check against.
	 * @return boolean
	 */
	public boolean equals( PerishableItem item ) {
		return ( item.getDate().compareTo( date ) == 0 ) &&
				( item.getName().equals( name ) ) &&
				( item.getPrice() == price ) &&
				( item.getExpirationDate().compareTo( expirationDate ) == 0 ) &&
				( item.isLeftover() == leftover );
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
