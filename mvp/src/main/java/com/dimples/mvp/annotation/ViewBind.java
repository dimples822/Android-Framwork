package com.dimples.mvp.annotation;

import android.app.Activity;

import butterknife.ButterKnife;

/**
 * 绑定
 *
 * @author zhongyj
 * @date 2019/4/21 13:49
 */
public class ViewBind {

    /**
     * 绑定
     *
     * @param activity Activity
     */
    public static void bind(Activity activity) {
        bindActivityView(activity);
    }

    private static void bindActivityView(Activity activity) {
        ViewInject annotation = activity.getClass().getAnnotation(ViewInject.class);
        if (null != annotation) {
            if (annotation.layoutId() == -1) {
                throw new RuntimeException("layoutId = 默认值(未注册)");
            } else {
                activity.setContentView(annotation.layoutId());
                bindView(activity);
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }

    /**
     * View的依赖注解绑定
     */
    private static void bindView(Activity activity) {
        ButterKnife.bind(activity);
    }

}



























