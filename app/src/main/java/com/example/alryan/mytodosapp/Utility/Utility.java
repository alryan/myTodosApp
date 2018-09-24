package com.example.alryan.mytodosapp.Utility;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.alryan.mytodosapp.DialogFragments.ErrorDialogFragment;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Utility
{
    public static void showError(FragmentManager fragmentManager, String errorMessage)
    {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();

        Bundle bundle = new Bundle();
        if (errorMessage != null) bundle.putString("ERROR_MESSAGE", errorMessage);
        errorDialogFragment.setArguments(bundle);

        errorDialogFragment.show(fragmentManager, "ErrorDialog");
    }

    public static void print(Object object)
    {
        String value;

        if (object.getClass() == String.class || object.getClass() == Integer.class)
        {
            value = object.toString();
        }
        else
        {
            Gson gson = new Gson();
            value = gson.toJson(object);
        }

        Log.d("TODO-APP-LOG", value);
    }

    public static String dateTimeToString(Date date)
    {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM. dd, yyyy h:mm a", Locale.US);
        return dateTimeFormat.format(date);
    }
}
