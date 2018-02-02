package com.demo.tomcat.jobschedulerui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


//http://android-er.blogspot.tw/2015/05/example-of-jobscheduler-and-jobservice.html
public class MainActivity extends AppCompatActivity
{
    private final static String TAG = MainActivity.class.getSimpleName();

    public final static int MSG_UNCOLOUR_START = 0;
    public final static int MSG_UNCOLOUR_STOP = 1;
    public final static int MSG_SERVICE_OBJ = 2;

    int defaultColor;
    int startJobColor;
    int stopJobColor;

    TextView mShowStartView, mShowStopView, mParamsTextView;
    EditText mDelayEditText, mDeadlineEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initControl();
    }



    //------ user define function ------------------------------------------------//
    private void initView()
    {

    }

    private void initControl()
    {

    }


}

