package com.example.alryan.mytodosapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alryan.mytodosapp.Interfaces.ListItemClickListener;
import com.example.alryan.mytodosapp.Models.ToDoHeader;
import com.example.alryan.mytodosapp.R;
import com.example.alryan.mytodosapp.Utility.Utility;

import java.util.ArrayList;
import java.util.List;


public class ToDoHeaderAdapter extends RecyclerView.Adapter<ToDoHeaderAdapter.ViewHolder>
{
    private ArrayList<ToDoHeader> toDoHeaderArrayList;
    private static ListItemClickListener listItemClickListener;


    // Provide a suitable constructor (depends on the kind of data set)
    public ToDoHeaderAdapter(ArrayList<ToDoHeader> toDoHeaderArrayList, ListItemClickListener _listItemClickListener)
    {
        this.toDoHeaderArrayList = toDoHeaderArrayList;
        listItemClickListener = _listItemClickListener;
    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return toDoHeaderArrayList.size();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ToDoHeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_listrow, parent, false);
        return new ViewHolder(view);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private View view;
        public TextView tvName;

        public ViewHolder(View view)
        {
            super(view);
            this.view = view;
            tvName = view.findViewById(R.id.tvName);
        }

        private void bind(final int position)
        {
            view.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    listItemClickListener.onClick(position);
                    return true;
                }
            });
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        ToDoHeader toDoHeader = toDoHeaderArrayList.get(i);
        viewHolder.tvName.setText(toDoHeader.getTitle());
        viewHolder.bind(i);
    }
}
