package com.darrell.dev.notekeeper.roomDb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey
//    @ForeignKey(entity = )
    public String course_id;
}
