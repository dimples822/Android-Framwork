package com.dimples.framework.base;

import android.app.Application;

import com.dimples.base.ExceptionCrashHandler;

/**
  *
  * @author zhongyj
  * @date 2019/4/29
  */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionCrashHandler.getInstance().init(this);
    }
}
