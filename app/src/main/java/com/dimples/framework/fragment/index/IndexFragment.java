package com.dimples.framework.fragment.index;

import android.widget.Button;
import android.widget.LinearLayout;

import com.dimples.base.BaseFragment;
import com.dimples.base.annotation.ViewInject;
import com.dimples.base.dialog.AlertDialog;
import com.dimples.framework.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 *
 * @author zhongyj
 * @date 2019/5/9
 */
@ViewInject(layoutId = R.layout.fragment_index)
public class IndexFragment extends BaseFragment implements IIndexContract.IView {

    private static final String D_TAG = "D-IndexFragment";


    @BindView(R.id.linear_layout)
    LinearLayout mLinearLayout;
    @BindView(R.id.btn_index_dialog)
    Button mBtnIndexDialog;

    private IIndexContract.IPresenter mPresenter;

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void createPresenter() {
        mPresenter = new IndexPresenter(this);
    }

    @OnClick(R.id.btn_index_dialog)
    public void onViewClicked() {
        AlertDialog mDialog = new AlertDialog.Builder(mContext)
                .setContentView(R.layout.detail_comment_dialog)
                .show();
    }
}





























