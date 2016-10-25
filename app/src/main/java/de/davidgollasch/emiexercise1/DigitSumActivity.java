package de.davidgollasch.emiexercise1;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DigitSumActivity extends AppCompatActivity {

    private TextView tvOutput;
    private EditText etNumber;
    private Button btnFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_sum);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.tuAkzentfarbe1BlauHell)));

        // Initialises the views and event listeners
        InitializeActivity();
    }

    /**
     * Construct the Interactive Structure
     */
    private void InitializeActivity() {
        tvOutput = (TextView) findViewById(R.id.textViewDsum);
        btnFire = (Button) findViewById(R.id.b_Dsum);
        etNumber = (EditText) findViewById(R.id.editNumberDsum);

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear text view
                tvOutput.setText("");

                // Invoke calculation
                CalculateDigitSum();
            }
        });
    }

    /**
     * Trigger the Digit Sum Calculation
     */
    private void CalculateDigitSum() {
        try{
            int qsum;
            qsum = Integer.parseInt(etNumber.getText().toString());
            qsum = getDigitSum(qsum);
            tvOutput.setText(Integer.toString(qsum));
        /*
         * b) Calculate digit sum
         *    (Hint: This can be done recursively using an additional function/method
         *    private int getDigitSum(int n))
         */

        /*
         * c) Print result
         */

        }
        catch (NumberFormatException e) {
            tvOutput.setText("Die Eingabe übersteigt den zulässigen Wertebereich");}
    }

    private int getDigitSum(int i) {
        if (i<=9) {return i;}
        return (i%10) + getDigitSum(i/10);
    }
}
