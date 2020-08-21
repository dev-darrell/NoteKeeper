package com.darrell.dev.notekeeper.roomDb;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CourseWithNote {
    @Embedded
    public Course course;
    @Relation(parentColumn = "course_id", entityColumn = "note_course_id")
    public List<Note> note;

    public CourseWithNote(List<Note> note, Course course) {
        this.note = note;
        this.course = course;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
