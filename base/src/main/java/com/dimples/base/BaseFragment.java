package com.dimples.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dimples.base.annotation.ViewBind;
import com.dimples.mvp.base.BaseMvpFragment;

/**
  *
  * @author zhongyj
  * @date 2019/4/22 22:21
  */
public abstract class BaseFragment extends BaseMvpFragment {

    public Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = ViewBind.bind(this);
        createPresenter();
        afterBindView();
        return view;
    }

    /**
     * 模板方法  设计模式
     */
    public abstract void afterBindView();

    /**
     * 创建presenter
     */
    public abstract void createPresenter();

}
