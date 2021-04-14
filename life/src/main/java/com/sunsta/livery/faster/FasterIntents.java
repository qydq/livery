package com.sunsta.livery.faster;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.sunsta.bear.AnApplication;
import com.sunsta.bear.AnConstants;
import com.sunsta.bear.config.PictureConfig;
import com.sunsta.bear.config.PictureMimeType;
import com.sunsta.bear.faster.DataService;
import com.sunsta.bear.faster.FileUtils;
import com.sunsta.bear.faster.LaLog;
import com.sunsta.bear.faster.ToastUtils;
import com.sunsta.bear.faster.ValueOf;
import com.sunsta.bear.model.entity.CropOptions;
import com.sunsta.bear.model.entity.TContextWrap;
import com.sunsta.bear.immersion.DoubleUtils;
import com.sunsta.bear.view.activity.AliWebActivity;
import com.sunsta.bear.view.multipleimage.activities.AlbumSelectActivity;
import com.sunsta.bear.view.multipleimage.helper.Constants;
import com.sunsta.livery.PicturePreviewActivity;
import com.sunsta.livery.PictureSelectorPreviewWeChatStyleActivity;
import com.sunsta.livery.PictureVideoPlayActivity;
import com.sunsta.livery.R;

import java.io.File;
import java.util.List;

import static com.sunsta.bear.AnConstants.EXTRA.APP_TITLE;
import static com.sunsta.bear.AnConstants.EXTRA.APP_URL_MORE;

/**
 * <h2>请关注个人知乎Bgwan， 在【an系列】专栏会有本【livery框架】的使用案例（20190922-正在持续更新中...</h2>
 * 中文描述：Activity跳转 * <br/>
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期（可选）：2016/6/7 0007 13:41
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 * @author sunst // sunst0069
 * @version 3.0 |   2019/10/07           |   Activity跳转
 */
public class FasterIntents {
    private static final String TAG = FasterIntents.class.getName();
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    /**
     * 获取裁剪照片的Intent
     * @param targetUri 要裁剪的照片
     * @param outPutUri 裁剪完成的照片
     * @param options   裁剪配置
     * @return
     */
    public static Intent getCropIntentWithOtherApp(Uri targetUri, Uri outPutUri, CropOptions options) {
        boolean isReturnData = TUtils.isReturnData();
        LaLog.w(TAG, "getCaptureIntentWithCrop:isReturnData:" + (isReturnData ? "true" : "false"));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(targetUri, "image/*");
        intent.putExtra("crop", "true");
        if (options.getAspectX() * options.getAspectY() > 0) {
            intent.putExtra("aspectX", options.getAspectX());
            intent.putExtra("aspectY", options.getAspectY());
        }
        if (options.getOutputX() * options.getOutputY() > 0) {
            intent.putExtra("outputX", options.getOutputX());
            intent.putExtra("outputY", options.getOutputY());
        }
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        intent.putExtra("return-data", isReturnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        return intent;
    }

    /**
     * 启动视频播放页面
     * @param context
     * @param bundle
     */
    public static void startPictureVideoPlayActivity(Context context, Bundle bundle, int requestCode) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClass(context, PictureVideoPlayActivity.class);
            intent.putExtras(bundle);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, requestCode);
            }
        }
    }

    /**
     * 获取图片多选的Intent
     * @param limit 最多选择图片张数的限制
     */
    public static Intent getPickMultipleIntent(TContextWrap contextWrap, int limit) {
        Intent intent = new Intent(contextWrap.getActivity(), AlbumSelectActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_LIMIT, limit > 0 ? limit : 1);
        return intent;
    }

    /**
     * 获取拍照的Intent
     * @return
     */
    public static Intent getCaptureIntent(Uri outPutUri) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);//将拍取的照片保存到指定URI
        return intent;
    }

    /**
     * 获取选择照片的Intent
     * @return
     */
    public static Intent getPickIntentWithGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);//Pick ali item from the data
        intent.setType("image/*");//从所有图片中进行选择
        return intent;
    }

    /**
     * 获取从文件中选择照片的Intent
     */
    public static Intent getPickIntentWithDocuments() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return intent;
    }


    /**
     * Return the context of Application object.
     * @return the context of Application object
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        Application app = AnApplication.getApplication();
        init(app);
        return app;
    }


    public static boolean isAppForeground() {
        ActivityManager am = (ActivityManager) getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (aInfo.processName.equals(getApp().getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     * @param app application
     */
    public static void init(final Application app) {
        if (sApplication == null) {
            if (app == null) {
                sApplication = AnApplication.getApplication();
            } else {
                sApplication = app;
            }
        } else {
            if (app != null && app.getClass() != sApplication.getClass()) {
                sApplication = app;
            }
        }
    }

    /**
     * Install the app.
     * <p>Target APIs greater than 25 must hold
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     * @param filePath The path of file.
     */
    public static void installApp(final String filePath) {
        installApp(getFileByPath(filePath));
    }

    private static File getFileByPath(final String filePath) {
        return FileUtils.INSTANCE.isSpace(filePath) ? null : new File(filePath);
    }


    /**
     * Install the app.
     * <p>Target APIs greater than 25 must hold
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     * @param file The file.
     */
    public static void installApp(final File file) {
        if (!FileUtils.INSTANCE.isFileExists(file)) return;
        getApp().startActivity(getInstallAppIntent(file, true));
    }

    /**
     * Install the app.
     * <p>Target APIs greater than 25 must hold
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     * @param activity    The activity.
     * @param filePath    The path of file.
     * @param requestCode If &gt;= 0, this code will be returned in
     *                    onActivityResult() when the activity exits.
     */
    public static void installApp(final Activity activity,
                                  final String filePath,
                                  final int requestCode) {
        installApp(activity, getFileByPath(filePath), requestCode);
    }

    /**
     * Install the app.
     * <p>Target APIs greater than 25 must hold
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     * @param activity    The activity.
     * @param file        The file.
     * @param requestCode If &gt;= 0, this code will be returned in
     *                    onActivityResult() when the activity exits.
     */
    public static void installApp(final Activity activity,
                                  final File file,
                                  final int requestCode) {
        if (!FileUtils.INSTANCE.isFileExists(file)) return;
        activity.startActivityForResult(getInstallAppIntent(file), requestCode);
    }

    private static Intent getInstallAppIntent(final File file) {
        return getInstallAppIntent(file, false);
    }

    private static Intent getInstallAppIntent(final File file, final boolean isNewTask) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        String type = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            data = Uri.fromFile(file);
        } else {
            String authority = getApp().getPackageName() + ".utilcode.provider";
            data = FileProvider.getUriForFile(getApp(), authority, file);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        getApp().grantUriPermission(getApp().getPackageName(), data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(data, type);
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

    /**
     * Uninstall the app.
     * @param packageName The name of the package.
     */
    public static void uninstallApp(final String packageName) {
        if (FileUtils.INSTANCE.isSpace(packageName)) return;
        getApp().startActivity(getUninstallAppIntent(packageName, true));
    }

    /**
     * Uninstall the app.
     * @param activity    The activity.
     * @param packageName The name of the package.
     * @param requestCode If &gt;= 0, this code will be returned in
     *                    onActivityResult() when the activity exits.
     */
    public static void uninstallApp(final Activity activity,
                                    final String packageName,
                                    final int requestCode) {
        if (FileUtils.INSTANCE.isSpace(packageName)) return;
        activity.startActivityForResult(getUninstallAppIntent(packageName), requestCode);
    }

    private static Intent getUninstallAppIntent(final String packageName) {
        return getUninstallAppIntent(packageName, false);
    }

    private static Intent getUninstallAppIntent(final String packageName, final boolean isNewTask) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

    /**
     * Return whether the app is installed.
     * @param pkgName The name of the package.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppInstalled(@NonNull final String pkgName) {
        PackageManager packageManager = getApp().getPackageManager();
        try {
            return packageManager.getApplicationInfo(pkgName, 0) != null;
        } catch (PackageManager.NameNotFoundException e) {
            LaLog.e(ValueOf.logLivery(AnConstants.VALUE.LOG_LIVERY_EXCEPTION, e.getClass().toString(), e.getMessage()));
            return false;
        }
    }

    /**
     * Return whether it is a system application.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppSystem() {
        return isAppSystem(getApp().getPackageName());
    }

    /**
     * Return whether it is a system application.
     * @param packageName The name of the package.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppSystem(final String packageName) {
        if (FileUtils.INSTANCE.isSpace(packageName)) return false;
        try {
            PackageManager pm = getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            LaLog.e(ValueOf.logLivery(AnConstants.VALUE.LOG_LIVERY_EXCEPTION, e.getClass().toString(), e.getMessage()));
            return false;
        }
    }


    /**
     * Launch the application.
     * @param packageName The name of the package.
     */
    public static void launchApp(final String packageName) {
        if (FileUtils.INSTANCE.isSpace(packageName)) return;
        Intent launchAppIntent = getLaunchAppIntent(packageName, true);
        if (launchAppIntent == null) {
            LaLog.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        getApp().startActivity(launchAppIntent);
    }

    /**
     * Launch the application.
     * @param activity    The activity.
     * @param packageName The name of the package.
     * @param requestCode If &gt;= 0, this code will be returned in
     *                    onActivityResult() when the activity exits.
     */
    public static void launchApp(final Activity activity, final String packageName, final int requestCode) {
        if (FileUtils.INSTANCE.isSpace(packageName)) return;
        Intent launchAppIntent = getLaunchAppIntent(packageName);
        if (launchAppIntent == null) {
            LaLog.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        activity.startActivityForResult(launchAppIntent, requestCode);
    }


    private static Intent getLaunchAppIntent(final String packageName) {
        return getLaunchAppIntent(packageName, false);
    }

    private static Intent getLaunchAppIntent(final String packageName, final boolean isNewTask) {
        String launcherActivity = getLauncherActivity(packageName);
        if (!launcherActivity.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, launcherActivity);
            intent.setComponent(cn);
            return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
        }
        return null;
    }

    private static String getLauncherActivity(@NonNull final String pkg) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pkg);
        PackageManager pm = getApp().getPackageManager();
        List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        int size = info.size();
        if (size == 0) return "";
        for (int i = 0; i < size; i++) {
            ResolveInfo ri = info.get(i);
            if (ri.activityInfo.processName.equals(pkg)) {
                return ri.activityInfo.name;
            }
        }
        return info.get(0).activityInfo.name;
    }

    /**
     * 启动视频播放页面
     */
    public static void startPictureVideoPlayActivity(@NonNull Context context, String videoPath) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(context, PictureVideoPlayActivity.class);
            intent.putExtra(PictureConfig.EXTRA_VIDEO_PATH, videoPath);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static void startAliWebViewActivity(@NonNull Context context, String title, String data) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(context, AliWebActivity.class);
            intent.putExtra(APP_URL_MORE, data);
            intent.putExtra(APP_TITLE, title);
            context.startActivity(intent);
        }
    }

    public static void launchBrowser(@NonNull Context context, String url) {
        if (!DoubleUtils.isFastDoubleClick()) {
            DataService.getInstance().reUrlAvailable(url, new DataService.AvailableListener() {
                @Override
                public void available(String availableUrl) {
                    Uri uri = Uri.parse(url);
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(it);
                }

                @Override
                public void unAvailable(String url) {
                    ToastUtils.s(context, "非法地址");
                }
            });

        }
    }

    /**
     * 打开文件 ，判断兼容版本大于等于 7.0
     * @param actionAbsPath 需要操作的最终文件路径
     */
    public static void launchAppPath(@NonNull Context mContext, @NonNull String actionAbsPath) {
        launchAppWithFile(mContext, new File(actionAbsPath));
    }

    /**
     * 打开文件 ，判断兼容版本大于等于 7.0
     * @param actionAbsFile 需要操作的最终文件
     */
    public static void launchAppWithFile(@NonNull Context mContext, @NonNull File actionAbsFile) {
        Uri uri;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//add
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", actionAbsFile);
            try {
                mContext.grantUriPermission(mContext.getApplicationContext().getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } catch (Exception e) {
                e.printStackTrace();
            }
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        } else {
            uri = Uri.fromFile(actionAbsFile);
        }
        intent.setDataAndType(uri, PictureMimeType.getMimeType(actionAbsFile));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            Intent in = Intent.createChooser(intent, mContext.getResources().getString(R.string.anOpenWith));
            mContext.startActivity(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 启动预览界面
     * @param context
     * @param isWeChatStyle
     * @param bundle
     * @param requestCode
     */
    public static void startPicturePreviewActivity(Context context, boolean isWeChatStyle, Bundle bundle, int requestCode) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClass(context, isWeChatStyle ? PictureSelectorPreviewWeChatStyleActivity.class : PicturePreviewActivity.class);
            intent.putExtras(bundle);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, requestCode);
            }
        }
    }
}