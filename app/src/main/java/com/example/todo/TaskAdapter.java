package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task>
{
    private ArrayList<Task> tasks;

    TaskAdapter(Context context, ArrayList<Task> tasks)
    {
        super(context, 0, tasks);
        this.tasks = tasks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        final Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_row_item, parent, false);
        }
        // Lookup view for data population
        TextView taskName = convertView.findViewById(R.id.task_name);
        final ImageView tick = convertView.findViewById(R.id.check);
        final ImageView star = convertView.findViewById(R.id.star);
        ImageView dustbin = convertView.findViewById(R.id.task_dust_bin);
        // Populate the data into the template view using the data object
        assert task != null;
        taskName.setText(task.getTaskName());

        if (task.getIsCompleted())
            tick.setImageResource(R.drawable.ic_check_complete);
        else
            tick.setImageResource(R.drawable.ic_check);

        if (task.getIsImportant())
            star.setImageResource(R.drawable.ic_star_complete);
        else
            star.setImageResource(R.drawable.ic_star);

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tick.getDrawable().getConstantState() == getContext().getResources().getDrawable(R.drawable.ic_check).getConstantState())
                {
                    task.setIsCompleted(true);
                    tick.setImageResource(R.drawable.ic_check_complete);
                }
                else
                {
                    task.setIsCompleted(false);
                    tick.setImageResource(R.drawable.ic_check);
                }
            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (star.getDrawable().getConstantState() == getContext().getResources().getDrawable(R.drawable.ic_star).getConstantState())
                {
                    task.setIsImportant(true);
                    star.setImageResource(R.drawable.ic_star_complete);
                }
                else
                {
                    task.setIsImportant(false);
                    star.setImageResource(R.drawable.ic_star);
                }
            }
        });

        dustbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.remove(position);
                notifyDataSetChanged();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
