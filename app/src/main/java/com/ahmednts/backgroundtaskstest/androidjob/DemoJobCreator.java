package com.ahmednts.backgroundtaskstest.androidjob;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by AhmedNTS on 5/29/2017.
 */

public class DemoJobCreator implements JobCreator {

    @Override
    public Job create(String tag) {
        switch (tag) {
            case DemoSyncJob.TAG:
                return new DemoSyncJob();
            default:
                return null;
        }
    }
}