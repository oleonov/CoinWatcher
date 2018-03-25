package com.app.coinwatcher.components

import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.Job


/**
 * Created by Leonov Oleg, http://pandorika-it.com on 13.01.17.
 */

abstract class NetworkActivity : AppCompatActivity() {
    private val jobList = HashMap<String,Job>()

    internal fun addAsyncJob(job: Job, jobName:String?=null) {
        jobList[jobName?:System.currentTimeMillis().toString()] = job
    }
    internal fun stopJob(jobName:String)
    {
        jobList[jobName]?.let {
            it.cancel(null)
            jobList.remove(jobName)
        }
    }

    public override fun onStop() {
        for (job in jobList.values)
            job.cancel(null)
        jobList.clear()
        super.onStop()
    }
}
