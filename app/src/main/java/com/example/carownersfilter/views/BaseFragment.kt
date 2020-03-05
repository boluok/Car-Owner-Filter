package com.example.carownersfilter.views


import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.example.carownersfilter.R


open class BaseFragment:Fragment(){

    protected open fun getNavOptions(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_out_left)
            .setPopExitAnim(R.anim.slide_out_left)
            .build()
    }
}