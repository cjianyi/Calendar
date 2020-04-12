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
    static MonthViewAdapter.OnDayClickListener mOnEventsClickListener;

    public EventViewAdapter (Context context, ArrayList<Event> arrayList, MonthViewAdapter.OnDayClickListener onEventsClickListener) {
        this.context = context;
        this.events = arrayList;
        mOnEventsClickListener = onEventsClickListener;
    }

    @Override
    public EventViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_single_event, parent, false);
        return new ViewHolder(view, mOnEventsClickListener);
    }

    @Override
    public void onBindViewHolder(EventViewAdapter.ViewHolder holder, int position) {
        Log.d("event", Integer.toString(events.size()));
        int l = this.events.size();
        int count = 0;
        for(TextView t: holder.eventNames){
            t.setText(this.events.get(count).getName());
            count += 1;
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventName;
        MonthViewAdapter.OnDayClickListener onEventListener;
        LinearLayout linearLayout;
        RecyclerView r;

        TextView event1;
        TextView event2;
        TextView event3;
        TextView event4;
        ArrayList<TextView> eventNames;

        public  ViewHolder(View itemView, MonthViewAdapter.OnDayClickListener onDayClickListener) {
            super(itemView);
            eventName = itemView.findViewById(R.id.eventName);
            linearLayout = itemView.findViewById(R.id.showEvents);
            this.onEventListener = onDayClickListener;
            linearLayout.setOnClickListener(this);
            event1 = itemView.findViewById(R.id.event1);
            event2 = itemView.findViewById(R.id.event2);
            event3 = itemView.findViewById(R.id.event3);
            event4 = itemView.findViewById(R.id.event4);
            eventNames.add(event1);
            eventNames.add(event2);
            eventNames.add(event3);
            eventNames.add(event4);
        }


        @Override
        public void onClick(View v) {
            r = (RecyclerView) v.getParent();
            ViewHolder currentViewHolder = (ViewHolder) r.getChildViewHolder(v);

            mOnEventsClickListener.onDayClick(currentViewHolder.getAdapterPosition());
        }
    }

    public interface OnEventsClickListener{
        void onEventsClick();
    }


}