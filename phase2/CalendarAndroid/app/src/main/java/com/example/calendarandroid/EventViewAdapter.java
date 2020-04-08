package com.example.calendarandroid;
import android.content.Context;
import android.media.MediaDrm;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Event> events;
    MonthViewAdapter.OnDayClickListener mOnEventsClickListener;

    public EventViewAdapter (Context context, ArrayList<Event> arrayList, MonthViewAdapter.OnDayClickListener onEventsClickListener) {
        this.context = context;
        this.events = arrayList;
        this.mOnEventsClickListener = onEventsClickListener;
    }

    @Override
    public EventViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_single_event, parent, false);
        return new ViewHolder(view, this.mOnEventsClickListener);
    }

    @Override
    public void onBindViewHolder(EventViewAdapter.ViewHolder holder, int position) {
        Log.d("event", Integer.toString(events.size()));
        holder.eventName.setText(events.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventName;
        MonthViewAdapter.OnDayClickListener onEventListener;
        LinearLayout linearLayout;

        public ViewHolder(View itemView, MonthViewAdapter.OnDayClickListener onDayClickListener) {
            super(itemView);
            eventName = itemView.findViewById(R.id.eventName);
            linearLayout = itemView.findViewById(R.id.showEvents);
            this.onEventListener = onDayClickListener;
            linearLayout.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            RecyclerView r = (RecyclerView) v.getParent();
            ViewHolder currentViewHolder = (ViewHolder) r.getChildViewHolder(v);

            mOnEventsClickListener.onDayClick(currentViewHolder.getAdapterPosition());
        }
    }

    public interface OnEventsClickListener{
        void onEventsClick();
    }


}