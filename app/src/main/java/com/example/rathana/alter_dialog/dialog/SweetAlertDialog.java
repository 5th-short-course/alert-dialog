package com.example.rathana.alter_dialog.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rathana.alter_dialog.MainActivity;
import com.example.rathana.alter_dialog.R;

public class SweetAlertDialog extends DialogFragment{

    private String[] items;
    private  boolean[] checkList = new boolean[]{true, false, false, false, true};

    public void setItems(String[] items){
        this.items=items;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("choose color")
                .setIcon(R.drawable.ic_announcement_black_24dp)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        //set checkbox
        builder.setMultiChoiceItems(items, checkList,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                            Toast.makeText(getActivity(), ""+items[which] +"is checked", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(), ""+items[which] +"is unchecked", Toast.LENGTH_SHORT).show();


                    }
                });

        return builder.create();

    }
}
