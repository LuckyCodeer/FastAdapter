package com.yhw.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yhw.demo.R

private const val ARG_PARAM1 = "param1"

class DemoFragment : Fragment() {
    private var param1: String? = null
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_demo, container, false)
        textView = view.findViewById(R.id.tv_text)
        textView.text = param1
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            DemoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}