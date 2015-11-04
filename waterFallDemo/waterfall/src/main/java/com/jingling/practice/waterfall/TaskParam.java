package com.jingling.practice.waterfall;

import android.content.res.AssetManager;

/**
 * Created by Spring on 2015/11/4.
 *
 */
public class TaskParam {
    private String filename;
    private AssetManager assetManager;
    private int ItemWidth;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setItemWidth(int itemWidth) {
        ItemWidth = itemWidth;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public String getFilename() {
        return filename;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public int getItemWidth() {
        return ItemWidth;
    }
}
