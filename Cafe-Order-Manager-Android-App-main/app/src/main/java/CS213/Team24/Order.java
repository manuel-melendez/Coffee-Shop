package CS213.Team24;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class maintains a list menu items and contains a unique order number as an identifier.
 * @author Jesse Fisher and Manuel Melendez
 */
public class Order implements Customizable {

    private static final double TAX_RATE = 0.06625;
    private static int nextOrderNumber = 1;

    private int orderNumber;
    private ArrayList<MenuItem> itemList;

    /**
     * Create a new order instance
     * the orderNumber will be set to the value of the nextOrderNumber counter
     */
    public Order(){
        orderNumber = nextOrderNumber;
        nextOrderNumber++;
        itemList = new ArrayList<MenuItem>();
    }

    /**
     * This function attempts to add a new menu item to the itemList
     * @param obj The MenuItem to be added to the list
     * @return True if successfully added, false if the object is not a MenuItem instance
     * @Override
     */
    public boolean add(Object obj) {

        if(obj instanceof MenuItem){

            itemList.add((MenuItem) obj);
            return true;

        } else {

            return false;

        }

    }

    /**
     * This function attempts to remove a menu item from the itemList.
     * Support is provided to remove the item based on its toString value; essentially
     * it allows us to take the display string from the GUI and use that to find and remove
     * the menu item in question.
     * @param obj The MenuItem or description of a menu item to be removed from the list
     * @return True if successfully removed, false if obj is not a MenuItem or if the item is not in the list
     * @Override
     */
    public boolean remove(Object obj) {

        if(obj instanceof MenuItem) {

            MenuItem item = (MenuItem) obj;
            return itemList.remove(item);

        } else if(obj instanceof String) {

            String itemDescription = (String) obj;

            for (MenuItem item: itemList) {
                if(item.equals(itemDescription)) return itemList.remove(item);
            }

            return false;

        } else {
            return false;
        }

    }

    /**
     * Return the array list of menu items associated with this order
     * @return itemList an array list of menu items
     */
    public ArrayList<MenuItem> getItemList(){
        return itemList;
    }

    /**
     * This function outputs the order data as a string for display in the GUI
     * @return output The string representation of this order object
     * @Override
     */
    public String toString(){

        if(itemList.isEmpty()){

            return "Order " + String.valueOf(orderNumber) + ": No menu items added to this order yet.";

        } else {

            String output = "Order " + String.valueOf(orderNumber) + ":\n";

            for(MenuItem item : itemList){
                output += ("\t" + item + "\n");
            }

            return output;

        }

    }

    /**
     * Check if the current order instance is equal to the input object
     * @return true if equal, false if obj is not an Order or the orderNumber fields don't match
     * @Override
     */
    public boolean equals(Object obj){

        if(obj instanceof Order){

            Order order = (Order) obj;
            return (orderNumber == order.orderNumber);

        } else {

            return false;

        }

    }

    /**
     * Calculate the subtotal, taxes, and final total for the current items in the order.
     * Values will be formatted as strings and returned as elements in a string array.
     * Index 0 = subtotal
     * Index 1 = taxes
     * Index 2 = total
     * @return totals The three totals as formatted strings
     */
    public String[] getTotals(){

        //calculate the subtotal
        double subtotal = 0;
        for(MenuItem item : itemList){
            subtotal += item.itemPrice();
        }

        //calculate the taxes and final total
        double taxes = subtotal * TAX_RATE;
        double total = subtotal + taxes;

        //format the totals as strings
        String[] totals = {String.format(new DecimalFormat().getCurrencyInstance().format(subtotal)),
                String.format(new DecimalFormat().getCurrencyInstance().format(taxes)),
                String.format(new DecimalFormat().getCurrencyInstance().format(total))};

        return totals;

    }

    /**
     * get the order number as a string
     * @return orderNumber the number as a string
     */
    public String getOrderNumber() {
        return String.valueOf(orderNumber);
    }
}
