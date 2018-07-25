package com.bustrack.bustrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bustrack.bustrack.R;
import com.bustrack.bustrack.activity.MapActivity;
import com.bustrack.bustrack.model.BusDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admininstrator on 10/11/17.
 */

public class BusListAdapter extends BaseAdapter {

    private Context context;
    private List<BusDetailsModel> busDetailsModels;
    ArrayList<Button> buttons;
    public BusListAdapter(Context context, List<BusDetailsModel> busDetailsModels) {
        this.context = context;
        this.busDetailsModels = busDetailsModels;
        buttons = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return busDetailsModels.size();
    }

    @Override
    public Object getItem(int i) {
        return busDetailsModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.bus_list_item, viewGroup, false);
            myViewHolder.busNameTextView = view.findViewById(R.id.busnameTextView);
            myViewHolder.sourceTextView = view.findViewById(R.id.sourceTextView);
            myViewHolder.destinationTextView = view.findViewById(R.id.destinationTextView);
            myViewHolder.trackButton = view.findViewById(R.id.trackTextView);
            buttons.add(myViewHolder.trackButton);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        final BusDetailsModel busDetailsModel = (BusDetailsModel) getItem(i);
        myViewHolder.busNameTextView.setText(busDetailsModel.getBusName());
        myViewHolder.sourceTextView.setText(busDetailsModel.getSource());
        myViewHolder.destinationTextView.setText(busDetailsModel.getDestination());
        buttons.get(i).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("bus_id", busDetailsModel.getBusId());
                context.startActivity(intent);
            }
        });
        return view;
    }

    private class MyViewHolder {

        TextView busNameTextView;
        TextView sourceTextView;
        TextView destinationTextView;
        Button trackButton;

    }
}