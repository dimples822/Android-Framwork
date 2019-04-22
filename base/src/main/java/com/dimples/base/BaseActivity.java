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
        ViewBind.bind(this);
        createPresenter();
        afterBindView();
    }

    /**
     * 创建presenter
     */
    public abstract void createPresenter();

    /**
     * 模板方法  设计模式
     */
    public abstract void afterBindView();

}
