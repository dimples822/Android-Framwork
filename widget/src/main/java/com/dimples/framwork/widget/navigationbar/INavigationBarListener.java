package com.dimples.framwork.widget.navigationbar;

import android.view.View;

/**
 * 导航栏的监听事件
 *
 * @author zhongyj
 * @date 2019/6/15
 */
public interface INavigationBarListener {

    /**
     * 左项被点击
     *
     * @param v 被点击的左项View
     */
    void onLeftClick(View v);

    /**
     * 标题被点击
     *
     * @param v 被点击的标题View
     */
    void onTitleClick(View v);

    /**
     * 右项被点击
     *
     * @param v 被点击的右项View
     */
    void onRightClick(View v);
}
