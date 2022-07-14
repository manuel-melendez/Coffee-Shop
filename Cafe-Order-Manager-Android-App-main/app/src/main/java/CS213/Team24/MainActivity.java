package CS213.Team24;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class controls interactions in the main view
 * @author Jesse Fisher and Manuel Melendez
 */
public class MainActivity extends AppCompatActivity {

    private Button coffeeButton;
    private Button donutButton;
    private Button basketButton;
    private Button ordersButton;

    public static StoreOrders storeOrders;
    public static Order currentOrder;

    /**
     * Initial setup for the Views
     * @param savedInstanceState provided data bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeOrders = new StoreOrders();
        currentOrder = new Order();

        coffeeButton = findViewById(R.id.coffeeButton);
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * this function opens the order coffee activity when a button is clicked
             * @param v a button view
             */
            @Override
            public void onClick(View v) {
                openCoffeeActivity();
            }
        });

        donutButton = findViewById(R.id.donutButton);
        donutButton.setOnClickListener(new View.OnClickListener() {
            /**
             * this function opens the order donut activity when a button is clicked
             * @param v a button view
             */
            @Override
            public void onClick(View v) {
                openDonutActivity();
            }
        });

        basketButton = findViewById(R.id.basketButton);
        basketButton.setOnClickListener(new View.OnClickListener() {
            /**
             * this function opens the order basket activity when a button is clicked
             * @param v a button view
             */
            @Override
            public void onClick(View v) {
                openBasketActivity();
            }
        });

        ordersButton = findViewById(R.id.ordersButton);
        ordersButton.setOnClickListener(new View.OnClickListener() {
            /**
             * this function opens the store orders activity when a button is clicked
             * @param v a button view
             */
            @Override
            public void onClick(View v) {
                openOrdersActivity();
            }
        });
    }

    /**
     * function for opening a new activity
     */
    public void openCoffeeActivity(){
        Intent intent = new Intent(this, OrderCoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * function for opening a new activity
     */
    public void openDonutActivity(){
        Intent intent = new Intent(this, OrderDonutsActivity.class);
        startActivity(intent);
    }

    /**
     * function for opening a new activity
     */
    public void openBasketActivity(){
        Intent intent = new Intent(this, OrderBasketActivity.class);
        startActivity(intent);
    }

    /**
     * function for opening a new activity
     */
    public void openOrdersActivity(){
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }

}