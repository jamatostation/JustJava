/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        CheckBox whippedCreamBox = findViewById(R.id.whippedCream);
        Boolean hasWhippedCream = whippedCreamBox.isChecked();
        displayMessage(createOrderSummary(price, hasWhippedCream));
    }

    /**
     * puts together the order info
     * int price = total cost for summary
     * hasWhippedCream = boolean readout for whip cream check box
     */
    public String createOrderSummary(int price, Boolean hasWhippedCream) {
        return "Name: Kaptain Kunal" + "\nAdd whipped cream? " + hasWhippedCream +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price + "\nThank you!";
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * These methods make the plus and minus buttons make the quantity go up and down
     */
    int quantity = 2;
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


}