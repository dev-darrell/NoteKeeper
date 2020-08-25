package com.darrell.dev.notekeeper.roomdb.roomDb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class CourseWithNote {

    @ColumnInfo(name = "note_id")
    @NonNull
    public int noteId;
    @ColumnInfo(name = "note_title")
    public String noteTitle;
    @ColumnInfo(name = "course_title")
    public String courseTitle;

    public CourseWithNote(@NonNull int noteId, String noteTitle, String courseTitle) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.courseTitle = courseTitle;
    }

    @NonNull
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(@NonNull int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
