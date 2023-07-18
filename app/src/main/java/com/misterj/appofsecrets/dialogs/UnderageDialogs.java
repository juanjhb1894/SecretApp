package com.misterj.appofsecrets.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.misterj.appofsecrets.MainActivity;
import com.misterj.appofsecrets.R;
import com.misterj.appofsecrets.utils.DataProccessor;
import com.misterj.appofsecrets.utils.DateCalculations;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UnderageDialogs {

    private Context context;
    private Button action, cancel, submit;
    private EditText etDay, etMonth, etYear;

    public UnderageDialogs(Context context) {
        this.context = context;
    }

    public void Show(){
        // custom dialog
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.not_old_enough);
        action = (Button) dialog.findViewById(R.id.notOldEnoughAppel);
        cancel = (Button) dialog.findViewById(R.id.notOldEnoughCancel);
        // if button is clicked, close the custom dialog
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void Ask(){
        // custom dialog
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.ask_birthday);

        etDay = (EditText) dialog.findViewById(R.id.etDay);
        etMonth = (EditText) dialog.findViewById(R.id.etMonth);
        etYear = (EditText) dialog.findViewById(R.id.etYear);
        submit = (Button) dialog.findViewById(R.id.submit_birthday);
        // if button is clicked, close the custom dialog
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String birthday = etDay.getText().toString() + "-" +
                etMonth.getText().toString() + "-" +
                etYear.getText().toString();
                new DataProccessor(context.getApplicationContext()).setStr("birthday",birthday);
                String today = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                int age = new DateCalculations().differenceInYears(birthday, today);
                if(age >= 18)
                {
                    dialog.dismiss();
                    context.startActivity(new Intent(context, MainActivity.class));
                }
                else
                {
                    dialog.dismiss();
                    new UnderageDialogs(context).Show();
                }

            }
        });

        dialog.show();
    }
}


