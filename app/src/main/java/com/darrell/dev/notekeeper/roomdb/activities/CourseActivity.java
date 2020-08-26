package com.darrell.dev.notekeeper.roomdb.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.darrell.dev.notekeeper.roomdb.R;
import com.darrell.dev.notekeeper.roomdb.roomDb.Course;
import com.darrell.dev.notekeeper.roomdb.roomDb.NKeeperViewModel;

import java.util.concurrent.CountDownLatch;

public class CourseActivity extends AppCompatActivity {

//    TODO: Set up update and delete note functionality.


    public static final String COURSE_KEY = "com.darrell.dev.notekeeper.roomdb.activities.CourseActivity.COURSE_KEY";
    private static final String TAG = "CourseActivity";
    private static final int KEY_NOT_SET = -1;
    private EditText mCourseTitle;
    private EditText mCourseId;
    private Switch mAutoId;
    private String mCourseIdText;
    private String mCourseTitleText;
    private NKeeperViewModel mKeeperViewModel;
    private int mCourse_key;
    private Course mCurrentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        mCourseTitle = findViewById(R.id.text_course_title);
        mCourseId = findViewById(R.id.text_course_id);
        mAutoId = findViewById(R.id.auto_course_id_switch);
        mKeeperViewModel = new ViewModelProviders().of(this).get(NKeeperViewModel.class);


        mAutoId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!compoundButton.isChecked()) {
                    mCourseId.setVisibility(View.VISIBLE);
                } else {
                    mCourseId.setVisibility(View.INVISIBLE);
                }
            }
        });

        displayCurrentValues();
    }

    private void displayCurrentValues() {
        Intent intent = getIntent();
        mCourse_key = intent.getIntExtra(COURSE_KEY, KEY_NOT_SET);

        final CountDownLatch downLatch = new CountDownLatch(1);

        @SuppressLint("StaticFieldLeak")
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mCurrentCourse = mKeeperViewModel.getCourse(mCourse_key);
                downLatch.countDown();
                return null;
            }
        };
        task.execute();

        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        displayCourse();
    }

    private void displayCourse() {
        if (mCourse_key != KEY_NOT_SET && mCurrentCourse != null) {
            String currentCourseTitle = mCurrentCourse.getCourse_title();
            String currentCourseId = mCurrentCourse.getCourse_id();

            mCourseTitle.setText(currentCourseTitle);
            mAutoId.setChecked(false);
            mCourseId.setText(currentCourseId);
        }
    }

    @Override
    protected void onPause() {
        if (mCourseTitle.getText().toString().isEmpty()) {
            Toast.makeText(this, "No course title entered. Course will not be saved.", Toast.LENGTH_LONG).show();
            super.onPause();
            return;
        }

        super.onPause();

        saveCourse();
    }

    private void saveCourse() {
        mCourseTitleText = mCourseTitle.getText().toString();
        if (!mAutoId.isChecked()) {
            mCourseIdText = mCourseId.getText().toString();
        } else {
            mCourseIdText = generateCourseId();
        }

        int rowID = mKeeperViewModel.insertCourse(new Course(mCourseIdText, mCourseTitleText));
        Log.i(TAG, "saveCourse: " + rowID);
    }

    private String generateCourseId() {
        int titleLength = mCourseTitleText.length();
//        String debugString = mCourseTitleText.substring(0, titleLength/2);
        String[] splitTitle = mCourseTitleText.split(" ", titleLength / 2);
        String genCourseId;
        if (splitTitle.length < 3) {
            genCourseId = splitTitle[0] + "_" + splitTitle[1] + "_" + splitTitle[3];
        } else {
            genCourseId = splitTitle[0] + "_" + splitTitle[1];
        }
        return genCourseId;
    }
}