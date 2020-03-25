package com.example.todo;

import java.io.Serializable;
import java.util.ArrayList;

class List implements Serializable
{
    private String listName;
    private ArrayList<Task> tasks;

    List(String listName)
    {
        this.listName = listName;
        this.tasks = new ArrayList<>();
    }

    String getListName()
    {
        return this.listName;
    }
    ArrayList<Task> getTasks()
    {
        return this.tasks;
    }

    void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void addTask(Task task)
    {
        tasks.add(task);
    }
}
