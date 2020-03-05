package com.example.carownersfilter.utils

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide

fun ImageView.loadImage(resource:Int,context: Context){
    Glide.with(context).load(resource).into(this)
}

