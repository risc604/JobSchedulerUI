package com.demo.tomcat.jobschedulerui;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.LinkedList;


// https://www.programcreek.com/java-api-examples/index.php?source_dir=android-JobScheduler-master/Application/src/main/java/com/example/android/jobscheduler/MainActivity.java#

public class UIJobService extends JobService
{
    private final static String TAG = UIJobService.class.getSimpleName();

    MainActivity mActivity;
    private final LinkedList<JobParameters> jobParamsMap = new LinkedList<JobParameters>();

    public UIJobService() {
        Log.i(TAG, "UIJobService()");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i(TAG, "onStartCommand()");

        Messenger callback = intent.getParcelableExtra("messenger");
        Message m = Message.obtain();
        m.what = MainActivity.MSG_SERVICE_OBJ;
        m.obj = this;

        try {
            callback.send(m);
        } catch (RemoteException e) {
            Log.e(TAG, "Error !! passing service object back to activity.");
            e.printStackTrace();
        }
        return START_NOT_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params)
    {
        Log.i(TAG, "onStartJob(), params: " + params.getJobId());

        jobParamsMap.add(params);
        if (mActivity != null)
        {
            mActivity.onReceivedStartJob(params);
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob(), params: " + params.getJobId());

        jobParamsMap.remove(params);
        if (mActivity != null)
        {
            mActivity.onReceivedStopJob();
        }
        return true;
    }

    public void setUICallback(MainActivity activity)
    {
        Log.i(TAG, "setUICallback()");

        mActivity = activity;
    }

    public void scheduleJob(JobInfo t)
    {
        Log.i(TAG, "scheduleJob()");

        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(t);
    }

    public boolean callJobFinished()
    {
        Log.i(TAG, "callJobFinished()");

        JobParameters params = jobParamsMap.poll();
        if (params == null)
        {
            return  false;
        }
        else
        {
            jobFinished(params, false);
            return true;
        }
    }


}
