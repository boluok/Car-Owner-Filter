package com.example.carownersfilter.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carownersfilter.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


import kotlinx.android.synthetic.main.fragment_single_choice_question_bottom_sheet.*

class SingleChoiceQuestionBottomSheetFragment : BottomSheetDialogFragment() {
    interface SingleChoiceQuestionListener{
        fun onPostiveClick(caller: String)
        fun onNegativeClick(caller: String)
    }
    lateinit var singleChoiceQuestionListener: SingleChoiceQuestionListener
     lateinit var positiveButtonText:String
    lateinit var negativeButtonText:String
    lateinit var question:String
    lateinit var caller:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_choice_question_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        singleChoiceQuestionListener = parentFragment as SingleChoiceQuestionListener
        getData(arguments)
        updateUI()
    }

    private fun getData(arguments: Bundle?) {
        positiveButtonText = arguments!!.getString("positiveText").getString()
        negativeButtonText = arguments!!.getString("negativeText").getString()
        question = arguments!!.getString("question").getString()
        caller = arguments!!.getString("caller").getString()
    }

    private fun updateUI() {
       buttonPositive.text = positiveButtonText
        buttonNegative.text = negativeButtonText
        textViewQuestion.text = question
        buttonPositive.setOnClickListener {
            dialog?.dismiss()
            singleChoiceQuestionListener.onPostiveClick(caller)
        }
        buttonNegative.setOnClickListener {
            dialog?.dismiss()
            singleChoiceQuestionListener.onNegativeClick(caller)
        }
    }


    companion object{
        fun newInstance(caller:String,question:String,postiveButtonText:String,negativeButtonText:String):SingleChoiceQuestionBottomSheetFragment{
            val frag = SingleChoiceQuestionBottomSheetFragment()
            val bundle = Bundle()
            bundle.putString("caller",caller)
            bundle.putString("question",question)
            bundle.putString("positiveText",postiveButtonText)
            bundle.putString("negativeText",negativeButtonText)
            frag.arguments = bundle
            return frag
        }
    }
}

fun String?.getString(): String{
    return this ?: ""
}
