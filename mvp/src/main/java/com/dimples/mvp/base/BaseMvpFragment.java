package com.dimples.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimples.mvp.annotation.ViewInject;
import com.dimples.mvp.view.LifeCircleMvpFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * Fragment基类
 *
 * @author zhongyj
 * @date 2019/4/19 0:30
 */
public abstract class BaseMvpFragment extends LifeCircleMvpFragment {

    public Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (null != annotation) {
            int fragmentLayoutId = annotation.layoutId();
            if (fragmentLayoutId == -1) {
                throw new RuntimeException("layoutId = 默认值(未注册)");
            } else {
                view = initFragmentView(fragmentLayoutId);
                bindView(view);
                afterBindView();
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
        return view;
    }

    private View initFragmentView(int fragmentLayoutId) {
        return LayoutInflater.from(mContext).inflate(fragmentLayoutId, null);
    }

    /**
     * 模板方法  设计模式
     */
    public abstract void afterBindView();

    /**
     * View的依赖注解绑定
     */
    private void bindView(View view) {
        ButterKnife.bind(this, view);
    }

}



























