package com.dimples.mvp.base;

import android.content.Intent;
import android.os.Bundle;

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

    @Override
    public void onCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

}
