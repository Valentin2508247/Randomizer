package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception
import kotlin.math.max

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    private var toast: Toast? = null
    private lateinit var tvMin: TextView
    private lateinit var tvMax: TextView
    private lateinit var mListener: FirstFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        tvMin = view.findViewById(R.id.min_value)
        tvMax = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            if (validate()){
                val min = tvMin.text.toString().toInt()
                val max = tvMax.text.toString().toInt()
                mListener.generate(min, max)
            }

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as FirstFragmentListener
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

    interface FirstFragmentListener{
        fun generate(min: Int, max: Int)
    }

    fun getValue(value: String){
        previousResult?.text = value
    }

    private fun validate() :Boolean {
        val minValue = tvMin.text.toString()
        val maxValue = tvMax.text.toString()

        if (minValue.isEmpty()){
            showMessage("Enter value")
            tvMin.requestFocus()
            return false
        }

        if (maxValue.isEmpty())    {
            showMessage("Enter value")
            tvMax.requestFocus()
            return false               
        }

        try {
            val min = minValue.toInt()
            val max = maxValue.toInt()
            if (min > max)
            {
                showMessage("Min value should be <= then max value.")
                return false
            }
        }
        catch (ex: Exception)
        {
            showMessage("Invalid data input.")
            return false
        }
        return true
    }

    private fun showMessage(message: String){
        toast?.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast!!.show()
    }
}