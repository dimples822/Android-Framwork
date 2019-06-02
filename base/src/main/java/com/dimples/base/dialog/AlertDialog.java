package com.dimples.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.dimples.base.R;


/**
 * 自定义万能Dialog
 *
 * @author zhongyj
 * @date 2019/6/2
 */
public class AlertDialog extends Dialog {

    private AlertController mAlert;


    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    /**
     * 设置文本
     *
     * @param viewId int
     * @param text   CharSequence
     */
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    /**
     * 设置点击事件
     *
     * @param viewId   int
     * @param listener View.OnClickListener
     */
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnclickListener(viewId, listener);
    }


    public static class Builder {

        private final AlertController.AlertParams P;

        /**
         * 为默认的对话框创建构造器
         *
         * @param context the parent context
         */
        public Builder(Context context) {
            this(context, R.style.dialog);
        }


        /**
         * 为使用显示的主题资源的对话框创建构造器
         *
         * @param context    the parent context
         * @param themeResId the resource ID
         */
        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }


        /**
         * 设置自定义的对话框视图文件
         */
        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        /**
         * 设置布局内容的layoutId
         *
         * @param layoutId int
         * @return Builder
         */
        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }

        /**
         * 设置文本
         *
         * @param viewId int
         * @param text   CharSequence
         * @return Builder
         */
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }

        /**
         * @param view     int
         * @param listener View.OnClickListener
         * @return 设置点击事件
         */
        public Builder setOnClickListener(int view, View.OnClickListener listener) {
            P.mClickArray.put(view, listener);
            return this;
        }

        /**
         * 配置一些万能的参数
         *
         * @return Builder
         */
        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 从底部弹出
         *
         * @param isAnimation 是否有动画
         * @return Builder
         */
        public Builder formBottom(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }


        /**
         * 设置Dialog的宽高
         *
         * @param width  int
         * @param height int
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 添加默认动画
         *
         * @return Builder
         */
        public Builder addDefaultAnimation() {
            P.mAnimations = R.style.dialog_scale_anim;
            return this;
        }

        /**
         * 设置动画
         *
         * @param styleAnimation int
         * @return Builder
         */
        public Builder setAnimations(int styleAnimation) {
            P.mAnimations = styleAnimation;
            return this;
        }

        /**
         * 是否点击空白取消dialog，默认为true
         *
         * @return Builder
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * 设置取消的回调监听
         *
         * @return Builder
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * 设置dialog Dismiss监听
         *
         * @return Builder
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        /**
         * 设置dialog Key监听
         *
         * @return Builder
         */
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }


        /**
         * 根据构建的Builder创建对应的dialog
         *
         * @return AlertDialog
         */
        AlertDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * 显示对应的dialog
         *
         * @return AlertDialog
         */
        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
