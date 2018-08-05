package com.example.rathana.alter_dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rathana.alter_dialog.dialog.SweetAlertDialog;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @BindView(R.id.btnMessageAlterDialog) Button btnMessageAlterDialog;
    @BindView(R.id.btnSingleChoiceAlterDialog) Button btnSingleChoice;
    @BindView(R.id.btnRadioAlterDialog) Button btnRadioAlterDialog;
    @BindView(R.id.btnCheckBoxAlterDialog) Button btnCheckBoxAlterDialog;

    @BindString(R.string.message) String mMessage;
    @BindArray(R.array.single_choice_values) String[] listChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //btnMessageAlterDialog=findViewById(R.id.btnMessageAlterDialog);

        //register event
        btnMessageAlterDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMessageAlterDialog:
            // TODO: 8/5/2018   show alter dialog
            createAlterDialogMessage(mMessage);
        }
    }

    @OnClick(R.id.btnSingleChoiceAlterDialog)
    public void onClickSingleChoiceAlterDialog(){
        // TODO: 8/5/2018
        createSingleChoiceAlterDialog("Choose Color",listChoices);
    }

    @OnClick(R.id.btnRadioAlterDialog)
    public void onClickRadioButtonAlterDialog(){
        createRadioButtonAlterDialog("Choose Color",listChoices);
    }

    @OnClick(R.id.btnCheckBoxAlterDialog)
    public void onClickCheckBoxAlterDialog(){
        createCheckBoxAlterDialog("Choose",listChoices);
    }

    @OnClick(R.id.btnDialogFragmentAlterDialog)
    public void onClickDialogFragment(){
        SweetAlertDialog dialog=new SweetAlertDialog();
        dialog.setItems(listChoices);
        dialog.show(getFragmentManager(),"dialog");
    }

    private  boolean[] checkList = new boolean[]{true, false, false, false, true};
    private void createCheckBoxAlterDialog(String choose, final String[] listChoices) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(choose)
                .setIcon(R.drawable.ic_announcement_black_24dp)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        //set checkbox
        builder.setMultiChoiceItems(listChoices, checkList,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                            Toast.makeText(MainActivity.this, ""+listChoices[which] +"is checked", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, ""+listChoices[which] +"is unchecked", Toast.LENGTH_SHORT).show();

                    }
                });

        builder.create().show();
    }

    private static int isCheck=0;
    private void createRadioButtonAlterDialog(final String chooseColor, final String[] listChoices) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_announcement_black_24dp);
        builder.setTitle(chooseColor);
        //builder.setMessage(message);
        //set Radio Button items
        builder.setSingleChoiceItems(listChoices, isCheck, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, ""+ listChoices[which], Toast.LENGTH_SHORT).show();
                isCheck=which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public void createSingleChoiceAlterDialog(String title, final String[] Choices){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_announcement_black_24dp);
        builder.setTitle(title);

        //builder.setMessage(message);
        //set item
        builder.setItems(Choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, Choices[which], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        builder.create().show();
    }


    private void createAlterDialogMessage(String message) {
        //create object alterDialog
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_announcement_black_24dp);
        builder.setTitle("Update new Version 12.3.123");
        builder.setMessage(message);
        builder.setCancelable(false);
        //create cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //create OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You agree!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.create().show();

    }
}
