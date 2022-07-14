package CS213.Team24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;

/**
 * This class controls the interactions with the order basket activity
 * @author Jesse Fisher and Manuel Melendez
 */
public class OrderBasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listview;
    private ArrayAdapter adapter;
    private TextView totalsView;
    private Button removeButton;
    private Button placeOrderButton;
    private int selection;

    private Order currentOrder;
    private StoreOrders storeOrders;


    /**
     * Initial setup for the Views and the adapter for the ListView
     * @param savedInstanceState provided data bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_basket);

        // Pull the data from the main activity
        currentOrder = MainActivity.currentOrder;
        storeOrders = MainActivity.storeOrders;

        super.setTitle((getString(R.string.orderTitle) + currentOrder.getOrderNumber()));

        // set up the two buttons
        removeButton = findViewById(R.id.removeButton);
        placeOrderButton = findViewById(R.id.placeOrderButton);

        // Set up the list and text views
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currentOrder.getItemList());
        listview = findViewById(R.id.orderList);
        listview.setOnItemClickListener(this); //register the listener for an OnItemClick event.
        listview.setAdapter(adapter);
        totalsView = findViewById(R.id.totalsView);

        // initialize variable to keep track of removal selections
        selection = -1;

        // fill the text area with the current totals
        updateTotals();

    }

    /**
     * Event handler function for when a list item is selected
     * @param adapterView an adapterView instance
     * @param view a view object
     * @param i index of the selected item
     * @param l long value
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selection = i;
    }

    /**
     * this function is called when the remove button is clicked
     * @param view an instance of the view class
     */
    public void onRemoveClicked(View view) {

        if(selection == -1){

            createAlert(getString(R.string.errorTitle), getString(R.string.orderBasketRemovalError));

        } else {

            adapter.remove(adapter.getItem(selection));
            if(adapter.getCount() == selection) selection = -1;

        }

        updateTotals();

    }

    /**
     * this function is called whenever the order placed button is clicked
     * @param view an instance of the view class
     */
    public void onPlaceOrderClicked(View view) {

        if(adapter.getCount() == 0){
            createAlert(getString(R.string.errorTitle), getString(R.string.placeOrderEmptyError));
        } else {
            storeOrders.add(currentOrder);
            removeButton.setEnabled(false);
            placeOrderButton.setEnabled(false);
            createAlert(getString(R.string.orderPlacedTitle), getString(R.string.placeOrderSuccessMessage));
            MainActivity.currentOrder = new Order();
        }

    }

    /**
     * This function creates a new alert with a given title and message
     * @param title The title of the new alert
     * @param message The message for the new alert
     */
    private void createAlert(String title, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Calculates the subtotal, taxes, and total amount for the order as it stands.
     * Results are published to the text area called the totalsArea
     */
    private void updateTotals(){
        String[] newTotals = currentOrder.getTotals();
        totalsView.setText(getString(R.string.subTotalStub) + newTotals[0] + getString(R.string.newline) +
                getString(R.string.taxesStub) + newTotals[1] + getString(R.string.newline) +
                getString(R.string.totalStub) + newTotals[2] + getString(R.string.newline));
    }

}