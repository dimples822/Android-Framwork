package com.dimples.framework;

import com.dimples.base.BaseActivity;
import com.dimples.base.annotation.ViewInject;

/**
 * MainActivity
 *
 * @author zhongyj
 * @date 2019/4/21 14:09
 */
@ViewInject(layoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    public void createPresenter() {

    }

    @Override
    public void afterBindView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_fragment, new MainFragment()).commit();
    }
}
