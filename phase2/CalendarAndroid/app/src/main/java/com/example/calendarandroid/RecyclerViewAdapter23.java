package com.example.calendarandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter23 extends RecyclerView.Adapter<RecyclerViewAdapter23.ViewHolder> {

    private ArrayList<String> daysWeek;

    public RecyclerViewAdapter23(ArrayList<String> daysWeek)
    {
        this.daysWeek = daysWeek;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_of_the_week, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String day = daysWeek.get(position);
        holder.tv.setText(day);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        LinearLayout ll;

        public ViewHolder(View v)
        {
            super(v);
            tv = v.findViewById(R.id.days);
            ll = v.findViewById(R.id.days_layout);
        }
    }
}
