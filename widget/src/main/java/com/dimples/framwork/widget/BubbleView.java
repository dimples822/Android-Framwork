package com.dimples.framwork.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * 仿QQ消息气泡效果，可拖动
 *
 * @author zhongyj
 * @date 2019/5/9
 */
public class BubbleView extends FrameLayout {

    private static final int MAX_DISTANCE = 100;

    //定义一个文本框
    private TextView mTextView;

    /**
     * 文本框的初始化坐标
     */
    private PointF mInitPosition;

    /**
     * 手指是否触摸到控件
     */
    private boolean clicked = false;

    /**
     * 手指移动的坐标
     */
    private PointF mMovePosition;

    /**
     * 定义圆的半径
     */
    private float mRadio = 20;

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 路径
     */
    private Path mPath;

    /**
     * 判断控件是否已经超出范围
     */
    private boolean out = false;


    public BubbleView(@NonNull Context context) {
        super(context);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPath = new Path();
        //初始化画笔
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        //初始化初始坐标
        mInitPosition = new PointF(200, 200);
        //初始化滑动坐标
        mMovePosition = new PointF();
        //初始化文本框
        mTextView = new TextView(getContext());
        mTextView.setTextColor(Color.WHITE);
        //设置背景颜色
        mTextView.setBackgroundResource(R.drawable.bubble_text_view_bg);
        mTextView.setPadding(10, 10, 10, 10);
        mTextView.setText(getResources().getString(R.string.bubble_text));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTextView.setLayoutParams(params);
        this.addView(mTextView);
    }

    /**
     * 重绘控件里面的所以内容
     *
     * @param canvas Canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        //保存canvas的状态
        canvas.save();
        if (clicked) {
            mTextView.setX(mMovePosition.x - mTextView.getWidth() / 2.0f);
            mTextView.setY(mMovePosition.y - mTextView.getHeight() / 2.0f);
            drawPath();
            out = false;
            canvas.drawPath(mPath, mPaint);
            //起点的圆
            canvas.drawCircle(mInitPosition.x, mInitPosition.y, mRadio, mPaint);
            //终点的圆
            canvas.drawCircle(mMovePosition.x, mMovePosition.y, mRadio, mPaint);
        } else {
            mTextView.setX(mInitPosition.x - mTextView.getWidth() / 2.0f);
            mTextView.setY(mInitPosition.y - mTextView.getHeight() / 2.0f);
        }
        //恢复canvas之前保存的状态
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    /**
     *
     */
    public void drawPath() {
        //终点与起点x的差值
        float widthX = mMovePosition.x - mInitPosition.x;
        float heightY = mMovePosition.y - mInitPosition.y;
        //获取两个坐标之间的距离
        float sss = (float) Math.sqrt(Math.pow(widthX, 2) + Math.pow(heightY, 2));
        //判断两点之间的距离是否已经超出距离
        if (sss >= MAX_DISTANCE) {
            out = true;
        }
        //得到三角形的正切值
        double atan = Math.atan(heightY / widthX);
        //获取偏移的X值
        float offsetX = (float) (Math.sin(atan) * mRadio);
        float offsetY = (float) (Math.cos(atan) * mRadio);
        //获取第一个圆的A坐标
        float p0X = mInitPosition.x + offsetX;
        float p0Y = mInitPosition.y - offsetY;
        //获取到第个圆的坐标
        float p1X = mMovePosition.x + offsetX;
        float p1Y = mMovePosition.y - offsetY;

        float p2X = mMovePosition.x - offsetX;
        float p2Y = mMovePosition.y + offsetY;

        float p3X = mInitPosition.x - offsetX;
        float p3Y = mInitPosition.y + offsetY;
        //获取两个圆的中间坐标
        float pX = (mInitPosition.x + mMovePosition.x) / 2;
        float pY = (mInitPosition.y + mMovePosition.y) / 2;
        //连接四个点
        mPath.reset();
        //将画笔定在p0点
        mPath.moveTo(p0X, p0Y);
        //从第一个点连接到第二个点
        mPath.quadTo(pX, pY, p1X, p1Y);
        //从第二点到第三个点
        mPath.lineTo(p2X, p2Y);
        //从第三个点到第四个点
        mPath.quadTo(pX, pY, p3X, p3Y);
        //从第四个到起点
        mPath.lineTo(p0X, p0Y);
    }

    /**
     * 监听屏幕动作
     *
     * @param event MotionEvent
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMovePosition.set(mInitPosition.x, mInitPosition.y);
                //判断是否触摸到了文本控件之内
                Rect rect = new Rect();
                int[] location = new int[2];
                //获取到textView在窗体中的x,y坐标
                mTextView.getLocationOnScreen(location);
                //获取到textView的对象
                rect.left = location[0];
                rect.top = location[1];
                rect.right = location[0] + mTextView.getWidth();
                rect.bottom = location[1] + mTextView.getHeight();
                //判断mTextView控件的坐标域包含触摸的范围
                if (rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    clicked = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                clicked = false;

                break;
            case MotionEvent.ACTION_MOVE:
                //event.getRawX():获取控件相对于父控件的坐标    event.getX()：获取控件在整个屏幕中的坐标
                mMovePosition.set(event.getX(), event.getY());
                break;
            default:
                break;
        }
        //更新UI的方法
        postInvalidate();
        performClick();
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}




















