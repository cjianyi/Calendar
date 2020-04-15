package com.example.calendarandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewEventsAdapter extends  RecyclerView.Adapter<ViewEventsAdapter.ViewHolder>  {
    Context context;
    ArrayList<Event> events;

    public ViewEventsAdapter(Context context, ArrayList<Event> events){
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public ViewEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_events, parent, false);

        return new ViewEventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewEventsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
