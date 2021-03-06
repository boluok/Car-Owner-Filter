package com.example.carownersfilter.views

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carownersfilter.MainActivity
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.UserStatus
import com.example.carownersfilter.viewmodel.UserStatus.*
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashScreenFragment :BaseFragment(){
    private val carOwnerViewModel: CarOwnersViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI()
    }

    private fun updateUI() {
        logoIV.loadImage(R.drawable.app_logo,context!!)
        (activity as MainActivity).supportActionBar?.hide()
        Handler().postDelayed({
            val status = carOwnerViewModel.getStatus(context!!)
            println("Status ${status}")
            when(status){
                PERMISSION_GRANTED_DATA_LOADED -> mFragmentNavigation.switchFragment(MainActivity.MYFILTERS)
                PERMISSION_GRANTED_NO_FILE -> mFragmentNavigation.pushFragment(CouldntFindFileFragment())
                NO_PERMISSION -> mFragmentNavigation.pushFragment(GetPermissionsFragment())

            }

        },500)
    }


}