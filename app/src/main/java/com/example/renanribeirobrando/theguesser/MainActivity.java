package com.example.renanribeirobrando.theguesser;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnGuess;
    private EditText txtGuess;
    private TextView lblLastGuess;
    private Integer input;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finds components by id in the view via R Class
        btnGuess = (Button) findViewById(R.id.btn_guess);
        txtGuess = (EditText) findViewById(R.id.txt_guess);
        lblLastGuess = (TextView) findViewById(R.id.lbl_lastguess);
        // Generates a number between 0 and 10
        final Integer rand = new Random().nextInt(10);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the input is not empty
                if (!txtGuess.getText().toString().equals("")) {
                    input = Integer.parseInt(txtGuess.getText().toString());
                    lblLastGuess.setText(input.toString());
                    if (input == rand) {
                        result = getResources().getString(R.string.str_equals);
                    } else if (input > rand) {
                        result = getResources().getString(R.string.str_higher);
                    } else {
                        result = getResources().getString(R.string.str_lower);
                    }
                    // Use the Builder class for convenient dialog construction
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setCancelable(false);
                    dialog.setMessage(result);
                    dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //Action for "Ok".
                        }
                    })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Action for "Cancel".
                                }
                            });

                    final AlertDialog alert = dialog.create();
                    alert.show();

                } else {
                    // Shows a toast saying that the user did not made an input
                    Toast.makeText(getApplicationContext(), R.string.str_help, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
