package com.myapplicationdev.databaserevision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnInsertRecord, btnRetrieveRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertRecord = findViewById(R.id.btnInsertRecord);
        btnRetrieveRecords = findViewById(R.id.btnGetRecord);

        btnInsertRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



            }
        });

        btnRetrieveRecords.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

    }
}