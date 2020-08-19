package com.darrell.dev.notekeeper.roomDb;

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

    @Query("select note.note_id, note.note_text, note_title, note.course_id, " +
            "course.course_title from note join course on (note.course_id = course.course_id) " +
            "where note_id == :noteId")
    LiveData<CourseWithNote> getNote(int noteId);
}
