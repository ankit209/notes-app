package com.maheshwari.notes;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JavaAddNoteActivity extends AppCompatActivity {

    private NotesStore notesStore;

    private EditText titleEdit;
    private EditText contentEdit;
    private Button saveNoteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesStore = ((MainApplication) getApplication()).getNotesStore();

        setContentView(R.layout.activity_add_note);

        titleEdit = findViewById(R.id.title_edit);
        contentEdit = findViewById(R.id.content_edit);
        saveNoteButton = findViewById(R.id.btn_save_note);

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdit.getText().toString();
                if (TextUtils.isEmpty(title)){
                    Toast.makeText(JavaAddNoteActivity.this, "Please enter title", Toast.LENGTH_SHORT).show();
                    return;
                }
                String content = contentEdit.getText().toString();
                if (TextUtils.isEmpty(content)){
                    Toast.makeText(JavaAddNoteActivity.this, "Please enter content", Toast.LENGTH_SHORT).show();
                    return;
                }
                notesStore.saveNote(new Note(title, content));
                finish();
            }
        });
    }
}
