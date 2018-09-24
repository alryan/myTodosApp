package com.example.alryan.mytodosapp.Models;

import android.os.Parcel;
import android.os.Parcelable;


public class ToDo implements Parcelable
{
    private int id;
    private String todo;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTodo()
    {
        return todo;
    }

    public void setTodo(String todo)
    {
        this.todo = todo;
    }

    public ToDo(){}


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(id);
        parcel.writeString(todo);
    }

    protected ToDo(Parcel in)
    {
        id = in.readInt();
        todo = in.readString();
    }

    public static final Creator<ToDo> CREATOR = new Creator<ToDo>()
    {
        @Override
        public ToDo createFromParcel(Parcel in)
        {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size)
        {
            return new ToDo[size];
        }
    };
}
