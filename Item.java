import java.util.Date;

public class Item {
	//private Date currentDate;
	private int Id = 0;
	private String name;
	private double price;
	/**
	 * Default constructor.
	 */
	public Item() {
		Id = 100;
		name = "New Item";
		price = 0.00;

	}


	public Item(String n, double p ) {
		name = n;
		price = p;

	}

	public Item( Item i ) {
		name   = i.getName();
		price  = i.getPrice();
	}
 
	// Setter

	public void setName( String n ) {
		name = n;
	}
	public void setPrice( double p ) {
		price = p;
	}
	public void setId(int i){
		Id=i;
	}

	//getters
	public String getName() {
		return name;
	}

		public double getPrice() {
		return price;
	}
	public int getId(){
		return Id;
	}
	/**
	 * Check if an item is equivalent to another.
	 */
	public boolean equals( Item i ) {
		return (name == i.getName() &&price == i.getPrice() );
	}

	//toString
	public String toString() {
		return String.format( "%s %f", name, price );
	}
}
