package com.dimples.base;

import com.dimples.mvp.IMvpView;
import com.dimples.mvp.base.BaseMvpPresenter;

/**
  *
  * @author zhongyj
  * @date 2019/4/22 22:21
  */
public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {
}
