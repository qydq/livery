package com.sunsta.livery.faster.photo.compress;

import android.content.Context;

import com.sunsta.bear.model.entity.LubanOptions;
import com.sunsta.bear.model.entity.TImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>请关注个人知乎Bgwan， 在【an系列】专栏会有本【livery框架】的使用案例（20190922-正在持续更新中...</h2>
 * 中文描述：压缩照片,采用luban * <br/>
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期：2016/11/5
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 * @author sunst // sunst0069
 * @version 2.0 |   2019/10/07           |   an系列alidd框架,对aili应用项目兼容心重新命名，以在知乎更新
 */
public class CompressWithLuBan implements CompressImage {
    private ArrayList<TImage> images;
    private CompressListener listener;
    private Context context;
    private LubanOptions options;
    private ArrayList<File> files = new ArrayList<>();

    public CompressWithLuBan(Context context, CompressConfig config, ArrayList<TImage> images,
                             CompressListener listener) {
        options = config.getLubanOptions();
        this.images = images;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void compress() {
        if (images == null || images.isEmpty()) {
            listener.onCompressFailed(images, " images is null");
            return;
        }
        for (TImage image : images) {
            if (image == null) {
                listener.onCompressFailed(images, " There are pictures of compress  is null.");
                return;
            }
            files.add(new File(image.getOriginalPath()));
        }
        if (images.size() == 1) {
            compressOne();
        } else {
            compressMulti();
        }
    }

    private void compressOne() {
//    Luban.compress(context, files.get(0))
//        .putGear(Luban.CUSTOM_GEAR)
//        .setMaxHeight(options.getMaxHeight())
//        .setMaxWidth(options.getMaxWidth())
//        .setMaxSize(options.getMaxSize() / 1000)
//        .launch(new OnCompressListener() {
//          @Override public void onStart() {
//
//          }
//
//          @Override public void onSuccess(File file) {
//            TImage image = images.get(0);
//            image.setCompressPath(file.getPath());
//            image.setCompressed(true);
//            listener.onCompressSuccess(images);
//          }
//
//          @Override public void onError(Throwable e) {
//            listener.onCompressFailed(images, e.getMessage() + " is compress failures");
//          }
//        });
    }

    private void compressMulti() {
        //复合压缩移除该方法，todo
//    Luban.compress(context, files)
//        .putGear(Luban.CUSTOM_GEAR)
//        .setMaxSize(
//            options.getMaxSize() / 1000)                // limit the final image size（unit：Kb）
//        .setMaxHeight(options.getMaxHeight())             // limit image height
//        .setMaxWidth(options.getMaxWidth())
//        .launch(new OnMultiCompressListener() {
//          @Override public void onStart() {
//
//          }
//
//          @Override public void onSuccess(List<File> fileList) {
//            handleCompressCallBack(fileList);
//          }
//
//          @Override
//          public void onError(Throwable e) {
//            listener.onCompressFailed(images, e.getMessage() + " is compress failures");
//          }
//        });
    }

    private void handleCompressCallBack(List<File> files) {
        for (int i = 0, j = images.size(); i < j; i++) {
            TImage image = images.get(i);
            image.setCompressed(true);
            image.setCompressPath(files.get(i).getPath());
        }
        listener.onCompressSuccess(images);
    }
}
