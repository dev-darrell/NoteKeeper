package com.darrell.dev.notekeeper.roomDb;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteKeeperDao {

    @Insert
    void insertNote(Note note);

    @Insert
    void insertCourse(Course course);

    @Update
    void updateNote(Note note);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteNote(Note note);

    @Delete
    void deleteCourse(Course course);

    @Query("select note_text, note_title, note_course_id from note where note_id = :noteId")
    LiveData<Note> getNote(int noteId);

    @Query("select * from Course")
    LiveData<Cursor> getAllCourses();

    @Query("select note_id, note_title, course_title from note join course on " +
            "(note_course_id = course_id)")
    LiveData<Cursor> getAllNoteInfo();
}
