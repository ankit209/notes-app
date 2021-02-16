package com.interview.notes;

import java.util.List;

interface NotesStore {

    List<Note> getNotes();

    void saveNote(Note note);

}
