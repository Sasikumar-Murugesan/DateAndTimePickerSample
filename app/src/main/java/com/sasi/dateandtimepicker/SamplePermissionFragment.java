package com.sasi.dateandtimepicker;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sasi.dateandtimepicker.databinding.SamplePermissionFragmentBinding;

public class SamplePermissionFragment extends Fragment {

    private SamplePermissionViewModel mViewModel;

    public static SamplePermissionFragment newInstance() {
        return new SamplePermissionFragment();
    }
public SamplePermissionFragmentBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.sample_permission_fragment,container,false);
    return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SamplePermissionViewModel.class);
        binding.setViewModel(mViewModel);
        mViewModel.requestingPermission.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String permissionRequest) {
                if (permissionRequest.equals("Camera")) {
                    if (PermissionUtils.checkAndRequestCameraPermissions(getActivity())) {
                        mViewModel.getImageFromCamera(getActivity());
                    }
                } else if (permissionRequest.equals("Gallery")) {
                    if (PermissionUtils.checkAndRequestGalleryPermissions(getActivity())) {
                        mViewModel.getImageFromGallery(getActivity());
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.REQUEST_CAMERA_PERMISSIONS) {
            if (PermissionUtils.checkCameraPermission(getActivity())) {
                mViewModel.getImageFromCamera(getActivity());
            } else {
                showPermissionNeededMsg();
            }
        } else if (requestCode == PermissionUtils.REQUEST_GALLERY_PERMISSIONS) {
            if (PermissionUtils.checkGalleryPermission(getActivity())) {
                mViewModel.getImageFromGallery(getActivity());
            } else {
                showPermissionNeededMsg();
            }
        }
    }

    private void showPermissionNeededMsg() {
        Toast.makeText(getActivity(), "Permission Not granted", Toast.LENGTH_SHORT).show();
    }


}
