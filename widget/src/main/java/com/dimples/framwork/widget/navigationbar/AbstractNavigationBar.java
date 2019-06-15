package com.dimples.framwork.widget.navigationbar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;

/**
 * 头部的Builder基类
 *
 * @author zhongyj
 * @date 2019/6/3
 */
public abstract class AbstractNavigationBar<P extends AbstractNavigationBar.Builder.AbsNavigationParams> implements INavigationBar {

    private P mParams;

    private View mNavigationView;

    public AbstractNavigationBar(P params) {
        this.mParams = params;
        createAndBindView();
    }


    protected P getParams() {
        return mParams;
    }

    /**
     * 设置文本
     *
     * @param viewId int
     * @param text   String
     */
    protected void setText(@IdRes int viewId, String text) {
        TextView tv = findViewById(viewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    /**
     * 设置控件的背景颜色
     *
     * @param color  int
     */
    protected void setBackgroundColor(@ColorInt int color) {
        View mChildAt = mParams.mParent.getChildAt(0);
        mChildAt.setBackgroundColor(color);
    }

    /**
     * 设置控件的内容颜色
     *
     * @param color  int
     * @param viewId int
     */
    protected void setForegroundColor(@ColorInt int color, @IdRes int... viewId) {
        TextView tv;
        for (int mId : viewId) {
            tv = findViewById(mId);
            tv.setTextColor(color);
        }
    }

    /**
     * 设置点击
     *
     * @param viewId   int
     * @param listener View.OnClickListener
     */
    protected void setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
    }


    @SuppressWarnings("unchecked")
    private <T extends View> T findViewById(@IdRes int viewId) {
        return (T) mNavigationView.findViewById(viewId);
    }

    /**
     * 绑定和创建View
     */
    private void createAndBindView() {
        // 1. 创建View
        if (mParams.mParent == null) {
            // 获取系统activity的根布局，View源码
            ViewGroup activityRoot = (ViewGroup) ((Activity) (mParams.mContext)).getWindow().getDecorView();
            mParams.mParent = (ViewGroup) activityRoot.getChildAt(0);
            Log.e("TAG", mParams.mParent + "");
        }
        // 处理Activity的源码，后面再去看
        if (mParams.mParent == null) {
            return;
        }
        mNavigationView = LayoutInflater.from(mParams.mContext)
                // 插件换肤
                .inflate(getNavigationBarLayout(), mParams.mParent, false);
        // 2.添加
        mParams.mParent.addView(mNavigationView, 0);
        //绑定参数
        bindView();
    }

    /**
     * Builder  仿照系统写的， 套路，活  AbstractNavigationBar  Builder  参数Params
     */
    public abstract static class Builder {

        public Builder(Context context, ViewGroup parent) {
        }

        /**
         * 获取AbsNavigationBar
         *
         * @return AbstractNavigationBar
         */
        public abstract AbstractNavigationBar builder();


        public static class AbsNavigationParams {
            public Context mContext;
            ViewGroup mParent;

            public AbsNavigationParams(Context context, ViewGroup parent) {
                this.mContext = context;
                this.mParent = parent;
            }
        }
    }
}
