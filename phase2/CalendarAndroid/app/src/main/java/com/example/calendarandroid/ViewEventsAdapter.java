package com.example.calendarandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ViewEventsAdapter extends  RecyclerView.Adapter<ViewEventsAdapter.ViewHolder>  {
    Context context;
    ArrayList<String[]> events;
    public OnEventClickListener mOnEventClickListener;

    public ViewEventsAdapter(Context context, ArrayList<String[]> events, OnEventClickListener onEventClickListener){
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
        holder.name.setText(this.events.get(position)[0]);
        LocalDateTime s = LocalDateTime.parse(this.events.get(position)[1]);
        LocalDateTime e = LocalDateTime.parse(this.events.get(position)[2]);
        StringBuilder time = new StringBuilder(s.toLocalTime().toString());
        time.append(" - ");
        if(s.toLocalDate().equals(e.toLocalDate())){
            time.append(e.toLocalTime().toString());
        }
        holder.time.setText(time.toString());

    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ConstraintLayout c;
        TextView time;
        OnEventClickListener clicks;
        Button share;
        Button delete;

        public ViewHolder(@NonNull View itemView, OnEventClickListener onEventClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.eventText);
            c = itemView.findViewById(R.id.eventsView);
            share = itemView.findViewById(R.id.btn_share_event);
            delete = itemView.findViewById(R.id.btn_delete_event);
            this.clicks = onEventClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEventClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onEventClickListener.onEventClick(position);
                        }
                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEventClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onEventClickListener.onDeleteClick(position);
                        }
                    }
                }
            });
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEventClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onEventClickListener.onShareClick(position);
                        }
                    }
                }
            });


            time = itemView.findViewById(R.id.eventTime);

        }



    }

    public interface OnEventClickListener{
        void onEventClick(int position);
        void onDeleteClick(int position);
        void onShareClick(int position);
    }

}
