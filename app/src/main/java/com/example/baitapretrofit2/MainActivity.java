package com.example.baitapretrofit2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenRetrofit = findViewById(R.id.btnOpenRetrofit);
        btnOpenRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang RetrofitActivity
                Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(intent);
            }
        });
    }
}
