package com.darrell.dev.notekeeper.roomDb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey
    public String course_id;
    public String course_title;

    public Course(String course_id, String course_title) {
        this.course_id = course_id;
        this.course_title = course_title;
    }
}
