package CS213.Team24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 *  * This class controls the interactions with the order donuts activity
 * @author Jesse Fisher and Manuel Melendez
 */
public class OrderDonutsActivity extends AppCompatActivity implements recyclerAdapter.OnDonutClickListener {
    private ArrayList<Donut> donutList;
    private RecyclerView recyclerView;
    private TextView donutOutput;
    private Order currentOrder;
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;
    double total = 0;
    String quantity = null;

    private static final int ONE = 1;
    private static final double roundFactor = 100.0;

    /**
     * Initial setup for the Views and the adapter for the recyclerView
     * @param savedInstanceState provided data bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donuts);
        currentOrder = MainActivity.currentOrder;
        recyclerView = findViewById(R.id.recycler);
        donutOutput = findViewById(R.id.donutOutput);
        textInputEditText = findViewById(R.id.textInputEditText);
        textInputLayout = findViewById(R.id.textInputLayout);
        donutList = new ArrayList<>();

        setDonutInfo();
        setAdapter();

    }

    /**
     * this function initializes an adapter for the recycler view
     */
    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter((donutList), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    /**
     * this function populates the donut array list with the possible flavors and types
     */
    private void setDonutInfo(){
        donutList.add(new Donut(ONE, getString(R.string.yd), getString(R.string.plainFlavor)));
        donutList.add(new Donut(ONE, getString(R.string.yd), getString(R.string.chocolate)));
        donutList.add(new Donut(ONE, getString(R.string.yd), getString(R.string.powdered)));
        donutList.add(new Donut(ONE, getString(R.string.yd), getString(R.string.special)));

        donutList.add(new Donut(ONE, getString(R.string.cd), getString(R.string.plainFlavor)));
        donutList.add(new Donut(ONE, getString(R.string.cd), getString(R.string.chocolate)));
        donutList.add(new Donut(ONE, getString(R.string.cd), getString(R.string.powdered)));
        donutList.add(new Donut(ONE, getString(R.string.cd), getString(R.string.special)));

        donutList.add(new Donut(ONE, getString(R.string.dh), getString(R.string.plainFlavor)));
        donutList.add(new Donut(ONE, getString(R.string.dh), getString(R.string.chocolate)));
        donutList.add(new Donut(ONE, getString(R.string.dh), getString(R.string.powdered)));
        donutList.add(new Donut(ONE, getString(R.string.dh), getString(R.string.special)));

    }

    /**
     * this function is called whenever a recycler view item is clicked
     * @param position the position of the item clicked in the recycler view
     */
    @Override
    public void onDonutClick(int position) {
        quantity = textInputEditText.getText().toString();

        if (quantity != null && validInt(textInputEditText.getText().toString()) && Integer.parseInt(quantity) > 0) {

        donutList.get(position).setQuantity(Integer.parseInt(quantity));
        total += donutList.get(position).itemPrice();
        total = Math.round(total * roundFactor) / roundFactor;
        currentOrder.add(donutList.get(position));
        donutOutput.setText((getString(R.string.totalStub) + total));
        Toast.makeText(getApplicationContext(),donutList.get(position).getQuantity() + getString(R.string.space) + donutList.get(position).getItemName() + getString(R.string.space) + getString(R.string.addToBasketConfirmation), Toast.LENGTH_SHORT).show();
        }
        else{
            createAlert(getString(R.string.errorTitle), getString(R.string.quantityError));
        }
    }

    /**
     * This function checks whether the input is a valid integer
     * @param str An integer as a string representation
     * @return true if the string can be parsed, false otherwise
     */
    private boolean validInt(String str) {
        try {
           int quantity = Integer.parseInt(textInputEditText.getText().toString());
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
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