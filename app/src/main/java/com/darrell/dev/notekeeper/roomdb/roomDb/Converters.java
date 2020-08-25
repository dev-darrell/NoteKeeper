package com.darrell.dev.notekeeper.roomdb.roomDb;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public LiveData<Cursor> fromCursor(Cursor cursor) {
        return cursor == null ? null : (LiveData<Cursor>) cursor;
    }

    @TypeConverter
    public Cursor liveDataToCursor(LiveData<Cursor> cursorLiveData) {
        return cursorLiveData == null ? null : (Cursor) cursorLiveData;
    }
}
