package com.dimples.base.annotation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * 视图控件绑定
 *
 * @author zhongyj
 * @date 2019/4/21 13:49
 */
public class ViewBind {

    /**
     * 绑定Activity
     *
     * @param activity Activity
     */
    public static void bind(Activity activity) {
        bindActivityView(activity);
    }

    /**
     * 绑定Fragment
     *
     * @param fragment Fragment
     */
    public static View bind(Fragment fragment) {
        return bindFragmentView(fragment);
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

    private static View bindFragmentView(Fragment fragment) {
        Context context = fragment.getContext();
        View view;
        ViewInject annotation = fragment.getClass().getAnnotation(ViewInject.class);
        if (null != annotation) {
            int fragmentLayoutId = annotation.layoutId();
            if (fragmentLayoutId == -1) {
                throw new RuntimeException("layoutId = 默认值(未注册)");
            } else {
                view = LayoutInflater.from(context).inflate(fragmentLayoutId, null);
                bindView(fragment, view);
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
        return view;
    }

    /**
     * View的依赖注解绑定
     */
    private static void bindView(Activity activity) {
        ButterKnife.bind(activity);
    }

    private static void bindView(Fragment fragment, View view) {
        ButterKnife.bind(fragment, view);
    }

}



























