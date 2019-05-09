package com.dimples.framework.fragment.index;

import android.widget.TextView;

import com.dimples.base.BaseFragment;
import com.dimples.base.annotation.ViewInject;
import com.dimples.framework.R;

import butterknife.BindView;

/**
 * 水波纹动画
 *
 * @author zhongyj
 * @date 2019/5/9
 */
@ViewInject(layoutId = R.layout.fragment_main)
public class IndexFragment extends BaseFragment implements IIndexContract.IView {


    private static final String D_TAG = "D-IndexFragment";

    @BindView(R.id.tv_index_content)
    TextView mTvIndexContent;

    private IIndexContract.IPresenter mPresenter;

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        String content = mPresenter.getContentString();
        mTvIndexContent.setText(content);
    }

    @Override
    public void createPresenter() {
        mPresenter = new IndexPresenter(this);
    }
}





























