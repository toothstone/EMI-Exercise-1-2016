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
        }
        catch (NumberFormatException e) {
            tvOutput.setText("Die Eingabe übersteigt den zulässigen Wertebereich");}
    }

    /**
     * Diese Funktion berechnet iterativ die Quersumme einer Zahl
     * Dabei wird die der Rest der Zahl geteilt durch 10 addiert, anschließend wird die Zahl durch 10 geiteilt,
     * solange bis das Ergebnis der Division kleiner 1 ist.
     * @param i - Number
     * @return digitsum of i
     */
    private int getDigitSum(int i) {
        int dsum=0;
        while (i>=1)
        {   dsum = dsum + (i%10);
            i=i/10;}
        return (dsum);
    }
}
