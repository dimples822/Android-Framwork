package com.dimples.mvp.base;

import com.dimples.mvp.IMvpView;
import com.dimples.mvp.presenter.BaseLifeCircleMvpPresenter;

/**
 * P层的中间类
 *
 * @author zhongyj
 * @date 2019/3/13 22:35
 */
public abstract class BaseMvpPresenter<T extends IMvpView> extends BaseLifeCircleMvpPresenter<T> {

    public BaseMvpPresenter(T view) {
        super(view);
    }

}
