package com.sunsta.livery.faster.photo.compress;

import android.content.Context;
import android.text.TextUtils;

import com.sunsta.bear.model.entity.TImage;

import java.io.File;
import java.util.ArrayList;

/**
 * <h2>请关注个人知乎Bgwan， 在【an系列】专栏会有本【livery框架】的使用案例（20190922-正在持续更新中...</h2>
 * 中文描述：压缩照片 * <br/>
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期：2016/6/7 0007 18:01
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 * @author sunst // sunst0069
 * @version 2.0 |   2019/10/07           |   an系列alidd框架,对aili应用项目兼容心重新命名，以在知乎更新
 */
public class CompressImageImpl implements CompressImage {
    private CompressImageUtil compressImageUtil;
    private ArrayList<TImage> images;
    private CompressImage.CompressListener listener;

    public static CompressImage of(Context context, CompressConfig config, ArrayList<TImage> images, CompressImage.CompressListener listener) {
        if (config.getLubanOptions() != null) {
            return new CompressWithLuBan(context, config, images, listener);
        } else {
            return new CompressImageImpl(context, config, images, listener);
        }
    }

    private CompressImageImpl(Context context, CompressConfig config, ArrayList<TImage> images, CompressImage.CompressListener listener) {
        compressImageUtil = new CompressImageUtil(context, config);
        this.images = images;
        this.listener = listener;
    }

    @Override
    public void compress() {
        if (images == null || images.isEmpty())
            listener.onCompressFailed(images, " images is null");
        for (TImage image : images) {
            if (image == null) {
                listener.onCompressFailed(images, " There are pictures of compress  is null.");
                return;
            }
        }
        compress(images.get(0));
    }

    private void compress(final TImage image) {
        if (TextUtils.isEmpty(image.getOriginalPath())) {
            continueCompress(image, false);
            return;
        }

        File file = new File(image.getOriginalPath());
        if (file == null || !file.exists() || !file.isFile()) {
            continueCompress(image, false);
            return;
        }

        compressImageUtil.compress(image.getOriginalPath(), new CompressImageUtil.CompressListener() {
            @Override
            public void onCompressSuccess(String imgPath) {
                image.setCompressPath(imgPath);
                continueCompress(image, true);
            }

            @Override
            public void onCompressFailed(String imgPath, String msg) {
                continueCompress(image, false, msg);
            }
        });
    }

    private void continueCompress(TImage image, boolean preSuccess, String... message) {
        image.setCompressed(preSuccess);
        int index = images.indexOf(image);
        boolean isLast = index == images.size() - 1;
        if (isLast) {
            handleCompressCallBack(message);
        } else {
            compress(images.get(index + 1));
        }
    }

    private void handleCompressCallBack(String... message) {
        if (message.length > 0) {
            listener.onCompressFailed(images, message[0]);
            return;
        }

        for (TImage image : images) {
            if (!image.isCompressed()) {
                listener.onCompressFailed(images, image.getCompressPath() + " is compress failures");
                return;
            }
        }
        listener.onCompressSuccess(images);
    }
}
