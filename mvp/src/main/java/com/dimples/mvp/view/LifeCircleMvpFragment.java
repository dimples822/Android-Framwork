package com.dimples.mvp.view;

import android.content.Intent;
import android.os.Bundle;

import com.dimples.mvp.IMvpView;
import com.dimples.mvp.MvpController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 静态代理类
 *
 * @author zhongyj
 * @date 2019/3/13 23:08
 */
public class LifeCircleMvpFragment extends Fragment implements IMvpView {

    private MvpController mvpController;

    @Override
    public MvpController getMvpController() {
        if (this.mvpController == null) {
            this.mvpController = new MvpController();
        }
        return this.mvpController;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (null == bundle) {
            bundle = new Bundle();
        }
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onCreated(savedInstanceState, null, bundle);
            mvpController.onActivityCreated(savedInstanceState, null, bundle);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (null == bundle) {
            bundle = new Bundle();
        }
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onActivityCreated(savedInstanceState, null, bundle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpController mvpController = this.getMvpController();
        if ((mvpController != null)) {
            mvpController.onViewDestroy();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpController mvpController = this.getMvpController();
        if (mvpController != null) {
            mvpController.onActivityResult(requestCode, resultCode, data);
        }
    }
}











