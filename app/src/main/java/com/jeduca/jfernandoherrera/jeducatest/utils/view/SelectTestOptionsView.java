package com.jeduca.jfernandoherrera.jeducatest.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import com.jeduca.jfernandoherrera.jeducatest.R;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.TestAttributes;

public class SelectTestOptionsView extends RelativeLayout{

    private RadioButton accomplished;
    private RadioButton notAccomplished;
    private RadioButton dontApply;

    public SelectTestOptionsView(Context context, AttributeSet attrs) {

        super(context, attrs);

        init(context);

    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.view_select_test_options, this);

        accomplished = (RadioButton) findViewById(R.id.accomplished);

        notAccomplished = (RadioButton) findViewById(R.id.notAccomplished);

        dontApply = (RadioButton) findViewById(R.id.dontApply);

        setOnClick();

    }

    private void setOnClick() {

        accomplished.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if(accomplished.isChecked()) {

                    notAccomplished.setChecked(false);

                    dontApply.setChecked(false);

                }
            }
        });

        notAccomplished.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if(notAccomplished.isChecked()) {

                    accomplished.setChecked(false);

                    dontApply.setChecked(false);

                }
            }
        });

        dontApply.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if(dontApply.isChecked()) {

                    notAccomplished.setChecked(false);

                    accomplished.setChecked(false);

                }
            }
        });

    }

    public void restart() {

        accomplished.setChecked(false);

        notAccomplished.setChecked(false);

        dontApply.setChecked(false);

    }

    public String getSelected() {

        String selected = null;

        if(accomplished.isChecked()) {

            selected = TestAttributes.accomplished;

        } else if(notAccomplished.isChecked()) {

            selected = TestAttributes.notAccomplished;

        } else if(dontApply.isChecked()) {

            selected = TestAttributes.dontApply;

        }

        return selected;

    }

}
