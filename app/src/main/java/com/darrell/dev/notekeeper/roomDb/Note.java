package com.darrell.dev.notekeeper.roomDb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int note_id;
    public String note_title;
    public String note_text;
    public String course_id;

    public Note(int id, String note_title, String note_text, String course_id) {
        this.note_id = id;
        this.note_title = note_title;
        this.note_text = note_text;
        this.course_id = course_id;
    }
}