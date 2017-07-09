package com.ahmednts.backgroundtaskstest.androidjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmednts.backgroundtaskstest.R;
import com.evernote.android.job.JobManager;

public class AndroidJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_job);

        JobManager.create(this).addJobCreator(new DemoJobCreator());

        DemoSyncJob.scheduleJob();
    }
}
