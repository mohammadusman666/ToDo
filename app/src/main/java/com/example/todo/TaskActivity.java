package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity
{
    private ArrayList<Task> arrayList;
    private TaskAdapter arrayAdapter;
    private EditText editText;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        final List list = (List) getIntent().getSerializableExtra("list");
        this.position = getIntent().getIntExtra("position", 0);
        Log.d("myLog", "onCreate: " + position);

        if (list != null)
        {
            TextView listName = findViewById(R.id.title);
            listName.setText(list.getListName());

            // Construct the data source
            arrayList = list.getTasks();
            // Create the adapter to convert the array to views
            arrayAdapter = new TaskAdapter(this, arrayList);

            ListView listView = findViewById(R.id.taskList);
            Button addTaskButton = findViewById(R.id.addTask);
            Button removeTaskButton = findViewById(R.id.remove_completed_tasks);
            editText = findViewById(R.id.taskEditText);

            addTaskButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if (editText.getText().toString().isEmpty())
                    {
                        Toast.makeText(TaskActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        list.addTask(new Task(editText.getText().toString()));
                        editText.getText().clear();
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            });

            removeTaskButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Task> tempTasks = list.getTasks();
                    for (int i = tempTasks.size() - 1; i >= 0; i--)
                    {
                        if (tempTasks.get(i).getIsCompleted())
                            tempTasks.remove(i);
                    }
                    arrayAdapter.notifyDataSetChanged();
                }
            });

            // Attach the adapter to a ListView
            listView.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void onBackPressed()
    {
        Log.d("myLog", "onBackPressed: " + position);
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putSerializable("tasks", arrayList);

        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}
