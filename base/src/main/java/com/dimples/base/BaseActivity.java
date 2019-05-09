package com.dimples.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.dimples.base.annotation.ViewBind;
import com.dimples.mvp.base.BaseMvpActivity;

/**
  *
  * @author zhongyj
  * @date 2019/4/22 22:21
  */
public abstract class BaseActivity extends BaseMvpActivity {

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ViewBind.bindActivityView(this);
        createPresenter();
        initView();
        initData();
        initTitle();
    }

    /**
     * 创建presenter
     */
    public abstract void createPresenter();

    /**
     * 初始化title
     */
    public abstract void initTitle();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化视图
     */
    public abstract void initView();


}
