package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List>
{
    private ArrayList<List> lists;

    ListAdapter(Context context, ArrayList<List> lists)
    {
        super(context, 0, lists);
        this.lists = lists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        final List list = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_item, parent, false);
        }
        // Lookup view for data population
        TextView listName = convertView.findViewById(R.id.list_name);
        ImageView dustbin = convertView.findViewById(R.id.dust_bin);
        // Populate the data into the template view using the data object
        assert list != null;
        listName.setText(list.getListName());

        dustbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lists.remove(position);
                notifyDataSetChanged();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
