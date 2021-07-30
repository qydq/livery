package com.sunsta.livery.faster.photo;


import com.sunsta.bear.faster.PermissionManager;
import com.sunsta.bear.model.entity.InvokeParam;

/**
 * 授权管理回调
 */
public interface InvokeListener {
    PermissionManager.TPermissionType invoke(InvokeParam invokeParam);
}
