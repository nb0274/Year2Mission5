package com.example.year2mission5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    AlertDialog.Builder my_dialog;
    LinearLayout dialog;
    EditText dialogFirstValue;
    EditText dialogMarginOrMultiplex;
    RadioButton dialogMathmatical;
    RadioButton dialogGeometrical;
    ListView seriesList;
    ArrayAdapter<Double> adapterForList;
    TextView x1;
    TextView d;
    TextView n;
    TextView sn;

    String mathmaticalOrGeometric;
    double firstNumber;
    String firstNumberSTR;
    double marginOrMulitplex;
    String marginOrMulitplexSTR;
    Double[] arrOfSeries = new Double[20];
    double sum;
    Intent in;

    DialogInterface.OnClickListener onDialogBtnClick = new DialogInterface.OnClickListener() {
        /**
         * this func is activated when one of the dialog buttons are pressed and reset the inputs, cancel the dialog or activating the inputs according to the pressed button
         * @param dialog the dialog that received the click
         * @param which the button that was clicked (ex.
         *              {@link DialogInterface#BUTTON_POSITIVE}) or the position
         *              of the item clicked
         */
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // Apply button
            if(which == DialogInterface.BUTTON_POSITIVE) {
                // Saves the data of the series
                if(dialogGeometrical.isChecked())
                    mathmaticalOrGeometric = "geometric";
                if(dialogMathmatical.isChecked())
                    mathmaticalOrGeometric = "mathmatical";

                firstNumberSTR = dialogFirstValue.getText().toString();
                marginOrMulitplexSTR = dialogMarginOrMultiplex.getText().toString();

                if((!firstNumberSTR.equals("")) && (!marginOrMulitplexSTR.equals(""))){
                    firstNumber = Double.parseDouble(firstNumberSTR);
                    marginOrMulitplex = Double.parseDouble(marginOrMulitplexSTR);

                    // creates the arr
                    if(mathmaticalOrGeometric.equals("geometric"))
                    {
                        for(int i = 0; i < 20; i++)
                        {
                            arrOfSeries[i] = firstNumber * Math.pow(marginOrMulitplex, i);
                        }
                    }
                    else
                    {
                        for(int i = 0; i < 20; i++)
                        {
                            arrOfSeries[i] = firstNumber + marginOrMulitplex * i;
                        }
                    }
                    seriesList.setAdapter(adapterForList);

                    x1.setText(firstNumberSTR);
                    d.setText(marginOrMulitplexSTR);
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid data, please try again!",
                            Toast.LENGTH_LONG).show();
                }
            }
            else if(which == DialogInterface.BUTTON_NEUTRAL)
            {
                seriesList.setAdapter(null);

                dialogFirstValue.setText("");
                dialogMarginOrMultiplex.setText("");

                x1.setText("");
                d.setText("");
                n.setText("");
                sn.setText("");

                firstNumberSTR = "";
                marginOrMulitplexSTR = "";
            }
            else
            {
                dialog.cancel();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seriesList = (ListView) findViewById(R.id.listSeriesList);
        x1 = (TextView) findViewById(R.id.tvX1);
        d = (TextView) findViewById(R.id.tvD);
        n = (TextView) findViewById(R.id.tvN);
        sn = (TextView) findViewById(R.id.tvSn);


        adapterForList = new ArrayAdapter<Double>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrOfSeries);
        //seriesList.setAdapter(adapterForList);
        seriesList.setOnItemClickListener(this);
    }

    /**
     * this func sets the sum and the n edit texrs to the position and the sum for the currnt pressed item from the list view
     * @param parent The AdapterView where the click happened.
     * @param view The view within the AdapterView that was clicked (this
     *            will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        n.setText((position + 1) + "");

        if(mathmaticalOrGeometric.equals("geometric"))
        {
            sum = firstNumber * ((Math.pow(marginOrMulitplex, position + 1) - 1) / (marginOrMulitplex - 1));
        }
        else
        {
            sum = ((position + 1) * (firstNumber + (firstNumber + marginOrMulitplex * position))) / 2;
        }
        sn.setText("" + sum);
    }

    /**
     * this func creates the alert dialog and shows it on the screen
     * @param view
     */
    public void start(View view) {
        dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);

        dialogFirstValue = (EditText) dialog.findViewById(R.id.dialogFirstValue);
        dialogMarginOrMultiplex = (EditText) dialog.findViewById(R.id.dialogMarginOrMultiplex);
        dialogMathmatical = (RadioButton) dialog.findViewById(R.id.dialogMathmatical);
        dialogGeometrical = (RadioButton) dialog.findViewById(R.id.dialogGeometrical);

        my_dialog = new AlertDialog.Builder(this);

        my_dialog.setView(dialog);
        my_dialog.setTitle("Series data input");
        my_dialog.setCancelable(false);
        my_dialog.setPositiveButton("Generate arr", onDialogBtnClick);
        my_dialog.setNeutralButton("Delete inputs", onDialogBtnClick);
        my_dialog.setNegativeButton("Cancel", onDialogBtnClick);

        if(mathmaticalOrGeometric.equals("geometric"))
            dialogGeometrical.setChecked(true);
        else
            dialogMathmatical.setChecked(true);
        dialogFirstValue.setText(firstNumberSTR);
        dialogMarginOrMultiplex.setText(marginOrMulitplexSTR);
        my_dialog.show();
    }

    /**
     * goes to next activity(credits)
     * @param view
     */
    public void goNext(View view) {
        in = new Intent(this, MainActivity2.class);
        startActivity(in);
    }
}
