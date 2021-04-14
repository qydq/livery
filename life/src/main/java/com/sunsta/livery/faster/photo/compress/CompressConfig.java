package com.sunsta.livery.faster.photo.compress;


import com.sunsta.bear.model.entity.LubanOptions;

import java.io.Serializable;

/**
 * <h2>请关注个人知乎Bgwan， 在【an系列】专栏会有本【livery框架】的使用案例（20190922-正在持续更新中...</h2>
 * 中文描述：压缩配置类 * <br/>
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期（可选）：2016/6/7 0007 18:01
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 * @author sunst // sunst0069
 * @version 2.0 |   2019/10/07           |   an系列alidd框架,对aili应用项目兼容心重新命名，以在知乎更新
 */
public class CompressConfig implements Serializable {

    /**
     * 长或宽不超过的最大像素,单位px
     */
    private int maxPixel = 1200;
    /**
     * 压缩到的最大大小，单位B
     */
    private int maxSize = 100 * 1024;

    /**
     * 是否启用像素压缩
     */
    private boolean enablePixelCompress = true;
    /**
     * 是否启用质量压缩
     */
    private boolean enableQualityCompress = true;

    /**
     * 是否保留原文件
     */
    private boolean enableReserveRaw = true;

    /**
     * Luban压缩配置
     */
    private LubanOptions lubanOptions;

    public static CompressConfig ofDefaultConfig() {
        return new CompressConfig();
    }

    public static CompressConfig ofLuban(LubanOptions options) {
        return new CompressConfig(options);
    }

    private CompressConfig() {
    }

    private CompressConfig(LubanOptions options) {
        this.lubanOptions = options;
    }

    public LubanOptions getLubanOptions() {
        return lubanOptions;
    }

    public int getMaxPixel() {
        return maxPixel;
    }

    public CompressConfig setMaxPixel(int maxPixel) {
        this.maxPixel = maxPixel;
        return this;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isEnablePixelCompress() {
        return enablePixelCompress;
    }

    public void enablePixelCompress(boolean enablePixelCompress) {
        this.enablePixelCompress = enablePixelCompress;
    }

    public boolean isEnableQualityCompress() {
        return enableQualityCompress;
    }

    public void enableQualityCompress(boolean enableQualityCompress) {
        this.enableQualityCompress = enableQualityCompress;
    }

    public boolean isEnableReserveRaw() {
        return enableReserveRaw;
    }

    public void enableReserveRaw(boolean enableReserveRaw) {
        this.enableReserveRaw = enableReserveRaw;
    }

    public static class Builder {
        private CompressConfig config;

        public Builder() {
            config = new CompressConfig();
        }

        public Builder setMaxSize(int maxSize) {
            config.setMaxSize(maxSize);
            return this;
        }

        public Builder setMaxPixel(int maxPixel) {
            config.setMaxPixel(maxPixel);
            return this;
        }

        public Builder enablePixelCompress(boolean enablePixelCompress) {
            config.enablePixelCompress(enablePixelCompress);
            return this;
        }

        public Builder enableQualityCompress(boolean enableQualityCompress) {
            config.enableQualityCompress(enableQualityCompress);
            return this;
        }

        public Builder enableReserveRaw(boolean enableReserveRaw) {
            config.enableReserveRaw(enableReserveRaw);
            return this;
        }

        public CompressConfig create() {
            return config;
        }
    }
}

