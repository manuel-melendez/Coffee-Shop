package CS213.Team24;

import java.util.ArrayList;

/**
 * This class maintains a list of orders placed by the user.
 * @author Jesse Fisher and Manuel Melendez
 */
public class StoreOrders implements Customizable {

    private ArrayList<Order> orderList;

    /**
     * Creates a new instance of the StoreOrder class to hold and manage orders.
     */
    public StoreOrders(){
        orderList = new ArrayList<Order>();
    }

    /**
     * This function attempts to add a new order to the orderList
     * @param obj The Order instance to be added to the list
     * @return True if successfully added, false if the object is not an Order instance
     * @Override
     */
    public boolean add(Object obj) {

        if(obj instanceof Order){

            orderList.add((Order) obj);
            return true;

        } else {

            return false;

        }

    }

    /**
     * This function attempts to remove an order from the orderList
     * @param obj The Order instance to be removed from the list
     * @return True if successfully removed, false if obj is not an Order or if the item is not in the list
     * @Override
     */
    public boolean remove(Object obj) {

        if(obj instanceof Order){

            Order order = (Order) obj;
            return orderList.remove(order);

        } else {

            return false;

        }

    }

    /**
     * Retrieve the arraylist stored within this object
     * @return orderList the list associated with this object
     */
    public ArrayList<Order> getOrderList(){
        return orderList;
    }

}