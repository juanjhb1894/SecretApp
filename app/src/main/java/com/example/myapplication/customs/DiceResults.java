package com.example.myapplication.customs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.Random;

public class DiceResults extends Dialog implements   android.view.View.OnClickListener {

    public Activity c;
    public int play;
    public Dialog d;
    private ImageView imgAction, imgPart;
    private Button no;
    private TextView txtSummary;
    public static int FOREPLAY = 0;
    public static int SEXPLAY = 1;

    public DiceResults(Activity a, int play) {
        super(a);
        this.c = a;
        this.play = play;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_result_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        no = (Button) findViewById(R.id.btn_dice_result_no);
        imgAction = (ImageView) findViewById(R.id.imgAction_dice_result);
        imgPart = (ImageView) findViewById(R.id.imgPart_dice_result);
        txtSummary = (TextView) findViewById(R.id.tvDiceResult);

        switch (play)
        {
            case 0:
                switch (getRandomNumberRange(1, 6)) {
                    case 1:
                        imgAction.setImageResource(R.drawable.ic_tickled);
                        switch (getRandomNumberRange(1, 5)) {
                            case 1:
                                imgPart.setImageResource(R.drawable.bodypart1);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo11));
                                break;

                            case 2:
                                imgPart.setImageResource(R.drawable.bodypart2);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo12));
                                break;

                            case 3:
                                imgPart.setImageResource(R.drawable.bodypart3);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo13));
                                break;

                            case 4:
                                imgPart.setImageResource(R.drawable.bodypart4);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo14));
                                break;

                            case 5:
                                imgPart.setImageResource(R.drawable.bodypart5);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo15));
                                break;

                        }
                        break;
                    case 2:
                        imgAction.setImageResource(R.drawable.ic_suck);
                        switch (getRandomNumberRange(1, 4)) {
                            case 1:
                                imgPart.setImageResource(R.drawable.bodypart1);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo21));
                                break;

                            case 2:
                                imgPart.setImageResource(R.drawable.bodypart3);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo22));
                                break;

                            case 3:
                                imgPart.setImageResource(R.drawable.bodypart4);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo23));
                                break;

                            case 4:
                                imgPart.setImageResource(R.drawable.bodypart5);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo24));
                                break;
                        }
                        break;
                    case 3:
                        imgAction.setImageResource(R.drawable.ic_squiz);
                        switch (getRandomNumberRange(1, 6)) {
                            case 1:
                                imgPart.setImageResource(R.drawable.bodypart1);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo31));
                                break;

                            case 2:
                                imgPart.setImageResource(R.drawable.bodypart3);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo33));
                                break;


                            case 4:
                                imgPart.setImageResource(R.drawable.bodypart4);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo34));
                                break;

                            case 5:
                                imgPart.setImageResource(R.drawable.bodypart5);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo35));
                                break;

                            case 6:
                                imgPart.setImageResource(R.drawable.bodypart6);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo36));
                                break;
                        }
                        break;

                    case 4:
                        imgAction.setImageResource(R.drawable.ic_nibble);
                        switch (getRandomNumberRange(1, 5)) {
                            case 1:
                                imgPart.setImageResource(R.drawable.bodypart1);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo41));
                                break;

                            case 2:
                                imgPart.setImageResource(R.drawable.bodypart2);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo42));
                                break;

                            case 3:
                                imgPart.setImageResource(R.drawable.bodypart3);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo43));
                                break;

                            case 4:
                                imgPart.setImageResource(R.drawable.bodypart4);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo44));
                                break;

                            case 5:
                                imgPart.setImageResource(R.drawable.bodypart6);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo45));
                                break;
                        }
                        break;

                    case 5:
                        imgAction.setImageResource(R.drawable.ic_lick);
                        switch (getRandomNumberRange(1, 5)) {
                            case 1:
                                imgPart.setImageResource(R.drawable.bodypart1);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo51));
                                break;

                            case 2:
                                imgPart.setImageResource(R.drawable.bodypart2);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo52));
                                break;

                            case 3:
                                imgPart.setImageResource(R.drawable.bodypart3);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo53));
                                break;

                            case 4:
                                imgPart.setImageResource(R.drawable.bodypart4);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo54));
                                break;

                            case 5:
                                imgPart.setImageResource(R.drawable.bodypart5);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo55));
                                break;
                        }
                        break;

                    case 6:
                        imgAction.setImageResource(R.drawable.ic_kiss);
                        switch (getRandomNumberRange(1, 6)) {
                            case 1:
                                imgPart.setImageResource(R.drawable.bodypart1);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo61));
                                break;

                            case 2:
                                imgPart.setImageResource(R.drawable.bodypart2);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo62));
                                break;

                            case 3:
                                imgPart.setImageResource(R.drawable.bodypart3);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo63));
                                break;

                            case 4:
                                imgPart.setImageResource(R.drawable.bodypart4);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo64));
                                break;

                            case 5:
                                imgPart.setImageResource(R.drawable.bodypart5);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo65));
                                break;

                            case 6:
                                imgPart.setImageResource(R.drawable.bodypart6);
                                txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo66));
                                break;
                        }
                        break;
                }
                break;
            case 1:
                //rollingSexPlayResults();
                break;
        }

        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dice_result_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void rollingForeplayResults() {
        switch (getRandomNumberRange(1, 6)) {
            case 1:
                imgAction.setImageResource(R.drawable.ic_tickled);
                switch (getRandomNumberRange(1, 5)) {
                    case 1:
                        imgPart.setImageResource(R.drawable.bodypart1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo11));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.bodypart2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo12));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.bodypart3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo13));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.bodypart4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo14));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.bodypart5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo15));
                        break;

                }
                break;
            case 2:
                imgAction.setImageResource(R.drawable.ic_suck);
                switch (getRandomNumberRange(1, 4)) {
                    case 1:
                        imgPart.setImageResource(R.drawable.bodypart1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo21));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.bodypart3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo22));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.bodypart4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo23));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.bodypart5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo24));
                        break;
                }
                break;
            case 3:
                imgAction.setImageResource(R.drawable.ic_squiz);
                switch (getRandomNumberRange(1, 6)) {
                    case 1:
                        imgPart.setImageResource(R.drawable.bodypart1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo31));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.bodypart3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo33));
                        break;


                    case 4:
                        imgPart.setImageResource(R.drawable.bodypart4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo34));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.bodypart5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo35));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.bodypart6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo36));
                        break;
                }
                break;

            case 4:
                imgAction.setImageResource(R.drawable.ic_nibble);
                switch (getRandomNumberRange(1, 5)) {
                    case 1:
                        imgPart.setImageResource(R.drawable.bodypart1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo41));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.bodypart2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo42));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.bodypart3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo43));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.bodypart4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo44));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.bodypart6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo45));
                        break;
                }
                break;

            case 5:
                imgAction.setImageResource(R.drawable.ic_lick);
                switch (getRandomNumberRange(1, 5)) {
                    case 1:
                        imgPart.setImageResource(R.drawable.bodypart1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo51));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.bodypart2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo52));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.bodypart3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo53));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.bodypart4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo54));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.bodypart5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo55));
                        break;
                }
                break;

            case 6:
                imgAction.setImageResource(R.drawable.ic_kiss);
                switch (getRandomNumberRange(1, 6)) {
                    case 1:
                        imgPart.setImageResource(R.drawable.bodypart1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo61));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.bodypart2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo62));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.bodypart3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo63));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.bodypart4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo64));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.bodypart5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo65));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.bodypart6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.foreplaycombo66));
                        break;
                }
                break;
        }
    }

    /*
    private void rollingSexPlayResults() {
        switch (getRandomNumberRange(1, 6)) {
            case 1:
                switch (getRandomNumberRange(1, 6)) {
                    imgAction.setImageResource(R.drawable.sexAction1);
                    case 1:
                        imgPart.setImageResource(R.drawable.sexposition1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo11));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.sexposition2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo12));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.sexposition3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo13));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.sexposition4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo14));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.sexposition5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo15));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.sexposition6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo16));
                        break;
                }
                break;
            case 2:
                switch (getRandomNumberRange(1, 6)) {
                    imgAction.setImageResource(R.drawable.sexAction2);
                    case 1:
                        imgPart.setImageResource(R.drawable.sexposition1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo21));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.sexposition2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo22));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.sexposition3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo23));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.sexposition4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo24));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.sexposition5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo25));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.sexposition6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo26));
                        break;
                }
                break;
            case 3:
                switch (getRandomNumberRange(1, 6)) {
                    imgAction.setImageResource(R.drawable.sexAction3);
                    case 1:
                        imgPart.setImageResource(R.drawable.sexposition1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo31));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.sexposition2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo32));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.sexposition3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo33));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.sexposition4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo34));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.sexposition5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo35));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.sexposition6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo36));
                        break;
                }
                break;

            case 4:
                switch (getRandomNumberRange(1, 6)) {
                    imgAction.setImageResource(R.drawable.sexAction4);
                    case 1:
                        imgPart.setImageResource(R.drawable.sexposition1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo41));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.sexposition2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo42));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.sexposition3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo43));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.sexposition4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo44));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.sexposition5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo45));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.sexposition6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo46));
                        break;
                }
                break;

            case 5:
                switch (getRandomNumberRange(1, 6)) {
                    imgAction.setImageResource(R.drawable.sexAction5);
                    case 1:
                        imgPart.setImageResource(R.drawable.sexposition1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo51));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.sexposition2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo52));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.sexposition3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo53));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.sexposition4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo54));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.sexposition5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo55));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.sexposition6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo56));
                        break;
                }
                break;

            case 6:
                switch (getRandomNumberRange(1, 6)) {
                    imgAction.setImageResource(R.drawable.sexAction6);
                    case 1:
                        imgPart.setImageResource(R.drawable.sexposition1);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo61));
                        break;

                    case 2:
                        imgPart.setImageResource(R.drawable.sexposition2);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo62));
                        break;

                    case 3:
                        imgPart.setImageResource(R.drawable.sexposition3);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo63));
                        break;

                    case 4:
                        imgPart.setImageResource(R.drawable.sexposition4);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo64));
                        break;

                    case 5:
                        imgPart.setImageResource(R.drawable.sexposition5);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo65));
                        break;

                    case 6:
                        imgPart.setImageResource(R.drawable.sexposition6);
                        txtSummary.setText(c.getApplicationContext().getString(R.string.sexcombo66));
                        break;
                }
                break;
        }
    }
    */

    public int getRandomNumberRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}