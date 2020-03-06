package com.example.carownersfilter.views

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.carownersfilter.model.Filters

class FilersDiffUtils(
    oldFilters: List<Filters>,
    newFilters: List<Filters>
) :
    DiffUtil.Callback() {
    private val mOldListOfFilters: List<Filters> = oldFilters
    private val mNewListOfFilters: List<Filters> = newFilters
    override fun getOldListSize(): Int {
        return mOldListOfFilters.size
    }

    override fun getNewListSize(): Int {
        return mNewListOfFilters.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldBanksList = mOldListOfFilters
        val newBanksList = mNewListOfFilters
        return oldBanksList[oldItemPosition].id == newBanksList[newItemPosition].id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldBanksList = mOldListOfFilters
        val newBanksList = mNewListOfFilters
        return  oldBanksList[oldItemPosition].id == newBanksList[newItemPosition].id
    }

    @Nullable
    override fun getChangePayload(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Any? {
        // Implement method if you're going to use ItemAnimator

        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}