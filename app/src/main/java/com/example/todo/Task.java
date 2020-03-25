package com.example.todo;

import java.io.Serializable;

public class Task implements Serializable
{
    private String taskName;
    private boolean isCompleted;
    private boolean isImportant;

    Task(String taskName)
    {
        this.taskName = taskName;
        this.isCompleted = false;
        this.isImportant = false;
    }

    String getTaskName() {
        return taskName;
    }
    boolean getIsCompleted()
    {
        return isCompleted;
    }
    boolean getIsImportant() {
        return isImportant;
    }

    void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }
    void setIsImportant(boolean important) {
        isImportant = important;
    }
}
