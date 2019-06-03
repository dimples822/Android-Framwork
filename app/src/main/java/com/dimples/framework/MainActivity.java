package com.dimples.framework;

import android.util.Log;

import com.dimples.base.BaseActivity;
import com.dimples.base.DefaultNavigationBar;
import com.dimples.base.ExceptionCrashHandler;
import com.dimples.base.annotation.ViewInject;
import com.dimples.framework.fragment.index.IndexFragment;

import java.io.File;

/**
 * MainActivity
 *
 * @author zhongyj
 * @date 2019/4/21 14:09
 */
@ViewInject(layoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void createPresenter() {

    }

    @Override
    public void initTitle() {
        DefaultNavigationBar navigationBar = new
                DefaultNavigationBar.Builder(this)
                .setTitle("投稿")
                .builder();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        //首次进入软件首页时，获取上次软件的崩溃信息上传到服务器中
        File crashFile = ExceptionCrashHandler.getInstance().getCrashFile();
        if (crashFile.exists()) {
            // TODO: 2019/5/8 上传到服务器
            Log.i(TAG, "afterBindView: 上传软件的崩溃信息");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_fragment, new IndexFragment()).commit();
    }

}


















