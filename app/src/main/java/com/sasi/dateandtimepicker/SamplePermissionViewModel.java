package com.sasi.dateandtimepicker;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SamplePermissionViewModel extends ViewModel {

    public MutableLiveData<String> requestingPermission = new MutableLiveData<>();

    public void getImageFromCamera(Activity mActivity) {
        Toast.makeText(mActivity, "Permission Camera granted", Toast.LENGTH_SHORT).show();
    }

    public void getImageFromGallery(Activity mActivity) {
        Toast.makeText(mActivity, "Permission Gallery granted", Toast.LENGTH_SHORT).show();
    }

    public void requestPermission(View v) {
        requestingPermission.setValue("Camera");
    }
}
