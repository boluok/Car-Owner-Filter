package com.example.carownersfilter.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carownersfilter.MainActivity
import com.example.carownersfilter.R
import com.example.carownersfilter.model.Filters
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.utils.normalcase
import com.example.carownersfilter.utils.updateRecycler2
import com.example.carownersfilter.viewmodel.FiltersViewModel
import com.example.carownersfilter.viewmodel.observeChange
import kotlinx.android.synthetic.main.fragment_my_filters.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel


class MyFiltersFragment : BaseFragment() {

    private val filtersViewModel:FiltersViewModel by sharedViewModel()
    private var adaptor: RecyclerView.Adapter<*>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).supportActionBar?.title = "My Filters"
        setUpRecycler()
        setUpObservers()
        updateUI()
        filtersViewModel.getAllFiltersAPI()
        setRecyclerState(filtersViewModel.allFilters)

    }

    private fun updateUI() {
        swipe.setOnRefreshListener { filtersViewModel.getAllFiltersAPI() }
    }

    private fun setUpObservers() {
        filtersViewModel.allFiltersResponse.observeChange(viewLifecycleOwner){
            swipe.isRefreshing = false
            updateFilters(it)
        }
        filtersViewModel.showLoading.observeChange(viewLifecycleOwner){
            swipe.isRefreshing = true
        }
    }

    private fun setUpRecycler() {
         adaptor = filterRecycler.updateRecycler2(context!!, filtersViewModel.allFilters,R.layout.item_filter, listOf(R.id.ivProfileImage,R.id.groupCountry,R.id.textViewDateRange,R.id.textViewGender,R.id.textViewCountry,R.id.textViewColor),{ innerViews, item ->
            val ivProfileImage = innerViews[R.id.ivProfileImage] as ImageView
            val tvDateRange = innerViews[R.id.textViewDateRange] as TextView
            val tvGender = innerViews[R.id.textViewGender] as TextView
            val tvCountry = innerViews[R.id.textViewCountry] as TextView
            val tvColor = innerViews[R.id.textViewColor] as TextView
             val countryGroup = innerViews[R.id.groupCountry] as Group
             ivProfileImage.loadImage(item.avatar,context!!)
             tvDateRange.text = item.fullName
             tvGender.text = item.gender.normalcase()
             tvColor.text = item.colors.joinToString(separator = ", ")
             tvCountry.text = item.countries.joinToString(separator = ", ")
             if(item.countries.isEmpty()){
                 countryGroup.visibility = View.GONE
             }else{
                 countryGroup.visibility = View.VISIBLE
             }



        },{item ->
            mFragmentNavigation.pushFragment(FilteredCarOwnersFragment.newInstance(item))
        })
    }

    override fun onResume() {
        super.onResume()
        setRecyclerState(filtersViewModel.allFilters)
    }

    private fun updateFilters(filters: List<Filters>) {
        setRecyclerState(filters)
        val diffCallBack = FilersDiffUtils(filtersViewModel.allFilters,filters)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        filtersViewModel.allFilters.clear()
        filtersViewModel.allFilters.addAll(filters)
        adaptor?.let { it1 -> diffResult.dispatchUpdatesTo(it1) }
    }

    private fun setRecyclerState(filters: List<Filters>) {
        if(filters.isNullOrEmpty()){
            filterRecycler.visibility = View.GONE
            imageViewNoFilters.visibility = View.VISIBLE
            textViewNoFilters.visibility = View.VISIBLE
        }else{
            imageViewNoFilters.visibility = View.GONE
            textViewNoFilters.visibility = View.GONE
            filterRecycler.visibility = View.VISIBLE

        }

    }


}