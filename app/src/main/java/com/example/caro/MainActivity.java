package com.example.caro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnExit = findViewById(R.id.btnExit);

        btnPlay.setOnClickListener(v -> {
            startActivity(new Intent(this, GameActivity.class));
        });

        btnExit.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Thoát")
                    .setMessage("Bạn có muốn thoát?")
                    .setPositiveButton("Yes", (d,w)-> finish())
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}