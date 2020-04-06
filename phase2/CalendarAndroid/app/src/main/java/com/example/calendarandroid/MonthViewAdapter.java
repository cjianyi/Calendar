package com.example.calendarandroid;
import android.content.Context;
import androidx.core.util.Pair;
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

public class MonthViewAdapter extends RecyclerView.Adapter<MonthViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();

    public MonthViewAdapter(Context context, ArrayList<Pair<String, Integer>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @Override
    public MonthViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MonthViewAdapter.ViewHolder holder, int position) {


        if(arrayList.get(position).first.equals("Sunday")){
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
            flexboxLp.setWrapBefore(true);
        }
        
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
