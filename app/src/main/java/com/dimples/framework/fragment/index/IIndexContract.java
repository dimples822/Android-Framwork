package com.dimples.framework.fragment.index;

import com.dimples.mvp.ILifeCircle;
import com.dimples.mvp.IMvpView;

/**
 * @author zhongyj
 * @date 2019/5/9
 */
public interface IIndexContract {

    interface IView extends IMvpView {

    }

    interface IPresenter extends ILifeCircle {

        /**
         * 获取内容
         *
         * @return String
         */
        String getContentString();
    }

    IIndexContract.IView EMPTY_VIEW = () -> null;

}
