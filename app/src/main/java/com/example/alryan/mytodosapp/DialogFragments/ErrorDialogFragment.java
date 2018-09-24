package com.example.alryan.mytodosapp.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


public class ErrorDialogFragment extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        String errorMessage = "";

        Bundle bundle = getArguments();
        if (bundle != null && bundle.size() > 0) errorMessage = bundle.getString("ERROR_MESSAGE");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton("Close", null);

        return builder.create();
    }
}