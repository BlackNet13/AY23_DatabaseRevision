package com.myapplicationdev.databaserevision;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RetrieveActivityListView extends AppCompatActivity {

    Button btnGetNotes;

    ListView lv;
    ArrayAdapter<Note> aa;
    ArrayList<Note> al;
    ArrayList<Note> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_lv);

        btnGetNotes = findViewById(R.id.btnGetTasks);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        aa = new ArrayAdapter<Note>(RetrieveActivityListView.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnGetNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the activity's Context
                DBHelper db = new DBHelper(RetrieveActivityListView.this);
                data = db.getNotesInObjects();
                for(int i = 0; i<data.size(); i++){
                    al.add(data.get(i));
                }

            }
        });

                ArrayList<Note> notes = db.getNotesInObjects();

                // Clear the existing list and add retrieved notes
                al.clear();
                al.addAll(notes);

                // Notify the ArrayAdapter about the data change
                aa.notifyDataSetChanged();

                db.close();

        //Option: Implement dialog to edit a record
        //Option: Implement context to delete a record

    }
}