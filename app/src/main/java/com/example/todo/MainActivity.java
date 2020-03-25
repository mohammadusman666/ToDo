package com.example.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<List> arrayList;
    private ListAdapter arrayAdapter;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct the data source
        arrayList = new ArrayList<>();
        // Create the adapter to convert the array to views
        arrayAdapter = new ListAdapter(this, arrayList);

        ListView listView = findViewById(R.id.list);
        Button addListButton = findViewById(R.id.addList);
        editText = findViewById(R.id.editText);

        addListButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (editText.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    arrayList.add(new List(editText.getText().toString()));
                    editText.getText().clear();
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                intent.putExtra("list", arrayList.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, 0);
            }
        });

        // Attach the adapter to a ListView
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("myLog", "onActivityResult: " + requestCode + " " + resultCode);

        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                if (data != null)
                {
                    ArrayList<Task> tempTasks = (ArrayList<Task>) data.getSerializableExtra("tasks");
                    int tempPosition = data.getIntExtra("position", 0);

                    Log.d("myLog", "onActivityResult: " + tempPosition);

                    arrayList.get(tempPosition).setTasks(tempTasks);
                }
            }
        }
    }
}
