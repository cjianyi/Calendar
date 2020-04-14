package com.example.calendarandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateListAdopter extends RecyclerView.Adapter<DateListAdopter.ViewHolder> {
    private List<Event> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public DateListAdopter(List<Event> eventData) {
        values = eventData;
    }

    public void add(int position, Event item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public void updateList(List<Event> data) {
        values = data;
        notifyDataSetChanged();
    }

    @Override
    public DateListAdopter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.event_list_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Event event = values.get(position);
        // Set item header to event name
        holder.txtHeader.setText((event.getName()));
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
        // Set item footer to event Start Time
        holder.txtFooter.setText(event.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}
