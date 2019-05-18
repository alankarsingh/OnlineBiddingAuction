/*
 * This class will take care of items to be auctioned.
 */
public class Item {

    private final String name;
    
    public Item(String name) {
        this.name = name;
    }

    public String toString() {
        return "Item " + this.name ;
    }
}