package com.dimples.base.exception;

import android.util.Log;

/**
 * 自定义的注册异常类
 *
 * @author zhongyj
 * @date 2019/5/9
 */
public class ViewInjectException extends RuntimeException {

    private static final String TAG = "ViewInjectException";

    public ViewInjectException(String message) {
        super(message);
        Log.e(TAG, "ViewInjectException: " + message);
    }
}
