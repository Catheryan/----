package com.yzh.android.tools;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ToolLru {
	
	// 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。 
	// LruCache通过构造函数传入缓存值，以KB为单位。 
	int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	/**
     * 缓存json数据,Integer
     */
    private LruCache<Integer, String> mJsonCache;
    
    /**
     * 缓存json数据,Integer
     */
    private LruCache<String, String> mJsonStrCache;
    /**
     * 缓存图片信息
     */
    private LruCache<Integer, Bitmap> mBitmapCache;
 
    public ToolLru() {
    	mJsonStrCache =new LruCache<String, String>(maxMemory);
        mJsonCache = new LruCache<Integer, String>(maxMemory);
        mBitmapCache = new LruCache<Integer, Bitmap>(maxMemory);
    }
 
    /**
     * 添加进入缓存列表
     * 
     * @param key
     * @param value
     */
    public void addJsonLruCache(Integer key, String value) {
        mJsonCache.put(key, value);
    }
    
    public void addJsonLruCache(String key, String value) {
    	mJsonStrCache.put(key, value);
    }
    public void addBitmapLruCache(Integer key, Bitmap value) {
        mBitmapCache.put(key, value);
    }
 
    /**
     * 从缓存列表中拿出来
     * 
     * @param key
     * @return
     */
    public String getJsonLruCache(Integer key) {
        return mJsonCache.get(key);
    }
 
    public Bitmap getBitmapLruCache(Integer key) {
        return mBitmapCache.get(key);
    }
    
    public String getJsonStrLruCache(String key) {
        return mJsonStrCache.get(key);
    }
}
