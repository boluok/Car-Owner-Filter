package com.example.carownersfilter.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.carownersfilter.MainActivity
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.CheckPermissionUtil
import com.example.carownersfilter.utils.loadImage
import com.github.euzee.permission.PermissionCallback
import kotlinx.android.synthetic.main.fragment_get_permissions.*

class GetPermissionsFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_permissions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        imageView2.loadImage(R.drawable.access_storage,context!!)
        buttonPermissions.setOnClickListener {
            CheckPermissionUtil.checkWriteSd(context!!,object :PermissionCallback(){
                override fun onPermissionGranted() {
                   mFragmentNavigation.switchFragment(MainActivity.GETTING_FILE)

                }

                override fun onPermissionDenied() {
                    Toast.makeText(context!!,"We need this permission to proceed",Toast.LENGTH_SHORT).show()
                }

            })
        }
    }


}