package com.sunsta.livery.model.entity;

import android.app.Activity;
import android.net.Uri;

import com.sunsta.livery.faster.photo.TImageFiles;
import com.sunsta.livery.faster.TUtils;
import com.sunsta.bear.model.entity.TException;
import com.sunsta.bear.model.entity.TImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * brief:tackPhoto中对图片裁剪整合处理
 * 2.0 fix 一些警告处理setCropWithUri 完成既定任务AR20180117SGG
 * <br> author：晴雨【qy】
 * <br> email：staryumou@163.com
 * <br> create date： 2016/8/11 17:01
 * <br> update date：20180201
 * <br> website：https://qydq.github.io
 * <br> Copyrigth(c),2017,孙顺涛,inasst.com
 * <br> version 2.0
 */

public class MultipleCrop {
    private ArrayList<Uri> uris;
    private ArrayList<Uri> outUris;
    private ArrayList<TImage> tImages;
    private TImage.FromType fromType;
    public boolean hasFailed;//是否有裁切失败的标识

    public static MultipleCrop of(ArrayList<Uri> uris, Activity activity, TImage.FromType fromType) throws TException {
        return new MultipleCrop(uris, activity, fromType);
    }

    public static MultipleCrop of(ArrayList<Uri> uris, ArrayList<Uri> outUris, TImage.FromType fromType) {
        return new MultipleCrop(uris, outUris, fromType);
    }

    private MultipleCrop(ArrayList<Uri> uris, Activity activity, TImage.FromType fromType) throws TException {
        this.uris = uris;
        ArrayList<Uri> outUris = new ArrayList<>();
        for (Uri uri : uris) {
            outUris.add(Uri.fromFile(TImageFiles.getTempFile(activity, uri)));//生成临时裁切输出路径
        }
        this.outUris = outUris;
        this.tImages = TUtils.getTImagesWithUris(outUris, fromType);
        this.fromType = fromType;
    }

    private MultipleCrop(ArrayList<Uri> uris, ArrayList<Uri> outUris, TImage.FromType fromType) {
        this.uris = uris;
        this.outUris = outUris;
        this.tImages = TUtils.getTImagesWithUris(outUris, fromType);
        this.fromType = fromType;
    }

    public ArrayList<Uri> getUris() {
        return uris;
    }

    public void setUris(ArrayList<Uri> uris) {
        this.uris = uris;
    }

    public ArrayList<Uri> getOutUris() {
        return outUris;
    }

    public void setOutUris(ArrayList<Uri> outUris) {
        this.outUris = outUris;
    }

    public ArrayList<TImage> gettImages() {
        return tImages;
    }

    public void settImages(ArrayList<TImage> tImages) {
        this.tImages = tImages;
    }

    /**
     * 为被裁切的图片设置已被裁切的标识
     *
     * @param uri 被裁切的图片
     * @return 该图片是否是最后一张
     */
    public Map setCropWithUri(Uri uri, boolean cropped) {
        if (!cropped) hasFailed = true;
        int index = outUris.indexOf(uri);
        tImages.get(index).setCropped(cropped);
        /*fix warning['xxx==false' can be simplified to '!xxx']
        * [Unchecked call to 'put(K, V)' as a member of raw type 'java.util.HashMap']*/
        HashMap<String, Object> result = new HashMap<>();
        result.put("index", index);
        if (index == outUris.size() - 1) {
            result.put("isLast", true);
        } else {
            result.put("isLast", false);
        }
        return result;
    }
}
