package com.example.carownersfilter.views

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.carownersfilter.local.room.PaperPrefs
import com.example.carownersfilter.viewmodel.BaseViewModel
import com.example.carownersfilter.viewmodel.observeChange
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import org.koin.android.ext.android.inject


open class BaseFragment : Fragment() {



    lateinit var mFragmentNavigation: FragmentNavigation
    lateinit  var alertDialog: AlertDialog
    val paperPref: PaperPrefs by inject()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            mFragmentNavigation = context


        }
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
        fun popFragment()
        fun popFragments(n:Int)
        fun openDialogFragment(fragment:DialogFragment)
        fun openBottomSheet(bottomSheetDialogFragment: BottomSheetDialogFragment)
        fun clearStack()
        fun clearDialogFragmentStack()
        fun switchFragment(index: Int)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelAllJobs()
    }
    fun cancelAllJobs(){

    }

    fun setUpObservers(viewModel: BaseViewModel){
        setUpErrorHandler(viewModel)
        setUpLoading(viewModel)
    }
    private fun setUpErrorHandler(viewModel: BaseViewModel){
        viewModel.showError.observeChange(this){showError(it)}
    }
    fun setUpErrorHandler(viewModel: BaseViewModel, action:()->Unit){
        viewModel.showError.observeChange(this){
            action()
            showError(it)
        }
    }


    fun setUpLoading(viewModel:BaseViewModel){
        viewModel.showLoading.observeChange(this){status -> showLoading(status)}
    }

    fun showLoading(status:Boolean){
        if (status) alertDialog.show() else alertDialog.dismiss()
    }

    fun showError(errorMessage:String){
        Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show()
    }

}
