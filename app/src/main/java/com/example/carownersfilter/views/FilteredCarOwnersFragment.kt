package com.example.carownersfilter.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.carownersfilter.R
import com.example.carownersfilter.model.Filters
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.utils.normalcase
import com.example.carownersfilter.utils.updateRecycler2
import kotlinx.android.synthetic.main.fragment_filtered_car_owners.*
import kotlinx.android.synthetic.main.fragment_my_filters.*


class FilteredCarOwnersFragment : BaseFragment() {
    lateinit var filter:Filters
    private var adaptor: RecyclerView.Adapter<*>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filtered_car_owners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {filter = FilteredCarOwnersFragmentArgs.fromBundle(it).filter }
        println(filter)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        adaptor = recycler.updateRecycler2(context!!, listOf(1,2,3,4,56,67,7),R.layout.item_car_owner, listOf(),{ innerViews, item ->



        },{item ->

        })
    }


}