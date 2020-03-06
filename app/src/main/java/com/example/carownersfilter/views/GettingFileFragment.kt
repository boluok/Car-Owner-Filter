package com.example.carownersfilter.views

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carownersfilter.MainActivity
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.FileStatus
import com.example.carownersfilter.viewmodel.FileStatus.*
import com.example.carownersfilter.viewmodel.observeChange
import kotlinx.android.synthetic.main.fragment_getting_file.*
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel


class GettingFileFragment : BaseFragment() {
    private val carOwnerViewModel: CarOwnersViewModel by sharedViewModel()

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
        getCSVFile()

    }

    private fun getCSVFile() {
        carOwnerViewModel.getAndSaveCSV(context!!)
    }

    private fun setUpObservers() {
        carOwnerViewModel.onDataSaveSuccessful.observeChange(viewLifecycleOwner){
            println("Status ${it}")
            Handler().postDelayed({
                when(it){
                    COULD_NOT_FIND -> mFragmentNavigation.pushFragment(CouldntFindFileFragment())
                    FOUND -> mFragmentNavigation.switchFragment(MainActivity.MYFILTERS)

                }

            },1000)
        }
    }

    private fun updateUI() {
        imageView.loadImage(R.drawable.seach_file,context!!)
    }

}