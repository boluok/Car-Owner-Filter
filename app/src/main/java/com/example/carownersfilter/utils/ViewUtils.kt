  package com.example.carownersfilter.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil


class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
) :
        RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}


data class ViewPagerObject(val fragment: Fragment, val title:String)

fun <T:Any> RecyclerView.updateRecycler(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit,noImageViews:List<View>): RecyclerView.Adapter<*>? {
    if(listOfItems.isNullOrEmpty()){
        this.visibility = View.GONE
        noImageViews.forEach { it.visibility = View.VISIBLE }
    }else{
        this.visibility = View.VISIBLE
        noImageViews.forEach { it.visibility = View.GONE }
    }
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor


    return adapter
}

fun <T:Any> RecyclerView.updateRecyclerNew(itemDecoration: GridSpacingItemDecoration, context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = GridLayoutManager(context,3)
    this.addItemDecoration(itemDecoration)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }
    this.adapter = reyclerAdaptor
    return adapter
}


fun <T:Any> RecyclerView.updateRecycler(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor


    return adapter
}

fun RecyclerView.updateRecycler(context: Context, listOfItems:List<Any>, layout:Int, layoutManager: GridLayoutManager, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager =layoutManager
    val reyclerAdaptor = RecyclerAdapterUtil(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }

    this.adapter = reyclerAdaptor

    return adapter
}

  fun <T:Any> RecyclerView.updateRecycler2(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, T) -> Unit, onClickPosition:(T) -> Unit): RecyclerView.Adapter<*>? {
      this.layoutManager = LinearLayoutManager(context)
      val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
      reyclerAdaptor.addViewsList(listOfLayout)
      reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
          binder(innerViews,item)
      }
      reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(item) }
      this.adapter = reyclerAdaptor
      return adapter
  }

  fun <T:Any> RecyclerView.updateRecycler2(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, T) -> Unit, onClickPosition:(T) -> Unit,noImageViews:List<View>): RecyclerView.Adapter<*>? {

      this.layoutManager =layoutManager
      val reyclerAdaptor = RecyclerAdapterUtil(context,listOfItems,layout)
      reyclerAdaptor.addViewsList(listOfLayout)
      reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->

          binder(innerViews,item)
      }
      reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(item) }

      this.adapter = reyclerAdaptor


      return adapter
  }



