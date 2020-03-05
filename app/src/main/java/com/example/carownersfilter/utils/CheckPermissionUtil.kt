package com.example.carownersfilter.utils

import android.Manifest
import android.Manifest.permission
import android.Manifest.permission.*
import android.app.Activity
import android.content.Context
import com.github.euzee.permission.PermissionCallback
import com.github.euzee.permission.PermissionUtil


object CheckPermissionUtil {

    private val LOCATION_PERMISSION_REQ_CODE = 200
    private val WRITE_SD_REQ_CODE = 201
    private val PICK_CONTACT = 34
    private val PICK_CAMERA = 334


    @JvmStatic
    fun checkWriteSd(
        context: Context,
        callback:  PermissionCallback
    ) {
        PermissionUtil.storageRW(context,callback)
    }

    }


