package com.dimples.framework.base;

import com.dimples.base.BasePresenter;
import com.dimples.base.GenericsUtils;
import com.dimples.mvp.IMvpView;
import com.dimples.mvp.apt.MvpEmptyViewFactory;

/**
 * 通过apt，处理getEmptyView
 *
 * @author zhongyj
 * @date 2019/5/30 22:18
 */
public class MyPresenter<T extends IMvpView> extends BasePresenter<T> {

    public MyPresenter(T view) {
        super(view);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T getEmptyView() {
        T t = null;
        Class superClassGenericType = GenericsUtils.getSuperClassGenericsType(this.getClass(), 0);
        try {
            t = (T) MvpEmptyViewFactory.create(superClassGenericType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
