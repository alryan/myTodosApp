package com.example.alryan.mytodosapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class ToDoHeader implements Parcelable
{
    private int id;
    private String title;
    private Date createdOn;
    private Date lastUpdate;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Date getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }

    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public ToDoHeader(){}


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(id);
        parcel.writeString(title);
    }

    protected ToDoHeader(Parcel in)
    {
        id = in.readInt();
        title = in.readString();
    }

    public static final Creator<ToDoHeader> CREATOR = new Creator<ToDoHeader>()
    {
        @Override
        public ToDoHeader createFromParcel(Parcel in)
        {
            return new ToDoHeader(in);
        }

        @Override
        public ToDoHeader[] newArray(int size)
        {
            return new ToDoHeader[size];
        }
    };
}