package com.myapplicationdev.databaserevision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    Button btnInsert;
    EditText etContent, etPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        btnInsert = findViewById(R.id.btnInsert);
        etContent = findViewById(R.id.etContent);
        etPriority = findViewById(R.id.etPriority);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the activity's Context
                String content = etContent.getText().toString().trim();
                String priorityStr = etPriority.getText().toString().trim();

                if (content.isEmpty() || priorityStr.isEmpty()) {
                    Log.d("InsertActivity", "Input fields are empty");
                    return;
                }

                int priority = Integer.parseInt(priorityStr);



                DBHelper db = new DBHelper(InsertActivity.this);

                long insertedId = db.insertTask(content, priority);

                if (insertedId != -1) {
                    Log.d("InsertActivity", "Task inserted successfully with ID: " + insertedId);
                } else {
                    Log.d("InsertActivity", "Error inserting task");
                }

                //db.insertTask();
                db.close();
            }
        });


    }
}