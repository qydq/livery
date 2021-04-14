package com.sunsta.livery.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sunsta.bear.faster.LAScreen;
import com.sunsta.bear.faster.LaBitmap;
import com.sunsta.bear.layout.INASmoothImageView;
import com.sunsta.bear.layout.INAZoomImageView;
import com.sunsta.bear.view.AliActivity;
import com.sunsta.livery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>请关注个人知乎Bgwan， 在【an系列】专栏会有本【livery框架】的使用案例（20190922-正在持续更新中...</h2>
 * 中文描述：三级页面图片放大,可以根据传入的drawable的id，或者图片的路径地址，或者网络图片的地址来显示图片，你可以传入需要显示第几张的图片,
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期：2017/4/19
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 * @author sunst // sunst0069
 * @version 1.0 |   2016/4/9           |   应用场景是一个照片墙
 */
@SuppressLint("AllowBackup")
public class AliPhotoActivity extends AliActivity {
    List<String> mDatas;
    private INASmoothImageView imageView;
    private INAZoomImageView luueZoomIv;
    private String url;
    private boolean isZoom = false;//判断是否对图片缩放。
    private Bitmap bitmap;
    private int mDefaultWidth = 400;
    private int mDefaultHeight = 350;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.an_activity_picdetails);
        final Intent intent = getIntent();
        imageView = new INASmoothImageView(this);
        luueZoomIv = new INAZoomImageView(this);
        isZoom = intent.getBooleanExtra("isZoom", false);
        mDatas = new ArrayList<>();

        mDefaultWidth = LAScreen.getInstance(mContext).getScreenWidth();
        mDefaultHeight = LAScreen.getInstance(mContext).getScreenHeight();

        mDatas = intent.getStringArrayListExtra("images");
        final int mPosition = intent.getIntExtra("position", 0);
        url = intent.getStringExtra("url");
        int drawableId = intent.getIntExtra("drawableId", 0);
        String absPath = intent.getStringExtra("absPath");
        int width = intent.getIntExtra("width", mDefaultWidth);
        int height = intent.getIntExtra("height", mDefaultHeight / 2);

        if (mDatas == null) {

        } else {
            for (int i = 0; i < mDatas.size(); i++) {
                Log.d(TAG, "--yy@@--AliPhotoActivity--mDatas.get--" + mDatas.get(i));
            }
            url = mDatas.get(mPosition);
        }

        if (!TextUtils.isEmpty(url)) {
            if (isZoom) {
                Glide.with(this).load(url).into(luueZoomIv);

                Glide.with(this).load(url).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        luueZoomIv.setBackground(resource);
//                        getWindow().getDecorView().setBackground(resource);
                    }
                });
            } else {
                Glide.with(this).load(url).into(imageView);
            }
        }
        if (drawableId != 0) {
            bitmap = LaBitmap.INSTANCE.drawableToBitmap(ContextCompat.getDrawable(this, drawableId));
        }
        if (!TextUtils.isEmpty(absPath)) {
            bitmap = LaBitmap.INSTANCE.decodeBitmapFromPath(absPath, width, height);
            //maybe 裁剪 ，防止oom
        }

        int mLocationX = intent.getIntExtra("locationX", 0);
        int mLocationY = intent.getIntExtra("locationY", 0);
        int mWidth = intent.getIntExtra("width", mDefaultWidth / 4);
        int mHeight = intent.getIntExtra("height", mDefaultWidth / 5);
        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        imageView.transformIn();
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));

        if (bitmap != null) {
            if (isZoom) {
                luueZoomIv.setImageBitmap(bitmap);
            } else {
                imageView.setImageBitmap(bitmap);
            }
        }

        if (isZoom) {
            followSystemTheme();
            setContentView(luueZoomIv);
        } else {
            setContentView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        }
    }


    /**
     * 退出时动画
     */
    @Override
    public void onBackPressed() {
        if (isZoom) {
            finish();
        } else {
            imageView.setOnTransformListener(new INASmoothImageView.TransformListener() {
                @Override
                public void onTransformComplete(int mode) {
                    if (mode == 2) {
                        finish();
                    }
                }
            });
            imageView.transformOut();
        }
    }
}
