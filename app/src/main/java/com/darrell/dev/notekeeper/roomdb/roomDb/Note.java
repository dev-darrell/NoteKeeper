package com.darrell.dev.notekeeper.roomdb.roomDb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int note_id;
    public String note_title;
    public String note_text;
    public String note_course_id;

    @Ignore
    public Note(String note_title, String note_text, String note_course_id) {
        this.note_title = note_title;
        this.note_text = note_text;
        this.note_course_id = note_course_id;
    }

    public Note(int note_id, String note_title, String note_text, String note_course_id) {
        this.note_id = note_id;
        this.note_title = note_title;
        this.note_text = note_text;
        this.note_course_id = note_course_id;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public String getNote_course_id() {
        return note_course_id;
    }

    public void setNote_course_id(String note_course_id) {
        this.note_course_id = note_course_id;
    }
}
