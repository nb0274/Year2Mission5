package com.example.year2mission5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView creditsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        creditsText = (TextView) findViewById(R.id.creditsText);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        menu.add("Me(creator)");
        menu.add("Mentor(teacher)");

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * this func activates when an item from the menu is pressed and changes the credits
     * @param item The menu item that was selected.
     *
     * @return
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String choice = item.getTitle().toString();

        if (choice.equals("Me(creator)")) {
            creditsText.setText("Made by Noam Barazi");
        } else {
            creditsText.setText("teacher: Albert");
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * goes back to the main activity
     * @param view
     */
    public void goBack(View view) {
        finish();
    }
}