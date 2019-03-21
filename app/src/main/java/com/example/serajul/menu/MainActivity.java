package com.example.serajul.menu;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.show);
        sharedPreferences = this.getSharedPreferences("com.example.serajul.menu", MODE_PRIVATE);
        String language = sharedPreferences.getString("Language", "");

        if (language == "") {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.sym_def_app_icon)
                    .setTitle("Shared Preference App")
                    .setMessage("Which language do u want to select?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Bangla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("Bangla");
                        }
                    })
                    .show();
        } else {
            tv.setText(language);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.english) {
            setLanguage("Engliah");
        }

        if (item.getItemId() == R.id.bangla) {
            setLanguage("Bangla");
        }
        return true;
    }

    private void setLanguage(String language) {

        sharedPreferences.edit().putString("Language", language).commit();
        tv.setText(language);
    }
}
