package com.example.mybiodataapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private EditText tvName;
    private EditText tvAge;
    private EditText tvEmail;
    private EditText tvPhone;
    private EditText tvPlace;
    private Button btnEmail;
    private Button btnPhone;
    private Button btnPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvPlace = findViewById(R.id.tvPlace);
        btnEmail = findViewById(R.id.btnEmail);
        btnPhone = findViewById(R.id.btnPhone);
        btnPlace = findViewById(R.id.btnPlace);

        tvName.setText(getIntent().getStringExtra("name"));
        tvAge.setText(Integer.toString(getIntent().getIntExtra("age", 0)));
        tvEmail.setText(getIntent().getStringExtra("email"));
        tvPhone.setText(getIntent().getStringExtra("phone"));
        tvPlace.setText(getIntent().getStringExtra("place"));

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String msg = "Hello my name is "+getIntent().getStringExtra("name")+"\nI'am "+Integer.toString(getIntent().getIntExtra("age",0))+" years old.";
                intent.setData(Uri.parse("mailto: "+getIntent().getStringExtra("email")));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Greeting");
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(Intent.createChooser(intent, "Choose an email client: "));
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"));
                intent.putExtra(Intent.EXTRA_PHONE_NUMBER, getIntent().getStringExtra("phone"));
                if (ActivityCompat.checkSelfPermission(ResultActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q="+getIntent().getStringExtra("place")));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }
}
