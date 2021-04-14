package com.sunsta.livery.faster.photo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.sunsta.bear.AnConstants;
import com.sunsta.bear.faster.FileUtils;
import com.sunsta.bear.faster.LADateTime;
import com.sunsta.bear.model.entity.TException;
import com.sunsta.bear.model.entity.TExceptionType;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * <h2>请关注个人知乎Bgwan， 在【an系列】专栏会有本【livery框架】的使用案例（20190922-正在持续更新中...</h2>
 * 中文描述：an系列alidd框架中Uri解析工具类 * <br/>
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期（可选）：2015/8/26
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 * @author sunst // sunst0069
 * @version 2.0 |   2019/10/07           |   an系列alidd框架中Uri解析工具类
 */
public class TUriParse {
    private static final String TAG = TUriParse.class.getName();

    /**
     * 将scheme为file的uri转成FileProvider 提供的content uri
     * @param context
     * @param uri
     * @return
     */
    public static Uri convertFileUriToFileProviderUri(Context context, Uri uri) {
        if (uri == null) return null;
        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            return getUriForFile(context, FileUtils.INSTANCE.getFileByPath(uri.getPath()));
        }
        return uri;

    }

    /**
     * 获取一个临时的Uri, 文件名随机生成
     * @param context
     * @return
     */
    public static Uri getTempUri(Context context) {
        String timeStamp = LADateTime.getInstance().getCurrentTimeInString(LADateTime.PATTERN_DATE_TIME_FOLDER);
        String pwdFilepath = File.separator + AnConstants.FOLDER_IMAGES + File.separator;
        File dirtouch = FileUtils.INSTANCE.touchFile(pwdFilepath, timeStamp + AnConstants.FILE_SUFFIX_PNG);
        return getUriForFile(context, dirtouch);
    }

    /**
     * 获取一个临时的Uri, 通过传入字符串路径
     * @param context
     * @param path
     * @return
     */
    public static Uri getTempUri(Context context, String path) {
        File file = new File(path);
        return getTempUri(context, file);
    }

    /**
     * 获取一个临时的Uri, 通过传入File对象
     * @param context
     * @return
     */
    public static Uri getTempUri(Context context, File file) {
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return getUriForFile(context, file);
    }

    /**
     * 创建一个用于拍照图片输出路径的Uri (FileProvider)
     * @param context
     * @return
     */
    public static Uri getUriForFile(Context context, File file) {
        return FileProvider.getUriForFile(context, FileUtils.INSTANCE.getFileProviderName(context), file);
    }

    /**
     * 将TakePhoto 提供的Uri 解析出文件绝对路径
     * @param uri
     * @return
     */
    public static String parseOwnUri(Context context, Uri uri) {
        if (uri == null) return null;
        String path = AnConstants.EMPTY;
        if (TextUtils.equals(uri.getAuthority(), FileUtils.INSTANCE.getFileProviderName(context))) {
            String nativePath = uri.getPath();
            if (!TextUtils.isEmpty(nativePath))
                path = FileUtils.INSTANCE.createDir(uri.getPath()).getAbsolutePath();
        } else {
            path = uri.getPath();
        }
        return path;
    }

    /**
     * 通过URI获取文件的路径
     * @param uri
     * @param activity
     * @return Author JPH
     * Date 2016/6/6 0006 20:01
     */
    public static String getFilePathWithUri(Uri uri, Activity activity) throws TException {
        if (uri == null) {
            Log.w(TAG, "uri is null,activity may have been recovered?");
            throw new TException(TExceptionType.TYPE_URI_NULL);
        }
        File picture = getFileWithUri(uri, activity);
        String picturePath = picture == null ? null : picture.getPath();
        if (TextUtils.isEmpty(picturePath))
            throw new TException(TExceptionType.TYPE_URI_PARSE_FAIL);
        if (!TImageFiles.checkMimeType(activity, TImageFiles.getMimeType(activity, uri)))
            throw new TException(TExceptionType.TYPE_NOT_IMAGE);
        return picturePath;
    }

    /**
     * 通过URI获取文件
     * @param uri
     * @param activity
     * @return Author JPH
     * Date 2016/10/25
     */
    public static File getFileWithUri(Uri uri, Activity activity) {
        String picturePath = null;
        String scheme = uri.getScheme();
        if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = activity.getContentResolver().query(uri,
                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            if (columnIndex >= 0) {
                picturePath = cursor.getString(columnIndex);  //获取照片路径
            } else if (TextUtils.equals(uri.getAuthority(), FileUtils.INSTANCE.getFileProviderName(activity))) {
                picturePath = parseOwnUri(activity, uri);
            }
            cursor.close();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            picturePath = uri.getPath();
        }
        return TextUtils.isEmpty(picturePath) ? null : new File(picturePath);
    }

    /**
     * 通过从文件中得到的URI获取文件的路径
     * @param uri
     * @param activity
     * @return Author JPH
     * Date 2016/6/6 0006 20:01
     */
    public static String getFilePathWithDocumentsUri(Uri uri, Activity activity) throws TException {
        if (uri == null) {
            Log.e(TAG, "uri is null,activity may have been recovered?");
            return null;
        }
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && uri.getPath().contains("document")) {
            File tempFile = TImageFiles.getTempFile(activity, uri);
            try {
                TImageFiles.inputStreamToFile(activity.getContentResolver().openInputStream(uri), tempFile);
                return tempFile.getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new TException(TExceptionType.TYPE_NO_FIND);
            }
        } else {
            return getFilePathWithUri(uri, activity);
        }
    }
}
