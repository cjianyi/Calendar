package com.example.calendarandroid;
import android.content.Context;
import androidx.core.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MonthViewAdapter extends RecyclerView.Adapter<MonthViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Day> arrayList;

    public MonthViewAdapter(Context context, ArrayList<Day> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @Override
    public MonthViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_events, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MonthViewAdapter.ViewHolder holder, int position) {
        LinearLayoutManager l = new LinearLayoutManager(context);
        Event e = new Event();
        ArrayList<Event> g = new ArrayList<>();
        g.add(e);
        EventViewAdapter adapter = new EventViewAdapter(context, g);
        holder.r.setLayoutManager(l);
        holder.r.setAdapter(adapter);
        holder.t.setText(arrayList.get(position).getMonthDayNumber());

//        if(arrayList.get(position).getWeekNum()==7){
//            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
//            flexboxLp.setWrapBefore(true);
//        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView r;
        TextView t;
        FrameLayout f;
        TextView day;

        public ViewHolder(View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.dayNum);
            r  = itemView.findViewById(R.id.monthDayView);
            f = itemView.findViewById(R.id.monthDay);
            day = itemView.findViewById(R.id.tvMonday);
        }
    }
}
