package com.example.carownersfilter.views

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.UserStatus
import com.example.carownersfilter.viewmodel.UserStatus.*
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SplashScreenFragment :BaseFragment(){
    private val carOwnerViewModel: CarOwnersViewModel by sharedViewModel()
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

        Handler().postDelayed({
            when(carOwnerViewModel.getStatus(context!!)){
                PERMISSION_GRANTED_DATA_LOADED -> findNavController().navigate(R.id.action_splash_screen_to_filtersFragment)
                PERMISSION_GRANTED_NO_FILE -> findNavController().navigate(R.id.action_splash_screen_to_CouldntFragment)
                NO_PERMISSION -> findNavController().navigate(R.id.action_splash_screen_to_permissionsFragment)
            }

        },3000)
    }


}