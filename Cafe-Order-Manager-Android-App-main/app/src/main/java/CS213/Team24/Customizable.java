package CS213.Team24;

/**
 * This interface outlines functions for classes that are meant to be customizable
 * @author Jesse Fisher and Manuel Melendez
 */
public interface Customizable {

    /**
     * Function to add onto a customizable object
     * @param obj the object to add
     * @return true if successful, false otherwise
     */
    boolean add(Object obj);

    /**
     * Function to remove something from a customizable object
     * @param obj the object to remove
     * @return true if successful, false otherwise.
     */
    boolean remove(Object obj);

}
