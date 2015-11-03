package com.jingling.practice.waterfall;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Spring on 2015/11/2.
 * 自定义ScrollView
 */
public class CustomScrollView extends ScrollView {
    private View view;
    private Handler handler;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 获得由垂直方向滚动条代表的所有垂直范围，缺省的范围是当前视图的画图高度。
     */
    public int getComputeVerticalScrollRange() {
        //滚动视图的可滚动范围是所有子元素的高度。
        return super.computeVerticalScrollRange();
    }

    /**
     * 获得滚动条的滑块垂直方向的偏移。
     */
    public int getComputeVerticalScrollOffSet() {
        //计算垂直方向滚动条的滑块的偏移。此值用来计算滚动条轨迹的滑块的位置。
        return super.computeVerticalScrollOffset();
    }

    private void init() {
        this.setOnTouchListener(onTouchListener);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        /**
                         * getMeasuredHeight()获得控件的实际大小
                         * getScrollY()获得ScrollView滑动的距离
                         * getHeight是获得控件的显示的大小，如果控件大小超出的屏幕，那他的大小就是屏幕的大小。
                         * 详情见：http://www.cnblogs.com/qinghuaideren/p/3186990.html
                         */

                        if (view.getMeasuredHeight() <= getScaleY() + getHeight()) {
                            if (onScrollListener != null) {
                                onScrollListener.onBottom();
                            }

                        } else if (getScrollY() == 0) {
                            if (onScrollListener != null) {
                                onScrollListener.onTop();
                            }
                        } else {
                            if (onScrollListener != null) {
                                onScrollListener.onScroll();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }


    OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                //屏幕按下
                case MotionEvent.ACTION_DOWN:
                    break;
                //按下抬起
                case MotionEvent.ACTION_UP:
                    if (view != null && onScrollListener != null) {
                        handler.sendMessageDelayed(handler.obtainMessage(1), 200);
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    /**
     * 获得参考的View，主要是为了获得它的MeasuredHeight，然后和滚动条的ScrollY+getHeight作比较。
     */
    public void getView() {
        this.view = getChildAt(0);
        if (view != null) {
            init();
        }
    }

    /**
     * 定义接口
     *
     * @author admin
     */
    public interface OnScrollListener {
        void onBottom();

        void onTop();

        void onScroll();
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}
