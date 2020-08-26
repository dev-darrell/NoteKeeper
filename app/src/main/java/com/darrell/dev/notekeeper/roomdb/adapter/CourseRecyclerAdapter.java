package com.darrell.dev.notekeeper.roomdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.darrell.dev.notekeeper.roomdb.R;
import com.darrell.dev.notekeeper.roomdb.activities.CourseActivity;
import com.darrell.dev.notekeeper.roomdb.roomDb.Course;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * Created by Jim.
 */

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Course> mCourses;
    private final LayoutInflater mLayoutInflater;

    public CourseRecyclerAdapter(Context context, List<Course> courses) {
        mContext = context;
        mCourses = courses;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void changeDataList(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_course_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Course course = mCourses.get(position);
        holder.mTextCourse.setText(course.getCourse_title());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextCourse;
        public int mCurrentPosition;

        public ViewHolder(final View itemView) {
            super(itemView);
            mTextCourse = (TextView) itemView.findViewById(R.id.text_course);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Course currentCourse = mCourses.get(mCurrentPosition);
                    Snackbar.make(v, currentCourse.getCourse_title(), Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(itemView.getContext(), CourseActivity.class);
                    intent.putExtra(CourseActivity.COURSE_KEY, currentCourse.getCourse_key());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}







