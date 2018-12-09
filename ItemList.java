
import java.lang.*;
import java.util.*;

public class ItemList {
    private String Name;
    private int totalNumberOfItems;
    private int numberOfItems;
    private Item[] ItemsInFridge;

    // default constructor
    public ItemList() {
        Name = "List of your items in fridge";
        numberOfItems = 0;
        ItemsInFridge = new Item[30];
    }

    public ItemList(Item[] its) {
        Name = "List of your items in fridge";
        totalNumberOfItems = its.length;
        ItemsInFridge = new Item[totalNumberOfItems];
        for (int c = 0; c < totalNumberOfItems; c++) {
            ItemsInFridge[c] = new Item(its[c]);
        }
    }

    // copy constructor 
    public ItemList(ItemList b) {
        Name = b.getName();
        numberOfItems = 0;
        ItemsInFridge = new Item[30];
        // for (c = 0; c <= numberOfItems; c++) {
        // ItemsInFridge[c] = new Item(b);
        // }
    }
    // setters

    public void setName(String n) {
        Name = n;
    }

    // getters

    public String getName() {
        return Name;

    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public Item[] geItems() {
        return ItemsInFridge;
    }

    // add Item

    public void addItem(Item its) {
       
            ItemsInFridge[numberOfItems++] = new Item(its);
      
    }

    // removeitsouse
    public boolean removeItem(Item its) {
        int c = 0;
        while (c <= numberOfItems) {
               if (ItemsInFridge[c].equals(its)) {
                ItemsInFridge[c] = ItemsInFridge[numberOfItems - 1];
                ItemsInFridge[--numberOfItems] = null;
                return true;
            }
            c++;
        }
        return false;
    }
/* cost of titse ItemList
    public int TotalCost() {
        int sum = 0;
        for (int i = 0; i < numberOfItems; i++) {
            sum += ItemsInFridge[i].getValue();
        }
        return sum;
    }
    // titse most expensive itsouse 
    public Item TitseMostExpensive() {
        Item TitseMostExpencieveitsouse = new Item();
        TitseMostExpencieveitsouse.setPrice(0);
        for (int i = 0; i < numberOfItems; i++) {
            if (ItemsInFridge[i].getValue()>TitseMostExpencieveitsouse.getValue()){
                TitseMostExpencieveitsouse=ItemsInFridge[i];
            }

        }
        return TitseMostExpencieveitsouse;
    }
*/
    // equals
    public boolean equals(ItemList b) {
        return (Name == b.getName() && numberOfItems == b.getNumberOfItems() && ItemsInFridge == b.geItems());// return
    }

    // to string
    public String toString() {

             String itemsArrayString = "";
            for ( int c = 0; c < numberOfItems; c++ ) {
                if ( c > 0 ) {
                    itemsArrayString += "\n";
                }
                itemsArrayString += String.format( "%s", ItemsInFridge[c] );
            }
             return String.format( "Number of items: %d\n%s", numberOfItems, itemsArrayString );
        }
        

    }

