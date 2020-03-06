package com.example.carownersfilter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.carownersfilter.R
import com.example.carownersfilter.model.Filters
import com.example.carownersfilter.utils.argument
import com.example.carownersfilter.utils.updateRecycler2
import com.example.carownersfilter.utils.withArguments
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import kotlinx.android.synthetic.main.fragment_filtered_car_owners.*
import com.example.carownersfilter.utils.loadImage
import com.example.carownersfilter.utils.normalcase
import com.example.carownersfilter.utils.updateRecycler2
import com.example.carownersfilter.viewmodel.CarOwnersViewModel
import kotlinx.android.synthetic.main.fragment_filtered_car_owners.*
import kotlinx.android.synthetic.main.fragment_my_filters.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class FilteredCarOwnersFragment : BaseFragment() {
     val filter:Filters  by argument(FILTER)
    private var adaptor: RecyclerView.Adapter<*>? = null
    private val carOwnerViewModel: CarOwnersViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filtered_car_owners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }




    private fun setUpRecycler() {
       val carOwners = carOwnerViewModel.getCarOwnersForFilter(filter)
        adaptor = recycler.updateRecycler2(context!!,carOwners,R.layout.item_car_owner, listOf(R.id.textViewCarOwnerName,R.id.textViewCarModel,R.id.textViewGender,
            R.id.textViewNationality,R.id.textViewProfile
            ),{ innerViews, item ->
            val tvCarOwners = innerViews[R.id.textViewCarOwnerName] as TextView
            val tvCarModel = innerViews[R.id.textViewCarModel] as TextView
            val tvGender = innerViews[R.id.textViewGender] as TextView
            val tvNationality = innerViews[R.id.textViewNationality] as TextView
            val tvProfile = innerViews[R.id.textViewProfile] as TextView
            tvCarOwners.text = item.first_name + " " + item.last_name
            tvCarModel.text = item.car_model
            tvGender.text = item.gender
            tvNationality.text = item.country
            tvProfile.text = item.bio


        },{item ->

        })
    }

    companion object{
        val FILTER = "Filter"
        fun newInstance(filter:Filters):FilteredCarOwnersFragment{
            return FilteredCarOwnersFragment().withArguments(FILTER to filter )
        }
    }



}