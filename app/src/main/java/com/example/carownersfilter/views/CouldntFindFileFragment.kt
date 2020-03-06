package com.example.carownersfilter.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.loadImage
import kotlinx.android.synthetic.main.fragment_couldnt_find_file.*


class CouldntFindFileFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_couldnt_find_file, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        imageView.loadImage(R.drawable.no_file,context!!)
    }
}