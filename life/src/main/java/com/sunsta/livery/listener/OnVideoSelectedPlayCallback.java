package com.sunsta.livery.listener;


import com.sunsta.bear.entity.LocalMedia;

/**
 * @author：luck
 * @date：2020-01-15 14:38
 * @describe：自定义视频播放回调
 */
public interface OnVideoSelectedPlayCallback<T extends LocalMedia> {
    /**
     * 播放视频
     *
     * @param data
     */
    void startPlayVideo(T data);
}
