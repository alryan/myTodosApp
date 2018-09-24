package com.example.alryan.mytodosapp.Models;

public class ToDoDetail
{
    private int id;
    private int toDoHeaderId;
    private String toDo;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getToDoHeaderId()
    {
        return toDoHeaderId;
    }

    public void setToDoHeaderId(int toDoHeaderId)
    {
        this.toDoHeaderId = toDoHeaderId;
    }

    public String getToDo()
    {
        return toDo;
    }

    public void setToDo(String toDo)
    {
        this.toDo = toDo;
    }

    public ToDoDetail(){}
}
