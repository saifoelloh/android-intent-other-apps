package com.example.mybiodataapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inName;
    private EditText inAge;
    private EditText inEmail;
    private EditText inPhone;
    private EditText inPlace;
    private Button btnSubmit;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inName = (EditText) findViewById(R.id.inName);
        inAge = (EditText) findViewById(R.id.inAge);
        inEmail = (EditText) findViewById(R.id.inEmail);
        inPhone = (EditText) findViewById(R.id.inPhone);
        inPlace = (EditText) findViewById(R.id.inPlace);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString("name", inName.getText().toString());
                bundle.putInt("age", Integer.parseInt(inAge.getText().toString()));
                bundle.putString("phone", inPhone.getText().toString());
                bundle.putString("email", inEmail.getText().toString());
                bundle.putString("place", inPlace.getText().toString());
                resultActivity(bundle);
            }
        });
    }

    protected void resultActivity(Bundle bundle) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
