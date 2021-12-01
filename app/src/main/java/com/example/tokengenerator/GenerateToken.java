package com.example.tokengenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class GenerateToken extends AppCompatActivity {

Button button;
TextView txtView;
Spinner spinner;
DbConfig db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_token);


        spinner = findViewById(R.id.tokenList);
        button = findViewById(R.id.newButton);
        txtView = findViewById(R.id.newText);
        db = new DbConfig(this);


        List<String> list = db.getAllTokenName();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_layout, R.id.txt, list);

        spinner.setAdapter(arrayAdapter);


        button.setOnClickListener(v -> {
            txtView.setText(generateToken());
            String generatedToken = txtView.getText().toString();
            String associatedTokenName = spinner.getSelectedItem().toString();

            boolean checkdata = db.insertGeneratedToken(associatedTokenName,generatedToken);
            if (checkdata == true) {
                Toast.makeText(GenerateToken.this, "Added entry", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(GenerateToken.this, "Could not add entry to database", Toast.LENGTH_SHORT).show();
            }
        });



    }





    private final String generateToken() {


        int len = 64;
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));

       return sb.toString();
    }

}
