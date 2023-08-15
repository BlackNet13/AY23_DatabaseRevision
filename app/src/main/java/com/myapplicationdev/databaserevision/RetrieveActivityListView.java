package com.myapplicationdev.databaserevision;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

public class RetrieveActivityListView extends AppCompatActivity {

    Button btnGetNotes;

    ListView lv;
    ArrayAdapter<Note> aa;
    ArrayList<Note> al;
    Note data;

    DBHelper db;

    int pos;
    int index;

    String content;
    int priority;

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
                db = new DBHelper(RetrieveActivityListView.this);


                al.clear();
                al.addAll(db.getNotesInObjects());
                aa.notifyDataSetChanged();

            }
        });



        MaterialDialog deleteDialog = new MaterialDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Delete data?")
                .setCancelable(false)
                .setPositiveButton("Delete", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        db = new DBHelper(RetrieveActivityListView.this);
                        db.deleteNote(pos);

                        al.clear();
                        al.addAll(db.getNotesInObjects());
                        aa.notifyDataSetChanged();

                        Toast toast = Toast.makeText(RetrieveActivityListView.this, "Data has been deleted" ,Toast.LENGTH_LONG);

                        toast.show();
                    }
                })
                .setNegativeButton("Cancel", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        Toast toast = Toast.makeText(RetrieveActivityListView.this, "No changes has been made",Toast.LENGTH_LONG);

                        toast.show();
                    }
                })
                .build();


        MaterialDialog initDialog = new MaterialDialog.Builder(this)
                .setTitle("Choose what to do with data:")
                .setMessage("Click away to cancel")
                .setCancelable(true)
                .setPositiveButton("Edit", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View viewDialog = inflater.inflate(R.layout.input, null);

                        final EditText edInput1 = viewDialog.findViewById(R.id.edInput1);
                        final EditText edInput2 = viewDialog.findViewById(R.id.edInput2);

                        Note selectedNote = al.get(pos);
                        String oldC = selectedNote.getContent();
                        int oldP = selectedNote.getPriority();

                        edInput1.setText(oldC);
                        edInput2.setText(String.valueOf(oldP));

                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(RetrieveActivityListView.this);
                        myBuilder.setView(viewDialog); //set view of dialog
                        myBuilder.setTitle("Confirm Edit");
                        myBuilder.setPositiveButton("Edit", new android.content.DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(android.content.DialogInterface dialog, int which) {
                                //Extract the text entered by the user
                                String newC = edInput1.getText().toString();
                                int newP = Integer.parseInt(edInput2.getText().toString());


                                Note selectedNote = al.get(pos);
                                selectedNote.setNote(newC,newP);

                                db = new DBHelper(RetrieveActivityListView.this);
                                db.updateNote(selectedNote);

                                al.clear();
                                al.addAll(db.getNotesInObjects());
                                aa.notifyDataSetChanged();

                            }
                        });
                        myBuilder.setNegativeButton("CANCEL",null);
                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Delete", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        deleteDialog.show();
                        dialogInterface.dismiss();
                    }
                })
                .build();




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db = new DBHelper(RetrieveActivityListView.this);

                Note selectedNote = al.get(position);
                pos = position;
                index = selectedNote.getId();
                content = selectedNote.getContent();
                priority = selectedNote.getPriority();
                initDialog.show();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        db = new DBHelper(RetrieveActivityListView.this);


        al.clear();
        al.addAll(db.getNotesInObjects());
        aa.notifyDataSetChanged();

    }

}