/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    int toppingsCost = 0;
    String userName = "";
    String body = "";
    int quantity = 2;

    /**
     * This method is called when the order button is clicked.
     * This will call on summary generation and to have an email sent
     * This will check the order and call on pricing calculation
     */
    public void submitOrder(View view) {
        toppingsCost = 0;
        //Figure out if customer wants whip cream
        CheckBox whippedCreamBox = findViewById(R.id.whippedCream);
        Boolean hasWhippedCream = whippedCreamBox.isChecked();
        if (hasWhippedCream) {
            toppingsCost = toppingsCost + 1;
        }
        //Figure out if customer wants chocolate
        CheckBox chocolateBox = findViewById(R.id.chocolate);
        Boolean hasChocolate = chocolateBox.isChecked();
        if (hasChocolate) {
            toppingsCost = toppingsCost + 2;
        }
        //Read customer name field
        EditText uName = findViewById(R.id.nameField);
        String userName = uName.getText().toString();
        //Display
        int price = calculatePrice(toppingsCost);
        body = createOrderSummary(price, hasWhippedCream, hasChocolate, userName);
        String addresses = "jamamto1990@gmail.com";
        String subject = "JustJava order for " + userName;
        composeEmail(addresses, subject, body);
    }

    /**
     * puts together the order info
     * int price = total cost for summary
     * hasWhippedCream = boolean readout for whip cream check box
     *
     * @param hasChocolate boolean readout for chocolate checkbox
     */
    public String createOrderSummary(int price, Boolean hasWhippedCream, Boolean hasChocolate, CharSequence userName) {
        String addWhipCream = "";
        String addChocolate = "";
        if (hasWhippedCream) {
            addWhipCream = "ADD Whipped Cream";
        } else {
            addWhipCream = "NO Whipped Cream";
        }
        if (hasChocolate) {
            addChocolate = "ADD Chocolate";
        } else {
            addChocolate = "NO Chocolate";
        }
        return "Name: " + userName +
                "\n" + addWhipCream +
                "\n" + addChocolate +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price + "\nThank you!";
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(int toppingsCost) {
        int price = quantity * (5 + toppingsCost);
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
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//        }

    /**
     * These methods make the plus and minus buttons make the quantity go up and down
     * Toast messeges will show outside of 1-100 bounds
     */
    public void increment(View view) {
        if (quantity >= 100) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "CANNOT ORDER THAT MANY COFFEES!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.no_negative) , Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    //Send email
    public void composeEmail(String addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{addresses});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "no mail application installed", Toast.LENGTH_SHORT);
        }
    }
}