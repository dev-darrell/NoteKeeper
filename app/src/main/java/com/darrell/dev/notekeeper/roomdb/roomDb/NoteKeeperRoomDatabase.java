package com.darrell.dev.notekeeper.roomdb.roomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class, Course.class}, version = 1, exportSchema = false)
public abstract class NoteKeeperRoomDatabase extends RoomDatabase {
    abstract NoteKeeperDao noteKeeperDao();


    static NoteKeeperRoomDatabase noteKeeperDbInstance = null;

    public static NoteKeeperRoomDatabase getDatabase(Context context) {
        if (noteKeeperDbInstance == null) {
            noteKeeperDbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteKeeperRoomDatabase.class, "Notekeeper_database")
                    .build();
        }
        return noteKeeperDbInstance;
    }
}
