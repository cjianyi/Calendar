package com.example.calendarandroid;
import android.content.Context;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonthViewAdapter extends RecyclerView.Adapter<MonthViewAdapter.ViewHolder> implements EventViewAdapter.OnEventsClickListener  {

    Context context;
    ArrayList<Day> days;
    private OnDayClickListener mOnDayClickListener;
    CustomLinearLayoutManager l;

    public MonthViewAdapter(Context context, ArrayList<Day> arrayList, OnDayClickListener onDayClickListener) {
        this.mOnDayClickListener = onDayClickListener;
        this.context = context;
        this.days = arrayList;
    }

    @NonNull
    @Override
    public MonthViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_events, parent, false);
        return new ViewHolder(view, this.mOnDayClickListener);
    }

    @Override
    public void onBindViewHolder(MonthViewAdapter.ViewHolder holder, int position) {
//        LinearLayoutManager l = new LinearLayoutManager(context);
//        l.setOrientation(RecyclerView.VERTICAL);


//        Event e = new Event();
//        ArrayList<Event> g = new ArrayList<>();
//        g.add(e);
        l = new CustomLinearLayoutManager(context, LinearLayout.VERTICAL, false);
        ViewGroup.LayoutParams params=holder.r.getLayoutParams();
        params.height=100;
        holder.r.setLayoutParams(params);
        holder.r.setLayoutManager(l);
        EventViewAdapter adapter = new EventViewAdapter(context, days.get(position).getEvents(), this.mOnDayClickListener);

        holder.r.setAdapter(adapter);
        holder.t.setText(days.get(position).getMonthDayNumber());

    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    @Override
    public void onEventsClick() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView r;
        TextView t;
        FrameLayout f;
        LinearLayout l;

        OnDayClickListener onDayClickListener;

        public ViewHolder(View itemView, OnDayClickListener onDayClickListener) {
            super(itemView);
            l = itemView.findViewById(R.id.dayView);
            t = itemView.findViewById(R.id.dayNum);
            r  = itemView.findViewById(R.id.eventDayView);
//            f = itemView.findViewById(R.id.monthDay);


            this.onDayClickListener = onDayClickListener;
            t.setOnClickListener(this);
            r.setOnClickListener(this);
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
