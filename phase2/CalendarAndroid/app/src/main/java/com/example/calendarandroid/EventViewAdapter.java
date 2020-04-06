package com.example.calendarandroid;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Event> arrayList = new ArrayList<>();

    public EventViewAdapter (Context context, ArrayList<Event> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public EventViewAdapter .ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_single_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewAdapter .ViewHolder holder, int position) {

        holder.e.setText(arrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView e;

        public ViewHolder(View itemView) {
            super(itemView);
            e = itemView.findViewById(R.id.eventName);
        }
    }
}