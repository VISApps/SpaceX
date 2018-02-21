package com.visapps.spacex.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.visapps.spacex.R;

import java.util.Calendar;

public class YearSelectionDialog extends DialogFragment {

    private NumberPicker yearpicker;
    private YearSelectionCallback callback;

    public interface  YearSelectionCallback{
        void onSelectYear();
    }

    public void setCallback(YearSelectionCallback callback){
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.yearselection_dialog, null);
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        yearpicker = view.findViewById(R.id.yearpicker);
        yearpicker.setMinValue(2006);
        yearpicker.setMaxValue(Calendar.getInstance().get(Calendar.YEAR));
        yearpicker.setValue(sharedPreferences.getInt("year",2017));
        builder.setView(view)
                .setTitle(getString(R.string.selectyear))
                .setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        selectYear();
                    }
                });
        return builder.create();
    }

    private void selectYear(){
        int year = yearpicker.getValue();
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if(year != sharedPreferences.getInt("year", 2017)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("year", year);
            editor.apply();
            callback.onSelectYear();
        }
        dismiss();
    }
}
