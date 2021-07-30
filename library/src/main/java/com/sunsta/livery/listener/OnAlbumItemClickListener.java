package com.sunsta.livery.listener;

import com.sunsta.bear.entity.LocalMedia;

import java.util.List;

/**
 * @author：luck
 * @date：2020-03-26 10:57
 * @describe：OnAlbumItemClickListener
 */
public interface OnAlbumItemClickListener {
    /**
     * 相册目录item点击事件
     *
     * @param isCameraFolder
     * @param folderName
     * @param images
     */
    void onItemClick(boolean isCameraFolder, String folderName, List<LocalMedia> images);
}
