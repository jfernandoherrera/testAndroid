package com.jeduca.jfernandoherrera.jeducatest.activities.fragments;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jeduca.jfernandoherrera.jeducatest.R;
import com.jeduca.jfernandoherrera.jeducatest.activities.MainActivity;
import com.jeduca.jfernandoherrera.jeducatest.model.context.TestContext;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.Test;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.TestAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.test.TestCeaAttributes;
import com.jeduca.jfernandoherrera.jeducatest.model.domain.user.User;
import com.jeduca.jfernandoherrera.jeducatest.utils.view.InfoTextView;
import com.jeduca.jfernandoherrera.jeducatest.utils.view.SelectTestOptionsView;

public class TestFragment extends DialogFragment {

    private InfoTextView infoTextView;
    private SelectTestOptionsView selectTestOptionsView;
    private TestContext testContext;
    private String type;
    private Test test;
    private Toolbar toolbar;
    private TextView progressBar;
    private User user;
    private Activity activity;

    @Override
    public void onAttach(Activity activity) {

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        super.onAttach(activity);

        this.activity = activity;

    }

    public void setUser(User user) {

        this.user = user;

    }

    public User getUser() {

        return user;

    }

    public void setType(String type) {

        this.type = type;

    }

    public String getType() {

        return type;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_test, container, false);

        testContext = TestContext.context(testContext, getContext());

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.menu_ready);

        progressBar = (TextView) rootView.findViewById(R.id.progressBar);

        infoTextView = (InfoTextView) rootView.findViewById(R.id.info);

        selectTestOptionsView = (SelectTestOptionsView) rootView.findViewById(R.id.select);

        lookingForIncomplete();

        MenuItem ready = toolbar.getMenu().getItem(0);

        ready.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int percentage = test.getPercentage();

                String selected = selectTestOptionsView.getSelected();

                if(percentage == 100) {

                    TestFragment.this.dismiss();

                } else if(selected != null) {

                    test.setPercentage(percentage + 25);

                    if(selected.equals(TestAttributes.accomplished)) {

                        test.setAccomplished(test.getAccomplished() + 1);

                    } else if(selected.equals(TestAttributes.notAccomplished)){

                        test.setNotAccomplished(test.getNotAccomplished() + 1);

                    } else if(selected.equals(TestAttributes.dontApply)) {

                        test.setDontApply(test.getDontApply() + 1);

                    }

                    testContext.updateTest(test);

                    test();

                    selectTestOptionsView.restart();

                }

                return false;

            }

        });


        MenuItem close = toolbar.getMenu().getItem(1);

        close.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                dismiss();

                launchNotification();

                return false;

            }

        });

                return rootView;

    }

    private void launchNotification() {

        String tickerText = getString(R.string.test_closed);

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification(R.mipmap.ic_ready, tickerText, System.currentTimeMillis());

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notification.number += 1;

        Intent intent = activity.getIntent();

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(activity);

        builder.setAutoCancel(false);

        builder.setTicker("this is ticker text");

        builder.setContentTitle("JeducaTest Notification");

        builder.setContentText(tickerText);

        builder.setSmallIcon(R.mipmap.ic_launcher);

        builder.setContentIntent(pendingIntent);

        builder.setOngoing(true);

        builder.setNumber(100);

        builder.build();

        notification = builder.getNotification();

        notificationManager.notify(0, notification);

    }
    private void lookingForIncomplete() {

       test = testContext.getInitiatedTest(type);

       if(test == null) {

           initTest();

       }

        test();

    }

    private void initTest() {

        test = new Test(0, type);

        test.setId(testContext.insertTest(test));

    }

    private void test() {

        if(type.equals(TestAttributes.testCEA)) {

            infoTextView.setTitle1(TestCeaAttributes.title1);

            infoTextView.setTitle2(TestCeaAttributes.title2);

            getTextCEA();

        }

        String consultas = test.getPercentage() + "%" + " C: " +

                test.getAccomplished() + " NC: " + test.getNotAccomplished() + " NA: " + test.getDontApply();

        progressBar.setText(consultas);

    }

    private void getTextCEA() {

        if(test.getPercentage() == 0) {

            infoTextView.setText1(TestCeaAttributes.itemConstitution);

            infoTextView.setText2(TestCeaAttributes.requerimientoConstitution);

        }else if(test.getPercentage() == 25) {

            infoTextView.setText1(TestCeaAttributes.itemArea);

            infoTextView.setText2(TestCeaAttributes.requerimientoArea);

        } else if(test.getPercentage() == 50) {

            infoTextView.setText1(TestCeaAttributes.itemApertura);

            infoTextView.setText2(TestCeaAttributes.requerimientoApertura);

        } else  if(test.getPercentage() == 75) {

            infoTextView.setText1(TestCeaAttributes.itemCertificaciones);

            infoTextView.setText2(TestCeaAttributes.requerimientoCertificaciones);

        } else if(test.getPercentage() == 100) {

            infoTextView.setText1(TestAttributes.done);

            infoTextView.setText2(TestAttributes.done);

        }

    }


}
