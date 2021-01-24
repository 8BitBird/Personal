package com.example.workoutgen;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> Name;
    private ArrayList<Integer> Pic;
    private ArrayList<String> Reps;

    public CustomAdapter(Context context, ArrayList<String> name1, ArrayList<String> reps, ArrayList<Integer> pics) {
        mContext = context;
        Name = name1;
        Pic = pics;
        Reps = reps;
    }
        @Override
        public int getCount () {
            return Name.size();
        }
        public Object getItem( int position){
            return null;
        }

        @Override
        public long getItemId( int position){
            return position;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View recycler_view_item;
            recycler_view_item = inflater.inflate(R.layout.recycler_view_item, parent, false);
            TextView mName;
            TextView mReps;
            ImageView eIcon;
            eIcon = (ImageView) recycler_view_item.findViewById(R.id.eqIcon);
            mReps = (TextView) recycler_view_item.findViewById(R.id.textView1);
            mName = (TextView) recycler_view_item.findViewById(R.id.textView2);
            eIcon.setImageResource(Pic.get(position));
            mName.setText(Reps.get(position));
            mReps.setText(Name.get(position));

            return recycler_view_item;
        }
}
