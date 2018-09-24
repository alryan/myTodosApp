package com.example.alryan.mytodosapp.DialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.alryan.mytodosapp.Interfaces.DialogToDoAddEditListener;
import com.example.alryan.mytodosapp.Models.ToDo;
import com.example.alryan.mytodosapp.Utility.Utility;


public class AddEditToDoDialogFragment extends DialogFragment
{
    private Context context;
    private ToDo selectedToDo;
    private DialogToDoAddEditListener dialogToDoAddEditListener;


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
                if (bundle.containsKey("SELECTED_TODO")) selectedToDo = bundle.getParcelable("SELECTED_TODO");
            }
        }
        catch (Exception err)
        {
            Utility.showError(getFragmentManager(), "Error AddEditToDoDialogFragment onCreate: \n" + err.toString());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        // Dialog title
        String dialogTitle = (selectedToDo != null) ? "Edit" : "Add";
        builder.setTitle(dialogTitle + " Todo");

        // To do edit text
        final EditText etToDo = new EditText(getContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        etToDo.setLayoutParams(layoutParams);

        etToDo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        if (selectedToDo != null)
        {
            etToDo.setText(selectedToDo.getTodo());
            etToDo.setSelection(etToDo.getText().length());
        }

        builder.setView(etToDo);

        // Action buttons
        builder.setNegativeButton("Cancel", null)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        ToDo toDo;

                        if (selectedToDo != null)
                        {
                            selectedToDo.setTodo(etToDo.getText().toString());
                            toDo = selectedToDo;
                            selectedToDo = null;
                        }
                        else
                        {
                            toDo = new ToDo();
                            toDo.setTodo(etToDo.getText().toString());
                        }

                        dialogToDoAddEditListener.onFinish(toDo);
                    }
                });

        // Show keyboard
        AlertDialog alertDialog = builder.create();

        if (alertDialog.getWindow() != null) alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        else Utility.showError(getFragmentManager(), "Error AddEditToDoDialogFragment onCreateDialog: \nalertDialog.getWindow() is null");


        return alertDialog;
    }

    public void setDialogToDoAddEditListener(DialogToDoAddEditListener dialogToDoAddEditListener)
    {
        this.dialogToDoAddEditListener = dialogToDoAddEditListener;
    }
}
