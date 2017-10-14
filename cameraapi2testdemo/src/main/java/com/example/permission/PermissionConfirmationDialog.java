/*
 * Copyright (c) 2017, Jianguo Yang.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.permission;

/**
 * Created by Mi on 2017/10/14.
 */

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v13.app.FragmentCompat;

import com.example.cameraapi2testdemo.R;

import static com.example.permission.PermissionManager.CAMERA_PERMISSIONS;
import static com.example.permission.PermissionManager.REQUEST_CAMERA_PERMISSIONS;

/**
 * A dialog that explains about the necessary permissions.
 */
public class PermissionConfirmationDialog extends DialogFragment {

    public static PermissionConfirmationDialog newInstance() {
        return new PermissionConfirmationDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Fragment parent = getParentFragment();
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.request_permission)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentCompat.requestPermissions(parent, CAMERA_PERMISSIONS,
                                REQUEST_CAMERA_PERMISSIONS);
                    }
                })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        })
                .create();
    }

}
