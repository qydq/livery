package com.sunsta.livery.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sunsta.livery.R;
import com.sunsta.livery.callback.TakePhoto;
import com.sunsta.livery.contract.TakePhotoImpl;
import com.sunsta.livery.faster.photo.InvokeListener;
import com.sunsta.livery.faster.photo.TakePhotoInvocationHandler;
import com.sunsta.bear.callback.TakeResultListener;
import com.sunsta.bear.faster.PermissionManager;
import com.sunsta.bear.model.entity.InvokeParam;
import com.sunsta.bear.model.entity.TContextWrap;
import com.sunsta.bear.model.entity.TResult;
import com.sunsta.bear.view.BaseFragment;


/**
 * Created by stary on 2016/8/18.
 * 文件名称：AliTackPhotoFragment
 * 文件描述：Fragment超类
 * 作者：staryumou@163.com
 * 修改时间：2016/8/18
 */

public abstract class AliTackPhotoFragment extends BaseFragment implements TakeResultListener, InvokeListener {
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(getActivity(), type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
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

    public void reload() {
    }
}
