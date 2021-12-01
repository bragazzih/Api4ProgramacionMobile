package com.example.tokengenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inTap(View view) {
        Intent newIntent  = new Intent(this, GenerateToken.class);
        startActivity(newIntent);
    }

    public void click(View view) {
        Intent newIntent  = new Intent(this, AddNewTokenName.class);
        startActivity(newIntent);
    }

}