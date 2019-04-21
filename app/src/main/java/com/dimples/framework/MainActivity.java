package com.dimples.framework;

import com.dimples.mvp.annotation.ViewInject;
import com.dimples.mvp.base.BaseMvpActivity;

/**
 * MainActivity
 *
 * @author zhongyj
 * @date 2019/4/21 14:09
 */
@ViewInject(layoutId = R.layout.activity_main)
public class MainActivity extends BaseMvpActivity {

    @Override
    public void afterBindView() {

    }
}
