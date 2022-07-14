package CS213.Team24;

import java.text.DecimalFormat;

/**
 * MenuItem describes general characteristics of a menu item such as their price and name.
 * @author Jesse Fisher and Manuel Melendez
 */
public abstract class MenuItem {

    private int quantity;

    /**
     * Create a new MenuItem
     * @param itemQuantity the quantity of the item
     */
    public MenuItem(int itemQuantity) {
        quantity = itemQuantity;
    }

    /**
     * This function calculates the price of a menu item.
     * @return The price of the item
     */
    public abstract double itemPrice();

    /**
     * This function returns the name of the menu item.
     * @return name The name of the menu item
     */
    public abstract String getItemName();

    /**
     * Get the item quantity
     * @return the current item quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * set the item quantity
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * return a string representation of the object, which will be displayed in the gui
     * @return A string representation of the data
     * @Override
     */
    public String toString(){
        return String.valueOf(quantity) + "x " + getItemName() + ": " +
                new DecimalFormat().getCurrencyInstance().format(itemPrice());
    }

    /**
     * Determines if two menu items are the same based on name, price, and quantity.
     * Support for strings is also provided, which makes interacting with the
     * fx observable lists easier. In this case, the equals function will check
     * to see if the provided String matches the toString of the current MenuItem.
     * @param obj The second object for the comparison
     * @return true if equal, false otherwise
     * @Override
     */
    public boolean equals(Object obj){

        if(obj instanceof MenuItem) {

            MenuItem item = (MenuItem) obj;
            return ((getItemName().equals(item.getItemName())) && (itemPrice() == item.itemPrice()) && (quantity == item.getQuantity()));

        } else if(obj instanceof String){

            String queryString = (String) obj;
            return toString().equals(queryString);

        } else {

            return false;

        }

    }

}
