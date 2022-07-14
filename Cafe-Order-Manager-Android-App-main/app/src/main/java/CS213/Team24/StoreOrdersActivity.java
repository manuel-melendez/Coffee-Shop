package CS213.Team24;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;

/**
 * This class controls the interactions the user has with the store orders activity
 * @author Jesse Fisher and Manuel Melendez
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner orderSpinner;
    private ArrayAdapter adapter;
    private TextView orderDisplayArea;
    private StoreOrders storeOrders;
    private Order selectedOrder;

    /**
     * This function is called when the store order activity is created
     * @param savedInstanceState bundle instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        super.setTitle(getString(R.string.storeOrderTitle));

        storeOrders = MainActivity.storeOrders;

        orderDisplayArea = findViewById(R.id.orderDisplayArea);
        orderSpinner = findViewById(R.id.orderSpinner);
        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, storeOrders.getOrderList());
        orderSpinner.setAdapter(adapter);

        if(adapter.getCount() != 0){
            selectedOrder = (Order) adapter.getItem(0);
        } else {
            selectedOrder = null;
        }

        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Event handler for when an order spinner item is selected
             * @param adapterView an adapterView instance
             * @param view a view object
             * @param i index of the selected item
             * @param l long value
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOrder = (Order) adapterView.getSelectedItem();
                orderDisplayArea.setText(selectedOrder.toString());
            }

            /**
             * Event handler for when no item is selected
             * @param adapterView an adapter view instance
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                orderDisplayArea.setText(R.string.noPlacedOrders);
            }

        });

    }

    /**
     * This function is called whenever the remove button is clicked
     * @param view a view instance
     */
    public void onRemoveOrderClicked(View view) {

        if(selectedOrder == null){

            createAlert(getString(R.string.errorTitle), getString(R.string.storeOrderListEmptyError));

        } else {

            adapter.remove(selectedOrder);

            try{

                selectedOrder = (Order) orderSpinner.getSelectedItem();
                if(selectedOrder != null) orderDisplayArea.setText(selectedOrder.toString());

            } catch(IndexOutOfBoundsException exception){

                selectedOrder = (Order) adapter.getItem(adapter.getCount() - 1) ;
                if(selectedOrder != null) orderDisplayArea.setText(selectedOrder.toString());

            }

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

}