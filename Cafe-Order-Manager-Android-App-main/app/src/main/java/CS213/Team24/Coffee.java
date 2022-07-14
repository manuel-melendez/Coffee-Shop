package CS213.Team24;

import java.util.ArrayList;

/**
 * This is an object class for a coffee item. Coffee
 * extends the MenuItem class.
 * @author Jesse Fisher and Manuel Melendez
 */
public class Coffee extends MenuItem implements Customizable {

	private String size;
	private ArrayList<String> addOnList = new ArrayList<>();
	private static final double SHORT = 1.69;
	private static final double TALL = 2.09;
	private static final double GRANDE = 2.49;
	private static final double VENTI = 2.89;
	private final static double ADDON = 0.30;

	/**
	 * Create a new instance of the coffee class
	 * @param quantity the number of the specific item the user wants
	 * @param size The size of coffee
	 */
    public Coffee(int quantity, String size){
        super(quantity);
        this.size = size;
    }

	/**
	 * This class implements customizable to add and remove addins
	 * @param obj an object
	 * @return true if object has been added, false otherwise
	 * @Override
	 */
    public boolean add(Object obj) {
    	if(obj instanceof String){
    		String addOn = (String)obj;
        	addOnList.add(addOn);
        	return true;
    	}   
    	
    	else {
    		return false;
    	}
    }

	/**
	 * This class implements customizable to add and remove addins
	 * @param obj an object
	 * @return true if object has been removed, false otherwise
	 * @Override
	 */
    public boolean remove(Object obj) {
    	if(addOnList.isEmpty()) {
    		return false;
    	}
    	else if(obj instanceof String){
    		String addOn = (String)obj;
        	addOnList.remove(addOn);
        	return true;
    	}   
    	
    	else {
    		return false;
    	}
    }

    /**
     * This function calculates the price of a menu item.
     * @return The price of the item
	 * @Override
     */
    public double itemPrice() {
    	double price = 0;
        if(size.equals("Short")) {
        	price = (SHORT + (addOnList.size() *ADDON)) * (double)getQuantity();
        }
        
        else if(size.equals("Tall")) {
        	price = (TALL + (addOnList.size() *ADDON)) * (double)getQuantity();
        }
        
        else if(size.equals("Grande")) {
        	return (GRANDE + (addOnList.size() *ADDON)) * (double)getQuantity();
        }
        
        else if(size.equals("Venti")) {
        	return (VENTI + (addOnList.size() *ADDON)) * (double)getQuantity();
        }
        
        return price;    
    }

	/**
	 * This function returns the name of the menu item.
	 * @return name The name of the menu item
	 * @Override
	 */
	public String getItemName() {

		if(addOnList.isEmpty()){
			return (size + " coffee");
		} else {
			return (size + " coffee w/ " + String.join(", ", addOnList));
		}

	}
}
