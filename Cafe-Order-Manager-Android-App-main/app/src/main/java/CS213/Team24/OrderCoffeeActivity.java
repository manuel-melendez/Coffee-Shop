package CS213.Team24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;


/**
 * This class controls the interactions with the order coffee activity
 * @author Jesse Fisher and Manuel Melendez
 */
public class OrderCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputEditText textInputEditText;
    private TextView coffeeOutput;
    private Order currentOrder;
    private CheckBox cream;
    private CheckBox caramel;
    private CheckBox syrup;
    private CheckBox milk;
    private CheckBox whippedCream;

    private static final double roundFactor = 100.0;

    String choice;
    double total = 0;
    String quantity = null;

    /**
     * Initial setup for the Views and the adapter for the spinner
     * @param savedInstanceState provided data bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);
        currentOrder = MainActivity.currentOrder;
        Spinner spinner = findViewById(R.id.spinner);
        textInputEditText = findViewById(R.id.textInputEditText);
        Button coffeeAddBasket = findViewById(R.id.coffeeAddBasket);
        coffeeOutput = findViewById(R.id.textView);
        milk = findViewById(R.id.milk);
        cream = findViewById(R.id.cream);
        syrup = findViewById(R.id.syrup);
        caramel = findViewById(R.id.caramel);
        whippedCream = findViewById(R.id.whippedCream);
        coffeeAddBasket.setOnClickListener(new View.OnClickListener() {

            /**
             * this function is called whenever the add to basket button is clicked
             * @param v the button view
             */
            @Override
            public void onClick(View v) {

                quantity = textInputEditText.getText().toString().trim();
                if(choice != null && validInt(quantity) && Integer.parseInt(quantity) > 0){
                    Coffee coffee = new Coffee(Integer.parseInt(quantity), choice);
                    if(milk.isChecked()){
                        coffee.add(getString(R.string.milk));
                    }
                    if(caramel.isChecked()){
                        coffee.add(getString(R.string.caramel));
                    }
                    if(cream.isChecked()){
                        coffee.add(getString(R.string.cream));
                    }
                    if(syrup.isChecked()){
                        coffee.add(getString(R.string.syrup));
                    }
                    if(whippedCream.isChecked()){
                        coffee.add(getString(R.string.whippedCream));
                    }
                    total += coffee.itemPrice();
                    total = Math.round(total * roundFactor) / roundFactor;
                    currentOrder.add(coffee);
                    coffeeOutput.setText((getString(R.string.totalStub) + total));
                    Toast.makeText(getApplicationContext(),coffee.getQuantity() + getString(R.string.space) + coffee.getItemName() + getString(R.string.space) + getString(R.string.addToBasketConfirmation), Toast.LENGTH_SHORT).show();

                }
                else {
                    createAlert(getString(R.string.errorTitle), getString(R.string.quantitySizeError));
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.coffeeSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    /**
     * this function assigns the value of the item selected in the spinner to a string
     * @param parent the parent adapterView
     * @param view the spinner view
     * @param position  the position of the item selected
     * @param id the id that corresponds to spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice = parent.getItemAtPosition(position).toString();
    }

    /**
     * this function assigns the value of null to a string when there is no item
     * selected in the spinner view
     * @param parent the parent adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        choice = null;
    }

    /**
     * This function checks whether the input is a valid integer
     * @param str An integer as a string representation
     * @return true if the string can be parsed, false otherwise
     */
    private boolean validInt(String str) {
        try {
            int quantity = Integer.parseInt(textInputEditText.getText().toString().trim());
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