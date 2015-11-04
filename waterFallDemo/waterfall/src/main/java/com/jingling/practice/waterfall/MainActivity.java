package com.jingling.practice.waterfall;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
     * 访问assets目录下的文件
     */
    private AssetManager assetManager;

    /**
     * 每个item的宽度
     */
    private int itemWidth;
    /**
     * 显示列数
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

    public void initLayout() {
        waterfall_scroll = (CustomScrollView) findViewById(R.id.waterfall_scroll);
        waterfall_scroll.getView();
        //设置滚动监听
        waterfall_scroll.setOnScrollListener(new CustomScrollView.OnScrollListener() {
            @Override
            public void onBottom() {
                // 滚动到最低端
                // AddItemToContainer(++current_page, page_count);
            }

            @Override
            public void onTop() {
                // 滚动到最顶端
                Log.d("Scroll", "Scroll to top");
            }

            @Override
            public void onScroll() {
                // 滚动中
                Log.d("Scroll", "Scroll");
            }
        });

        waterfall_container = (LinearLayout) findViewById(R.id.waterfall_container);
        waterfall_items = new ArrayList<LinearLayout>();
        for (int i = 0; i < column_count; i++) {
            LinearLayout itemLayout = new LinearLayout(this);
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemLayout.setPadding(2, 2, 2, 2);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setLayoutParams(itemParams);
            waterfall_items.add(itemLayout);
            waterfall_container.addView(itemLayout);
        }

        // 加载所有图片路径
        try {
            image_filenames = Arrays.asList(assetManager.list(image_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 第一次加载
        AddItemToContainer(current_page, page_count);
    }

    private void AddItemToContainer(int pageindex, int pagecount) {
        int j = 0;
        int images_count = image_filenames.size();
        for (int i = pageindex * pagecount; i < pagecount * (pageindex + 1)
                && i < images_count; i++) {

            j = j >= column_count ? j = 0 : j;
            AddImage(image_filenames.get(i), j++);
        }


    }

    private void AddImage(String filename, int columnIndex) {
        ImageView item = (ImageView) LayoutInflater.from(this).inflate(
                R.layout.waterfall_item, null);
        waterfall_items.get(columnIndex).addView(item);

        TaskParam param = new TaskParam();
        param.setAssetManager(assetManager);
        param.setFilename(image_path + "/" + filename);
        param.setItemWidth(itemWidth);
        ImageLoaderTask task = new ImageLoaderTask(item);
        task.execute(param);
    }
}
