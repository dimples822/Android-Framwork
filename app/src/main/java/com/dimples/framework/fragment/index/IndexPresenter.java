package com.dimples.framework.fragment.index;

import com.dimples.base.BasePresenter;

/**
  *
  * @author zhongyj
  * @date 2019/5/9
  */
public class IndexPresenter extends BasePresenter<IIndexContract.IView> implements IIndexContract.IPresenter {

    public IndexPresenter(IIndexContract.IView view) {
        super(view);
    }

    @Override
    protected IIndexContract.IView getEmptyView() {
        return IIndexContract.EMPTY_VIEW;
    }

    @Override
    public String getContentString() {
        return "您好, 世界 !!!";
    }
}












