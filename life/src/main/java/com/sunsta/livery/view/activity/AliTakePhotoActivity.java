package com.sunsta.livery.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.sunsta.bear.AnConstants;
import com.sunsta.bear.callback.TakeResultListener;
import com.sunsta.bear.contract.DayNightTheme;
import com.sunsta.bear.faster.AndroidTranslucentBar;
import com.sunsta.bear.faster.LaLog;
import com.sunsta.bear.faster.NetBroadcastReceiver;
import com.sunsta.bear.faster.PermissionManager;
import com.sunsta.bear.faster.SPUtils;
import com.sunsta.bear.layout.ParallaxBackActivityHelper;
import com.sunsta.bear.layout.ParallaxBackLayout;
import com.sunsta.bear.model.entity.InvokeParam;
import com.sunsta.bear.model.entity.ResponseDayNightMode;
import com.sunsta.bear.model.entity.TContextWrap;
import com.sunsta.bear.model.entity.TResult;
import com.sunsta.bear.view.BaseActivity;
import com.sunsta.livery.R;
import com.sunsta.livery.callback.TakePhoto;
import com.sunsta.livery.contract.TakePhotoImpl;
import com.sunsta.livery.faster.photo.InvokeListener;
import com.sunsta.livery.faster.photo.TakePhotoInvocationHandler;

/**
 * <h2>请关注个人知乎Bgwan， 在【an系列】专栏会有本【livery框架】的使用案例（20190922-正在持续更新中...</h2>
 * </p><p>
 * * 替代EventBus及RxBus
 * * 代码源自：Android消息总线的演进之路：用LiveDataBus替代RxBus、EventBus
 * * https://mp.weixin.qq.com/s?__biz=MjM5NjQ5MTI5OA==&mid=2651748475&idx=4&sn=8feb14dd49ce79726ecf12eb6c243740&chksm=bd12a1368a652820df7c556182d3494d84ae38d4aee4e84c48c227aa5083ebf2b1a0150cf1b5&mpshare=1&scene=1&srcid=1010fzmNILeVVxi5HsAG8R17#rd
 * *
 * * 基本使用：
 * * 注册订阅：
 * * LiveDataBus.get().getChannel("key_test", Boolean.class)
 * *         .observe(this, new Observer<Boolean>() {
 * *             @Override
 * *             public void onChanged(@Nullable Boolean aBoolean) {
 * *             }
 * *         });
 * * 发送消息：
 * * LiveDataBus.get().getChannel("key_test").setValue(true);
 * </p>
 * 中文描述：在Android设备上获取照片（拍照或从相册、文件中选择）,继承BaseActivity可以监听网络等变化
 * * <p>
 * * 使用方法：拍照类继承这个类即可。参考
 * * https://blog.csdn.net/qq_27634797/article/details/79445010
 * * https://github.com/crazycodeboy/TakePhoto <br/>
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期：2017/3/23
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 *
 * @author sunst // sunst0069
 * @version 3.0 |   2020/12/12          |   构造方法修改数据缓存序列，引用最新的组件google lifedatabus，更完美适配mvvm，拍照集成
 */
public abstract class AliTakePhotoActivity extends BaseActivity implements NetBroadcastReceiver.NetEvevt, DayNightTheme, TakeResultListener, InvokeListener {
    private ParallaxBackActivityHelper mHelper;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    protected Context mContext;
    protected Window mWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        // TODO 固件化的操作
        super.onCreate(savedInstanceState);
        //环信集成功能，暂未开启。
//		EMChat.getInstance().init(this.getApplicationContext());
        mContext = this;
        mHelper = new ParallaxBackActivityHelper(this);
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        /*
         * 1/2/3为Livery提供,除此之外为系统原生兼容
         * */
        if (currentMode == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            int dayNightModeCode = getDayNightMode().getCode();
            if (dayNightModeCode == ResponseDayNightMode.NIGHT.getCode()) {
                setNightTheme();
                fitStatusBar(false, true);//设置状态栏颜色为白色
            } else if (dayNightModeCode == ResponseDayNightMode.DAY.getCode()) {
                setDayTheme();
                fitStatusBar(true, true);//设置状态栏颜色为黑色
            } else {
                followSystemTheme();
            }
        } else {
            if (currentMode == AppCompatDelegate.MODE_NIGHT_NO) {
                setDayTheme();
                fitStatusBar(true, true);//设置状态栏颜色为黑色
            } else if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                setNightTheme();
                fitStatusBar(false, true);//设置状态栏颜色为白色
            } else {
                followSystemTheme();
            }
        }
        setTheme(R.style.DayNightTheme);

        //夜间模式第一种方式
        windowTranslucent();
        //初始化视图
        initView(savedInstanceState);

        //网络变化监听相关
        evevt = this;
        inspectNet();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public void windowTranslucent() {
        mWindow = getWindow();
        AndroidTranslucentBar.getInstance().setTranslucentBar(mWindow);
        mWindow.getDecorView().setBackground(ContextCompat.getDrawable(mContext, R.drawable.in_background_angle));
    }

    @Override
    public void setDayTheme() {
        SPUtils.getInstance().putString(AnConstants.KEY.DAYNIGHT_MODE, ResponseDayNightMode.DAY.getName());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void setNightTheme() {
        SPUtils.getInstance().putString(AnConstants.KEY.DAYNIGHT_MODE, ResponseDayNightMode.NIGHT.getName());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    @Override
    public void followSystemTheme() {
        SPUtils.getInstance().putString(AnConstants.KEY.DAYNIGHT_MODE, ResponseDayNightMode.SYSTEM.getName());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                fitStatusBar(false, true);//设置状态栏颜色为白色
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                fitStatusBar(true, true);//设置状态栏颜色为黑色
                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                LaLog.d("UI_MODE_NIGHT_UNDEFINED");
                break;
        }
    }

    /**
     * finish check the preference and rb ; is ok
     */
    public ResponseDayNightMode getDayNightMode() {
        String mode = SPUtils.getInstance().getString(AnConstants.KEY.DAYNIGHT_MODE, ResponseDayNightMode.SYSTEM.getName());
        return ResponseDayNightMode.valueOf(mode);
    }

    public void setDayNightTheme(int colorId) {
        mWindow.getDecorView().setBackground(ContextCompat.getDrawable(mContext, colorId));
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netModel) {
        // TODO Auto-generated method stub
        this.netModel = netModel;
        isNetConnect();
    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netModel == 1) {
            return true;
        } else if (netModel == 0) {
            return true;
        } else if (netModel == -1) {
            return false;
        }
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    public <T extends View> T findViewById(@IdRes int id) {
        View view = getDelegate().findViewById(id);
        if (view == null && mHelper != null)
            return mHelper.findViewById(id);
        return getDelegate().findViewById(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        Log.i(TAG, "takeSuccess：" + result.getImage().getCompressPath());
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.i(TAG, "takeFail:" + msg);
    }

    @Override
    public void takeCancel() {
        Log.i(TAG, getResources().getString(R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onVoice(String type, String words) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.onActivityDestroy();
    }

    public ParallaxBackLayout getBackLayout() {
        return mHelper.getBackLayout();
    }

    public void setBackEnable(boolean enable) {
        mHelper.setBackEnable(enable);
    }

    public void scrollToFinishActivity() {
        mHelper.scrollToFinishActivity();
    }

    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            scrollToFinishActivity();
        }
    }

    /**
     * 各种对象、组件的初始化
     */
    protected abstract void initView(Bundle savedInstanceState);
}