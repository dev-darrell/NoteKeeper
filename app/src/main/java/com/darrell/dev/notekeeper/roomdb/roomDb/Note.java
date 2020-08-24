package com.darrell.dev.notekeeper.roomdb.roomDb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int note_id = 0;
    public String note_title;
    public String note_text;
    public String note_course_id;
//    @Embedded(prefix = "courseTable")
//    public Course course;

    public Note(int id, String note_title, String note_text, String course_id) {
        this.note_id = id;
        this.note_title = note_title;
        this.note_text = note_text;
        this.note_course_id = course_id;
    }

    public Note(String note_title, String note_text, String note_course_id) {
        this.note_title = note_title;
        this.note_text = note_text;
        this.note_course_id = note_course_id;
    }
}
