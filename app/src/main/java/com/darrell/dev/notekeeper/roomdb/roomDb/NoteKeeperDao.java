package com.darrell.dev.notekeeper.roomdb.roomDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface NoteKeeperDao {

    @Insert
    long insertNote(Note note);

    @Insert
    long insertCourse(Course course);

    @Update
    void updateNote(Note note);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteNote(Note note);

    @Delete
    void deleteCourse(Course course);

    @Query("select * from note where note_id = :noteId")
    LiveData<Note> getNote(int noteId);

    @Query("select * from Course")
    LiveData<List<Course>> getAllCourses();

    @Query("select note_id, note_title, course_title from note join course on " +
            "(note_course_id = course_id)")
    LiveData<List<CourseWithNote>> getAllNoteInfo();
}
