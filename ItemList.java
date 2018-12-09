
import java.lang.*;
import java.util.*;

public class ItemList {
    private String Name;
    private int numberOfItems;
    private Item[] ItemsInFridge;

    // default constructor
    public ItemList() {
        Name = "List of your items in fridge";
        numberOfItems = 0;
        ItemsInFridge = new Item[30];
    }

    public ItemList(Item[] h, int n) {
        Name = "List of your items in fridge";
        numberOfItems = n;
        ItemsInFridge = new Item[30];
        for (int c = 0; c < numberOfItems; c++) {
            ItemsInFridge[c] = new Item(h[c]);
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

    public Item[] geItem() {
        return ItemsInFridge;
    }

    // add Item

    public void addItem(Item h) {
       
            ItemsInFridge[numberOfItems++] = new Item(h);
      
    }

    // removeHouse
    public boolean removeItem(Item h) {
        int c = 0;
        while (c <= numberOfItems) {
            if (ItemsInFridge[c].equals(h)) {
                ItemsInFridge[c] = ItemsInFridge[numberOfItems - 1];
                ItemsInFridge[--numberOfItems] = null;
                return true;
            }
            c++;
        }
        return false;
    }
/* cost of the ItemList
    public int TotalCost() {
        int sum = 0;
        for (int i = 0; i < numberOfItems; i++) {
            sum += ItemsInFridge[i].getValue();
        }
        return sum;
    }
    // the most expensive house 
    public Item TheMostExpensive() {
        Item TheMostExpencieveHouse = new Item();
        TheMostExpencieveHouse.setPrice(0);
        for (int i = 0; i < numberOfItems; i++) {
            if (ItemsInFridge[i].getValue()>TheMostExpencieveHouse.getValue()){
                TheMostExpencieveHouse=ItemsInFridge[i];
            }

        }
        return TheMostExpencieveHouse;
    }
*/
    // equals
    public boolean equals(ItemList b) {
        return (Name == b.getName() && numberOfItems == b.getnumberOfItems() && ItemsInFridge == b.getItem());// return
    }

    // to string
    public String toString() {

        // return
        return String.format("%6s\n %s\n", Name, Arrays.toString(ItemsInFridge));

        // for(c=0, c<numberOfItems, c++){
        // num
        // }

        // System.out.println("Total number of houses " + numberOfItems);

    }

}