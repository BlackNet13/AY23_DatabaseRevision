package com.myapplicationdev.databaserevision;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RetrieveActivityTextView extends AppCompatActivity {

    Button btnGetNotes;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_tv);

        btnGetNotes = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        btnGetNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(RetrieveActivityTextView.this);

                // Retrieve notes as ArrayList of Note objects
                ArrayList<Note> notes = db.getNotesInObjects();

                // Display the notes in the TextView
                StringBuilder results = new StringBuilder();
                for (Note note : notes) {
                    results.append("ID: ").append(note.getId()).append("\n");
                    results.append("Content: ").append(note.getContent()).append("\n");
                    results.append("Priority: ").append(note.getPriority()).append("\n\n");
                }

                tvResults.setText(results.toString());

                db.close();


            }
        });

    }
}