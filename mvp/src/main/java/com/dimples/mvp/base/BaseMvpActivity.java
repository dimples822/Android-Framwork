package com.dimples.mvp.base;

import android.content.Context;
import android.os.Bundle;

import com.dimples.mvp.annotation.ViewInject;
import com.dimples.mvp.view.LifeCircleMvpActivity;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * Activity基类
 *
 * @author zhongyj
 * @date 2019/4/19 0:23
 */
public abstract class BaseMvpActivity extends LifeCircleMvpActivity {

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (null != annotation) {
            if (annotation.LayoutId() == -1) {
                throw new RuntimeException("LayoutId = 默认值(未注册)");
            } else {
                setContentView(annotation.LayoutId());
                bindView();
                afterBindView();
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }

    /**
     * 模板方法  设计模式
     */
    public abstract void afterBindView();

    /**
     * View的依赖注解绑定
     */
    private void bindView() {
        ButterKnife.bind(this);
    }

}



























