package com.example.calendarandroid;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();

    public EventViewAdapter (Context context, ArrayList<Pair<String, Integer>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public EventViewAdapter .ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.t, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewAdapter .ViewHolder holder, int position) {

//        holder.title.setText(arrayList.get(position).first);
//        holder.num.setText("5");
//        if(holder.title.getText().toString().equals("Su")){
//            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
//            flexboxLp.setWrapBefore(true);
//        }
//        holder.title.setText(holder.title.getText().toString() + "\n5");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout dayView;

        public ViewHolder(View itemView) {
            super(itemView);

            dayView = itemView.findViewById(R.id.monthDay);


        }
    }
}