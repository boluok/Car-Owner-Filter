package com.example.carownersfilter.views

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.net.toFile
import com.example.carownersfilter.MainActivity
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import com.example.carownersfilter.viewmodel.FileStatus
import com.example.carownersfilter.viewmodel.observeChange
import kotlinx.android.synthetic.main.fragment_couldnt_find_file.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.io.File
import java.io.InputStream
import java.net.URI


class CouldntFindFileFragment : BaseFragment() {
    private val carOwnerViewModel: CarOwnersViewModel by sharedViewModel()

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
        setObservers()
    }

    private fun setObservers() {
        carOwnerViewModel.showLoading.observeChange(viewLifecycleOwner){
            if(it){
                spin_kit.visibility = View.VISIBLE
            }else{
                spin_kit.visibility = View.GONE
            }
        }
        carOwnerViewModel.onDataSaveSuccessful.observeChange(viewLifecycleOwner){
            Handler().postDelayed({
                when(it){
                    FileStatus.FOUND -> mFragmentNavigation.switchFragment(MainActivity.MYFILTERS)

                }

            },1000)
        }
    }

    private fun updateUI() {
        imageView.loadImage(R.drawable.no_file,context!!)
        button.setOnClickListener {
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            val inputStream = selectedFile?.let { context?.contentResolver?.openInputStream(it) }
            val extention = MimeTypeMap.getFileExtensionFromUrl(selectedFile.toString())
            require(extention.equals("csv")){
                Toast.makeText(context!!,"File must be a CSV",Toast.LENGTH_LONG).show()
                return
            }
            inputStream?.let { carOwnerViewModel.getAndSaveCSV(it) }


        }
    }
}

