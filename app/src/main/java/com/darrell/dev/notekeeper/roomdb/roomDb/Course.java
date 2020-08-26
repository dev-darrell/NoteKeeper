package com.darrell.dev.notekeeper.roomdb.roomDb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int course_key;
    public String course_id;
    public String course_title;

    @Ignore
    public Course(String course_id, String course_title) {
        this.course_id = course_id;
        this.course_title = course_title;
    }

    public Course(int course_key, String course_id, String course_title) {
        this.course_key = course_key;
        this.course_id = course_id;
        this.course_title = course_title;
    }

    public int getCourse_key() {
        return course_key;
    }

    public void setCourse_key(int course_key) {
        this.course_key = course_key;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }
}
