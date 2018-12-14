
public class ItemList {
    private String name;
    private int numberOfItems;
    private Item[] itemsInFridge;

	/**
	 * Default constructor.
	 */
	public ItemList() {
        name = "";
        numberOfItems = 0;
        itemsInFridge = new Item[30];
    }

	/**
	 * Copy constructor.
	 *
	 * @param itemList Array to be added to item list.
	 */
	public ItemList( Item[] itemList ) {
        name = "";
        int totalNumberOfItems = itemList.length;
		itemsInFridge = new Item[totalNumberOfItems];
        for (int c = 0; c < totalNumberOfItems; c++ ) {
            itemsInFridge[c] = new Item( itemList[c] );
        }
    }

	/**
	 * Copy constructor.
	 *
	 * @param itemList Item list to be copied.
	 */
	public ItemList( ItemList itemList ) {
        name = itemList.getName();
        numberOfItems = 0;
	    itemsInFridge = new Item[30];
    }

	/**
	 * Sets the name of the list.
	 *
	 * @param name (maybe) Updated name of list.
	 */
	public void setName( String name ) {
        this.name = name;
    }

	/**
	 * Returns the name of the list.
	 *
	 * @return String
	 */
	public String getName() {
        return name;

    }

	/**
	 * Returns the number of items in the list.
	 *
	 * @return int
	 */
	public int getNumberOfItems() {
        return numberOfItems;
    }

	/**
	 * Returns the array of items in the system.
	 *
	 * @return array
	 */
	public Item[] getItems() {
        return itemsInFridge;
    }

	/**
	 * Add an item to the list.
	 *
	 * @param item Item to be added to the list.
	 */
	public void addItem( Item item ) {
        itemsInFridge[numberOfItems++] = new Item( item );
    }

	/**
	 * Add Perishable item to the list.
	 *
	 * @param item Perishable item to be added.
	 */
	public void addItem( PerishableItem item ) {
    	itemsInFridge[numberOfItems++] = new PerishableItem( item );
    }

	/**
	 * Remove item by ID
	 *
	 * @param id ID of item in the array.
	 */
	public void removeItem( int id ) {
        int d =0;
        Item[] tempArray = new Item[itemsInFridge.length];
        for (int c = 0; c < itemsInFridge.length; c++) {
            if ( c != id ) {
                tempArray[d] = itemsInFridge[c];
                d++;
            }
        }
        itemsInFridge = tempArray;
        numberOfItems--;
    }

	/**
	 * Check to see if the item list is equal to the item list passed in.
	 *
	 * @param itemList Item list to be checked against.
	 * @return boolean
	 */
	public boolean equals( ItemList itemList ) {
        return ( itemList.getName().equals( name ) ) &&
		        ( itemList.getNumberOfItems() == numberOfItems ) &&
		        ( itemList.getItems() == itemsInFridge );// return
    }

	/**
	 * Outputs the item list as a string.
	 *
	 * @return String
	 */
	public String toString() {

        String itemsArrayString = "";
        for (int c = 0; c < numberOfItems; c++) {
            if (c > 0) {
                itemsArrayString += "\n";
            }
            itemsArrayString += String.format("%d %s", c, itemsInFridge[c]);
        }
        return String.format("Number of items: %d\n%s", numberOfItems, itemsArrayString);
    }
}
