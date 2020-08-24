package com.darrell.dev.notekeeper.roomdb.roomDb;

import android.app.Application;
import android.database.Cursor;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NKeeperViewModel extends AndroidViewModel {
    private NoteKeeperDao mNoteKeeperDao;
    public LiveData<Cursor> allNoteInfo;
    public LiveData<List<Course>> allCourses;

    public NKeeperViewModel(@NonNull Application application) {
        super(application);

        NoteKeeperRoomDatabase noteKeeperDb = NoteKeeperRoomDatabase.getDatabase(application);
        mNoteKeeperDao = noteKeeperDb.noteKeeperDao();

        allNoteInfo = mNoteKeeperDao.getAllNoteInfo();
        allCourses = mNoteKeeperDao.getAllCourses();
    }

    public LiveData<Note> getNote(int noteId){
        return mNoteKeeperDao.getNote(noteId);
    }

    public void insertNote(Note note){
        new UpdateNoteAsyncTask(mNoteKeeperDao).execute(note);
    }

    public void insertCourse(Course course){
        new InsertCourseAsyncTask(mNoteKeeperDao).execute(course);
    }

    public void updateNote(Note note){
        new UpdateNoteAsyncTask(mNoteKeeperDao).execute(note);
    }

    public void updateCourse(Course course){
        new UpdateCourseAsyncTask(mNoteKeeperDao).execute(course);
    }

    public void deleteNote(Note note){
        new DeleteNoteAsyncTask(mNoteKeeperDao).execute(note);
    }

    public void deleteCourse(Course course){
        new DeleteCourseAsyncTask(mNoteKeeperDao).execute(course);
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteKeeperDao asyncDao;
        public InsertNoteAsyncTask(NoteKeeperDao noteKeeperDao) {
            asyncDao = noteKeeperDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncDao.insertNote(notes[0]);
            return null;
        }
    }

    private static class InsertCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private NoteKeeperDao asyncDao;
        public InsertCourseAsyncTask(NoteKeeperDao noteKeeperDao) {
            asyncDao = noteKeeperDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            asyncDao.insertCourse(courses[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteKeeperDao asyncDao;
        public UpdateNoteAsyncTask(NoteKeeperDao noteKeeperDao) {
            asyncDao = noteKeeperDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncDao.updateNote(notes[0]);
            return null;
        }
    }

    private static class UpdateCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private NoteKeeperDao asyncDao;
        public UpdateCourseAsyncTask(NoteKeeperDao noteKeeperDao) {
            asyncDao = noteKeeperDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            asyncDao.updateCourse(courses[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteKeeperDao asyncDao;
        public DeleteNoteAsyncTask(NoteKeeperDao noteKeeperDao) {
            asyncDao = noteKeeperDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncDao.deleteNote(notes[0]);
            return null;
        }
    }

    private static class DeleteCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private NoteKeeperDao asyncDao;
        public DeleteCourseAsyncTask(NoteKeeperDao noteKeeperDao) {
            asyncDao = noteKeeperDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            asyncDao.deleteCourse(courses[0]);
            return null;
        }
    }
}
