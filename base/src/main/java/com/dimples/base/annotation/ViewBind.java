package com.dimples.base.annotation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimples.base.BaseActivity;
import com.dimples.base.BaseFragment;
import com.dimples.base.exception.ViewInjectException;

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
    public static void bindActivityView(BaseActivity activity) {
        ViewInject annotation = activity.getClass().getAnnotation(ViewInject.class);
        if (null != annotation) {
            if (annotation.layoutId() == -1) {
                throw new ViewInjectException("layoutId = 默认值(未注册)");
            } else {
                activity.setContentView(annotation.layoutId());
                ButterKnife.bind(activity);
            }
        } else {
            throw new ViewInjectException("annotation = null");
        }
    }

    /**
     * 绑定Fragment
     *
     * @param fragment Fragment
     */
    public static View bindFragmentView(BaseFragment fragment, LayoutInflater inflater, ViewGroup container) throws ViewInjectException {
        View view;
        ViewInject annotation = fragment.getClass().getAnnotation(ViewInject.class);
        if (null != annotation) {
            int fragmentLayoutId = annotation.layoutId();
            if (fragmentLayoutId == -1) {
                throw new ViewInjectException("layoutId = 默认值(未注册)");
            } else {
                view = inflater.inflate(fragmentLayoutId, container, false);
                ButterKnife.bind(fragment, view);
            }
        } else {
            throw new ViewInjectException("annotation = null");
        }
        return view;
    }

}



























