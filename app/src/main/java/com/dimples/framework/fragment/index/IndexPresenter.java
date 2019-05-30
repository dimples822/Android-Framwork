package com.dimples.framework.fragment.index;

import com.dimples.framework.base.MyPresenter;

/**
 * 首页
 *
 * @author zhongyj
 * @date 2019/5/9
 */
public class IndexPresenter extends MyPresenter<IIndexContract.IView> implements IIndexContract.IPresenter {

    IndexPresenter(IIndexContract.IView view) {
        super(view);
    }

    @Override
    public String getContentString() {
        return "您好, 世界 !!!";
    }
}












