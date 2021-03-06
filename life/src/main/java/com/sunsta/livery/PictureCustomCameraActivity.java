package com.sunsta.livery;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraX;
import androidx.camera.view.CameraView;

import com.sunsta.livery.camera.CustomCameraView;
import com.sunsta.livery.camera.listener.CameraListener;
import com.sunsta.livery.camera.view.CaptureLayout;
import com.sunsta.bear.config.PictureMimeType;
import com.sunsta.livery.config.PictureSelectionConfig;
import com.sunsta.livery.dialog.PictureCustomDialog;
import com.sunsta.livery.permissions.PermissionChecker;
import com.sunsta.bear.config.PictureConfig;
import com.sunsta.bear.faster.ToastUtils;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * @author：luck
 * @date：2020-01-04 14:05
 * @describe：自定义拍照和录音
 */
public class PictureCustomCameraActivity extends PictureSelectorCameraEmptyActivity {


    private CustomCameraView mCameraView;
    protected boolean isEnterSetting;

    @Override
    public boolean isImmersive() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        // 验证存储权限
        boolean isExternalStorage = PermissionChecker
                .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
                PermissionChecker
                        .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!isExternalStorage) {
            PermissionChecker.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE);
            return;
        }

        // 验证相机权限
        if (PermissionChecker
                .checkSelfPermission(this, Manifest.permission.CAMERA)) {
            // 验证麦克风权限
            boolean isRecordAudio = PermissionChecker.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
            if (isRecordAudio) {
                createCameraView();
            } else {
                PermissionChecker.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, PictureConfig.APPLY_RECORD_AUDIO_PERMISSIONS_CODE);
            }
        } else {
            PermissionChecker.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, PictureConfig.APPLY_CAMERA_PERMISSIONS_CODE);
        }
    }

    /**
     * 创建CameraView
     */
    private void createCameraView() {
        if (mCameraView == null) {
            mCameraView = new CustomCameraView(getContext());
            setContentView(mCameraView);
            initView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 这里只针对权限被手动拒绝后进入设置页面重新获取权限后的操作
        if (isEnterSetting) {
            boolean isExternalStorage = PermissionChecker
                    .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
                    PermissionChecker
                            .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (isExternalStorage) {
                boolean isCameraPermissionChecker = PermissionChecker
                        .checkSelfPermission(this, Manifest.permission.CAMERA);
                if (isCameraPermissionChecker) {
                    boolean isRecordAudio = PermissionChecker
                            .checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
                    if (isRecordAudio) {
                        createCameraView();
                    } else {
                        showPermissionsDialog(false, getString(R.string.picture_audio));
                    }
                } else {
                    showPermissionsDialog(false, getString(R.string.picture_camera));
                }
            } else {
                showPermissionsDialog(false, getString(R.string.picture_jurisdiction));
            }
            isEnterSetting = false;
        }
    }

    /**
     * 初始化控件
     */
    protected void initView() {
        mCameraView.setPictureSelectionConfig(config);
        // 绑定生命周期
        mCameraView.setBindToLifecycle(new WeakReference<>(this).get());
        // 视频最大拍摄时长
        if (config.recordVideoSecond > 0) {
            mCameraView.setRecordVideoMaxTime(config.recordVideoSecond);
        }
        // 视频最小拍摄时长
        if (config.recordVideoMinSecond > 0) {
            mCameraView.setRecordVideoMinTime(config.recordVideoMinSecond);
        }
        // 获取CameraView
        CameraView cameraView = mCameraView.getCameraView();
        if (cameraView != null && config.isCameraAroundState) {
            cameraView.toggleCamera();
        }
        // 获取录制按钮
        CaptureLayout captureLayout = mCameraView.getCaptureLayout();
        if (captureLayout != null) {
            captureLayout.setButtonFeatures(config.buttonFeatures);
        }
        // 拍照预览
        mCameraView.setImageCallbackListener((file, imageView) -> {
            if (config != null && PictureSelectionConfig.imageEngine != null && file != null) {
                PictureSelectionConfig.imageEngine.loadImage(getContext(), file.getAbsolutePath(), imageView);
            }
        });
        // 设置拍照或拍视频回调监听
        mCameraView.setCameraListener(new CameraListener() {
            @Override
            public void onPictureSuccess(@NonNull File file) {
                config.cameraMimeType = PictureMimeType.ofImage();
                Intent intent = new Intent();
                intent.putExtra(PictureConfig.EXTRA_MEDIA_PATH, file.getAbsolutePath());
                if (config.camera) {
                    requestCamera(intent);
                } else {
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                }
            }

            @Override
            public void onRecordSuccess(@NonNull File file) {
                config.cameraMimeType = PictureMimeType.ofVideo();
                Intent intent = new Intent();
                intent.putExtra(PictureConfig.EXTRA_MEDIA_PATH, file.getAbsolutePath());
                if (config.camera) {
                    requestCamera(intent);
                } else {
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                }
            }

            @Override
            public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                ToastUtils.s(getContext(), message);
                onBackPressed();
            }
        });

        //左边按钮点击事件
        mCameraView.setOnClickListener(() -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        if (config != null && config.camera && PictureSelectionConfig.listener != null) {
            PictureSelectionConfig.listener.onCancel();
        }
        closeActivity();
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onDestroy() {
        if (mCameraView != null) {
            CameraX.unbindAll();
            mCameraView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE:
                // 存储权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PermissionChecker.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA}, PictureConfig.APPLY_CAMERA_PERMISSIONS_CODE);
                } else {
                    showPermissionsDialog(true, getString(R.string.picture_jurisdiction));
                }
                break;
            case PictureConfig.APPLY_CAMERA_PERMISSIONS_CODE:
                // 相机权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean isRecordAudio = PermissionChecker.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
                    if (isRecordAudio) {
                        createCameraView();
                    } else {
                        PermissionChecker.requestPermissions(this,
                                new String[]{Manifest.permission.RECORD_AUDIO}, PictureConfig.APPLY_RECORD_AUDIO_PERMISSIONS_CODE);
                    }
                } else {
                    showPermissionsDialog(true, getString(R.string.picture_camera));
                }
                break;
            case PictureConfig.APPLY_RECORD_AUDIO_PERMISSIONS_CODE:
                // 录音权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createCameraView();
                } else {
                    showPermissionsDialog(false, getString(R.string.picture_audio));
                }
                break;
        }
    }

    @Override
    protected void showPermissionsDialog(boolean isCamera, String errorMsg) {
        if (isFinishing()) {
            return;
        }
        final PictureCustomDialog dialog =
                new PictureCustomDialog(getContext(), R.layout.an_wind_base_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        Button btn_commit = dialog.findViewById(R.id.btn_commit);
        btn_commit.setText(getString(R.string.picture_go_setting));
        TextView tv_title = dialog.findViewById(R.id.tv_title);
        TextView tv_content = dialog.findViewById(R.id.tv_content);
        tv_title.setText(getString(R.string.an_prompt));
        tv_content.setText(errorMsg);
        btn_cancel.setOnClickListener(v -> {
            if (!isFinishing()) {
                dialog.dismiss();
            }
            closeActivity();
        });
        btn_commit.setOnClickListener(v -> {
            if (!isFinishing()) {
                dialog.dismiss();
            }
            PermissionChecker.launchAppDetailsSettings(getContext());
            isEnterSetting = true;
        });
        dialog.show();
    }
}
