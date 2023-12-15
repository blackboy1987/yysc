package com.bootx.yysc.util

import android.app.Activity
import java.util.Stack


object ActivityStackManager {

    private var activityStack: Stack<Activity>? = null

    var  instance: ActivityStackManager = ActivityStackManager


    fun pushActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }


    /**
     * 获取指定的Activity
     */
    fun getActivity(cls: Class<*>): Activity? {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                return activity
            }
        }
        return null
    }

}