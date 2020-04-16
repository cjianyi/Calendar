package com.example.calendarandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewEventsAdapter extends  RecyclerView.Adapter<ViewEventsAdapter.ViewHolder>  {
    Context context;
    ArrayList<Event> events;
    private OnEventClickListener mOnEventClickListener;

    public ViewEventsAdapter(Context context, ArrayList<Event> events, OnEventClickListener onEventClickListener){
        this.context = context;
        this.events = events;
        this.mOnEventClickListener = onEventClickListener;
    }

    @NonNull
    @Override
    public ViewEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_viewing_events, parent, false);

        return new ViewEventsAdapter.ViewHolder(view, this.mOnEventClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewEventsAdapter.ViewHolder holder, int position) {
        holder.t.setText(this.events.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView t;
        FrameLayout l;
        OnEventClickListener clicks;

        public ViewHolder(@NonNull View itemView, OnEventClickListener onEventClickListener) {
            super(itemView);
            t = itemView.findViewById(R.id.eventText);
            l = itemView.findViewById(R.id.eventsView);

            this.clicks = onEventClickListener;
            l.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            this.clicks.onEventClick(getAdapterPosition());
        }
    }

    public interface OnEventClickListener{
        void onEventClick(int position);
    }

}
