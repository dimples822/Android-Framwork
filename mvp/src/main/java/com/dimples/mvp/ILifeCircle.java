package com.dimples.mvp;

import android.content.Intent;
import android.os.Bundle;

/**
 * 生命周期方法
 *
 * @author zhongyj
 * @date 2019/4/16 22:06
 */
public interface ILifeCircle {

    /**
     * 创建
     *
     * @param savedInstanceState Bundle
     * @param intent             Intent
     * @param getArguments       Bundle
     */
    void onCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    /**
     * 开始
     */
    void onStart();

    /**
     * 恢复
     */
    void onResume();

    /**
     * 暂停
     */
    void onPause();

    /**
     * 停止
     */
    void onStop();

    /**
     * 销毁
     */
    void onDestroy();

    /**
     * 保存实例
     *
     * @param bundle Bundle
     */
    void onSaveInstanceState(Bundle bundle);

    /**
     * Fragment创建
     *
     * @param savedInstanceState Bundle
     * @param intent             Intent
     * @param getArguments       Bundle
     */
    void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    /**
     * Fragment销毁View
     */
    void onDestroyView();

    /**
     * 自定义
     */
    void onViewDestroy();

    /**
     * newIntent
     *
     * @param intent Intent
     */
    void onNewIntent(Intent intent);

    /**
     * onActivityResult
     *
     * @param requestCode int
     * @param resultCode  int
     * @param data        Intent
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * View绑定
     *
     * @param iMvpView IMvpView
     */
    void attachView(IMvpView iMvpView);

}






