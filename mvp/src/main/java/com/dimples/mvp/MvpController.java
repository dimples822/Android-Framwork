package com.dimples.mvp;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhongyj
 * @date 2019/4/16 22:04
 */
public class MvpController implements ILifeCircle {

    /**
     * 存放P层的实例
     */
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public void savePresenter(ILifeCircle lifeCircle) {
        this.lifeCircles.add(lifeCircle);
    }

    @Override
    public void onCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            lifeCircle.onCreated(savedInstanceState, intent, getArguments);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            lifeCircle.onActivityCreated(savedInstanceState, intent, getArguments);
        }
    }

    @Override
    public void onStart() {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onStart();
        }
    }

    @Override
    public void onResume() {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onResume();
        }
    }

    @Override
    public void onPause() {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onPause();
        }
    }

    @Override
    public void onStop() {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onDestroy();
        }
        this.lifeCircles.clear();
    }

    @Override
    public void onDestroyView() {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onDestroyView();
        }
    }

    @Override
    public void onViewDestroy() {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onViewDestroy();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        for (ILifeCircle lifeCircle : this.lifeCircles) {
            lifeCircle.attachView(iMvpView);
        }
    }
}
