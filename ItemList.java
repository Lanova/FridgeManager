import java.util.Arrays;
import java.util.Date;

public class ItemList {
  private String name;
  private int numberOfItems;
  private Item[] itemsInFridge;

  /** Default constructor. */
  public ItemList() {
    name = "";
    numberOfItems = 0;
    itemsInFridge = new Item[30];
  }

  /**
   * Constructor that is built with an array of items.
   *
   * @param itemList Array to be added to item list.
   */
  public ItemList(Item[] itemList) {
    name = "";
    int totalNumberOfItems = itemList.length;
    itemsInFridge = new Item[totalNumberOfItems];
    for (int c = 0; c < totalNumberOfItems; c++) {
      itemsInFridge[c] = new Item(itemList[c]);
    }
  }

  /**
   * Constructor that creates the object using the name.
   *
   * @param name Name of the item list.
   */
  public ItemList(String name) {
    this.name = name;
    numberOfItems = 0;
    itemsInFridge = new Item[30];
  }

  /**
   * Constructor that defines all properties.
   *
   * @param name Name of the list.
   * @param numberOfItems Number of items in the list.
   * @param itemsInFridge Items in the system.
   */
  public ItemList(String name, int numberOfItems, Item[] itemsInFridge) {
    this.name = name;
    this.numberOfItems = numberOfItems;
    this.itemsInFridge = itemsInFridge;
  }

  /**
   * Copy constructor.
   *
   * @param itemList Item list to be copied.
   */
  public ItemList(ItemList itemList) {
    name = itemList.getName();
    numberOfItems = itemList.getNumberOfItems();
    itemsInFridge = itemList.getItems();
  }

  /**
   * Sets the name of the list.
   *
   * @param name (maybe) Updated name of list.
   */
  public void setName(String name) {
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
   * Sets the number of items in the list.
   *
   * @param numberOfItems (maybe) Updated number of items in the list.
   */
  public void setNumberOfItems(int numberOfItems) {
    this.numberOfItems = numberOfItems;
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
  public void addItem(Item item) {
    itemsInFridge[numberOfItems++] = new Item(item);
  }

  /**
   * Add Perishable item to the list.
   *
   * @param item Perishable item to be added.
   */
  public void addItem(PerishableItem item) {
    itemsInFridge[numberOfItems++] = new PerishableItem(item);
  }

  /**
   * Remove item by ID
   *
   * @param id ID of item in the array.
   */
  public void removeItem(int id) {
    int d = 0;
    Item[] tempArray = new Item[itemsInFridge.length];
    for (int c = 0; c < itemsInFridge.length; c++) {
      if (c != id) {
        tempArray[d] = itemsInFridge[c];
        d++;
      }
    }
    itemsInFridge = tempArray;
    numberOfItems--;
  }

  /**
   * Goes through the list and checks for perishable items.
   *
   * @return ItemList
   */
  public ItemList getPerishables() {
    ItemList perishableItems = new ItemList();
    PerishableItem perishableItem;
    for (int c = 0; c < numberOfItems; c++) {
      if (itemsInFridge[c].getClass() == PerishableItem.class) {
        perishableItem = (PerishableItem) itemsInFridge[c];
        perishableItems.addItem(perishableItem);
      }
    }

    return perishableItems;
  }

  /**
   * Returns an ItemList of perishable items with the name of the list being the parameter passed
   * in.
   *
   * @param name Name of the newly created ItemList
   * @return ItemList
   */
  public ItemList getPerishables(String name) {
    ItemList perishableItems = addPerishableLoop();
    perishableItems.setName(name);
    return perishableItems;
  }

  /**
   * Loop through the ItemList and create a new lost of items that are perishable.
   *
   * @return ItemList
   */
  private ItemList addPerishableLoop() {
    ItemList perishableItems = new ItemList(name);
    PerishableItem perishableItem;
    for (int c = 0; c < numberOfItems; c++) {
      if (itemsInFridge[c].getClass() == PerishableItem.class) {
        perishableItem = (PerishableItem) itemsInFridge[c];
        perishableItems.addItem(perishableItem);
      }
    }

    return perishableItems;
  }

  /**
   * Returns an ItemList of expired items.
   *
   * @param date Date to check the item to.
   * @return ItemList
   */
  public ItemList getExpired(Date date) {
    return expirationLoop(date);
  }

  /**
   * Loop that gets the expired items out of an ItemList
   *
   * @param date Date to check the expiration date against.
   * @return ItemList
   */
  public ItemList expirationLoop(Date date) {
    ItemList expiredItems = new ItemList();
    PerishableItem expiredItem;

    for (int c = 0; c < numberOfItems; c++) {
      if (itemsInFridge[c].getClass() == PerishableItem.class) {
        expiredItem = (PerishableItem) itemsInFridge[c];
        if (expiredItem.isExpired(date)) {
          expiredItems.addItem(expiredItem);
        } else if (expiredItem.isSpoiled(date)) {
          expiredItems.addItem(expiredItem);
        }
      }
    }

    return expiredItems;
  }

  /**
   * Returns an ItemList of expired items with a name and the date to check the item to.
   *
   * @param name Name of the ItemList object.
   * @param date Date to check the expiration date against.
   * @return ItemList
   */
  public ItemList getExpired(String name, Date date) {
    ItemList expiredItems = expirationLoop(date);
    expiredItems.setName(name);

    return expiredItems;
  }

  /**
   * Creates an ItemList from the differences between two ItemLists
   *
   * @param mustHaveList List of items that MUST be in the system checked by name.
   * @return ItemList
   */
  public ItemList getShoppingList(ItemList mustHaveList) {
    ItemList shoppingList = new ItemList();
    Item[] mustHaveItems = mustHaveList.getItems();
    int mustHaveNumberOfItems = mustHaveList.getNumberOfItems();

    for (int count = 0; count < mustHaveNumberOfItems; count++) {
      boolean isInList = false;
      for (int innerCount = 0; innerCount < numberOfItems; innerCount++) {
        if (mustHaveItems[count].getName().equals(itemsInFridge[innerCount].getName())) {
          isInList = true;
          break;
        }
      }

      if (!isInList) {
        shoppingList.addItem(mustHaveItems[count]);
      }
    }

    return shoppingList;
  }

  /**
   * Chech if the items from mustHave in a fridge, if so return the list of these items.
   *
   * @param mustHaveList List of items that MUST be in the system.
   * @return ItemList
   */
  public ItemList gethaveInFridge(ItemList mustHaveList) {
    ItemList haveInFridge = new ItemList(name);
    Item[] mustHaveItems = mustHaveList.getItems();
    int mustHaveNumberOfItems = mustHaveList.getNumberOfItems();

    for (int count = 0; count < mustHaveNumberOfItems; count++) {
      boolean isInList = false;
      for (int innerCount = 0; innerCount < numberOfItems; innerCount++) {
        if (mustHaveItems[count].getName().equals(itemsInFridge[innerCount].getName())) {
          isInList = true;
          if (isInList) {
            haveInFridge.addItem(itemsInFridge[innerCount]);
          }
        }
      }
    }

    return haveInFridge;
  }

  /**
   * Gets the price paid of all items in the ItemList.
   *
   * @return double
   */
  public double getTotalCost() {
    double totalCost = 0.00;
    for (int count = 0; count < numberOfItems; count++) {
      totalCost += itemsInFridge[count].getPrice();
    }

    return totalCost;
  }

  /**
   * Check to see if the item list is equal to the item list passed in.
   *
   * @param itemList Item list to be checked against.
   * @return boolean
   */
  public boolean equals(ItemList itemList) {
    return (itemList.getName().equals(name))
        && (itemList.getNumberOfItems() == numberOfItems)
        && (Arrays.equals(itemList.getItems(), itemsInFridge));
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
      itemsArrayString += String.format("%3d %s", c, itemsInFridge[c]);
    }
    return String.format("Number of items: %d\n%s", numberOfItems, itemsArrayString);
  }
}
