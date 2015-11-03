package com.jingling.practice.waterfall;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    /**
     * 滚动视图
     */
    private CustomScrollView waterfall_scroll;
    /**
     * 布局内容框架
     */
    private LinearLayout waterfall_container;
    /**
     * 布局内容item
     */
   private ArrayList<LinearLayout> waterfall_items;
    /**
     * 图片文件名
     */
    private List<String> image_filenames;
    /**
     * 图片路径前缀
     */
    private final String image_path = "images";
    /**
     * 提供关于屏幕尺寸和分辨率的信息
     */
    private Display display;
    /**
     *访问assets目录下的文件
     */
    private AssetManager assetManager;

    /**
     * 每个item的宽度
     */
    private int itemWidth;
    /**
     *  显示列数
     */

    private int column_count = 3;
    /**
     * 每次加载15张图片
     */
    private int page_count = 15;
    /**
     * 当前页数
     */
    private int current_page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取手机默认的屏幕尺寸和分辨率的信息
        display = this.getWindowManager().getDefaultDisplay();
        //根据屏幕计算每列的大小
        itemWidth = display.getWidth() / column_count;
        //获取asset目录下的文件
        assetManager = this.getAssets();
        initLayout();
    }
    public void initLayout(){

    }



}
