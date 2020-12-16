package com.maheshwari.notes;

import android.app.Application;

public class MainApplication extends Application {

    private NotesStore notesStore;

    public NotesStore getNotesStore() {
        if (notesStore == null){
            notesStore = new NotesStoreImpl(getApplicationContext());
        }
        return notesStore;
    }
}
