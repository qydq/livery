package com.sunsta.livery.view.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatDelegate;

import com.sunsta.bear.AnConstants;
import com.sunsta.bear.contract.DayNightTheme;
import com.sunsta.bear.faster.LaLog;
import com.sunsta.bear.faster.NetBroadcastReceiver;
import com.sunsta.bear.faster.SPUtils;
import com.sunsta.bear.layout.INASwipeCloseFrameLayout;
import com.sunsta.bear.model.entity.ResponseDayNightMode;
import com.sunsta.bear.view.BaseActivity;
import com.sunsta.livery.R;

/**
 * 请关注个人知乎bgwan， 在【an系列】专栏会有本【ali框架】的详细使用案例（20190922-正在持续更新中...）
 * <p>
 * 请关注个人知乎bgwan， 在【an框架】专栏会有详细的使用案例（20190922-正在持续更新中...）
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
 * 中文描述：an系列ali框架增加三种向右滑动返回-前一个Activity界面的基类，SwipeBackActivity$$SwipeCloseActivity$$SwipeFinishActivity
 * <br/><a href="https://zhihu.com/people/qydq">
 * --------温馨提示：知识是应该分享的，an系列框架可以点击这里关注我获取更详细的信息</a><br/>
 * <h3><a href="https://zhuanlan.zhihu.com/p/80668416">版权声明：(C) 2016 The Android Developer Sunst</a></h3>
 * <br>创建日期（可选）：2016/8/18
 * <br>邮件Email：qyddai@gmail.com
 * <br>Github：<a href ="https://qydq.github.io">qydq</a>
 * <br>知乎主页：<a href="https://zhihu.com/people/qydq">Bgwan</a>
 *
 * @author sunst // sunst0069
 * @version 3.0 |   2020/12/12          |   构造方法修改数据缓存序列，引用最新的组件google lifedatabus，更完美适配mvvm
 * @link 知乎主页： https://zhihu.com/people/qydq
 */
public abstract class SwipeCloseActivity extends BaseActivity implements NetBroadcastReceiver.NetEvevt, DayNightTheme {
    protected Context mContext;

    protected Window mWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //环信集成功能，暂未开启。
//		EMChat.getInstance().init(this.getApplicationContext());
        mContext = this;
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
        /*
         * LAUi 设置状态栏属性，后期修改该类为单例模式 的枚举类型
         * */
        mWindow = getWindow();

        //SwipeCloseActivity init
        INASwipeCloseFrameLayout rootView = new INASwipeCloseFrameLayout(this);
        rootView.bindActivity(this);
        //初始化视图
        initView(savedInstanceState);
        //网络变化监听相关
        evevt = this;
        inspectNet();
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

    /**
     * 各种对象、组件的初始化
     */
    protected abstract void initView(Bundle savedInstanceState);
}
