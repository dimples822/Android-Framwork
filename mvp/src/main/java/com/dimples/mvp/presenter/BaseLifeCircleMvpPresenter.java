package com.dimples.mvp.presenter;

import com.dimples.mvp.ILifeCircle;
import com.dimples.mvp.IMvpView;
import com.dimples.mvp.MvpController;

import java.lang.ref.WeakReference;

/**
 * 抽象中介者
 * 保存和获取V层的引用
 *
 * @author zhongyj
 * @date 2019/3/13 22:10
 */
public abstract class BaseLifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    private WeakReference<T> weakReference;

    protected BaseLifeCircleMvpPresenter() {
        super();
    }

    protected BaseLifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        MvpController mvpController = iMvpView.getMvpController();
        mvpController.savePresenter(this);
    }

    /**
     * 绑定View为弱引用
     *
     * @param iMvpView IMvpView
     */
    @Override
    public void attachView(IMvpView iMvpView) {
        if (null == weakReference) {
            weakReference = new WeakReference(iMvpView);
        } else {
            T view = weakReference.get();
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    /**
     * 界面销毁时调用的方法
     * 销毁弱引用——（不是从其他类继承过来的）
     */
    @Override
    public void onDestroy() {
        weakReference = null;
    }

    protected T getView() {
        T view = weakReference != null ? weakReference.get() : null;
        if (view == null) {
            return getEmptyView();
        }
        return view;
    }

    /**
     * 获取View
     *
     * @return T
     */
    abstract T getEmptyView();
}













