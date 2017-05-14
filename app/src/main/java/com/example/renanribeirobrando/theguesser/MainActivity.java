package com.example.renanribeirobrando.theguesser;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnGuess;
    private EditText txtGuess;
    private TextView lblLastGuess;
    private Integer input;
    private String result;
    final String MY_PREFS = "range";
    private Integer rand = 10;
    private Integer restoredValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finds components by id in the view via R Class
        btnGuess = (Button) findViewById(R.id.btn_guess);
        txtGuess = (EditText) findViewById(R.id.txt_guess);
        lblLastGuess = (TextView) findViewById(R.id.lbl_lastguess);



        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the input is not empty
                if (!txtGuess.getText().toString().equals("")) {
                    input = Integer.parseInt(txtGuess.getText().toString());
                    lblLastGuess.setText(input.toString());
                    if (input == rand) {
                        result = getResources().getString(R.string.str_equals);
                        // Generates a new number since the player has won
                        rand = new Random().nextInt(restoredValue);
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


    protected void onResume(){
        super.onResume();
        // Gets value stored in preferences
        SharedPreferences prefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        this.restoredValue = prefs.getInt("range", 10);
        // Generates a number between 0 and restoredValue
        rand = new Random().nextInt(restoredValue);
        Toast.makeText(getApplicationContext(), "Guess a number between 0 and " + String.valueOf(restoredValue) , Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
