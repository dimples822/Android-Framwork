package com.dimples.framework.base;

import com.dimples.base.BaseApplication;
import com.dimples.base.ExceptionCrashHandler;

/**
  *
  * @author zhongyj
  * @date 2019/4/29
  */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionCrashHandler.getInstance().init(this);
    }
}
