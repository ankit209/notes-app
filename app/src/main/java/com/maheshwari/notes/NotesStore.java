package com.maheshwari.notes;

import java.util.List;

interface NotesStore {

    List<Note> getNotes();

    void saveNote(Note note);

}
