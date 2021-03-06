package com.sunsta.livery.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunsta.bear.entity.LocalMedia;
import com.sunsta.bear.entity.LocalMediaFolder;
import com.sunsta.bear.faster.ViewUtils;
import com.sunsta.bear.faster.ScreenUtils;
import com.sunsta.livery.R;
import com.sunsta.livery.adapter.PictureAlbumDirectoryAdapter;
import com.sunsta.livery.config.PictureSelectionConfig;
import com.sunsta.livery.listener.OnAlbumItemClickListener;
import com.sunsta.livery.tools.AttrsUtils;

import java.util.List;

/**
 * @author：luck
 * @date：2017-5-25 17:02
 * @describe：文件目录PopupWindow
 */

public class FolderPopWindow extends PopupWindow {
    private Context context;
    private View window;
    private View rootView;
    private RecyclerView recyclerView;
    private PictureAlbumDirectoryAdapter adapter;
    private boolean isDismiss = false;
    private ImageView ivArrowView;
    private Drawable drawableUp, drawableDown;
    private int chooseMode;
    private PictureSelectionConfig config;
    private int maxHeight;
    private View rootViewBg;

    public FolderPopWindow(Context context, PictureSelectionConfig config) {
        this.context = context;
        this.config = config;
        this.chooseMode = config.chooseMode;
        this.window = LayoutInflater.from(context).inflate(R.layout.an_window_folder, null);
        this.setContentView(window);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.PictureThemeWindowStyle);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        if (config.style != null) {
            if (config.style.pictureTitleUpResId != 0) {
                this.drawableUp = ContextCompat.getDrawable(context, config.style.pictureTitleUpResId);
            }
            if (config.style.pictureTitleDownResId != 0) {
                this.drawableDown = ContextCompat.getDrawable(context, config.style.pictureTitleDownResId);
            }
        } else {
            if (config.isWeChatStyle) {
                this.drawableUp = ContextCompat.getDrawable(context, R.mipmap.ic_color_wechat_up);
                this.drawableDown = ContextCompat.getDrawable(context, R.mipmap.ic_color_wechat_down);
            } else {
                if (config.upResId != 0) {
                    this.drawableUp = ContextCompat.getDrawable(context, config.upResId);
                } else {
                    // 兼容老的Theme方式
                    this.drawableUp = AttrsUtils.getTypeValueDrawable(context, R.attr.picture_arrow_up_icon);
                }
                if (config.downResId != 0) {
                    this.drawableDown = ContextCompat.getDrawable(context, config.downResId);
                } else {
                    // 兼容老的Theme方式 picture.arrow_down.icon
                    this.drawableDown = AttrsUtils.getTypeValueDrawable(context, R.attr.picture_arrow_down_icon);
                }
            }
        }
        this.maxHeight = (int) (ScreenUtils.getScreenHeight(context) * 0.6);
        initView();
    }

    public void initView() {
        rootViewBg = window.findViewById(R.id.rootViewBg);
        adapter = new PictureAlbumDirectoryAdapter(config);
        recyclerView = window.findViewById(R.id.folder_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        rootView = window.findViewById(R.id.rootView);
        rootViewBg.setOnClickListener(v -> dismiss());
    }

    public void bindFolder(List<LocalMediaFolder> folders) {
        adapter.setChooseMode(chooseMode);
        adapter.bindFolderData(folders);
        ViewGroup.LayoutParams lp = recyclerView.getLayoutParams();
        lp.height = folders != null && folders.size() > 8 ? maxHeight
                : ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    public void setArrowImageView(ImageView ivArrowView) {
        this.ivArrowView = ivArrowView;
    }

    @Override
    public void showAsDropDown(View anchor) {
        try {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                int[] location = new int[2];
                anchor.getLocationInWindow(location);
                showAtLocation(anchor, Gravity.NO_GRAVITY, 0, location[1] + anchor.getHeight());
            } else {
                super.showAsDropDown(anchor);
            }
            isDismiss = false;
            ivArrowView.setImageDrawable(drawableUp);
            ViewUtils.getInstance().rotateArrow(ivArrowView, true);
            rootViewBg.animate()
                    .alpha(1)
                    .setDuration(250)
                    .setStartDelay(250).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnAlbumItemClickListener(OnAlbumItemClickListener listener) {
        adapter.setOnAlbumItemClickListener(listener);
    }

    @Override
    public void dismiss() {
        if (isDismiss) {
            return;
        }
        rootViewBg.animate()
                .alpha(0)
                .setDuration(150)
                .start();
        ivArrowView.setImageDrawable(drawableDown);
        ViewUtils.getInstance().rotateArrow(ivArrowView, false);
        isDismiss = true;
        FolderPopWindow.super.dismiss();
        isDismiss = false;
    }

    /**
     * 设置选中状态
     */
    public void updateFolderCheckStatus(List<LocalMedia> result) {
        try {
            List<LocalMediaFolder> folders = adapter.getFolderData();
            int size = folders.size();
            for (int i = 0; i < size; i++) {
                // 先重置选中状态为未选中
                LocalMediaFolder mediaFolder = folders.get(i);
                mediaFolder.setCheckedNum(0);
                // 在重新遍历一次更新选中状态
                List<LocalMedia> images = mediaFolder.getImages();
                int iSize = images.size();
                int rSize = result.size();
                for (int j = 0; j < iSize; j++) {
                    LocalMedia oldMedia = images.get(j);
                    String path = oldMedia.getPath();
                    for (int k = 0; k < rSize; k++) {
                        LocalMedia newMedia = result.get(k);
                        if (path.equals(newMedia.getPath()) || oldMedia.getId() == newMedia.getId()) {
                            mediaFolder.setCheckedNum(1);
                            break;
                        }
                    }
                }
            }
            adapter.bindFolderData(folders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
