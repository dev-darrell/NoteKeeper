package com.darrell.dev.notekeeper.roomdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.darrell.dev.notekeeper.roomdb.R;
import com.darrell.dev.notekeeper.roomdb.activities.NoteActivity;
import com.darrell.dev.notekeeper.roomdb.roomDb.CourseWithNote;

import java.util.List;

/**
 * Created by Jim.
 * Maintained and upgraded by Darrell.
 */

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<CourseWithNote> mCourseWithNote;

    public NoteRecyclerAdapter(Context context, List<CourseWithNote> courseWithNote) {
        mContext = context;
        mCourseWithNote = courseWithNote;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void changeDataList(List<CourseWithNote> courseWithNote) {
        mCourseWithNote = courseWithNote;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_note_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseWithNote currentNoteInfo = mCourseWithNote.get(position);
        String course = currentNoteInfo.getCourseTitle();
        String noteTitle = currentNoteInfo.getNoteTitle();
        int id = currentNoteInfo.getNoteId();

        holder.mTextCourse.setText(course);
        holder.mTextTitle.setText(noteTitle);
        holder.mId = id;
    }

    @Override
    public int getItemCount() {
        return mCourseWithNote == null ? 0 : mCourseWithNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextCourse;
        public final TextView mTextTitle;
        public int mId;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextCourse = (TextView) itemView.findViewById(R.id.text_course);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NoteActivity.class);
                    intent.putExtra(NoteActivity.NOTE_ID, mId);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}







