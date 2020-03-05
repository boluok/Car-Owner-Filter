package com.example.carownersfilter.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carownersfilter.MainActivity
import com.example.carownersfilter.R
import com.example.carownersfilter.utils.updateRecycler2
import com.example.carownersfilter.viewmodel.FiltersViewModel
import com.example.carownersfilter.viewmodel.observeChange
import kotlinx.android.synthetic.main.fragment_my_filters.*
import org.koin.android.ext.android.inject


class MyFiltersFragment : Fragment() {

    private val filtersViewModel:FiltersViewModel by inject()
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
        filtersViewModel.getAllFiltersAPI()
    }

    private fun setUpObservers() {
        filtersViewModel.allFiltersResponse.observeChange(viewLifecycleOwner){
            adaptor?.notifyDataSetChanged()
        }
    }

    private fun setUpRecycler() {
         adaptor = filterRecycler.updateRecycler2(context!!, filtersViewModel.allFilters,R.layout.item_filter, listOf(R.id.ivProfileImage,R.id.textViewDateRange),{innerViews,item ->
            val ivProfileImage = innerViews[R.id.ivProfileImage] as ImageView
            val tvDateRange = innerViews[R.id.textViewDateRange] as TextView
            val tvGender = innerViews[R.id.textViewGender] as ImageView
            val tvCountry = innerViews[R.id.textViewCountry] as TextView
            val tvColor = innerViews[R.id.textViewColor] as TextView

        },{item ->

        })
    }


}