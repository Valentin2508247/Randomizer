package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private lateinit var mListener: SecondFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        mListener.getRandomValue(result?.text.toString())
        activity?.onBackPressed()
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
        return Random.nextInt(min, max + 1)
    }

    interface SecondFragmentListener {
        fun getRandomValue(value: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as SecondFragmentListener
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            return fragment.apply {
                arguments = Bundle().apply {
                    putInt(MIN_VALUE_KEY, min)
                    putInt(MAX_VALUE_KEY, max)
                }
            }
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}