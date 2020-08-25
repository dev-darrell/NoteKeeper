package com.darrell.dev.notekeeper.roomdb.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darrell.dev.notekeeper.roomdb.R;
import com.darrell.dev.notekeeper.roomdb.adapter.CourseRecyclerAdapter;
import com.darrell.dev.notekeeper.roomdb.adapter.NoteRecyclerAdapter;
import com.darrell.dev.notekeeper.roomdb.database.DataManager;
import com.darrell.dev.notekeeper.roomdb.model.CourseInfo;
import com.darrell.dev.notekeeper.roomdb.roomDb.CourseWithNote;
import com.darrell.dev.notekeeper.roomdb.roomDb.NKeeperViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int LOADER_NOTES = 0;
    private NoteRecyclerAdapter mNoteRecyclerAdapter;
    private RecyclerView mRecyclerItems;
    private LinearLayoutManager mNotesLayoutManager;
    private CourseRecyclerAdapter mCourseRecyclerAdapter;
    private GridLayoutManager mCoursesLayoutManager;
//    private NoteKeeperOpenHelper mDbOpenHelper;

    @Override
    protected void onDestroy() {
//        mDbOpenHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        mDbOpenHelper = new NoteKeeperOpenHelper(this);


        new ViewModelProviders();
        NKeeperViewModel keeperViewModel = ViewModelProviders.of(this).get(NKeeperViewModel.class);

        final Observer<List<CourseWithNote>> noteObserver = new Observer<List<CourseWithNote>>() {
            @Override
            public void onChanged(List<CourseWithNote> noteInfo) {
//                TODO: Add break point in recyclerView adapter and debug to see if it's being called by the Observer.
                mNoteRecyclerAdapter.changeDataList(noteInfo);
            }
        };
        keeperViewModel.allNoteInfo.observe(this, noteObserver);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecyclerItems.getAdapter() == mNoteRecyclerAdapter) {
                    startActivity(new Intent(MainActivity.this, NoteActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, CourseActivity.class));
                }
            }
        });

        PreferenceManager.setDefaultValues(this, R.xml.general_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.messages_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.sync_preferences, false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        getLoaderManager().restartLoader(LOADER_NOTES, null, this);
        updateNavHeader();
    }

    private void updateNavHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView textUserName = headerView.findViewById(R.id.text_user_name);
        TextView textEmailAddress = headerView.findViewById(R.id.text_email_address);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = pref.getString("user_display_name", "");
        String emailAddress = pref.getString("user_email_address", "");

        textUserName.setText(userName);
        textEmailAddress.setText(emailAddress);
    }

//    private void loadNotes() {
//        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
//        final String[] noteColumns = {
//                NoteInfoEntry.COLUMN_NOTE_TITLE,
//                NoteInfoEntry.COLUMN_COURSE_ID,
//                NoteInfoEntry._ID};
//
//        String noteOrderBy = NoteInfoEntry.COLUMN_COURSE_ID + ", " +
//                NoteInfoEntry.COLUMN_NOTE_TITLE;
//        final Cursor noteCursor = db.query(NoteInfoEntry.TABLE_NAME,
//                noteColumns, null, null, null,
//                null, noteOrderBy);
//        mNoteRecyclerAdapter.changeCursor(noteCursor);
//    }

    private void initializeDisplayContent() {
//        DataManager.loadFromDatabase(mDbOpenHelper);
        mRecyclerItems = findViewById(R.id.list_items);
        mNotesLayoutManager = new LinearLayoutManager(this);
        mCoursesLayoutManager = new GridLayoutManager(this,
                getResources().getInteger(R.integer.course_grid_span));

        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this, null);

        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        mCourseRecyclerAdapter = new CourseRecyclerAdapter(this, courses);

        displayNotes();
    }

    private void displayNotes() {
        mRecyclerItems.setLayoutManager(mNotesLayoutManager);
        mRecyclerItems.setAdapter(mNoteRecyclerAdapter);

        selectNavigationMenuItem(R.id.nav_notes);
    }

    private void selectNavigationMenuItem(int id) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(id).setChecked(true);
    }

    private void displayCourses() {
        mRecyclerItems.setLayoutManager(mCoursesLayoutManager);
        mRecyclerItems.setAdapter(mCourseRecyclerAdapter);

        selectNavigationMenuItem(R.id.nav_courses);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
            displayNotes();
        } else if (id == R.id.nav_courses) {
            displayCourses();
        } else if (id == R.id.nav_share) {
//            handleSelection(R.string.nav_share_message);
            handleShare();
        } else if (id == R.id.nav_send) {
            handleSelection(R.string.nav_send_message);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleShare() {
        View view = findViewById(R.id.list_items);
        Snackbar.make(view, "Share to - " +
                        PreferenceManager.getDefaultSharedPreferences(this).getString("user_favorite_social", ""),
                Snackbar.LENGTH_LONG).show();
    }

    private void handleSelection(int message_id) {
        View view = findViewById(R.id.list_items);
        Snackbar.make(view, message_id, Snackbar.LENGTH_LONG).show();
    }


//    @SuppressLint("StaticFieldLeak")
//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        Loader<Cursor> loader = null;
//        if(i == LOADER_NOTES) {
//            loader = new CursorLoader(this) {
//                @Override
//                public Cursor loadInBackground() {
//                    SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
//                    final String[] noteColumns = {
//                            NoteInfoEntry.getQName(NoteInfoEntry._ID),
//                            NoteInfoEntry.COLUMN_NOTE_TITLE,
//                            CourseInfoEntry.COLUMN_COURSE_TITLE
//                    };
//
//                    final String noteOrderBy = CourseInfoEntry.COLUMN_COURSE_TITLE +
//                            "," + NoteInfoEntry.COLUMN_NOTE_TITLE;
//
//                    // note_info JOIN course_info ON note_info.course_id = course_info.course_id
//                    String tablesWithJoin = NoteInfoEntry.TABLE_NAME + " JOIN " +
//                            CourseInfoEntry.TABLE_NAME + " ON " +
//                            NoteInfoEntry.getQName(NoteInfoEntry.COLUMN_COURSE_ID) + " = " +
//                            CourseInfoEntry.getQName( CourseInfoEntry.COLUMN_COURSE_ID);
//
//                    return db.query(tablesWithJoin, noteColumns,
//                            null, null, null, null, noteOrderBy);
//                }
//            };
//        }
//        return loader;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//        if(loader.getId() == LOADER_NOTES)  {
//            mNoteRecyclerAdapter.changeCursor(cursor);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        if(loader.getId() == LOADER_NOTES)  {
//            mNoteRecyclerAdapter.changeCursor(null);
//        }
//    }
}
