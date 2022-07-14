package CS213.Team24;

/**
 * This is an object class for a donut item. Donut
 * extends the MenuItem class.
 * @author Jesse Fisher and Manuel Melendez
 */
public class Donut extends MenuItem {
	private String flavor, donutType;
	private static final double YEASTDONUTPRICE = 1.59;
	private static final double CAKEDONUTPRICE = 1.79;
	private static final double DONUTHOLEPRICE = 0.39;

    /**
     * Create a new instance of the donut class
     * @param quantity the number of donuts
     * @param donutType the type of donut requested
     * @param flavor the flavor of the donut
     */
    public Donut(int quantity, String donutType, String flavor){
        super(quantity);
        this.donutType = donutType;
        this.flavor = flavor;
    }

    /**
     * This function returns the item name as a string
     * @return The price of the item
     * @Override
     */
    public String getItemName(){
        return (flavor + " " + donutType);
    }

    /**
     * This function calculates the price of a menu item.
     * @return The price of the item
     * @Override
     */
    public double itemPrice() {
    	double price = 0;
        if(donutType.equals("yeast donut")) {
        	price = (YEASTDONUTPRICE * (double)getQuantity());
        }
        else if(donutType.equals("cake donut")) {
        	price = (CAKEDONUTPRICE * (double)getQuantity());
        }
        else if(donutType.equals("donut holes")) {
        	price = (DONUTHOLEPRICE * (double)getQuantity());
        }
        return price;
    }
}
