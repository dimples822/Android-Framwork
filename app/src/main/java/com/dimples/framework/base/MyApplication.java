package com.dimples.framework.base;

import com.dimples.base.BaseApplication;
import com.dimples.base.ExceptionCrashHandler;
import com.dimples.base.fixbug.FixDexManager;

/**
  *
  * @author zhongyj
  * @date 2019/4/29
  */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置全局异常捕捉类
        ExceptionCrashHandler.getInstance().init(this);

        try {
            // 很耗时  热启动和冷启动  2s   400 ms
            FixDexManager fixDexManager = new FixDexManager(this);
            // 加载所有修复的Dex包
            fixDexManager.loadFixDex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
