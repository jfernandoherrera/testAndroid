package com.jeduca.jfernandoherrera.jeducatest.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeduca.jfernandoherrera.jeducatest.R;

public class InfoTextView extends RelativeLayout{

    private TextView title1;
    private TextView title2;
    private TextView text1;
    private TextView text2;

    public InfoTextView(Context context, AttributeSet attrs) {

        super(context, attrs);

        init(context);

    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.view_info_text, this);

        title1 = (TextView) findViewById(R.id.title1);

        title2 = (TextView) findViewById(R.id.title2);

        text1 = (TextView) findViewById(R.id.text1);

        text2 = (TextView) findViewById(R.id.text2);

    }

    public void setTitle1(String title1) {

        this.title1.setText(title1);

    }

    public void setTitle2(String title2) {

        this.title2.setText(title2);

    }

    public void setText1(String text1) {

        this.text1.setText(text1);

    }

    public void setText2(String text2) {

        this.text2.setText(text2);

    }

}
