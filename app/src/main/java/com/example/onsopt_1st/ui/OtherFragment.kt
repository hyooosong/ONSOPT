package com.example.onsopt_1st.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.onsopt_1st.R

// TODO: Rename parameter arguments, choose names that match

class OtherFragment : Fragment() {
    val name = "default"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_other, container, false)
        val textView: TextView = view.findViewById(R.id.secondFM_txt)
        textView.text=name
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setOnclick~ 은 onviewCreated 에서
    }
}