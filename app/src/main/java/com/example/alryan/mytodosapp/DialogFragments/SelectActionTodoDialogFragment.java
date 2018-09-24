package com.example.alryan.mytodosapp.DialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.alryan.mytodosapp.Interfaces.SelectActionListener;
import com.example.alryan.mytodosapp.Models.ToDoHeader;
import com.example.alryan.mytodosapp.Utility.Utility;


public class SelectActionTodoDialogFragment extends DialogFragment
{
    private Context context;
    private CharSequence[] toDoActions = {"View details"};
    private SelectActionListener selectActionListener;
    private ToDoHeader toDoHeader;


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try
        {
            Bundle bundle = getArguments();
            if (bundle != null)
            {
                if (bundle.containsKey("SELECTED_TODO")) toDoHeader = bundle.getParcelable("SELECTED_TODO");
            }
        }
        catch (Exception err)
        {
            Utility.showError(getFragmentManager(), "Error SelectActionTodoDialogFragment onCreate: \n" + err.toString());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle(toDoHeader.getTitle())
                .setItems(toDoActions, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        selectActionListener.onClick(toDoActions[which].toString());
                    }
                });

        return builder.create();
    }

    public void setSelectActionListener(SelectActionListener selectActionListener)
    {
        this.selectActionListener = selectActionListener;
    }
}
