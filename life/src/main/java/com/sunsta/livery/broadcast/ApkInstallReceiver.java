package com.sunsta.livery.broadcast;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.sunsta.bear.AnConstants;
import com.sunsta.bear.faster.EasyPermission;
import com.sunsta.bear.faster.LaLog;
import com.sunsta.bear.faster.SPUtils;
import com.sunsta.livery.faster.FasterIntents;


public class ApkInstallReceiver extends BroadcastReceiver {
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        installApk(intent);
    }

    private void installApk(Intent intent) {
        String resultApkFilePath = intent.getStringExtra(AnConstants.EXTRA.APK_INTALLPATH);
        if (TextUtils.isEmpty(resultApkFilePath)) {
            resultApkFilePath = SPUtils.getInstance().getString(AnConstants.EXTRA.APK_INTALLPATH);
        }
        if (!TextUtils.isEmpty(resultApkFilePath)) {
            LaLog.d("goto installApp ,the path =" + resultApkFilePath);
            if (EasyPermission.hasPermissions(mContext, Manifest.permission.REQUEST_INSTALL_PACKAGES)) {
                FasterIntents.installApp(resultApkFilePath);
            } else {
                LaLog.e("Permission Error ：\nPlease configure the android.permission.REQUEST_INSTALL_PACKAGES permission in your AndroidManifest.xml");
            }
        }
    }
}