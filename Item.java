import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
  protected Date date;
  protected String name;
  protected double price;

  /** Default constructor. */
  public Item() {
    name = "";
    price = 0.00;
    date = new Date();
  }

  /**
   * Constructor that sets the name and the price.
   *
   * @param name Name of the item.
   * @param price Price of the item.
   */
  public Item(String name, double price) {
    date = new Date();
    this.name = name;
    this.price = price;
  }

  /**
   * Constructor that sets all of the properties of the item.
   *
   * @param date Date of the item.
   * @param name Name of the item.
   * @param price Price of the item.
   */
  public Item(Date date, String name, double price) {
    this.date = date;
    this.name = name;
    this.price = price;
  }

  /**
   * Copy constructor.
   *
   * @param item Item to be copied.
   */
  public Item(Item item) {
    date = item.getDate();
    name = item.getName();
    price = item.getPrice();
  }

  /**
   * Sets the date of entry into the system.
   *
   * @param date (maybe) Updated item date.
   */
  public void setDate(Date date) {
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
    String expectedPattern = "MM/dd/yyyy";
    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
    formatter.applyPattern(expectedPattern);
    formatter.setLenient(false);

    return formatter.format(date);
  }

  /**
   * Sets the name of the item.
   *
   * @param name (maybe) Updated name of the item.
   */
  public void setName(String name) {
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
  public void setPrice(double price) {
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
  public boolean equals(Item item) {
    return (item.getDate().compareTo(date) == 0)
        && (item.getName().equals(name))
        && (item.getPrice() == price);
  }

  /** @return String */
  public String toString() {
    return String.format("%s %s %.2f", name, getDateAsString(), price);
  }
}
