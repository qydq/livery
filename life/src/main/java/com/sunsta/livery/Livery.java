package com.sunsta.livery;

import android.text.TextUtils;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.sunsta.bear.AnConstants;
import com.sunsta.bear.faster.SPUtils;
import com.sunsta.bear.presenter.net.InternetClient;

public class Livery {
    private volatile static Livery liffvery = null;

    public static Livery instance() {
        if (liffvery == null) {
            synchronized (InternetClient.class) {
                if (liffvery == null) {
                    liffvery = new Livery();
                }
            }
        }
        return liffvery;
    }

    public Livery initialze(@Nullable String baseUrl) {
        InternetClient.getInstance().initialze(baseUrl);
        return liffvery;
    }

    public Livery enableLog(@Nullable String logFilter, boolean enableLog) {
        InternetClient.getInstance().enableLog(enableLog, logFilter);
        return liffvery;
    }

    public Livery configChannel(@Nullable String channelID, @NonNull String channelNAME, @DrawableRes int icon) {
        configChannel(channelID, channelNAME, NotificationManagerCompat.IMPORTANCE_DEFAULT, icon);
        return liffvery;
    }

    public Livery configChannel(@Nullable String channelID, @NonNull String channelNAME, int level, @DrawableRes int icon) {
//        livery library all var can use this configChannel，just once config
        if (TextUtils.isEmpty(SPUtils.getInstance().getString(AnConstants.KEY.CHANNEL_ID))) {
            SPUtils.getInstance().putString(AnConstants.KEY.CHANNEL_ID, channelID);
            SPUtils.getInstance().putString(AnConstants.KEY.CHANNEL_NAME, channelNAME);
            SPUtils.getInstance().putInt(AnConstants.KEY.CHANNEL_LEVEL, level);
            SPUtils.getInstance().putInt(AnConstants.KEY.APP_ICON, icon);
        }
        return liffvery;
    }
}