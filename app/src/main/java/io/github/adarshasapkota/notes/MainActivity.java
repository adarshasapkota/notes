package io.github.adarshasapkota.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    
    private ArrayList<Note> arrayListNotes;
    private NoteAdapter noteAdapter;
    private SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayListNotes=new ArrayList<>();
        RecyclerView recyclerViewNotes = findViewById(R.id.rv_notes);
        noteAdapter=new NoteAdapter(arrayListNotes, this, recyclerViewNotes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(noteAdapter);
        sqlHelper=new SqlHelper(getApplicationContext());
        EditText editTextNote=findViewById(R.id.et_note);
        Button buttonAdd=findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(v -> {
            String stringNote=editTextNote.getText().toString().trim();
            if(!stringNote.isEmpty()){
                editTextNote.setText("");
                Date date=new Date();
                Note note=new Note(stringNote, date.toString(), date.toString());
                if(sqlHelper.create(note)) {
                    String id=String.valueOf(Integer.parseInt(arrayListNotes.get(arrayListNotes.size()-1).getId())+1);
                    note.setId(id);
                    arrayListNotes.add(note);
                    noteAdapter.notifyDataSetChanged();
                    Toast.makeText(
                            MainActivity.this,
                            "Added successfully",
                            Toast.LENGTH_SHORT)
                            .show();
                }else
                    Toast.makeText(MainActivity.this,
                            "Failed to add note",
                            Toast.LENGTH_SHORT)
                            .show();
            }else
                Toast.makeText(MainActivity.this,
                        "Enter a note to add",
                        Toast.LENGTH_SHORT)
                        .show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayListNotes.clear();
        getNotes();
        noteAdapter.notifyDataSetChanged();
    }

    private void getNotes(){
        Cursor cursor = sqlHelper.read();
        while(cursor.moveToNext()){
            arrayListNotes.add(new Note(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)
                    ));
        }
    }

}