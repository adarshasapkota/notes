package io.github.adarshasapkota.notes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Note note = (Note) getIntent().getSerializableExtra("note");

        EditText editTextNote = findViewById(R.id.et_note);
        editTextNote.setText(note.getNote());

        TextView textViewCreatedDate = findViewById(R.id.tv_created_date);
        String string="Created: " + note.getCreatedDate();
        textViewCreatedDate.setText(string);

        TextView textViewLastModifiedDate = findViewById(R.id.tv_last_modified_date);
        String string1="Last modified: " + note.getLastModifiedDate();
        textViewLastModifiedDate.setText(string1);

        Button buttonUpdate = findViewById(R.id.btn_update);
        Button buttonDelete = findViewById(R.id.btn_delete);

        buttonDelete.setOnClickListener(v -> {
            SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
            if (sqlHelper.delete(note)) {
                finish();
                Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show();
        });

        buttonUpdate.setOnClickListener(v -> {
            String stringNote = editTextNote.getText().toString();
            if (!stringNote.isEmpty()) {
                SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
                Date date = new Date();
                Note note1 = new Note(note.getId(),stringNote, date.toString(), note.getCreatedDate());
                if (sqlHelper.update(note1)) {
                    finish();
                    Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this, "Failed to update", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Enter a note to update", Toast.LENGTH_SHORT).show();
        });
    }
}