package com.example.carownersfilter.views

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.FileStatus
import com.example.carownersfilter.viewmodel.FileStatus.*
import com.example.carownersfilter.viewmodel.observeChange
import kotlinx.android.synthetic.main.fragment_getting_file.*
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject


class GettingFileFragment : BaseFragment() {
    private val carOwnerViewModel: CarOwnersViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_getting_file, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
        setUpObservers()
        carOwnerViewModel.getAndSaveCSV(context!!)
    }

    private fun setUpObservers() {
        carOwnerViewModel.onDataSaveSuccessful.observeChange(viewLifecycleOwner){
            Handler().postDelayed({
                when(it){
                    COULD_NOT_FIND -> findNavController().navigate(R.id.searchingToCouldntFind)
                    FOUND -> findNavController().navigate(R.id.searchingToMyFilters)
                }

            },3000)
        }
    }

    private fun updateUI() {
        imageView.loadImage(R.drawable.seach_file,context!!)
    }

}