package com.darrell.dev.notekeeper.roomdb.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.darrell.dev.notekeeper.roomdb.database.NoteKeeperDatabaseContract.CourseInfoEntry;
import com.darrell.dev.notekeeper.roomdb.database.NoteKeeperDatabaseContract.NoteInfoEntry;

public class NoteKeeperOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NoteKeeper.db";
    public static final int DATABASE_VERSION = 2;
    private static final String TAG = "NoteKeeperOpenHelper";

    public NoteKeeperOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: SQLite Database");
        sqLiteDatabase.execSQL(CourseInfoEntry.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(NoteInfoEntry.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(CourseInfoEntry.SQL_CREATE_INDEX1);
        sqLiteDatabase.execSQL(NoteInfoEntry.SQL_CREATE_INDEX1);

        DatabaseDataWorker worker = new DatabaseDataWorker(sqLiteDatabase);
        worker.insertCourses();
        worker.insertSampleNotes();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i < 2) {
            sqLiteDatabase.execSQL(CourseInfoEntry.SQL_CREATE_INDEX1);
            sqLiteDatabase.execSQL(NoteInfoEntry.SQL_CREATE_INDEX1);
        }
    }
}
