package com.example.tokengenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class AddNewTokenName extends AppCompatActivity {

    Button insertName;
    EditText tokenName;
    DbConfig db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_token_name);

        tokenName = findViewById(R.id.token);
        insertName = findViewById(R.id.addTokenName);
        db = new DbConfig(this);


        insertName.setOnClickListener(v -> {
            String name = tokenName.getText().toString();

            boolean checkdata = db.insertTokenName(name);
            if (checkdata == true) {
                Toast.makeText(AddNewTokenName.this, "Added token name", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(AddNewTokenName.this, "Could not add token name", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


