package com.example.calendarandroid;
import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.calendarandroid.R.*;

public class MonthViewAdapter extends RecyclerView.Adapter<MonthViewAdapter.ViewHolder>   {

    Context context;
    ArrayList<Day> days;
    private OnDayClickListener mOnDayClickListener;
    CustomLinearLayoutManager l;
    private AppCompatActivity activity;

    public MonthViewAdapter(Context context, ArrayList<Day> arrayList, OnDayClickListener onDayClickListener) {
        this.mOnDayClickListener = onDayClickListener;
        this.context = context;
        this.days = arrayList;
    }

    @NonNull
    @Override
    public MonthViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.content_events, parent, false);

        return new ViewHolder(view, this.mOnDayClickListener);

    }

    @Override
    public void onBindViewHolder(MonthViewAdapter.ViewHolder holder, int position) {
        LinearLayoutManager l = new LinearLayoutManager(context);
        l.setOrientation(RecyclerView.VERTICAL);


//        Event e = new Event();
//        ArrayList<Event> g = new ArrayList<>();
//        g.add(e);
//        l = new CustomLinearLayoutManager(context, LinearLayout.VERTICAL, false);
//        ViewGroup.LayoutParams params=holder.r.getLayoutParams();
//        params.height=228;
//        holder.r.setLayoutParams(params);
//        holder.r.setLayoutManager(l);
//        EventViewAdapter adapter = new EventViewAdapter(context, days.get(position).getEvents(), this.mOnDayClickListener);
//
//        holder.r.setAdapter(adapter);
        int count = holder.eventNames.size();
        for(int i = 0; i < count; i++) {
            if(i  < this.days.get(position).getEvents().size()) {

                holder.eventNames.get(i).setText(this.days.get(position).getEvents().get(i).getName());
                if(i == 0) {
                    holder.eventNames.get(i).setBackgroundResource(color.event1);
                }
                else if(i == 1){
                    holder.eventNames.get(i).setBackgroundResource(color.event2);
                }
                else if(i == 2){
                    holder.eventNames.get(i).setBackgroundResource(color.event3);
                }
                else if(i == 3){
                    holder.eventNames.get(i).setText("+" + Integer.toString(this.days.get(position).getEvents().size() - 3) + "...");
                }
            }

        }
        holder.t.setText(days.get(position).getMonthDayNumber());

        EventInMonthViewAdapter adapter =new EventInMonthViewAdapter(context, days.get(position).getEvents());


        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

    }

    @Override
    public int getItemCount() {
        return days.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView r;
        TextView t;
        FrameLayout f;
        CardView l;

        TextView event1;
        TextView event2;
        TextView event3;
        TextView event4;
        ArrayList<TextView> eventNames;

        OnDayClickListener onDayClickListener;

        public ViewHolder(View itemView, OnDayClickListener onDayClickListener) {
            super(itemView);
            l = itemView.findViewById(id.dayView);
            t = itemView.findViewById(id.dayNum);
            eventNames = new ArrayList<>();
            event1 = itemView.findViewById(id.event1);
            event2 = itemView.findViewById(id.event2);
            event3 = itemView.findViewById(id.event3);
            event4 = itemView.findViewById(id.event4);
            eventNames.add(event1);
            eventNames.add(event2);
            eventNames.add(event3);
            eventNames.add(event4);

//            f = itemView.findViewById(R.id.monthDay);


            this.onDayClickListener = onDayClickListener;
            t.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDayClickListener.onDayClick(getAdapterPosition());
        }
    }

    public interface OnDayClickListener{
        void onDayClick(int position);
        void onDayClick();
    }
}
