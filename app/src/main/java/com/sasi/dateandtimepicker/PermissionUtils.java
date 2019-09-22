package com.sasi.dateandtimepicker;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    public static final int REQUEST_CAMERA_PERMISSIONS = 1000;
    public static final int REQUEST_GALLERY_PERMISSIONS = 2000;

    public static boolean checkAndRequestCameraPermissions(Activity mActivity) {
        int camerapermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA);
        int writepermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(mActivity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_CAMERA_PERMISSIONS);
            return false;
        }
        return true;
    }

    public static boolean checkAndRequestGalleryPermissions(Activity mActivity) {
        int readpermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readpermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_GALLERY_PERMISSIONS);
            return false;
        }
        return true;

    }

    public static boolean checkCameraPermission(Activity mActivity){
        int camerapermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA);
        int writepermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean checkGalleryPermission(Activity mActivity){
        int readpermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (readpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            return false;
        }
        return true;
    }

}
